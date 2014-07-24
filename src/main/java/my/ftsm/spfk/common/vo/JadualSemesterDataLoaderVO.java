package my.ftsm.spfk.common.vo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import lombok.Data;

@Data
public class JadualSemesterDataLoaderVO {
	
	private String sesiAkademik;
	private String semester;
	private String jadualPerihal;
	private String fakulti;
	private String jumlahMinggu;
	private String aktif;

	

	
	
	public static JadualSemesterDataLoaderVO fromCell(Row row) {
		JadualSemesterDataLoaderVO result = new JadualSemesterDataLoaderVO();

        result.sesiAkademik = getStringOfCell(row.getCell(0));
        result.semester = getStringOfCell(row.getCell(1));
        result.jadualPerihal = getStringOfCell(row.getCell(2));
        result.fakulti = getStringOfCell(row.getCell(3));
        result.jumlahMinggu = getStringOfCell(row.getCell(4));
        result.aktif = getStringOfCell(row.getCell(5));
   
     

        return result;
    }

    @Override
    public String toString() {
        return sesiAkademik + "\t" + semester + "\t" + jadualPerihal  + "\t" + fakulti + "\t" + jumlahMinggu + "\t" + aktif ;
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
