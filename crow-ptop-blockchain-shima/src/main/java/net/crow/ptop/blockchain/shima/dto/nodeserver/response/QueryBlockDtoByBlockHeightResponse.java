package net.crow.ptop.blockchain.shima.dto.nodeserver.response;

import org.crow.ptop.blockchain.node.transport.dto.BlockDTO;

public class QueryBlockDtoByBlockHeightResponse {

	private BlockDTO blockDTO;
	
	public BlockDTO getBlockDTO() {
        return blockDTO;
    }

    public void setBlockDTO(BlockDTO blockDTO) {
        this.blockDTO = blockDTO;
    }
}
