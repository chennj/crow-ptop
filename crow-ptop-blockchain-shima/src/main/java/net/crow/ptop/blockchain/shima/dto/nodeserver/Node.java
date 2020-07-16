package net.crow.ptop.blockchain.shima.dto.nodeserver;

import java.math.BigInteger;

public class Node extends SimpleNode{

	private BigInteger blockChainHeight;
    private Boolean isNodeAvailable;
    private Integer errorConnectionTimes;
    private Boolean fork;
    
    public BigInteger getBlockChainHeight() {
        return blockChainHeight;
    }

    public void setBlockChainHeight(BigInteger blockChainHeight) {
        this.blockChainHeight = blockChainHeight;
    }

    public Boolean getIsNodeAvailable() {
        return isNodeAvailable;
    }

    public void setIsNodeAvailable(Boolean nodeAvailable) {
        isNodeAvailable = nodeAvailable;
    }

    public Integer getErrorConnectionTimes() {
        return errorConnectionTimes;
    }

    public void setErrorConnectionTimes(Integer errorConnectionTimes) {
        this.errorConnectionTimes = errorConnectionTimes;
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }
}
