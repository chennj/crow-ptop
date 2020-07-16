package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.response;

import java.util.List;

import net.crow.ptop.blockchain.core.model.transaction.Transaction;

public class QueryTransactionByTransactionHeightResponse {

	private List<Transaction> transactionList;
	
	public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
