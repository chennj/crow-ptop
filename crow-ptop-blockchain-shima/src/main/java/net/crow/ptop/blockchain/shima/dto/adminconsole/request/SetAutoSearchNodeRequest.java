package net.crow.ptop.blockchain.shima.dto.adminconsole.request;

public class SetAutoSearchNodeRequest {

	private boolean autoSearchNode;

	public boolean isAutoSearchNode() {
        return autoSearchNode;
    }

    public void setAutoSearchNode(boolean autoSearchNode) {
        this.autoSearchNode = autoSearchNode;
    }
}
