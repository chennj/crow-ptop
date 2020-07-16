package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.response;

import net.crow.ptop.blockchain.shima.dto.wallet.WalletDTO;

public class GenerateWalletResponse {

	private WalletDTO walletDTO;
	
	public WalletDTO getWalletDTO() {
        return walletDTO;
    }

    public void setWalletDTO(WalletDTO walletDTO) {
        this.walletDTO = walletDTO;
    }
}
