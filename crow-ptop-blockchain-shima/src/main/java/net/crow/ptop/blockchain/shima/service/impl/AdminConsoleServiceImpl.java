package net.crow.ptop.blockchain.shima.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.crow.ptop.blockchain.core.BlockChainCore;
import net.crow.ptop.blockchain.shima.service.AdminConsoleService;
import net.crow.ptop.blockchain.shima.service.NodeService;

@Service
public class AdminConsoleServiceImpl implements AdminConsoleService{

	@Autowired
    private BlockChainCore blockChainCore;

    @Autowired
    private NodeService nodeService;

    
	@Override
	public boolean isMinerActive() {
		return blockChainCore.getMiner().isActive();
	}

	@Override
	public void activeMiner() throws Exception {
		blockChainCore.getMiner().active();
	}

	@Override
	public void deactiveMiner() throws Exception {
		blockChainCore.getMiner().deactive();
	}

	@Override
	public boolean isSynchronizerActive() {
		return blockChainCore.getSynchronizer().isActive();
	}

	@Override
	public boolean deactiveSynchronizer() {
		blockChainCore.getSynchronizer().deactive();
        return true;
	}

	@Override
	public boolean activeSynchronizer() {
		blockChainCore.getSynchronizer().active();
        return true;
	}

}
