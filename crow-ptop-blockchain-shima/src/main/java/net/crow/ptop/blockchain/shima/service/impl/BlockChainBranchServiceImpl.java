package net.crow.ptop.blockchain.shima.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.crow.ptop.blockchain.core.model.Block;
import net.crow.ptop.blockchain.core.utils.BigIntegerUtil;
import net.crow.ptop.blockchain.shima.dao.BlockChainBranchDao;
import net.crow.ptop.blockchain.shima.dto.blockchainbranch.BlockchainBranchBlockDto;
import net.crow.ptop.blockchain.shima.entity.BlockchainBranchBlockEntity;
import net.crow.ptop.blockchain.shima.service.BlockChainBranchService;
import net.crow.ptop.blockchain.shima.service.BlockChainCoreService;

@Service
public class BlockChainBranchServiceImpl implements BlockChainBranchService{

	private Map<String,String> blockHeightBlockHashMap = new HashMap<>();
    private boolean isRefreshCache = false;
    
	@Autowired
    private BlockChainBranchDao blockChainBranchDao;
    @Autowired
    private BlockChainCoreService blockChainCoreService;
    
    /**
     * 将分支加载到内存
     */
    private void refreshCache(){
        List<BlockchainBranchBlockEntity> blockchainBranchBlockEntityList = blockChainBranchDao.queryAllBlockchainBranchBlock();
        if(blockchainBranchBlockEntityList != null){
            for(BlockchainBranchBlockEntity entity:blockchainBranchBlockEntityList){
                blockHeightBlockHashMap.put(String.valueOf(entity.getBlockHeight()),entity.getBlockHash());
            }
        }
        isRefreshCache = true;
    }
    
	@Override
	public boolean isFork(BigInteger blockHeight, String blockHash) {
		String stringBlockHeight = String.valueOf(blockHeight);
        String blockHashTemp = blockHeightBlockHashMap.get(stringBlockHeight);
        if(blockHashTemp == null){
            return false;
        }
        return !blockHashTemp.equals(blockHash);
	}

	@Override
	public BigInteger getFixBlockHashMaxBlockHeight(BigInteger blockHeight) {
		BigInteger nearBlockHeight = BigInteger.ZERO;
        Set<String> set = blockHeightBlockHashMap.keySet();
        for(String stringBlockHeight:set){
            BigInteger intBlockHeight = new BigInteger(stringBlockHeight);
            if(BigIntegerUtil.isLessThan(intBlockHeight,blockHeight)  && BigIntegerUtil.isGreatThan(intBlockHeight,nearBlockHeight)){
                nearBlockHeight = intBlockHeight;
            }
        }
        return nearBlockHeight;
	}

	@Override
	public boolean isBlockchainConfirmABranch() {
		List<BlockchainBranchBlockEntity> blockchainBranchBlockEntityList = blockChainBranchDao.queryAllBlockchainBranchBlock();
        if(blockchainBranchBlockEntityList == null || blockchainBranchBlockEntityList.size()==0){
            return false;
        }
        return true;
	}

	@Transactional
	@Override
	public void updateBranchchainBranch(List<BlockchainBranchBlockDto> blockList) throws Exception {
		blockChainBranchDao.removeAll();
        for(BlockchainBranchBlockDto blockchainBranchBlockDto:blockList){
            BlockchainBranchBlockEntity entity = classCast(blockchainBranchBlockDto);
            blockChainBranchDao.add(entity);
        }
        refreshCache();
        branchchainBranchHandler();
	}

	@Override
	public void branchchainBranchHandler() throws Exception {
		if(!isRefreshCache){
            refreshCache();
        }
        List<BlockchainBranchBlockEntity> blockchainBranchBlockEntityList = blockChainBranchDao.queryAllBlockchainBranchBlock();
        if(blockchainBranchBlockEntityList == null || blockchainBranchBlockEntityList.size()==0){
            return;
        }
        blockchainBranchBlockEntityList.sort(Comparator.comparing(BlockchainBranchBlockEntity::getBlockHeight));
        for(int i=0;i<blockchainBranchBlockEntityList.size();i++){
            BlockchainBranchBlockEntity entity = blockchainBranchBlockEntityList.get(i);
            Block block = blockChainCoreService.queryNoTransactionBlockDtoByBlockHeight(entity.getBlockHeight());
            if(block == null){
                return;
            }
            if(entity.getBlockHash().equals(block.getHash()) && BigIntegerUtil.isEquals(entity.getBlockHeight(),block.getHeight())){
                continue;
            }
            BigInteger deleteBlockHeight;
            if(i==0){
                deleteBlockHeight = BigInteger.ONE;
            }else {
                deleteBlockHeight = blockchainBranchBlockEntityList.get(i-1).getBlockHeight().add(BigInteger.ONE);
            }
            blockChainCoreService.removeBlocksUtilBlockHeightLessThan(deleteBlockHeight);
            return;
        }
	}

	@Override
	public List<BlockchainBranchBlockDto> queryBlockchainBranch() {
		List<BlockchainBranchBlockEntity> blockchainBranchBlockEntityList = blockChainBranchDao.queryAllBlockchainBranchBlock();
        if(blockchainBranchBlockEntityList == null || blockchainBranchBlockEntityList.size()==0){
            return null;
        }
        return classCast(blockchainBranchBlockEntityList);
	}

	private List<BlockchainBranchBlockDto> classCast(List<BlockchainBranchBlockEntity> blockchainBranchBlockEntityList) {
        if(blockchainBranchBlockEntityList == null || blockchainBranchBlockEntityList.size()==0){
            return null;
        }
        List<BlockchainBranchBlockDto> dtoList = new ArrayList<>();
        for(BlockchainBranchBlockEntity entity:blockchainBranchBlockEntityList){
            dtoList.add(classCast(entity));
        }
        return dtoList;
    }

    private BlockchainBranchBlockDto classCast(BlockchainBranchBlockEntity entity) {
        BlockchainBranchBlockDto dto = new BlockchainBranchBlockDto();
        dto.setBlockHash(entity.getBlockHash());
        dto.setBlockHeight(entity.getBlockHeight());
        return dto;
    }

    private BlockchainBranchBlockEntity classCast(BlockchainBranchBlockDto dto) {
        BlockchainBranchBlockEntity entity = new BlockchainBranchBlockEntity();
        entity.setBlockHash(dto.getBlockHash());
        entity.setBlockHeight(dto.getBlockHeight());
        return entity;
    }
}
