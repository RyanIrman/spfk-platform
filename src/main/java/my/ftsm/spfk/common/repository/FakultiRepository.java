package my.ftsm.spfk.common.repository;

import my.ftsm.spfk.common.domain.Fakulti;

import org.springframework.data.jpa.repository.JpaRepository;



public interface FakultiRepository extends JpaRepository<Fakulti, Long> {

	Fakulti findByKodFakulti(String kodFakulti);

}
