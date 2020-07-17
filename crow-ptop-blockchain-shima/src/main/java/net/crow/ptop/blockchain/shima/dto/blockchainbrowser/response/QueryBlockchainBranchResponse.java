package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.response;

import java.util.List;

import net.crow.ptop.blockchain.shima.dto.blockchainbranch.BlockchainBranchBlockDto;

public class QueryBlockchainBranchResponse {

	private List<BlockchainBranchBlockDto> blockList;
	
	public List<BlockchainBranchBlockDto> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<BlockchainBranchBlockDto> blockList) {
        this.blockList = blockList;
    }
}
