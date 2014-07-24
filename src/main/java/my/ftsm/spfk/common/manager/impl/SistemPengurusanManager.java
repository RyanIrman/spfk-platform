package my.ftsm.spfk.common.manager.impl;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import my.ftsm.spfk.common.manager.ISistemPengurusanManager;
import my.ftsm.spfk.common.service.IDataLoaderSistemPengurusanService;

@Data
@Service
@Transactional
public class SistemPengurusanManager implements ISistemPengurusanManager{

		
	@Autowired
	private IDataLoaderSistemPengurusanService getdataLoaderSistemPengurusanService;
	   
	@Override
	public void loadData(){
		this.getdataLoaderSistemPengurusanService.initData();
			
	}
	
	
	
}
