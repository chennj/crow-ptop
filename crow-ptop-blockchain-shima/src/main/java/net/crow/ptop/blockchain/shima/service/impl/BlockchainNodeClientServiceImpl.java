package net.crow.ptop.blockchain.shima.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;

import org.crow.ptop.blockchain.node.transport.dto.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.crow.ptop.blockchain.core.BlockChainCore;
import net.crow.ptop.blockchain.shima.dto.common.EmptyResponse;
import net.crow.ptop.blockchain.shima.dto.common.ServiceResult;
import net.crow.ptop.blockchain.shima.dto.nodeserver.Node;
import net.crow.ptop.blockchain.shima.dto.nodeserver.NodeServerApiRoute;
import net.crow.ptop.blockchain.shima.dto.nodeserver.SimpleNode;
import net.crow.ptop.blockchain.shima.dto.nodeserver.request.AddOrUpdateNodeRequest;
import net.crow.ptop.blockchain.shima.dto.nodeserver.request.PingRequest;
import net.crow.ptop.blockchain.shima.dto.nodeserver.request.QueryBlockDtoByBlockHeightRequest;
import net.crow.ptop.blockchain.shima.dto.nodeserver.request.QueryBlockHashByBlockHeightRequest;
import net.crow.ptop.blockchain.shima.dto.nodeserver.request.ReceiveTransactionRequest;
import net.crow.ptop.blockchain.shima.dto.nodeserver.response.AddOrUpdateNodeResponse;
import net.crow.ptop.blockchain.shima.dto.nodeserver.response.PingResponse;
import net.crow.ptop.blockchain.shima.dto.nodeserver.response.QueryBlockDtoByBlockHeightResponse;
import net.crow.ptop.blockchain.shima.dto.nodeserver.response.QueryBlockHashByBlockHeightResponse;
import net.crow.ptop.blockchain.shima.dto.nodeserver.response.ReceiveTransactionResponse;
import net.crow.ptop.blockchain.shima.service.BlockchainNodeClientService;
import net.crow.ptop.blockchain.shima.util.NetUtil;

@Service
public class BlockchainNodeClientServiceImpl implements BlockchainNodeClientService {

	private static final Logger logger = LoggerFactory.getLogger(BlockchainNodeClientServiceImpl.class);
	
	@Autowired
    private BlockChainCore blockChainCore;

    @Autowired
    private Gson gson;

    @Value("${server.port}")
    private int serverPort;
    
	@Override
	public ServiceResult<EmptyResponse> sumiteTransaction(SimpleNode node, TransactionDTO transactionDTO)
			throws Exception {
		try {
            String url = String.format("http://%s:%d%s",node.getIp(),node.getPort(), NodeServerApiRoute.RECEIVE_TRANSACTION);
            ReceiveTransactionRequest request = new ReceiveTransactionRequest();
            request.setTransactionDTO(transactionDTO);
            String html = NetUtil.getHtml(url,request);
            Type jsonType = new TypeToken<ServiceResult<ReceiveTransactionResponse>>() {}.getType();
            ServiceResult<ReceiveTransactionResponse> pingResponseServiceResult = gson.fromJson(html,jsonType);
            if(ServiceResult.isSuccess(pingResponseServiceResult)){
                return ServiceResult.createSuccessServiceResult("");
            } else {
                return ServiceResult.createFailServiceResult(pingResponseServiceResult.getMessage());
            }
        } catch (IOException e) {
            logger.debug(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()),e);
            return ServiceResult.createFailServiceResult(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()));
        } catch (Exception e) {
            logger.debug(String.format("提交交易[%s]至节点[%s:%d]出现异常",gson.toJson(transactionDTO),node.getIp(),node.getPort()),e);
            return ServiceResult.createFailServiceResult(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()));
        }
	}

	@Override
	public ServiceResult<PingResponse> pingNode(SimpleNode node) {
		try {
            String url = String.format("http://%s:%d%s",node.getIp(),node.getPort(), NodeServerApiRoute.PING);
            PingRequest pingRequest = new PingRequest();
            pingRequest.setPort(serverPort);
            String html = NetUtil.getHtml(url,pingRequest);
            Type jsonType = new TypeToken<ServiceResult<PingResponse>>() {}.getType();
            ServiceResult<PingResponse> pingResponseServiceResult = gson.fromJson(html,jsonType);
            if(ServiceResult.isSuccess(pingResponseServiceResult)){
                return pingResponseServiceResult;
            } else {
                return ServiceResult.createFailServiceResult(pingResponseServiceResult.getMessage());
            }
        } catch (IOException e) {
            logger.debug(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()),e);
            return ServiceResult.createFailServiceResult(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()));
        } catch (Exception e) {
            logger.debug(String.format("Ping节点[%s:%d]出现异常",node.getIp(),node.getPort()),e);
            return ServiceResult.createFailServiceResult(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()));
        }
	}

	@Override
	public ServiceResult<EmptyResponse> unicastLocalBlockChainHeight(SimpleNode node,
			BigInteger localBlockChainHeight) {
		try {
            String url = String.format("http://%s:%d%s",node.getIp(), node.getPort(), NodeServerApiRoute.ADD_OR_UPDATE_NODE);
            AddOrUpdateNodeRequest request = new AddOrUpdateNodeRequest();
            request.setPort(serverPort);
            request.setBlockChainHeight(localBlockChainHeight);
            String html = NetUtil.getHtml(url,request);
            Type jsonType = new TypeToken<ServiceResult<AddOrUpdateNodeResponse>>() {}.getType();
            ServiceResult<AddOrUpdateNodeResponse> pingResponseServiceResult = gson.fromJson(html,jsonType);
            if(ServiceResult.isSuccess(pingResponseServiceResult)){
                return ServiceResult.createSuccessServiceResult("");
            } else {
                return ServiceResult.createFailServiceResult(pingResponseServiceResult.getMessage());
            }
        } catch (IOException e) {
            logger.debug(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()),e);
            return ServiceResult.createFailServiceResult(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()));
        } catch (Exception e) {
            logger.debug(String.format("将本地区块链高度单播给节点[%s:%d]出现异常",node.getIp(),node.getPort()),e);
            return ServiceResult.createFailServiceResult(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()));
        }
	}

	@Override
	public ServiceResult<QueryBlockHashByBlockHeightResponse> queryBlockHashByBlockHeight(Node node,
			BigInteger blockHeight) {
		try {
            String url = String.format("http://%s:%d%s",node.getIp(),node.getPort(), NodeServerApiRoute.QUERY_BLOCK_HASH_BY_BLOCK_HEIGHT);
            QueryBlockHashByBlockHeightRequest request = new QueryBlockHashByBlockHeightRequest();
            request.setBlockHeight(blockHeight);
            String html = NetUtil.getHtml(url,request);
            Type jsonType = new TypeToken<ServiceResult<QueryBlockHashByBlockHeightResponse>>() {}.getType();
            ServiceResult<QueryBlockHashByBlockHeightResponse> serviceResult = gson.fromJson(html,jsonType);
            if(ServiceResult.isSuccess(serviceResult)){
                return serviceResult;
            } else {
                return ServiceResult.createFailServiceResult(serviceResult.getMessage());
            }
        } catch (IOException e) {
            logger.debug(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()),e);
            return ServiceResult.createFailServiceResult(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()));
        } catch (Exception e) {
            logger.debug(String.format("将本地区块链高度单播给节点[%s:%d]出现异常",node.getIp(),node.getPort()),e);
            return ServiceResult.createFailServiceResult(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()));
        }
	}

	@Override
	public ServiceResult<QueryBlockDtoByBlockHeightResponse> queryBlockDtoByBlockHeight(Node node,
			BigInteger blockHeight) {
		try {
            String url = String.format("http://%s:%d%s",node.getIp(),node.getPort(), NodeServerApiRoute.QUERY_BLOCKDTO_BY_BLOCK_HEIGHT);
            QueryBlockDtoByBlockHeightRequest request = new QueryBlockDtoByBlockHeightRequest();
            request.setBlockHeight(blockHeight);
            String html = NetUtil.getHtml(url,request);
            Type jsonType = new TypeToken<ServiceResult<QueryBlockDtoByBlockHeightResponse>>() {}.getType();
            ServiceResult<QueryBlockDtoByBlockHeightResponse> serviceResult = gson.fromJson(html,jsonType);
            if(ServiceResult.isSuccess(serviceResult)){
                return serviceResult;
            } else {
                return ServiceResult.createFailServiceResult(serviceResult.getMessage());
            }
        } catch (IOException e) {
            logger.debug(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()),e);
            return ServiceResult.createFailServiceResult(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()));
        } catch (Exception e) {
            logger.debug(String.format("将本地区块链高度单播给节点[%s:%d]出现异常",node.getIp(),node.getPort()),e);
            return ServiceResult.createFailServiceResult(String.format("节点%s:%d网络异常",node.getIp(),node.getPort()));
        }
	}

}
