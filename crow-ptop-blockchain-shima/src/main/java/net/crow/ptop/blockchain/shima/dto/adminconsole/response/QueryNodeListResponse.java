package net.crow.ptop.blockchain.shima.dto.adminconsole.response;

import java.util.List;

import net.crow.ptop.blockchain.shima.dto.nodeserver.Node;

public class QueryNodeListResponse {

	private List<Node> nodeList;
	
	public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }
}
