package my.ftsm.spfk.common.service;

import my.ftsm.spfk.common.domain.Pengguna;
import my.ftsm.spfk.common.domain.RunningNumber;


public interface ISistemPengurusanService {

	void savePengguna(Pengguna pengguna);

	Pengguna findPenggunaByEmail(String email);

	RunningNumber findRunningNumberByCode(String code);

	RunningNumber saveRunningNumber(RunningNumber number);

}
