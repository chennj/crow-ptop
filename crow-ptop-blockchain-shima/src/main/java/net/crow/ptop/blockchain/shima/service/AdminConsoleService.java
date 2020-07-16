package net.crow.ptop.blockchain.shima.service;

/**
 * 管理员控制台service
 * @author chenn
 *
 */
public interface AdminConsoleService {

	/**
     * 矿工是否处于激活状态
     */
    boolean isMinerActive();
    /**
     * 开始挖矿
     */
    void activeMiner() throws Exception;
    /**
     * 停止挖矿
     */
    void deactiveMiner() throws Exception;

    /**
     * 同步器是否激活
     */
    boolean isSynchronizerActive();
    /**
     * 停止同步器
     */
    boolean deactiveSynchronizer();
    /**
     * 恢复同步器
     */
    boolean activeSynchronizer();
}
