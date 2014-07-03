package my.ftsm.spfk.service;

import my.ftsm.spfk.common.domain.Pengguna;
import my.ftsm.spfk.common.domain.RunningNumber;


public interface IDmsService {

	void savePengguna(Pengguna pengguna);

	Pengguna findPenggunaByEmail(String email);

	RunningNumber findRunningNumberByCode(String code);

	RunningNumber saveRunningNumber(RunningNumber number);

}
