package net.crow.ptop.blockchain.shima.service;

import net.crow.ptop.blockchain.shima.dto.nodeserver.Node;

/**
 * 同步节点数据service
 * @author chenn
 *
 */
public interface SynchronizeRemoteNodeBlockService {

	/**
     * 同步远程节点的区块到本地区块链系统
     */
    void synchronizeRemoteNodeBlock(Node node) throws Exception;
}
