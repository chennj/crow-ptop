package org.crow.ptop.blockchain.node.transport.dto;

import java.io.Serializable;
import java.util.List;

public class TransactionOutputDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//交易输出的地址
    private String address;
    //交易输出的金额
    private String value;
    //脚本锁
    private List<String> scriptLock;
    
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<String> getScriptLock() {
		return scriptLock;
	}
	public void setScriptLock(List<String> scriptLock) {
		this.scriptLock = scriptLock;
	}
    
}
