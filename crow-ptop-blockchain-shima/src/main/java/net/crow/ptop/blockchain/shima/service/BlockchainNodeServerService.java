package net.crow.ptop.blockchain.shima.service;

import org.crow.ptop.blockchain.node.transport.dto.TransactionDTO;

/**
 * 区块链节点服务端service
 * @author chenn
 *
 */
public interface BlockchainNodeServerService {

	/**
     * 接收交易
     */
    void receiveTransaction(TransactionDTO transactionDTO) throws Exception;
}
