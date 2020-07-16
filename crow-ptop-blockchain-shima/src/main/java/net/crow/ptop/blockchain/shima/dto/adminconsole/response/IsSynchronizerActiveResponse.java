package net.crow.ptop.blockchain.shima.dto.adminconsole.response;

public class IsSynchronizerActiveResponse {

	private boolean synchronizerInActiveState;
	
	public boolean isSynchronizerInActiveState() {
        return synchronizerInActiveState;
    }

    public void setSynchronizerInActiveState(boolean synchronizerInActiveState) {
        this.synchronizerInActiveState = synchronizerInActiveState;
    }
}
