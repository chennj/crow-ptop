package net.crow.ptop.blockchain.core;

import java.io.File;

import org.crow.ptop.blockchain.crypto.model.account.StringAddress;

import net.crow.ptop.blockchain.core.impl.BlockChainCoreImpl;
import net.crow.ptop.blockchain.core.impl.BlockChainDataBaseDefaultImpl;
import net.crow.ptop.blockchain.core.impl.IncentiveDefaultImpl;
import net.crow.ptop.blockchain.core.impl.MinerDefaultImpl;
import net.crow.ptop.blockchain.core.impl.MinerTransactionDtoDtoDataBaseDefaultImpl;
import net.crow.ptop.blockchain.core.impl.ProofOfWorkConsensusImpl;
import net.crow.ptop.blockchain.core.impl.SynchronizerDataBaseDefaultImpl;
import net.crow.ptop.blockchain.core.impl.SynchronizerDefaultImpl;

/**
 * 创建BlockChainCore的工厂
 * 
 * @author chenn
 *
 */
public class BlockChainCoreFactory {

    /**
     * 创建BlockChainCore实例
     *
     * @param blockchainDataPath 区块链数据存放位置
     * @param minerAddress 矿工钱包地址
     * 
     */
    public BlockChainCore createBlockChainCore(String blockchainDataPath,String minerAddress) throws Exception {

    	// 建立矿工缺省激励机制
        Incentive incentive = new IncentiveDefaultImpl();
        // 建立缺省共识机制
        Consensus consensus = new ProofOfWorkConsensusImpl();
        BlockChainDataBase blockChainDataBase = new BlockChainDataBaseDefaultImpl(blockchainDataPath,incentive,consensus);
        // 初始化矿工对象实例
        MinerTransactionDtoDataBase minerTransactionDtoDataBase = new MinerTransactionDtoDtoDataBaseDefaultImpl(blockchainDataPath);
        StringAddress minerStringAddress = new StringAddress(minerAddress);
        Miner miner = new MinerDefaultImpl(blockChainDataBase, minerTransactionDtoDataBase,minerStringAddress);
        // 初始化同步节点数据库对象实例
        SynchronizerDataBase synchronizerDataBase = new SynchronizerDataBaseDefaultImpl(blockchainDataPath);
        // 初始化同步区块对象实例
        BlockChainDataBase temporaryBlockChainDataBase = new BlockChainDataBaseDefaultImpl(new File(blockchainDataPath,"TemporaryBlockChainDataBase").getAbsolutePath(),incentive,consensus);
        Synchronizer synchronizer = new SynchronizerDefaultImpl(blockChainDataBase,temporaryBlockChainDataBase, synchronizerDataBase);
        // 初始化区块链核心对象
        BlockChainCore blockChainCore = new BlockChainCoreImpl(blockChainDataBase,miner,synchronizer);
        return blockChainCore;
    }
}
