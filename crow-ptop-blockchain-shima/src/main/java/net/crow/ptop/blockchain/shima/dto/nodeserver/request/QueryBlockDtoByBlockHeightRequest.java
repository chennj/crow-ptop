package net.crow.ptop.blockchain.shima.dto.nodeserver.request;

import java.math.BigInteger;

public class QueryBlockDtoByBlockHeightRequest {

	private BigInteger blockHeight;
	
	public BigInteger getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(BigInteger blockHeight) {
        this.blockHeight = blockHeight;
    }
}
