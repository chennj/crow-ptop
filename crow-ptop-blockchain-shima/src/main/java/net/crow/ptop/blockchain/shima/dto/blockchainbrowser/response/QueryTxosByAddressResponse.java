package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.response;

import java.util.List;

import net.crow.ptop.blockchain.core.model.transaction.TransactionOutput;

public class QueryTxosByAddressResponse {

	private List<TransactionOutput> txos;
	
	public List<TransactionOutput> getTxos() {
        return txos;
    }

    public void setTxos(List<TransactionOutput> txos) {
        this.txos = txos;
    }
}
