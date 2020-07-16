package net.crow.ptop.blockchain.shima.dto.adminconsole.request;

import java.util.List;

import net.crow.ptop.blockchain.shima.dto.blockchainbranch.BlockchainBranchBlockDto;

public class UpdateBlockchainBranchRequest {

	private List<BlockchainBranchBlockDto> blockList;
	
	public List<BlockchainBranchBlockDto> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<BlockchainBranchBlockDto> blockList) {
        this.blockList = blockList;
    }
}
