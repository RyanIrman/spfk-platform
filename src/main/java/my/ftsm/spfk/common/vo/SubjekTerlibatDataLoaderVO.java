package my.ftsm.spfk.common.vo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import lombok.Data;

@Data
public class SubjekTerlibatDataLoaderVO {
	
	private String condinator;
	private String idSubjek;
	private String tugasan;
	private String aktif;
	private String sesiAkademik;
	private String semester;
	private String dateCreate;
	private String createBy;
	

	
	
	public static SubjekTerlibatDataLoaderVO fromCell(Row row) {
		SubjekTerlibatDataLoaderVO result = new SubjekTerlibatDataLoaderVO();

        result.condinator = getStringOfCell(row.getCell(0));
        result.idSubjek= getStringOfCell(row.getCell(1));
        result.tugasan = getStringOfCell(row.getCell(2));
        result.aktif = getStringOfCell(row.getCell(3));
        result.sesiAkademik = getStringOfCell(row.getCell(4));
        result.semester = getStringOfCell(row.getCell(5));
        result.dateCreate = getStringOfCell(row.getCell(6)); 
        result.createBy = getStringOfCell(row.getCell(7));

        return result;
    }

    @Override
    public String toString() {
        return condinator + "\t" + idSubjek + "\t" + tugasan  + "\t" + aktif + "\t" + sesiAkademik + "\t" + semester + "\t" + dateCreate + "\t" + createBy;
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
