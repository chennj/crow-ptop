package net.crow.ptop.blockchain.core.impl;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.WriteBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.crow.ptop.blockchain.core.BlockChainDataBase;
import net.crow.ptop.blockchain.core.Consensus;
import net.crow.ptop.blockchain.core.Incentive;
import net.crow.ptop.blockchain.core.model.Block;
import net.crow.ptop.blockchain.core.model.enums.BlockChainActionEnum;
import net.crow.ptop.blockchain.core.model.transaction.Transaction;
import net.crow.ptop.blockchain.core.model.transaction.TransactionOutput;
import net.crow.ptop.blockchain.core.utils.BigIntegerUtil;
import net.crow.ptop.blockchain.core.utils.LevelDBUtil;

/**
 * 注意这是一个线程不安全的实现。在并发的情况下，不保证功能的正确性。
 * 
 * @author chenn
 *
 */
public class BlockChainDataBaseDefaultImpl extends BlockChainDataBase{

	private Logger logger = LoggerFactory.getLogger(BlockChainDataBaseDefaultImpl.class);
	
	// ------------------- 成员变量 -----------------------
	
	/**
	 * 数据库类名称
	 */
	private final static String BlockChain_DataBase_DirectName = "BlockChainDataBase";
	
	/**
	 * 区块链数据库
	 */
	private DB blockChainDB;
	
	/**
	 * 区块链高度key：它对应的值是区块链的高度
	 */
	private final static String BLOCK_CHAIN_HEIGHT_KEY = "B_C_H_K";
	
	/**
	 * 区块链中总的交易数量
	 */
	private final static String TOTAL_TRANSACTION_QUANTITY_KEY = "T_T_Q_K";
	
	/**
	 * 区块链中的交易序列号
	 */
	private final static String TRANSACTION_SEQUENCE_NUMBER_IN_BLOCKCHIAN_PREFIX_FLAG  = "T_S_N_I_B_P_F";
	
	/**
	 * 区块高度标识：存储区块链高度到区块的映射
	 */
	private final static String BLOCK_HEIGHT_PREFIX_FLAG = "B_H_P_F_";
	
	/**
	 * 区块高度标识：存储区块链高度到没有交易信息的区块的映射
	 */
	private final static String BLOCK_HEIGHT_MAP_NO_TRANSACTION_BLOCK_PREFIX_FLAG = "B_H_M_N_T_B_P_F_";
	
	/**
	 * 标识：存储区块链Hash到区块高度的映射
	 */
	private final static String BLOCK_HASH_BLOCK_HEIGHT_PREFIX_FLAG = "B_HA_B_H_P_F_";
	
	/**
	 * 交易标识：存储交易哈希到交易的映射
	 */
	private final static String TRANSACTION_HASH_PREFIX_FLAG = "T_U_P_F_";
	
	/**
	 * 交易输出标识：存储交易输出哈希到交易输出的映射
	 */
	private final static String TRANSACTION_OUTPUT_HASH_PREFIX_FLAG = "T_O_U_P_F_";
	
	/**
	 * 未花费的交易输出标识：存储未花费交易输出哈希到未花费交易输出的映射
	 */
	private final static String UNSPEND_TRANSACTION_OUTPUT_HASH_PREFIX_FLAG = "U_T_O_U_P_F_";
	
	/**
	 * 哈希标识：哈希(交易哈希、交易输出哈希)的前缀，这里希望系统中所有使用到的哈希都是不同的
	 */
	private final static String HASH_PREFIX_FLAG = "U_F_";
	
	/**
	 * 地址标识：存储地址到交易输出的映射
	 */
	private final static String ADDRESS_TO_TRANSACTION_OUTPUT_LIST_KEY_PREFIX_FLAG = "A_T_T_O_P_F_";
	
	/**
	 * 地址标识：存储地址到未花费交易输出的映射
	 */
	private final static String ADDRESS_TO_UNSPEND_TRANSACTION_OUTPUT_LIST_KEY_PREFIX_FLAG = "A_T_U_T_O_P_F_";
	
	/**
	 * 钱包地址截止标记
	 */
	private final static String ADDRESS_END_FLAG = "#" ;
	
    /**
     * 锁:保证对区块链增区块、删区块的操作是同步的。
     * 查询区块操作不需要加锁，原因是，只有对区块链进行区块的增删才会改变区块链的数据。
     */
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    public BlockChainDataBaseDefaultImpl(String blockchainDataPath,Incentive incentive,Consensus consensus) throws Exception {
        super(consensus,incentive);
        this.blockChainDB = LevelDBUtil.createDB(new File(blockchainDataPath,BlockChain_DataBase_DirectName));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                blockChainDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }
        
    // ------------------- 区块增加与删除 --------------------------
    
    @Override
    public boolean addBlock(Block block) throws Exception {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try{
            boolean isBlockCanApplyToBlockChain = isBlockCanApplyToBlockChain(block);
            if(!isBlockCanApplyToBlockChain){
                return false;
            }
            WriteBatch writeBatch = createWriteBatch(block,BlockChainActionEnum.ADD_BLOCK);
            LevelDBUtil.write(blockChainDB,writeBatch);
            return true;
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * 补充区块的属性
     */
    private void fillBlockPropertity(Block block) throws Exception {

        BigInteger transactionSequenceNumberInBlock = BigInteger.ZERO;
        BigInteger transactionSequenceNumberInBlockChain = queryTransactionQuantity();
        BigInteger blockHeight = block.getHeight();
        List<Transaction> transactions = block.getTransactions();
        BigInteger transactionQuantity = transactions==null?BigInteger.ZERO:BigInteger.valueOf(transactions.size());
        block.setTransactionQuantity(transactionQuantity);
        block.setStartTransactionSequenceNumberInBlockChain(
                BigIntegerUtil.isEquals(transactionQuantity,BigInteger.ZERO)?
                        BigInteger.ZERO:transactionSequenceNumberInBlockChain.add(BigInteger.ONE));
        block.setEndTransactionSequenceNumberInBlockChain(transactionSequenceNumberInBlockChain.add(transactionQuantity));
        for(Transaction transaction:transactions){
            transactionSequenceNumberInBlock = transactionSequenceNumberInBlock.add(BigInteger.ONE);
            transactionSequenceNumberInBlockChain = transactionSequenceNumberInBlockChain.add(BigInteger.ONE);
            transaction.setBlockHeight(blockHeight);
            transaction.setTransactionSequenceNumberInBlock(transactionSequenceNumberInBlock);
            transaction.setTransactionSequenceNumberInBlockChain(transactionSequenceNumberInBlockChain);

            List<TransactionOutput> outputs = transaction.getOutputs();
            if(outputs != null){
                for (int i=0; i <outputs.size(); i++){
                    TransactionOutput transactionOutput = outputs.get(i);
                    transactionOutput.setBlockHeight(blockHeight);
                    transactionOutput.setTransactionOutputSequence(BigInteger.valueOf(i).add(BigInteger.ONE));
                    transactionOutput.setTransactionSequenceNumberInBlock(transaction.getTransactionSequenceNumberInBlock());
                }
            }
        }
    }
    
    // ------------------- 拼装WriteBatch -------------------------

}
