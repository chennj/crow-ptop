package net.crow.ptop.blockchain.shima.dto.nodeserver.request;

import org.crow.ptop.blockchain.node.transport.dto.TransactionDTO;

public class ReceiveTransactionRequest {

	private TransactionDTO transactionDTO;
	
	public TransactionDTO getTransactionDTO() {
        return transactionDTO;
    }

    public void setTransactionDTO(TransactionDTO transactionDTO) {
        this.transactionDTO = transactionDTO;
    }
}
