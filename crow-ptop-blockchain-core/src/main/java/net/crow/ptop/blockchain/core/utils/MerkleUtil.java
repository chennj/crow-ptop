package net.crow.ptop.blockchain.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.crow.ptop.blockchain.crypto.Base64Util;
import org.crow.ptop.blockchain.crypto.SHA256Util;

import net.crow.ptop.blockchain.core.model.transaction.Transaction;

/**
 * 默克尔树工具类
 * @author chenn
 *
 */
public class MerkleUtil {

    public static String calculateTransactionMerkleRoot(List<Transaction> transactions) {
        List<String> hashList = new ArrayList<>();
        if(transactions != null){
            for(Transaction transaction : transactions) {
                hashList.add(transaction.getTransactionHash());
            }
        }
        return calculateMerkleRoot(hashList);
    }

    public static String calculateMerkleRoot(List<String> stringList) {
        if(stringList.size()==0){
            return "";
        }
        if(stringList.size()==1){
            return stringList.get(0);
        }

        List<String> currentLayer = stringList;
        List<String> nextLayer = null;

        int count = currentLayer.size();
        while(count > 1) {
            if(currentLayer.size()%2 == 1){
                currentLayer.add("");
            }
            nextLayer = new ArrayList<>();
            for(int i=1; i < currentLayer.size(); i+=2) {
                nextLayer.add(sha256Base64(currentLayer.get(i-1) + currentLayer.get(i)));
            }
            currentLayer = nextLayer;
            count = currentLayer.size();
        }
        if(count != 1){
            throw new RuntimeException("计算默克尔树根出错。");
        }
        String merkleRoot = nextLayer.get(0);
        return merkleRoot;
    }

    private static String sha256Base64(String inputs) {
        byte[] sha256Digest = SHA256Util.applySha256(inputs.getBytes());
        return Base64Util.encode(sha256Digest);
    }
}
