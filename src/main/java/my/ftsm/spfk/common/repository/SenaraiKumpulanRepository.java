package my.ftsm.spfk.common.repository;

import my.ftsm.spfk.common.domain.SenaraiKumpulan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SenaraiKumpulanRepository extends JpaRepository<SenaraiKumpulan, Long> {
	
	 SenaraiKumpulan findByKod(String kod);

}
