package my.ftsm.spfk.service;

import my.ftsm.spfk.common.domain.Pengguna;

public interface IDmsManager {

	void initData();

	Pengguna findPenggunaByEmail(String email);

}
