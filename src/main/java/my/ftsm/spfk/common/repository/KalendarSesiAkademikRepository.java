package my.ftsm.spfk.common.repository;

import my.ftsm.spfk.common.domain.KalendarSesiAkedemik;

import org.springframework.data.jpa.repository.JpaRepository;



public interface KalendarSesiAkademikRepository extends JpaRepository<KalendarSesiAkedemik, Long> {

	KalendarSesiAkedemik findBySesiAkademik(String sesiAkademik);

}
