package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.response;

import java.util.List;

import org.crow.ptop.blockchain.node.transport.dto.TransactionDTO;

public class QueryMiningTransactionListResponse {

	private List<TransactionDTO> transactionDtoList;
	
	public List<TransactionDTO> getTransactionDtoList() {
        return transactionDtoList;
    }

    public void setTransactionDtoList(List<TransactionDTO> transactionDtoList) {
        this.transactionDtoList = transactionDtoList;
    }
}
