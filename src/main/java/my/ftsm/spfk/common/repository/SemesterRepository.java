package my.ftsm.spfk.common.repository;

import my.ftsm.spfk.common.domain.Semester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface SemesterRepository extends JpaRepository<Semester, Long> {

	@Query (value = "SELECT sem FROM Semester sem WHERE sem.kod=:kod")
	Semester findByKod(@Param("kod")String kod);

}
