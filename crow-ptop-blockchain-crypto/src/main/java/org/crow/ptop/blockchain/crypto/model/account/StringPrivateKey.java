package org.crow.ptop.blockchain.crypto.model.account;

import java.io.Serializable;

/**
 * 字符串格式的私钥
 * @author chenn
 *
 */
public class StringPrivateKey implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String value;

    public StringPrivateKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
