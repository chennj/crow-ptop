package net.crow.ptop.blockchain.shima.util;

import org.crow.ptop.blockchain.crypto.model.account.StringAddress;
import org.crow.ptop.blockchain.crypto.model.account.StringPrivateKey;
import org.crow.ptop.blockchain.crypto.model.account.StringPublicKey;

import net.crow.ptop.blockchain.core.model.key.Wallet;
import net.crow.ptop.blockchain.shima.dto.wallet.WalletDTO;

public class WalletDtoUtil {

	/**
     * 类型转换
     */
    public static WalletDTO classCast(Wallet wallet){
        WalletDTO walletDTO = new WalletDTO(wallet.getStringPrivateKey().getValue(),wallet.getStringPublicKey().getValue(),wallet.getStringAddress().getValue());
        return walletDTO;
    }
    
    /**
     * 类型转换
     */
    public static Wallet classCast(WalletDTO walletDTO){
        Wallet wallet = new Wallet(new StringPrivateKey(walletDTO.getPrivateKey()),
                new StringPublicKey(walletDTO.getPublicKey()),
                new StringAddress(walletDTO.getAddress()));
        return wallet;
    }
}
