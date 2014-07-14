package my.ftsm.spfk.common.repository;

import my.ftsm.spfk.common.domain.JadualSemester;
import my.ftsm.spfk.common.domain.Semester;

import org.springframework.data.jpa.repository.JpaRepository;



public interface JadualSemesterRepository extends JpaRepository<JadualSemester, Long> {

//	JadualSemester findByKod(String kod);

}
