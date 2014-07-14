package my.ftsm.spfk.common.repository;

import my.ftsm.spfk.common.domain.Subjek;

import org.springframework.data.jpa.repository.JpaRepository;



public interface SubjekRepository extends JpaRepository<Subjek, Long> {

//	JadualSemester findByKod(String kod);

}
