package net.crow.ptop.blockchain.shima.service;

import java.math.BigInteger;
import java.util.List;

import net.crow.ptop.blockchain.shima.dto.blockchainbranch.BlockchainBranchBlockDto;

/**
 * 区块链分叉service
 * @author chenn
 *
 */
public interface BlockChainBranchService {

	boolean isFork(BigInteger blockHeight, String blockHash);

    /**
     * 获取固定Hash的[小于传入的区块高度]的最大区块高度
     * @param blockHeight 传入的区块高度
     */
    BigInteger getFixBlockHashMaxBlockHeight(BigInteger blockHeight);

    boolean isBlockchainConfirmABranch();
    void updateBranchchainBranch(List<BlockchainBranchBlockDto> blockList) throws Exception;
    void branchchainBranchHandler() throws Exception;
    List<BlockchainBranchBlockDto> queryBlockchainBranch();
}
