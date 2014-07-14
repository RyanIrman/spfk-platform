package my.ftsm.spfk.common.repository;

import my.ftsm.spfk.common.domain.SenaraiAhliKumpulan;
import my.ftsm.spfk.common.domain.SenaraiKumpulan;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SenaraiAhliKumpulanRepository extends JpaRepository<SenaraiAhliKumpulan, Long> {
	
	@Query (value = "SELECT sak FROM SenaraiAhliKumpulan sak WHERE sak.kod=:kod")
	 SenaraiAhliKumpulan findByKod(@Param ("kod")String kod);
	 
	
	 SenaraiAhliKumpulan findByNama(String nama);
	 
//	 @Query("SELECT sak FROM SenaraiAhliKumpulan sak WHERE sak.kod = !? (SELECT sak.senaraiKumpulan FROM SenaraiKumpulan sak2 WHERE sak2.kod =: kod2")
//	 SenaraiAhliKumpulan findByKodAndSenaraiKumpulan_Kod(@Param ("kod")String kod,
//			 @Param ("kod2")String senaraiKumpulan_kod);

}
