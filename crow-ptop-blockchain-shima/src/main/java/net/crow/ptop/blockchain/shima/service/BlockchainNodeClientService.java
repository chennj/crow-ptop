package net.crow.ptop.blockchain.shima.service;

import java.math.BigInteger;

import org.crow.ptop.blockchain.node.transport.dto.TransactionDTO;

import net.crow.ptop.blockchain.shima.dto.common.EmptyResponse;
import net.crow.ptop.blockchain.shima.dto.common.ServiceResult;
import net.crow.ptop.blockchain.shima.dto.nodeserver.Node;
import net.crow.ptop.blockchain.shima.dto.nodeserver.SimpleNode;
import net.crow.ptop.blockchain.shima.dto.nodeserver.response.PingResponse;
import net.crow.ptop.blockchain.shima.dto.nodeserver.response.QueryBlockDtoByBlockHeightResponse;
import net.crow.ptop.blockchain.shima.dto.nodeserver.response.QueryBlockHashByBlockHeightResponse;

/**
 * 区块链节点客户端service
 * @author chenn
 *
 */
public interface BlockchainNodeClientService {

	/**
     * 提交交易至其它节点
     */
    ServiceResult<EmptyResponse> sumiteTransaction(SimpleNode node, TransactionDTO transactionDTO) throws Exception ;

    /**
     * Ping指定节点
     */
    ServiceResult<PingResponse> pingNode(SimpleNode node) ;

    /**
     * 单播：将本地区块链高度传给指定节点
     */
    ServiceResult<EmptyResponse> unicastLocalBlockChainHeight(SimpleNode node, BigInteger localBlockChainHeight) ;

    /**
     * 根据区块高度，获取对应的区块hash
     */
    ServiceResult<QueryBlockHashByBlockHeightResponse> queryBlockHashByBlockHeight(Node node, BigInteger blockHeight);

    /**
     * 根据区块高度，获取对应的区块
     */
    ServiceResult<QueryBlockDtoByBlockHeightResponse> queryBlockDtoByBlockHeight(Node node, BigInteger blockHeight) ;

}
