package net.crow.ptop.blockchain.shima.dto.blockchainbranch;

import java.util.List;

public class BlockchainBranchDto {

	private List<BlockchainBranchBlockDto> blockList;
	
	public List<BlockchainBranchBlockDto> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<BlockchainBranchBlockDto> blockList) {
        this.blockList = blockList;
    }
}
