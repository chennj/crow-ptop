package net.crow.ptop.blockchain.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.crow.ptop.blockchain.core.BlockChainCore;
import net.crow.ptop.blockchain.core.BlockChainDataBase;
import net.crow.ptop.blockchain.core.Miner;
import net.crow.ptop.blockchain.core.Synchronizer;

/**
 * 默认实现
 * @author chenn
 *
 */
public class BlockChainCoreImpl extends BlockChainCore{

	private Logger logger = LoggerFactory.getLogger(BlockChainCoreImpl.class);
	
	public BlockChainCoreImpl(BlockChainDataBase blockChainDataBase, Miner miner, Synchronizer synchronizer) {
        super(blockChainDataBase,miner,synchronizer);
    }
	
	@Override
	public void start(){
		
		// 启动区块链同步器
		new Thread(
			()->{
				try {
					synchronizer.start();
				} catch (Exception e){
					logger.error("区块链同步器在运行中发生异常并退出，请检查修复异常！",e);
				}
			}
		).start();
		
		// 启动矿工进程
		new Thread(
			()->{
				try {
					miner.start();
				} catch (Exception e){
					logger.error("矿工在运行中发生异常并退出，请检查修复异常！",e);
				}
			}
		).start();
	}
}
