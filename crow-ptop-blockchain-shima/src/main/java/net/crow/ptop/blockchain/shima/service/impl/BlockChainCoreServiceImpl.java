package net.crow.ptop.blockchain.shima.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.crow.ptop.blockchain.crypto.AccountUtil;
import org.crow.ptop.blockchain.crypto.model.account.StringAccount;
import org.crow.ptop.blockchain.crypto.model.account.StringAddress;
import org.crow.ptop.blockchain.crypto.model.account.StringPrivateKey;
import org.crow.ptop.blockchain.node.transport.dto.BlockDTO;
import org.crow.ptop.blockchain.node.transport.dto.TransactionDTO;
import org.crow.ptop.blockchain.node.transport.dto.TransactionInputDTO;
import org.crow.ptop.blockchain.node.transport.dto.TransactionOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.crow.ptop.blockchain.core.BlockChainCore;
import net.crow.ptop.blockchain.core.BlockChainDataBase;
import net.crow.ptop.blockchain.core.VirtualMachine;
import net.crow.ptop.blockchain.core.config.GlobalConfig;
import net.crow.ptop.blockchain.core.model.Block;
import net.crow.ptop.blockchain.core.model.key.Wallet;
import net.crow.ptop.blockchain.core.model.transaction.Transaction;
import net.crow.ptop.blockchain.core.model.transaction.TransactionOutput;
import net.crow.ptop.blockchain.core.model.transaction.TransactionType;
import net.crow.ptop.blockchain.core.tools.NodeTransportDtoTool;
import net.crow.ptop.blockchain.core.tools.TransactionTool;
import net.crow.ptop.blockchain.core.tools.WalletTool;
import net.crow.ptop.blockchain.shima.dto.blockchainbrowser.NormalTransactionDto;
import net.crow.ptop.blockchain.shima.dto.blockchainbrowser.request.QueryMiningTransactionListRequest;
import net.crow.ptop.blockchain.shima.dto.blockchainbrowser.request.QueryTxosByAddressRequest;
import net.crow.ptop.blockchain.shima.dto.blockchainbrowser.request.QueryUtxosByAddressRequest;
import net.crow.ptop.blockchain.shima.dto.blockchainbrowser.response.SubmitNormalTransactionResponse;
import net.crow.ptop.blockchain.shima.dto.common.EmptyResponse;
import net.crow.ptop.blockchain.shima.dto.common.ServiceResult;
import net.crow.ptop.blockchain.shima.dto.common.page.PageCondition;
import net.crow.ptop.blockchain.shima.dto.nodeserver.Node;
import net.crow.ptop.blockchain.shima.dto.wallet.WalletDTO;
import net.crow.ptop.blockchain.shima.service.BlockChainCoreService;
import net.crow.ptop.blockchain.shima.service.BlockchainNodeClientService;
import net.crow.ptop.blockchain.shima.service.BlockchainNodeServerService;
import net.crow.ptop.blockchain.shima.service.NodeService;
import net.crow.ptop.blockchain.shima.util.WalletDtoUtil;

@Service
public class BlockChainCoreServiceImpl implements BlockChainCoreService {

	@Autowired
    private BlockChainCore blockChainCore;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private BlockchainNodeClientService blockchainNodeClientService;

    @Autowired
    private BlockchainNodeServerService blockchainNodeServerService;
    
    
	@Override
	public WalletDTO generateWalletDTO() {
		Wallet wallet = WalletTool.generateWallet();
        return WalletDtoUtil.classCast(wallet);
	}

	@Override
	public TransactionDTO queryTransactionDtoByTransactionHash(String transactionHash) throws Exception {
		Transaction transaction = blockChainCore.getBlockChainDataBase().findTransactionByTransactionHash(transactionHash);
        if(transaction == null){
            return null;
        }
        TransactionDTO transactionDTO = NodeTransportDtoTool.classCast(transaction);
        return transactionDTO;
	}

	@Override
	public List<Transaction> queryTransactionByTransactionHeight(PageCondition pageCondition) throws Exception {
		BlockChainDataBase blockChainDataBase = blockChainCore.getBlockChainDataBase();
        List<Transaction>  transactionList = blockChainDataBase.queryTransactionByTransactionHeight(BigInteger.valueOf(pageCondition.getFrom()),BigInteger.valueOf(pageCondition.getSize()));
        return transactionList;
	}

	@Override
	public List<TransactionOutput> queryUtxoListByAddress(QueryUtxosByAddressRequest request) throws Exception {
		PageCondition pageCondition = request.getPageCondition();
        if(pageCondition == null){
            pageCondition = PageCondition.defaultPageCondition;
        }
        StringAddress stringAddress = new StringAddress(request.getAddress());
        List<TransactionOutput> utxo =  blockChainCore.getBlockChainDataBase().queryUnspendTransactionOuputListByAddress(stringAddress,pageCondition.getFrom(),pageCondition.getSize());
        return utxo;
	}

	@Override
	public List<TransactionOutput> queryTxoListByAddress(QueryTxosByAddressRequest request) throws Exception {
		PageCondition pageCondition = request.getPageCondition();
        if(pageCondition == null){
            pageCondition = PageCondition.defaultPageCondition;
        }
        StringAddress stringAddress = new StringAddress(request.getAddress());
        List<TransactionOutput> utxo =  blockChainCore.getBlockChainDataBase().queryTransactionOuputListByAddress(stringAddress,pageCondition.getFrom(),pageCondition.getSize());
        return utxo;
	}

	@Override
	public SubmitNormalTransactionResponse sumiteTransaction(NormalTransactionDto normalTransactionDto) throws Exception {
		TransactionDTO transactionDTO = classCast(normalTransactionDto);
        blockChainCore.getMiner().getMinerTransactionDtoDataBase().insertTransactionDTO(transactionDTO);
        List<Node> nodes = nodeService.queryAllNoForkAliveNodeList();

        List<SubmitNormalTransactionResponse.Node> successSubmitNode = new ArrayList<>();
        List<SubmitNormalTransactionResponse.Node> failSubmitNode = new ArrayList<>();
        if(nodes != null){
            for(Node node:nodes){
                ServiceResult<EmptyResponse> submitSuccess = blockchainNodeClientService.sumiteTransaction(node,transactionDTO);
                if(ServiceResult.isSuccess(submitSuccess)){
                    successSubmitNode.add(new SubmitNormalTransactionResponse.Node(node.getIp(),node.getPort()));
                } else {
                    failSubmitNode.add(new SubmitNormalTransactionResponse.Node(node.getIp(),node.getPort()));
                }
            }
        }

        SubmitNormalTransactionResponse response = new SubmitNormalTransactionResponse();
        response.setTransactionDTO(transactionDTO);
        response.setSuccessSubmitNode(successSubmitNode);
        response.setFailSubmitNode(failSubmitNode);
        response.setTransactionHash(TransactionTool.calculateTransactionHash(transactionDTO));
        return response;
	}
	
	private TransactionDTO classCast(NormalTransactionDto normalTransactionDto) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();

        StringAccount stringAccount = AccountUtil.stringAccountFrom(new StringPrivateKey(normalTransactionDto.getPrivateKey()));

        List<NormalTransactionDto.Output> outputs = normalTransactionDto.getOutputs();
        List<TransactionOutputDTO> transactionOutputDtoList = new ArrayList<>();
        //理应支付总金额
        BigDecimal values = BigDecimal.ZERO;
        if(outputs != null){
            for(NormalTransactionDto.Output o:outputs){
                TransactionOutputDTO transactionOutputDTO = new TransactionOutputDTO();
                transactionOutputDTO.setAddress(o.getAddress());
                transactionOutputDTO.setValue(o.getValue());
                transactionOutputDTO.setScriptLock(VirtualMachine.createPayToClassicAddressOutputScript(o.getAddress()));
                transactionOutputDtoList.add(transactionOutputDTO);
                values = values.add(new BigDecimal(o.getValue()));
            }
        }
        //手续费
        values = values.add(GlobalConfig.TransactionConstant.MIN_TRANSACTION_FEE);

        List<TransactionOutput> utxoList = blockChainCore.getBlockChainDataBase().queryUnspendTransactionOuputListByAddress(stringAccount.getStringAddress(),0,100);
        //交易输入列表
        List<String> inputs = new ArrayList<>();
        //交易输入总金额
        BigDecimal useValues = BigDecimal.ZERO;
        //找零
        BigDecimal change = BigDecimal.ZERO;
        boolean haveMoreMoneyToPay = false;
        for(TransactionOutput transactionOutput:utxoList){
            useValues = useValues.add(transactionOutput.getValue());
            //交易输入
            inputs.add(transactionOutput.getTransactionOutputHash());
            if(useValues.compareTo(values)>=0){
                haveMoreMoneyToPay = true;
                break;
            }
        }

        if(!haveMoreMoneyToPay){
            throw new ClassCastException("账户没有足够的金额去支付。");
        }else {
            //找零
            change = useValues.subtract(values);
            TransactionOutputDTO transactionOutputDTO = new TransactionOutputDTO();
            transactionOutputDTO.setAddress(stringAccount.getStringAddress().getValue());
            transactionOutputDTO.setValue(change.toPlainString());
            transactionOutputDTO.setScriptLock(VirtualMachine.createPayToClassicAddressOutputScript(stringAccount.getStringAddress().getValue()));
            transactionOutputDtoList.add(transactionOutputDTO);
        }


        List<TransactionInputDTO> transactionInputDtoList = new ArrayList<>();
        for(String input:inputs){
            TransactionInputDTO transactionInputDTO = new TransactionInputDTO();
            transactionInputDTO.setUnspendTransactionOutputHash(input);
            transactionInputDtoList.add(transactionInputDTO);
        }



        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTimestamp(currentTimeMillis);
        transactionDTO.setTransactionTypeCode(TransactionType.NORMAL.getCode());
        transactionDTO.setInputs(transactionInputDtoList);
        transactionDTO.setOutputs(transactionOutputDtoList);

        for(TransactionInputDTO transactionInputDTO:transactionInputDtoList){
            String signature = signatureTransactionDTO(transactionDTO, stringAccount.getStringPrivateKey());
            transactionInputDTO.setScriptKey(VirtualMachine.createPayToClassicAddressInputScript(signature, stringAccount.getStringPublicKey().getValue()));
        }
        return transactionDTO;
    }

	private String signatureTransactionDTO(TransactionDTO transactionDTO, StringPrivateKey stringPrivateKey) {
        String signature = NodeTransportDtoTool.signature(transactionDTO,stringPrivateKey);
        return signature;
    }
	
	@Override
	public BlockDTO queryBlockDtoByBlockHeight(BigInteger blockHeight) throws Exception {
		Block block = blockChainCore.getBlockChainDataBase().findBlockByBlockHeight(blockHeight);
        if(block == null){
            return null;
        }
        BlockDTO blockDTO = NodeTransportDtoTool.classCast(block);
        return blockDTO;
	}

	@Override
	public Block queryNoTransactionBlockDtoByBlockHash(String blockHash) throws Exception {
		BlockChainDataBase blockChainDataBase = blockChainCore.getBlockChainDataBase();
        BigInteger blockHeight = blockChainDataBase.findBlockHeightByBlockHash(blockHash);
        if(blockHeight == null){
            return null;
        }
        Block block = blockChainDataBase.findNoTransactionBlockByBlockHeight(blockHeight);
        return block;
	}

	@Override
	public Block queryNoTransactionBlockDtoByBlockHeight(BigInteger blockHeight) throws Exception {
		Block block = blockChainCore.getBlockChainDataBase().findNoTransactionBlockByBlockHeight(blockHeight);
        return block;
	}

	@Override
	public String queryBlockHashByBlockHeight(BigInteger blockHeight) throws Exception {
		Block block = blockChainCore.getBlockChainDataBase().findNoTransactionBlockByBlockHeight(blockHeight);
        if(block == null){
            return null;
        }
        return block.getHash();
	}

	@Override
	public BigInteger queryBlockChainHeight() throws Exception {
		return blockChainCore.getBlockChainDataBase().obtainBlockChainHeight();
	}

	@Override
	public List<TransactionDTO> queryMiningTransactionList(QueryMiningTransactionListRequest request) throws Exception {
		PageCondition pageCondition = request.getPageCondition();
        if(pageCondition == null){
            pageCondition = PageCondition.defaultPageCondition;
        }
        List<TransactionDTO> transactionDtoList = blockChainCore.getMiner().getMinerTransactionDtoDataBase().selectTransactionDtoList(blockChainCore.getBlockChainDataBase(),pageCondition.getFrom(),pageCondition.getSize());
        return transactionDtoList;
	}

	@Override
	public TransactionDTO queryMiningTransactionDtoByTransactionHash(String transactionHash) throws Exception {
		TransactionDTO transactionDTO = blockChainCore.getMiner().getMinerTransactionDtoDataBase().selectTransactionDtoByTransactionHash(transactionHash);
        return transactionDTO;
	}

	@Override
	public void removeBlocksUtilBlockHeightLessThan(BigInteger blockHeight) throws Exception {
		 blockChainCore.getBlockChainDataBase().removeBlocksUtilBlockHeightLessThan(blockHeight);
	}

}
