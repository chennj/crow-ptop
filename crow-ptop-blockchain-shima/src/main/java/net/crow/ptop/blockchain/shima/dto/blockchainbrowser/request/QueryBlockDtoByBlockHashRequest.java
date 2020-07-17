package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.request;

public class QueryBlockDtoByBlockHashRequest {

	private String blockHash;
	
	public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }
}
