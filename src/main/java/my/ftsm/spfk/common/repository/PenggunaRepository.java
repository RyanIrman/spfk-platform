package my.ftsm.spfk.common.repository;

import my.ftsm.spfk.common.domain.Pengguna;

import org.springframework.data.jpa.repository.JpaRepository;



public interface PenggunaRepository extends JpaRepository<Pengguna, Long> {

	Pengguna findByEmail(String email);

}
