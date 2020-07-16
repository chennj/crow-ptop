package net.crow.ptop.blockchain.shima.dto.adminconsole.request;

import net.crow.ptop.blockchain.shima.dto.nodeserver.SimpleNode;

public class QueryNodeListRequest {

	private SimpleNode node;
	
	public SimpleNode getNode() {
        return node;
    }

    public void setNode(SimpleNode node) {
        this.node = node;
    }
}
