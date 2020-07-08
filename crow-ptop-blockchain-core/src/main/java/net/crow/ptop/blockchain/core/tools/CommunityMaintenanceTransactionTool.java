package net.crow.ptop.blockchain.core.tools;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.crow.ptop.blockchain.crypto.model.account.StringAddress;

import net.crow.ptop.blockchain.core.VirtualMachine;
import net.crow.ptop.blockchain.core.model.transaction.Transaction;
import net.crow.ptop.blockchain.core.model.transaction.TransactionOutput;
import net.crow.ptop.blockchain.core.model.transaction.TransactionType;
import net.crow.ptop.blockchain.core.utils.BigIntegerUtil;

/**
 * 社区维护交易
 * @author chenn
 *
 */
public class CommunityMaintenanceTransactionTool {

    public static Transaction obtainMaintenanceTransaction(long timestamp, BigInteger blockHeight){
        if(BigIntegerUtil.isEquals(blockHeight,new BigInteger("2"))){
            return block2MaintenanceTransaction(timestamp);
        }
        return null;
    }
    
    public static boolean isMaintenanceTransactionRight(long timestamp, BigInteger blockHeight, Transaction maintenanceTransaction){
        if(BigIntegerUtil.isEquals(blockHeight,new BigInteger("2"))){
            if(maintenanceTransaction == null){
                return false;
            }
            Transaction transaction = block2MaintenanceTransaction(timestamp);
            return transaction.getTransactionHash().equals(maintenanceTransaction.getTransactionHash());
        }
        return true;
    }
    
    private static Transaction block2MaintenanceTransaction(long timestamp){
        Transaction transaction = new Transaction();
        transaction.setTimestamp(timestamp);
        transaction.setTransactionType(TransactionType.COMMUNITY_MAINTENANCE);
        transaction.setInputs(null);

        ArrayList<TransactionOutput> outputs = new ArrayList<>();
        TransactionOutput output = new TransactionOutput();
        output.setStringAddress(new StringAddress("1F7cCJVfRoogxx32xUyGP5oGfRpDUthPed"));
        output.setValue(new BigDecimal("2000000000"));
        output.setScriptLock(VirtualMachine.createPayToClassicAddressOutputScript("1F7cCJVfRoogxx32xUyGP5oGfRpDUthPed"));
        output.setTransactionOutputHash(TransactionTool.calculateTransactionOutputHash(transaction,output));
        outputs.add(output);

        transaction.setOutputs(outputs);
        transaction.setTransactionHash(TransactionTool.calculateTransactionHash(transaction));
        return transaction;
    }
}
