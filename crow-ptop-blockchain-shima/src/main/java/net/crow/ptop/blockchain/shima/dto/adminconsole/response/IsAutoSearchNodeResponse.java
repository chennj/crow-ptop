package net.crow.ptop.blockchain.shima.dto.adminconsole.response;

public class IsAutoSearchNodeResponse {

	private boolean autoSearchNewNode;
	
	public boolean isAutoSearchNewNode() {
        return autoSearchNewNode;
    }

    public void setAutoSearchNewNode(boolean autoSearchNewNode) {
        this.autoSearchNewNode = autoSearchNewNode;
    }
}
