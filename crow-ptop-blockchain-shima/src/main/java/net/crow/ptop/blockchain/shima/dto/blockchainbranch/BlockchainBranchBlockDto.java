package net.crow.ptop.blockchain.shima.dto.blockchainbranch;

import java.math.BigInteger;

public class BlockchainBranchBlockDto {

	private BigInteger blockHeight;
    private String blockHash;

    public BigInteger getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(BigInteger blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }
}
