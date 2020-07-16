package net.crow.ptop.blockchain.shima.service;

import java.math.BigInteger;
import java.util.List;

import org.crow.ptop.blockchain.node.transport.dto.BlockDTO;
import org.crow.ptop.blockchain.node.transport.dto.TransactionDTO;

import net.crow.ptop.blockchain.core.model.Block;
import net.crow.ptop.blockchain.core.model.transaction.Transaction;
import net.crow.ptop.blockchain.core.model.transaction.TransactionOutput;
import net.crow.ptop.blockchain.shima.dto.blockchainbrowser.NormalTransactionDto;
import net.crow.ptop.blockchain.shima.dto.blockchainbrowser.request.QueryMiningTransactionListRequest;
import net.crow.ptop.blockchain.shima.dto.blockchainbrowser.request.QueryTxosByAddressRequest;
import net.crow.ptop.blockchain.shima.dto.blockchainbrowser.request.QueryUtxosByAddressRequest;
import net.crow.ptop.blockchain.shima.dto.blockchainbrowser.response.SubmitNormalTransactionResponse;
import net.crow.ptop.blockchain.shima.dto.common.page.PageCondition;
import net.crow.ptop.blockchain.shima.dto.wallet.WalletDTO;

/**
 * 区块链core service
 * @author chenn
 *
 */
public interface BlockChainCoreService {

	/**
     * 生成钱包
     */
    WalletDTO generateWalletDTO();
    /**
     * 根据交易哈希获取交易
     */
    TransactionDTO queryTransactionDtoByTransactionHash(String transactionHash) throws Exception;
    /**
     * 根据交易高度获取交易
     */
    List<Transaction> queryTransactionByTransactionHeight(PageCondition pageCondition) throws Exception;
    /**
     * 根据地址获取UTXO
     */
    List<TransactionOutput> queryUtxoListByAddress(QueryUtxosByAddressRequest request) throws Exception;
    /**
     * 根据地址获取TXO
     */
    List<TransactionOutput> queryTxoListByAddress(QueryTxosByAddressRequest request) throws Exception;
    /**
     * 提交交易到区块链网络
     */
    SubmitNormalTransactionResponse sumiteTransaction(NormalTransactionDto transactionDTO) throws Exception;

    /**
     * 根据区块高度获取区块DTO
     */
    BlockDTO queryBlockDtoByBlockHeight(BigInteger blockHeight) throws Exception;
    /**
     * 根据区块哈希获取区块
     */
    Block queryNoTransactionBlockDtoByBlockHash(String blockHash) throws Exception;
    /**
     * 根据区块高度获取区块DTO
     */
    Block queryNoTransactionBlockDtoByBlockHeight(BigInteger blockHeight) throws Exception;
    /**
     * 根据区块高度获取区块Hash
     */
    String queryBlockHashByBlockHeight(BigInteger blockHeight) throws Exception;
    /**
     * 获取区块链高度
     */
    BigInteger queryBlockChainHeight() throws Exception;
    /**
     * 查询挖矿中的交易
     */
    List<TransactionDTO> queryMiningTransactionList(QueryMiningTransactionListRequest request) throws Exception;

    TransactionDTO queryMiningTransactionDtoByTransactionHash(String transactionHash) throws Exception;

    void removeBlocksUtilBlockHeightLessThan(BigInteger blockHeight) throws Exception;
}
