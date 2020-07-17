package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.response;

import net.crow.ptop.blockchain.core.model.Block;

public class QueryBlockDtoByBlockHeightResponse {

	private Block block ;
	
	public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
