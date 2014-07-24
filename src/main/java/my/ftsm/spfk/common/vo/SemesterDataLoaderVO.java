package my.ftsm.spfk.common.vo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import lombok.Data;

@Data
public class SemesterDataLoaderVO {
	
	private String kod;
	private String nama;
	

	
	
	public static SemesterDataLoaderVO fromCell(Row row) {
		SemesterDataLoaderVO result = new SemesterDataLoaderVO();

        result.kod = getStringOfCell(row.getCell(0));
        result.nama = getStringOfCell(row.getCell(1));


        return result;
    }

    @Override
    public String toString() {
        return kod + "\t" + nama;
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
