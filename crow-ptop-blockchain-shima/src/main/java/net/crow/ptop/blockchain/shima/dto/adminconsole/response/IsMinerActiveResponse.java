package net.crow.ptop.blockchain.shima.dto.adminconsole.response;

public class IsMinerActiveResponse {

	private boolean minerInActiveState;
	
	public boolean isMinerInActiveState() {
        return minerInActiveState;
    }

    public void setMinerInActiveState(boolean minerInActiveState) {
        this.minerInActiveState = minerInActiveState;
    }
}
