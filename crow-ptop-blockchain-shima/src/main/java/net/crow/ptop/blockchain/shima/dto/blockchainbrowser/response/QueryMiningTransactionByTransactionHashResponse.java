package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.response;

import org.crow.ptop.blockchain.node.transport.dto.TransactionDTO;

public class QueryMiningTransactionByTransactionHashResponse {

	private TransactionDTO transactionDTO;

	public TransactionDTO getTransactionDTO() {
		return transactionDTO;
	}

	public void setTransactionDTO(TransactionDTO transactionDTO) {
		this.transactionDTO = transactionDTO;
	}
	
	
}
