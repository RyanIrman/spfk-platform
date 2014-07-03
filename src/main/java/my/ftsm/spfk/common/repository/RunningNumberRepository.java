package my.ftsm.spfk.common.repository;

import my.ftsm.spfk.common.domain.RunningNumber;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RunningNumberRepository extends 
		JpaRepository<RunningNumber, Long> {

	RunningNumber findByCode(String code);

}
