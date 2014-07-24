package my.ftsm.spfk.common.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import my.ftsm.spfk.common.domain.Pengguna;
import my.ftsm.spfk.common.domain.RunningNumber;
import my.ftsm.spfk.common.repository.PenggunaRepository;
import my.ftsm.spfk.common.repository.RunningNumberRepository;
import my.ftsm.spfk.common.service.ISistemPengurusanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SistemPengurusanService implements ISistemPengurusanService {

	protected EntityManager entityManager;
	
	@Autowired
	private PenggunaRepository penggunaRepository;

	@Autowired
	private RunningNumberRepository runningNumberRepository;

	@Override
	public void savePengguna(Pengguna pengguna) {
		penggunaRepository.save(pengguna);
	}

	@Override
	@Transactional(readOnly = true)
	public Pengguna findPenggunaByEmail(String email) {
		return penggunaRepository.findByEmail(email);
	}
	
	@Override
	@Transactional(readOnly=true)
	public RunningNumber findRunningNumberByCode(String code){
		return runningNumberRepository.findByCode(code);
	}
	
	@Override
	public RunningNumber saveRunningNumber(RunningNumber number){
		return runningNumberRepository.save(number);
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "spfkPU")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
