package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.request;

import net.crow.ptop.blockchain.shima.dto.blockchainbrowser.NormalTransactionDto;

public class SubmitNormalTransactionRequest {

	private NormalTransactionDto normalTransactionDto;
	
	public NormalTransactionDto getNormalTransactionDto() {
        return normalTransactionDto;
    }

    public void setNormalTransactionDto(NormalTransactionDto normalTransactionDto) {
        this.normalTransactionDto = normalTransactionDto;
    }
}
