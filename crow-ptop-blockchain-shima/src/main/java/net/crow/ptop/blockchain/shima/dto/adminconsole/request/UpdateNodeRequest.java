package net.crow.ptop.blockchain.shima.dto.adminconsole.request;

import net.crow.ptop.blockchain.shima.dto.nodeserver.Node;

public class UpdateNodeRequest {

	private Node node;
	
	public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
