package org.crow.ptop.blockchain.node.transport.dto;

import java.io.Serializable;
import java.util.List;

public class TransactionInputDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//交易的输入
    private String unspendTransactionOutputHash;
    //脚本钥匙
    private List<String> scriptKey;
    
    
	public String getUnspendTransactionOutputHash() {
		return unspendTransactionOutputHash;
	}
	public void setUnspendTransactionOutputHash(String unspendTransactionOutputHash) {
		this.unspendTransactionOutputHash = unspendTransactionOutputHash;
	}
	public List<String> getScriptKey() {
		return scriptKey;
	}
	public void setScriptKey(List<String> scriptKey) {
		this.scriptKey = scriptKey;
	}

    
}
