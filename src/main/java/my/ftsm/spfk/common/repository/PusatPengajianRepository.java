package my.ftsm.spfk.common.repository;

import my.ftsm.spfk.common.domain.Fakulti;
import my.ftsm.spfk.common.domain.PusatPengajian;

import org.springframework.data.jpa.repository.JpaRepository;



public interface PusatPengajianRepository extends JpaRepository<PusatPengajian, Long> {

	PusatPengajian findByKodPusat(String kodPusat);

}
