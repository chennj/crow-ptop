package net.crow.ptop.blockchain.core.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.crow.ptop.blockchain.node.transport.dto.TransactionDTO;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.WriteBatch;
import org.iq80.leveldb.impl.WriteBatchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.crow.ptop.blockchain.core.BlockChainDataBase;
import net.crow.ptop.blockchain.core.MinerTransactionDtoDataBase;
import net.crow.ptop.blockchain.core.tools.TransactionTool;
import net.crow.ptop.blockchain.core.utils.LevelDBUtil;

public class MinerTransactionDtoDtoDataBaseDefaultImpl extends MinerTransactionDtoDataBase{

	private Logger logger = LoggerFactory.getLogger(MinerTransactionDtoDtoDataBaseDefaultImpl.class);

    private final static String MinerTransaction_DataBase_DirectName = "MinerTransactionDtoDataBase";
    private DB transactionPoolDB;

    public MinerTransactionDtoDtoDataBaseDefaultImpl(String blockchainDataPath) throws Exception {

        this.transactionPoolDB = LevelDBUtil.createDB(new File(blockchainDataPath,MinerTransaction_DataBase_DirectName));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                transactionPoolDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

    public void insertTransactionDTO(TransactionDTO transactionDTO) throws Exception {
        //交易已经持久化进交易池数据库 丢弃交易
        synchronized (BlockChainDataBase.class){
            String transactionHash = TransactionTool.calculateTransactionHash(transactionDTO);
            LevelDBUtil.put(transactionPoolDB,transactionHash, encode(transactionDTO));
        }
    }

    @Override
    public List<TransactionDTO> selectTransactionDtoList(BlockChainDataBase blockChainDataBase,long from, long size) throws Exception {
        synchronized (BlockChainDataBase.class){
            List<TransactionDTO> transactionDtoList = new ArrayList<>();
            int cunrrentFrom = 0;
            int cunrrentSize = 0;
            for (DBIterator iterator = this.transactionPoolDB.iterator(); iterator.hasNext(); iterator.next()) {
                byte[] byteValue = iterator.peekNext().getValue();
                if(byteValue == null || byteValue.length==0){
                    continue;
                }
                cunrrentFrom++;
                if(cunrrentFrom>=from && cunrrentSize<size){
                    TransactionDTO transactionDTO = decodeToTransactionDTO(byteValue);
                    transactionDtoList.add(transactionDTO);
                    cunrrentSize++;
                }
                if(cunrrentSize>=size){
                    break;
                }
            }
            return transactionDtoList;
        }
    }

    @Override
    public void deleteTransactionDto(TransactionDTO transactionDTO) throws Exception {
        String transactionHash = TransactionTool.calculateTransactionHash(transactionDTO);
        LevelDBUtil.delete(transactionPoolDB,transactionHash);
    }

    @Override
    public void deleteTransactionDtoListByTransactionHashList(List<String> transactionHashList) throws Exception {
        if(transactionHashList == null || transactionHashList.size()==0){
            return;
        }
        WriteBatch writeBatch = new WriteBatchImpl();
        for(String transactionHash:transactionHashList){
            writeBatch.delete(LevelDBUtil.stringToBytes(transactionHash));
        }
        LevelDBUtil.write(transactionPoolDB,writeBatch);
    }

    @Override
    public TransactionDTO selectTransactionDtoByTransactionHash(String transactionHash) throws Exception {
        byte[] byteTransactionDTO = LevelDBUtil.get(transactionPoolDB,transactionHash);
        if(byteTransactionDTO == null){
            return null;
        }
        return decodeToTransactionDTO(byteTransactionDTO);
    }

    private static byte[] encode(TransactionDTO transactionDTO) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(transactionDTO);
        byte[] bytesTransactionDTO = byteArrayOutputStream.toByteArray();
        return bytesTransactionDTO;
    }

    private static TransactionDTO decodeToTransactionDTO(byte[] bytesTransactionDTO) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytesTransactionDTO);
        ObjectInputStream objectInputStream = null;
        objectInputStream = new ObjectInputStream(byteArrayInputStream);
        TransactionDTO transactionDTO = (TransactionDTO) objectInputStream.readObject();
        return transactionDTO;
    }
}
