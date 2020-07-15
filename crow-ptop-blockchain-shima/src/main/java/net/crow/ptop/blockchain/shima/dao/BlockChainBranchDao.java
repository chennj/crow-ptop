package net.crow.ptop.blockchain.shima.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import net.crow.ptop.blockchain.shima.entity.BlockchainBranchBlockEntity;

/**
 * 区块分叉dao
 * @author chenn
 *
 */
@Mapper
@Component
public interface BlockChainBranchDao {

	List<BlockchainBranchBlockEntity> queryAllBlockchainBranchBlock();

    void removeAll();

    void add(BlockchainBranchBlockEntity entity);
}
