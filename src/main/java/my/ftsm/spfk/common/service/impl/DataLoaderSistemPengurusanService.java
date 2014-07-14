package my.ftsm.spfk.common.service.impl;


import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import my.ftsm.spfk.common.constent.LoadDataConstant;
import my.ftsm.spfk.common.constent.SenaraiAhliKumpulanConstent;
import my.ftsm.spfk.common.domain.BaseForEntity;
import my.ftsm.spfk.common.domain.Fakulti;
import my.ftsm.spfk.common.domain.Jabatan;
import my.ftsm.spfk.common.domain.JadualSemester;
import my.ftsm.spfk.common.domain.KalendarSesiAkedemik;
import my.ftsm.spfk.common.domain.Pengguna;
import my.ftsm.spfk.common.domain.PusatPengajian;
import my.ftsm.spfk.common.domain.Semester;
import my.ftsm.spfk.common.domain.SenaraiAhliKumpulan;
import my.ftsm.spfk.common.domain.SenaraiKumpulan;
import my.ftsm.spfk.common.domain.Subjek;
import my.ftsm.spfk.common.domain.SubjekTerlibat;
import my.ftsm.spfk.common.repository.FakultiRepository;
import my.ftsm.spfk.common.repository.JabatanRepository;
import my.ftsm.spfk.common.repository.JadualSemesterRepository;
import my.ftsm.spfk.common.repository.KalendarSesiAkademikRepository;
import my.ftsm.spfk.common.repository.PenggunaRepository;
import my.ftsm.spfk.common.repository.PusatPengajianRepository;
import my.ftsm.spfk.common.repository.SemesterRepository;
import my.ftsm.spfk.common.repository.SenaraiAhliKumpulanRepository;
import my.ftsm.spfk.common.repository.SenaraiKumpulanRepository;
import my.ftsm.spfk.common.repository.SubjekRepository;
import my.ftsm.spfk.common.repository.SubjekTerlibatRepository;
import my.ftsm.spfk.common.service.IDataLoaderSistemPengurusanService;
import my.ftsm.spfk.common.vo.FakultiDataLoaderVO;
import my.ftsm.spfk.common.vo.JabatanDataLoaderVO;
import my.ftsm.spfk.common.vo.JadualSemesterDataLoaderVO;
import my.ftsm.spfk.common.vo.KalendarSesiAkademikDataLoaderVO;
import my.ftsm.spfk.common.vo.MasterDataLoaderCommonVO;
import my.ftsm.spfk.common.vo.MasterDataLoaderVO;
import my.ftsm.spfk.common.vo.PenggunaDataLoaderVO;
import my.ftsm.spfk.common.vo.PusatPengajianDataLoaderVO;
import my.ftsm.spfk.common.vo.SemesterDataLoaderVO;
import my.ftsm.spfk.common.vo.SenaraiAhliKumpulanDataLoaderVO;
import my.ftsm.spfk.common.vo.SenaraiKumpulanDataLoaderVO;
import my.ftsm.spfk.common.vo.SubjekDataLoaderVO;
import my.ftsm.spfk.common.vo.SubjekTerlibatDataLoaderVO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



@Data
@Service
public class DataLoaderSistemPengurusanService implements IDataLoaderSistemPengurusanService {
	
	@Autowired
	private PenggunaRepository penggunaRepository;
	
	@Autowired
	private SenaraiKumpulanRepository senaraiKumpulanRepository;
	
	@Autowired
	private SenaraiAhliKumpulanRepository senaraiAhliKumpulanRepository;
	
	@Autowired
	private FakultiRepository fakultiRepository;
	
	@Autowired
	private PusatPengajianRepository pusatPengajianRepository;
	
	@Autowired
	private KalendarSesiAkademikRepository kalendarSesiAkademikRepository;
	
	@Autowired
	private SemesterRepository semesterRepository;
	
	@Autowired
	private JadualSemesterRepository jadualSemesterRepository;
	
	@Autowired
	private JabatanRepository jabatanRepository;
	
	@Autowired
	private SubjekRepository subjekRepository;
	
	@Autowired
	private SubjekTerlibatRepository subjekTerlibatRepository;
	
	@Override
	public void initData(){
		
		this.loadDataFromExcel();
	}
	
	public void loadDataFromExcel(){
		
		List<MasterDataLoaderCommonVO> masterDataLoaderPerformList = new ArrayList<>();
		try (InputStream fs = this.getClass().getClassLoader().getResourceAsStream("dataloading/spfk_dataloader.xlsx")) {

            final XSSFWorkbook wb = new XSSFWorkbook(fs); // get total sheet
            
            /* searching MasterData sheet */
            final XSSFSheet sheet = wb.getSheet(LoadDataConstant.CONTROLLER);

            /* get last number from row. */
            final int rowsCount = sheet.getLastRowNum();

            /* populate data from excel to database. */
            for (int i = 0; i <= rowsCount; i++) {
                if (i != 0) {
                    final Row row = sheet.getRow(i);

                    // to ignore last row
                    if (row == null || row.getCell(0) == null) {
                        break;
                    }

                    MasterDataLoaderVO fromCell = new MasterDataLoaderVO();
                    fromCell = MasterDataLoaderVO.fromCell(row);

                    final MasterDataLoaderCommonVO vo = new MasterDataLoaderCommonVO();
                    vo.setKod(fromCell.getKod());
                    vo.setPerkara(fromCell.getPerkara());
                    vo.setAktif(convertOracleBooleanDataType(fromCell.getAktif()));
                    vo.setUrutan(Integer.valueOf(fromCell.getUrutan()));
                    
                    masterDataLoaderPerformList.add(vo);

                }
            }

            int num = 0;
            if (!CollectionUtils.isEmpty(masterDataLoaderPerformList)) {
                for (num = 0; num < masterDataLoaderPerformList.size(); num++) {
                    for (MasterDataLoaderCommonVO master : masterDataLoaderPerformList) {
                    	if(master.getUrutan() != null || master.getUrutan() > 0)
                    	if(master.getAktif() && master.getUrutan().equals(num)){
                    	
                    		XSSFSheet sheets = wb.getSheet(master.getKod());
                    		this.populateDataLoader_Process(sheets, wb, master.getKod(), master.getUrutan());
                    	}
                    }

                }

            }
            
		}catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
		
		
	}
	
    public Boolean convertOracleBooleanDataType(final String fromCellDataType) {
        Boolean value = false;
        if (fromCellDataType == null) {
            value = false;
        } else if (fromCellDataType !=null && fromCellDataType.equalsIgnoreCase("Y")) {
            value = true;
        } else if (fromCellDataType != null && fromCellDataType.equalsIgnoreCase("N")) {

            value = false;
        }
        return value;
    }
    
    public Date convertStringtoDateFormatted(String rawDate){
    	Date convertedDate = null; 	
    	try{
            		
    		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
    		 convertedDate = dateFormat.parse(rawDate); 

    	       
        
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return convertedDate;
    }

    public void populateDataLoader_Process(XSSFSheet sheets, XSSFWorkbook wb, String kodSheet, Integer urutan){
    	Date today = new Date();
    	if(LoadDataConstant.SNR_KUMPULAN.equals(kodSheet) && urutan == 0){
    		  final int rowsCount = sheets.getLastRowNum();

              for (int s = 0; s <= rowsCount; s++) {

                  if (s != 0) {
                      final Row row = sheets.getRow(s);

                      // to ignore last row
                      if (row.getCell(0) == null) {
                          break;
                      }

                      SenaraiKumpulanDataLoaderVO fromCell = new SenaraiKumpulanDataLoaderVO();
                      fromCell = SenaraiKumpulanDataLoaderVO.fromCell(row);

                      SenaraiKumpulan senaraiKumpulan = new SenaraiKumpulan();
                      senaraiKumpulan.setCreatedBy("SYSTEM");
                      senaraiKumpulan.setCreatedDate(today);
                      senaraiKumpulan.setLastModifiedBy("SYSTEM");
                      senaraiKumpulan.setLastModifiedDate(today);
                      senaraiKumpulan.setAktif(convertOracleBooleanDataType(fromCell.getAktif()));
                      
                      senaraiKumpulan.setKod(fromCell.getKod());
                      senaraiKumpulan.setNama(fromCell.getNama());
                      senaraiKumpulan.setPerihal(fromCell.getPerihal());
                
                      senaraiKumpulan.setNilaiStr(fromCell.getNilaiStr());
                      if (fromCell.getParents() != null) {
                          SenaraiKumpulan parentId = senaraiKumpulanRepository.findByKod(fromCell.getParents());

                          senaraiKumpulan.setParent(parentId);

                      }

                      senaraiKumpulanRepository.save(senaraiKumpulan);

                  }
              }
              
              
    	}
    	
    	if(LoadDataConstant.SNR_AHLI_KUMPULAN.equals(kodSheet) && urutan == 1){
    		final int rowsCount = sheets.getLastRowNum();
    	
            for (int s = 0; s <= rowsCount; s++) {

                if (s != 0) {
                    final Row row = sheets.getRow(s);

                    // to ignore last row
                    if (row.getCell(0) == null) {
                        break;
                    }

                    SenaraiAhliKumpulanDataLoaderVO fromCell = new SenaraiAhliKumpulanDataLoaderVO();
                    fromCell = SenaraiAhliKumpulanDataLoaderVO.fromCell(row);

                    SenaraiAhliKumpulan senaraiAhliKumpulan = new SenaraiAhliKumpulan();
                    senaraiAhliKumpulan.setCreatedBy("SYSTEM");
                    senaraiAhliKumpulan.setCreatedDate(today);
                    senaraiAhliKumpulan.setLastModifiedBy("SYSTEM");
                    senaraiAhliKumpulan.setLastModifiedDate(today);
                    
                    senaraiAhliKumpulan.setKod(fromCell.getKod());
                    senaraiAhliKumpulan.setNama(fromCell.getNama());
                    senaraiAhliKumpulan.setPerihal(fromCell.getPerihal());
                    senaraiAhliKumpulan.setAktif(convertOracleBooleanDataType(fromCell.getAktif()));
                    senaraiAhliKumpulan.setNilaiStr(fromCell.getNilaiStr());
                    if(fromCell.getKodSK()!= null || StringUtils.isBlank(fromCell.getKodSK())){
                    SenaraiKumpulan sk = senaraiKumpulanRepository.findByKod(fromCell.getKodSK());
                    
                 
                    senaraiAhliKumpulan.setSenaraiKumpulan_Id(sk);
                    if(!sk.getAktif()){
                    	break;
                    }
                    
                    }
                    if (fromCell.getParents() != null || !StringUtils.isBlank(fromCell.getParents())) {
                        SenaraiAhliKumpulan parentId = senaraiAhliKumpulanRepository.findByKod(fromCell.getParents());

                        senaraiAhliKumpulan.setParent(parentId);

                    }

                    senaraiAhliKumpulanRepository.save(senaraiAhliKumpulan);

                }
            }
    		
    		
    	}
    	
    	if(LoadDataConstant.FAKULTI.equals(kodSheet) && urutan==2){
    		final int rowsCount = sheets.getLastRowNum();
        	
            for (int s = 0; s <= rowsCount; s++) {

                if (s != 0) {
                    final Row row = sheets.getRow(s);

                    // to ignore last row
                    if (row.getCell(0) == null) {
                        break;
                    }

                    FakultiDataLoaderVO fromCell = new FakultiDataLoaderVO();
                    fromCell = FakultiDataLoaderVO.fromCell(row);
                    
                    Fakulti fakulti = new Fakulti();
                    
                    fakulti.setCreatedBy("SYSTEM");
                    fakulti.setCreatedDate(new Date());
                    fakulti.setLastModifiedBy("SYSTEM");
                    fakulti.setLastModifiedDate(today);
               
                    
                    fakulti.setKodFakulti(fromCell.getKod());
                    fakulti.setNamaFakulti(fromCell.getNama());
                    fakulti.setPerihal(fromCell.getPerihal());
                    fakulti.setAlamat1(fromCell.getAlamat());
                    fakulti.setAlamat2(fromCell.getAlamat2());
                    fakulti.setAlamat3(fromCell.getAlamat3());
                    fakulti.setPoskod(fromCell.getPoskod());
                    
                    SenaraiAhliKumpulan bandar = senaraiAhliKumpulanRepository.findByNama(fromCell.getBandar());
                     fakulti.setBandar(bandar);
                     
                     SenaraiAhliKumpulan negeri = senaraiAhliKumpulanRepository.findByNama(fromCell.getNegeri());
                     fakulti.setNegeri(negeri);
                     
                     
                     fakultiRepository.save(fakulti);

                }
            }
    		
    	}
    	
    	if(LoadDataConstant.PUSAT_PENGAJIAN.equals(kodSheet) && urutan==3){
    		final int rowsCount = sheets.getLastRowNum();
        	
            for (int s = 0; s <= rowsCount; s++) {

                if (s != 0) {
                    final Row row = sheets.getRow(s);

                    // to ignore last row
                    if (row.getCell(0) == null) {
                        break;
                    }

                   PusatPengajianDataLoaderVO fromCell = new PusatPengajianDataLoaderVO();
                   fromCell = PusatPengajianDataLoaderVO.fromCell(row);
                   
                   
                   PusatPengajian pusat = new PusatPengajian();
                   
                   pusat.setCreatedBy("SYSTEM");
                   pusat.setCreatedDate(new Date());
                   pusat.setLastModifiedBy("SYSTEM");
                   pusat.setLastModifiedDate(new Date());
                             
                   pusat.setKodPusat(fromCell.getKod());
                   pusat.setNamaPusat(fromCell.getNama());
                   
                   Fakulti fakulti = fakultiRepository.findByKodFakulti(fromCell.getFakulti());
                   pusat.setFakulti(fakulti);
                   
                   pusat.setAktif(convertOracleBooleanDataType(fromCell.getAktif()));
                   
                   pusatPengajianRepository.save(pusat);

                }
            }
    		
    	}
    	
    	if(LoadDataConstant.PENGGUNA.equals(kodSheet) && urutan==4){
    		final int rowsCount = sheets.getLastRowNum();
        	
            for (int s = 0; s <= rowsCount; s++) {

                if (s != 0) {
                    final Row row = sheets.getRow(s);

                    // to ignore last row
                    if (row.getCell(0) == null) {
                        break;
                    }

                   PenggunaDataLoaderVO fromCell = new PenggunaDataLoaderVO();
                   fromCell = PenggunaDataLoaderVO.fromCell(row);
                   
                   
                  Pengguna pengguna = new Pengguna();
                  
                  pengguna.setCreatedBy("SYSTEM");
                  pengguna.setCreatedDate(new Date());
                  pengguna.setLastModifiedBy("SYSTEM");
                  pengguna.setLastModifiedDate(new Date());
            
                  
//                  SenaraiKumpulan skGelaran = senaraiKumpulanRepository.findByKod(SenaraiAhliKumpulanConstent.GELARAN);
                  SenaraiAhliKumpulan gelaran = senaraiAhliKumpulanRepository.findByKod(fromCell.getGelaran());
                  pengguna.setGelaran(gelaran);
                  
                  pengguna.setNamaPertama(fromCell.getNamaPertama());
                  pengguna.setNamaAkhir(fromCell.getNamaAkhir());
                  pengguna.setNoStaff(fromCell.getNoStaff());
                  pengguna.setEmail(fromCell.getEmail());
                  pengguna.setAktif(convertOracleBooleanDataType(fromCell.getAktif()));
                  pengguna.setNoKadPengenalan(fromCell.getNoKadPengenalan());
                  pengguna.setKataLaluan(fromCell.getKataLaluan());
                  
//                  SenaraiKumpulan skPosition = senaraiKumpulanRepository.findByKod(SenaraiAhliKumpulanConstent.POSITION);
                  SenaraiAhliKumpulan jawatan = senaraiAhliKumpulanRepository.findByKod(fromCell.getJawatan());
                
                  pengguna.setJawatan(jawatan);
                  
//                  SenaraiKumpulan skRole = senaraiKumpulanRepository.findByKod(SenaraiAhliKumpulanConstent.ROLE);
                  SenaraiAhliKumpulan role = senaraiAhliKumpulanRepository.findByNama(fromCell.getRole());
              
                  pengguna.setRole(role);
                  
                  PusatPengajian pusat = pusatPengajianRepository.findByKodPusat(fromCell.getPusatPengajian());
                  pengguna.setPusatPengajian(pusat);
                  
//                  SenaraiKumpulan skJantina = senaraiKumpulanRepository.findByKod(SenaraiAhliKumpulanConstent.JANTINA);
                  SenaraiAhliKumpulan jantina = senaraiAhliKumpulanRepository.findByNama(fromCell.getJantina());
                 
                  pengguna.setJantina(jantina);
                  
//                  SenaraiKumpulan skBlok = senaraiKumpulanRepository.findByKod(SenaraiAhliKumpulanConstent.BLOK_BANGUNAN);
                  SenaraiAhliKumpulan blok = senaraiAhliKumpulanRepository.findByNama(fromCell.getBlokBangunan());
                  pengguna.setBlok(blok);
                  pengguna.setNoBilik(fromCell.getNoBilik());
                  
                  penggunaRepository.save(pengguna);
                  
                  
                }
            }
    		
    	}
    	
    	if(LoadDataConstant.SESI_AKADEMIK.equals(kodSheet) && urutan==5){
    		final int rowsCount = sheets.getLastRowNum();
        	
            for (int s = 0; s <= rowsCount; s++) {

                if (s != 0) {
                    final Row row = sheets.getRow(s);

                    // to ignore last row
                    if (row.getCell(0) == null) {
                        break;
                    }

                   KalendarSesiAkademikDataLoaderVO fromCell = new KalendarSesiAkademikDataLoaderVO();
                   fromCell = KalendarSesiAkademikDataLoaderVO.fromCell(row);
                   
                   KalendarSesiAkedemik sesi = new KalendarSesiAkedemik();
                   
                   sesi.setCreatedBy("SYSTEM");
                   sesi.setCreatedDate(new Date());
                   sesi.setLastModifiedBy("SYSTEM");
                   sesi.setLastModifiedDate(new Date());
                   sesi.setAktif(convertOracleBooleanDataType(fromCell.getAktif()));
                   
                   sesi.setSesiAkademik(fromCell.getSesiAkademik());
                   
                   
                   sesi.setTarikhMulaSesi(convertStringtoDateFormatted(fromCell.getTarikhMulaSesi()));
                   
                   sesi.setTarikhTamat(convertStringtoDateFormatted(fromCell.getTarikhTamatSesi()));
                   sesi.setTotalDay(null);
                   
                   kalendarSesiAkademikRepository.save(sesi);

                  
                }
            }
    		
    	}
    	
    	if(LoadDataConstant.SEM.equals(kodSheet) && urutan==6){
    		final int rowsCount = sheets.getLastRowNum();
        	
            for (int s = 0; s <= rowsCount; s++) {

                if (s != 0) {
                    final Row row = sheets.getRow(s);

                    // to ignore last row
                    if (row.getCell(0) == null) {
                        break;
                    }

                   SemesterDataLoaderVO fromCell = new SemesterDataLoaderVO();
                   fromCell = SemesterDataLoaderVO.fromCell(row);
                   
                   Semester sem = new Semester();
                   
                   sem.setCreatedBy("SYSTEM");
                   sem.setCreatedDate(new Date());
                   sem.setLastModifiedBy("SYSTEM");
                   sem.setLastModifiedDate(new Date());
              
                   
                   sem.setKod(fromCell.getKod());
                   sem.setNameSemester(fromCell.getNama());
                   
                   semesterRepository.save(sem);
                                   
                }
            }
    		
    	}
    	
      	if(LoadDataConstant.JADUAL.equals(kodSheet) && urutan==7){
    		final int rowsCount = sheets.getLastRowNum();
        	
            for (int s = 0; s <= rowsCount; s++) {

                if (s != 0) {
                    final Row row = sheets.getRow(s);

                    // to ignore last row
                    if (row.getCell(0) == null) {
                        break;
                    }

                   JadualSemesterDataLoaderVO fromCell = new JadualSemesterDataLoaderVO();
                   fromCell = JadualSemesterDataLoaderVO.fromCell(row);
                   
                   JadualSemester jadual = new JadualSemester();
                   
                   jadual.setCreatedBy("SYSTEM");
                  jadual.setCreatedDate(new Date());
                   jadual.setLastModifiedBy("SYSTEM");
                   jadual.setLastModifiedDate(new Date());
                   jadual.setAktif(convertOracleBooleanDataType(fromCell.getAktif()));
                   
                   Fakulti fakulti = fakultiRepository.findByKodFakulti(fromCell.getFakulti());
                   jadual.setFakulti(fakulti);
                   
                   Semester semester = semesterRepository.findByKod(fromCell.getSemester());
                   jadual.setSemester(semester);
                   
                   KalendarSesiAkedemik kalendarSesiAkedemik = kalendarSesiAkademikRepository.findBySesiAkademik(fromCell.getSesiAkademik());
                   jadual.setKalendarSesiAkedemik(kalendarSesiAkedemik);
                   
                   SenaraiAhliKumpulan jenisPerihal = senaraiAhliKumpulanRepository.findByKod(fromCell.getJadualPerihal());
                   jadual.setJenisPerihal(jenisPerihal);
                   
                   jadual.setMinggu(null);
             
                   
                   jadualSemesterRepository.save(jadual);
                                   
                }
            }
    		
    	}
      	
      	if(LoadDataConstant.JABATAN.equals(kodSheet) && urutan==8){
    		final int rowsCount = sheets.getLastRowNum();
        	
            for (int s = 0; s <= rowsCount; s++) {

                if (s != 0) {
                    final Row row = sheets.getRow(s);

                    // to ignore last row
                    if (row.getCell(0) == null) {
                        break;
                    }

                   JabatanDataLoaderVO fromCell = new JabatanDataLoaderVO();
                   fromCell = JabatanDataLoaderVO.fromCell(row);
                   
                   Jabatan jabatan = new Jabatan();
                   
                   jabatan.setCreatedBy("SYSTEM");
                   jabatan.setCreatedDate(new Date());
                   jabatan.setLastModifiedBy("SYSTEM");
                   jabatan.setLastModifiedDate(new Date());
                   jabatan.setAktif(convertOracleBooleanDataType(fromCell.getAktif()));
                   
                   jabatan.setKodJabatan(fromCell.getKod());
                   jabatan.setNamaJabatan(fromCell.getNama());
                   jabatan.setPerihal(fromCell.getPerihal());
                   
                   PusatPengajian pusatPengajian = pusatPengajianRepository.findByKodPusat(fromCell.getPusatPengajian());
                   jabatan.setPusatPengajian(pusatPengajian);
                   
                   SenaraiAhliKumpulan blok = senaraiAhliKumpulanRepository.findByNama(fromCell.getBlok());
                   jabatan.setBlok(blok);
                   
                   
                   jabatanRepository.save(jabatan);
                                  
                }
            }
    		
    	}
      	
      	if(LoadDataConstant.SUBJEK_LIST.equals(kodSheet) && urutan==9){
    		final int rowsCount = sheets.getLastRowNum();
    	
            for (int s = 0; s <= rowsCount; s++) {

                if (s != 0) {
                    final Row row = sheets.getRow(s);

                    // to ignore last row
                    if (row.getCell(0) == null) {
                        break;
                    }

                   SubjekDataLoaderVO fromCell = new SubjekDataLoaderVO();
                   fromCell = SubjekDataLoaderVO.fromCell(row);
                   
                   Subjek subjek = new Subjek();
                   subjek.setCreatedBy("SYSTEM");
                   subjek.setCreatedDate(new Date());
                   subjek.setLastModifiedBy("SYSTEM");
                   subjek.setLastModifiedDate(new Date());
                   subjek.setAktif(convertOracleBooleanDataType(fromCell.getAktif()));
                   
                   Jabatan jabatan = jabatanRepository.findByKodJabatan(fromCell.getJabatan());
                   subjek.setJabatan(jabatan);
                   
                   subjek.setKodSubjek(fromCell.getKod());
                   
                   SenaraiAhliKumpulan jenisSubjek = senaraiAhliKumpulanRepository.findByNama(fromCell.getJenisSubjek());
                   subjek.setJenisSubjek(jenisSubjek);
                   subjek.setNamaSubjekEng(fromCell.getNamaEng());
                   subjek.setNamaSubjekBM(fromCell.getNamaBM());
                   subjek.setGlobalSubjek(convertOracleBooleanDataType(fromCell.getGlobalSubjek()));
                   
                   subjekRepository.save(subjek);
                   
             
                   
                                
                }
            }
    		
    	}
      	
     	if(LoadDataConstant.SUBJEK_TERLIBAT.equals(kodSheet) && urutan==10){
    		final int rowsCount = sheets.getLastRowNum();
    		Map<String, Subjek> subjekMap = findSubjekBySubjekKod();
            for (int s = 0; s <= rowsCount; s++) {

                if (s != 0) {
                    final Row row = sheets.getRow(s);

                    // to ignore last row
                    if (row.getCell(0) == null) {
                        break;
                    }

                   SubjekTerlibatDataLoaderVO fromCell = new SubjekTerlibatDataLoaderVO();
                   fromCell = SubjekTerlibatDataLoaderVO.fromCell(row);
                   
                   SubjekTerlibat subjekTelibat = new SubjekTerlibat();
                   subjekTelibat.setCreatedBy("SYSTEM");
                   subjekTelibat.setCreatedDate(new Date());
                   subjekTelibat.setLastModifiedBy("SYSTEM");
                   subjekTelibat.setLastModifiedDate(new Date());
                   subjekTelibat.setAktif(convertOracleBooleanDataType(fromCell.getAktif()));
                   
                   Pengguna pengguna = penggunaRepository.findByEmail(fromCell.getCondinator());
                   subjekTelibat.setPengguna(pengguna);
                   
                   Subjek subjek = subjekMap.get(fromCell.getIdSubjek());
                   subjekTelibat.setSubjek(subjek);
                   
                   SenaraiAhliKumpulan tugasan = senaraiAhliKumpulanRepository.findByNama(fromCell.getTugasan());
                   subjekTelibat.setTugasan(tugasan);
                   
                   KalendarSesiAkedemik sesiAkademik = kalendarSesiAkademikRepository.findBySesiAkademik(fromCell.getSesiAkademik());
                   subjekTelibat.setSesiAkademik(sesiAkademik);
                   
                   Semester semester = semesterRepository.findByKod(fromCell.getSemester());
                   subjekTelibat.setSemester(semester);
            
                   subjekTerlibatRepository.save(subjekTelibat);
                   
                                
                }
            }
    		
    	}
    	
    }
    
    //Mapping
    @Transactional(readOnly = true)
    protected Map<String, SenaraiKumpulan> findSenaraiKumpulanBySenaraiKumpulanKod() {
        final Map<String, SenaraiKumpulan> SenaraiKumpulanMap = new HashMap<>();
        final List<SenaraiKumpulan> SenaraiKumpulanList = senaraiKumpulanRepository.findAll();
        for (final SenaraiKumpulan sk : SenaraiKumpulanList) {
        	SenaraiKumpulanMap.put(sk.getKod(), sk);
        }
        return SenaraiKumpulanMap;
    }
	
    
    //Mapping
    @Transactional(readOnly = true)
    protected Map<String, Subjek> findSubjekBySubjekKod() {
        final Map<String, Subjek> subjekMap = new HashMap<>();
        final List<Subjek> SubjekList = subjekRepository.findAll();
        for (final Subjek sk : SubjekList) {
        	Jabatan jabatan = jabatanRepository.findById(sk.getJabatan().getId());
        	subjekMap.put(jabatan.getKodJabatan() + ""+ sk.getKodSubjek(), sk);
        }
        return subjekMap;
    }
}
