package net.crow.ptop.blockchain.core.model.key;

import org.crow.ptop.blockchain.crypto.model.account.StringAddress;
import org.crow.ptop.blockchain.crypto.model.account.StringPrivateKey;
import org.crow.ptop.blockchain.crypto.model.account.StringPublicKey;

/**
 * 钱包
 * @author chenn
 *
 */
public class Wallet {

    private StringPrivateKey stringPrivateKey;

    private StringPublicKey stringPublicKey;

    private StringAddress stringAddress;

    public Wallet(StringPrivateKey stringPrivateKey, StringPublicKey stringPublicKey, StringAddress stringAddress) {
        this.stringPrivateKey = stringPrivateKey;
        this.stringPublicKey = stringPublicKey;
        this.stringAddress = stringAddress;
    }

	public StringPrivateKey getStringPrivateKey() {
		return stringPrivateKey;
	}

	public void setStringPrivateKey(StringPrivateKey stringPrivateKey) {
		this.stringPrivateKey = stringPrivateKey;
	}

	public StringPublicKey getStringPublicKey() {
		return stringPublicKey;
	}

	public void setStringPublicKey(StringPublicKey stringPublicKey) {
		this.stringPublicKey = stringPublicKey;
	}

	public StringAddress getStringAddress() {
		return stringAddress;
	}

	public void setStringAddress(StringAddress stringAddress) {
		this.stringAddress = stringAddress;
	}

}
