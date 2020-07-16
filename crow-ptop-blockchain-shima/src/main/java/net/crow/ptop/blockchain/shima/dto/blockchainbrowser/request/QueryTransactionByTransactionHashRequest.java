package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.request;

public class QueryTransactionByTransactionHashRequest {

	private String transactionHash;
	
	public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }
}
