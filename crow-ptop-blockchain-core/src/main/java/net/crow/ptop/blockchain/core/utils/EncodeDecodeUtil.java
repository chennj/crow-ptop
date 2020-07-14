package net.crow.ptop.blockchain.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;

import net.crow.ptop.blockchain.core.config.GlobalConfig;
import net.crow.ptop.blockchain.core.model.Block;
import net.crow.ptop.blockchain.core.model.transaction.Transaction;
import net.crow.ptop.blockchain.core.model.transaction.TransactionOutput;

public class EncodeDecodeUtil {

	public static byte[] encode(Transaction transaction) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(transaction);
        byte[] bytesTransaction = byteArrayOutputStream.toByteArray();
        return bytesTransaction;
    }
    public static Transaction decodeToTransaction(byte[] bytesTransaction) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytesTransaction);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Transaction transaction = (Transaction) objectInputStream.readObject();
        return transaction;
    }


    public static byte[] encode(TransactionOutput transactionOutput) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(transactionOutput);
        byte[] bytesTransactionOutput = byteArrayOutputStream.toByteArray();
        return bytesTransactionOutput;
    }
    public static TransactionOutput decodeToTransactionOutput(byte[] bytesTransactionOutput) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytesTransactionOutput);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        TransactionOutput transactionOutput = (TransactionOutput) objectInputStream.readObject();
        return transactionOutput;
    }


    public static byte[] encode(Block block) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(block);
        byte[] bytesBlock = byteArrayOutputStream.toByteArray();
        return bytesBlock;
    }
    public static Block decodeToBlock(byte[] bytesBlock) throws Exception{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytesBlock);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Block block = (Block) objectInputStream.readObject();
        return block;
    }


    public static byte[] encode(BigInteger bigInteger){
        return String.valueOf(bigInteger).getBytes(GlobalConfig.GLOBAL_CHARSET);
    }
    public static BigInteger decodeToBigInteger(byte[] bytesBigInteger){
        String strBigInteger = new String(bytesBigInteger, GlobalConfig.GLOBAL_CHARSET);
        BigInteger bigInteger = new BigInteger(strBigInteger);
        return bigInteger;
    }
}
