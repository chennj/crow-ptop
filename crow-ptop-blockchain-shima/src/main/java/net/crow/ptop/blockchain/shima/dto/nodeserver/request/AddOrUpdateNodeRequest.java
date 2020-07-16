package net.crow.ptop.blockchain.shima.dto.nodeserver.request;

import java.math.BigInteger;

public class AddOrUpdateNodeRequest {

	private int port;
    private BigInteger blockChainHeight;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public BigInteger getBlockChainHeight() {
        return blockChainHeight;
    }

    public void setBlockChainHeight(BigInteger blockChainHeight) {
        this.blockChainHeight = blockChainHeight;
    }
}
