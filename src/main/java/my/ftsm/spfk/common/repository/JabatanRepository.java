package my.ftsm.spfk.common.repository;

import my.ftsm.spfk.common.domain.Jabatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface JabatanRepository extends JpaRepository<Jabatan, Long> {

	Jabatan findByKodJabatan(String kodJabatan);
	
	 @Query(name = "Aplikasi.findById")
	Jabatan findById(@Param("Id")Long id);

}
