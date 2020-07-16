package net.crow.ptop.blockchain.core.tools;

import org.crow.ptop.blockchain.crypto.AccountUtil;
import org.crow.ptop.blockchain.crypto.model.account.StringAccount;
import org.crow.ptop.blockchain.crypto.model.account.StringAddress;
import org.crow.ptop.blockchain.crypto.model.account.StringPrivateKey;
import org.crow.ptop.blockchain.crypto.model.account.StringPublicKey;

import net.crow.ptop.blockchain.core.model.key.Wallet;

public class WalletTool {

	public static Wallet loadWallet(StringPrivateKey stringPrivateKey, StringPublicKey stringPublicKey, StringAddress stringAddress){
        try {
            Wallet wallet = new Wallet(stringPrivateKey,stringPublicKey,stringAddress);
            return wallet;
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Wallet generateWallet(){
        try {
            StringAccount stringAccount = AccountUtil.randomStringAccount();
            StringPublicKey stringPublicKey = stringAccount.getStringPublicKey();
            StringPrivateKey stringPrivateKey = stringAccount.getStringPrivateKey();
            StringAddress stringAddress = stringAccount.getStringAddress();
            Wallet wallet = new Wallet(stringPrivateKey,stringPublicKey,stringAddress);
            return wallet;
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
