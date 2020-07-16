package net.crow.ptop.blockchain.shima.service.impl;

import org.crow.ptop.blockchain.node.transport.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.crow.ptop.blockchain.core.BlockChainCore;
import net.crow.ptop.blockchain.shima.service.BlockchainNodeServerService;

@Service
public class BlockchainNodeServerServiceImpl implements BlockchainNodeServerService{

	@Autowired
    private BlockChainCore blockChainCore;

	@Override
	public void receiveTransaction(TransactionDTO transactionDTO) throws Exception {
		blockChainCore.getMiner().getMinerTransactionDtoDataBase().insertTransactionDTO(transactionDTO);
	}

}
