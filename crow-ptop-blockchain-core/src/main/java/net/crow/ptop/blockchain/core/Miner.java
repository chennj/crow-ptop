package net.crow.ptop.blockchain.core;

import org.crow.ptop.blockchain.crypto.model.account.StringAddress;

import net.crow.ptop.blockchain.core.model.Block;
import net.crow.ptop.blockchain.core.model.transaction.Transaction;

/**
 * 矿工:挖矿、分配挖矿奖励、将挖取的区块放入区块链
 * @author chenn
 *
 */
public abstract class Miner {

	/**
	 * 矿工钱包地址
	 */
	protected StringAddress minerStringAddress;
	
	/**
	 * 矿工挖矿所在的区块链
	 */
	protected BlockChainDataBase blockChainDataBase;
	
	/**
	 * 矿工交易数据库：矿工从交易数据库里获取挖矿的原材料(交易数据)
	 */
	protected MinerTransactionDtoDataBase minerTransactionDtoDataBase;
	
    public Miner(StringAddress minerStringAddress, BlockChainDataBase blockChainDataBase, MinerTransactionDtoDataBase minerTransactionDtoDataBase) {
        this.minerStringAddress = minerStringAddress;
        this.blockChainDataBase = blockChainDataBase;
        this.minerTransactionDtoDataBase = minerTransactionDtoDataBase;
    }

    // ----------------------- 挖矿相关 ------------------------------
    
    /**
     * 启用矿工。
     * 矿工有两种状态：活动状态与非活动状态。
     * 若矿工处于活动作态，开始挖矿。
     * 若矿工处于非活动状态，矿工不会进行任何工作。
     */
    public abstract void start() throws Exception;

    /**
     * 矿工是否处于活动状态。
     */
    public abstract boolean isActive() ;

    /**
     * 激活矿工：设置矿工为活动状态。
     */
    public abstract void active() ;

    /**
     * 停用矿工：设置矿工为非活动状态。
     */
    public abstract void deactive() throws Exception;

    // ---------------------- 挖矿奖励 -------------------------------
    
    /**
     * 构建区块的挖矿奖励交易，这里可以实现挖矿奖励的分配。
     * @param block 目标区块
     */
    public abstract Transaction buildMineAwardTransaction(
    		long timestamp, 
    		BlockChainDataBase blockChainDataBase, 
    		Block block) throws Exception;

    // ---------------------- 挖矿设置 -------------------------------
    
    /**
     * 重置矿工地址
     */
    public void resetMinerStringAddress(StringAddress minerStringAddress) {
        this.minerStringAddress = minerStringAddress;
    }
    
    // ---------------------- get set ------------------------------
    public StringAddress getMinerStringAddress() {
        return minerStringAddress;
    }

    public BlockChainDataBase getBlockChainDataBase() {
        return blockChainDataBase;
    }

    public MinerTransactionDtoDataBase getMinerTransactionDtoDataBase() {
        return minerTransactionDtoDataBase;
    }
}
