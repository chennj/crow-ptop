package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.response;

import java.util.List;

import net.crow.ptop.blockchain.core.model.transaction.TransactionOutput;

public class QueryUtxosByAddressResponse {

	private List<TransactionOutput> utxos;
	
	public List<TransactionOutput> getUtxos() {
        return utxos;
    }

    public void setUtxos(List<TransactionOutput> utxos) {
        this.utxos = utxos;
    }
}
