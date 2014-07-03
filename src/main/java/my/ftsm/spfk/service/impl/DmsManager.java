package my.ftsm.spfk.service.impl;


import my.ftsm.spfk.common.domain.Pengguna;
import my.ftsm.spfk.service.IDmsManager;
import my.ftsm.spfk.service.IDmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class DmsManager implements IDmsManager {

	@Autowired
	private IDmsService dmsService;
	
	@Override
	public void initData(){

		Pengguna pengguna=new Pengguna();
		pengguna.setEmail("ryan@gmail.com");
		pengguna.setKataLaluan("1234");
		dmsService.savePengguna(pengguna);
		
		pengguna=new Pengguna();
		pengguna.setEmail("domu@gmail.com");
		pengguna.setKataLaluan("1234");
		dmsService.savePengguna(pengguna);
		
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Pengguna findPenggunaByEmail(String email){
		return dmsService.findPenggunaByEmail(email);
	}
}
