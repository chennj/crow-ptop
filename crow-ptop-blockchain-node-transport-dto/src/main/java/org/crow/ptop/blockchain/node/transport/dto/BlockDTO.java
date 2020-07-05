package org.crow.ptop.blockchain.node.transport.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class BlockDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long timestamp;
    private BigInteger height;
    private List<TransactionDTO> transactions;
    // 共识
    private String consensusValue;
    
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public BigInteger getHeight() {
		return height;
	}
	public void setHeight(BigInteger height) {
		this.height = height;
	}
	public List<TransactionDTO> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}
	public String getConsensusValue() {
		return consensusValue;
	}
	public void setConsensusValue(String consensusValue) {
		this.consensusValue = consensusValue;
	}

    
}
