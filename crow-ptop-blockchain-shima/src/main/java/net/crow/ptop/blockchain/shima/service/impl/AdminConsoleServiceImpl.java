package net.crow.ptop.blockchain.shima.service.impl;

import org.springframework.stereotype.Service;

import net.crow.ptop.blockchain.shima.service.AdminConsoleService;

@Service
public class AdminConsoleServiceImpl implements AdminConsoleService{

	@Override
	public boolean isMinerActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activeMiner() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactiveMiner() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSynchronizerActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deactiveSynchronizer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean activeSynchronizer() {
		// TODO Auto-generated method stub
		return false;
	}

}
