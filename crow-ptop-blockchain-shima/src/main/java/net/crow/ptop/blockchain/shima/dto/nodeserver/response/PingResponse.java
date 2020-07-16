package net.crow.ptop.blockchain.shima.dto.nodeserver.response;

import java.math.BigInteger;
import java.util.List;

import net.crow.ptop.blockchain.shima.dto.nodeserver.Node;

public class PingResponse {

	private String blockChainId;
    private Long blockChainVersion;
    private BigInteger blockChainHeight ;
    private List<Node> nodeList;
    
    public String getBlockChainId() {
        return blockChainId;
    }

    public void setBlockChainId(String blockChainId) {
        this.blockChainId = blockChainId;
    }

    public Long getBlockChainVersion() {
        return blockChainVersion;
    }

    public void setBlockChainVersion(Long blockChainVersion) {
        this.blockChainVersion = blockChainVersion;
    }

    public BigInteger getBlockChainHeight() {
        return blockChainHeight;
    }

    public void setBlockChainHeight(BigInteger blockChainHeight) {
        this.blockChainHeight = blockChainHeight;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }
}
