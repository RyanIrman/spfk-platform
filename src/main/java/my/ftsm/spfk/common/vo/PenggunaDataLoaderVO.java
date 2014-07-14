package my.ftsm.spfk.common.vo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import lombok.Data;

@Data
public class PenggunaDataLoaderVO {
	
	private String gelaran;
	private String namaPertama;
	private String noStaff;
	private String email;
	private String aktif;
	private String noKadPengenalan;
	private String kataLaluan;
	private String jawatan;
	private String role;
	private String pusatPengajian;
	private String jantina;
	private String blokBangunan;
	private String noBilik;
	private String namaAkhir;
	
	

	
	
	public static PenggunaDataLoaderVO fromCell(Row row) {
		PenggunaDataLoaderVO result = new PenggunaDataLoaderVO();

        result.gelaran = getStringOfCell(row.getCell(0));
        result.namaPertama = getStringOfCell(row.getCell(1));
        result.namaAkhir = getStringOfCell(row.getCell(2));
        result.noStaff = getStringOfCell(row.getCell(3));
        result.email = getStringOfCell(row.getCell(4));
        result.aktif = getStringOfCell(row.getCell(5));
        result.noKadPengenalan = getStringOfCell(row.getCell(6)); 
        result.kataLaluan = getStringOfCell(row.getCell(7));
        result.jawatan = getStringOfCell(row.getCell(8));
        result.role = getStringOfCell(row.getCell(9));
        result.pusatPengajian = getStringOfCell(row.getCell(10));
        result.jantina = getStringOfCell(row.getCell(11));
        result.blokBangunan = getStringOfCell(row.getCell(12));
        result.noBilik = getStringOfCell(row.getCell(13));

        return result;
    }

    @Override
    public String toString() {
        return gelaran + "\t" + namaPertama + "\t" + namaAkhir  + "\t" + noStaff + "\t" + email + "\t" + aktif + "\t" + 
        noKadPengenalan + "\t" + kataLaluan+ "\t" + jawatan + "\t" + role + "\t" + pusatPengajian  + "\t" + jantina + "\t" + blokBangunan+ "\t" + noBilik;
    }

    private static String getStringOfCell(Cell cell) {
        String value = null;

        if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            value = null;
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            // value = Double.valueOf(cell.getNumericCellValue()).toString();
            double d = Double.valueOf(cell.getNumericCellValue());
            int i = (int) d;
            value = Integer.valueOf(i).toString();
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

            value = String.valueOf(cell.getBooleanCellValue());

        } else {
            value = cell.getStringCellValue();
        }
        return value;
    }

	

}
