package my.ftsm.spfk.common.vo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import lombok.Data;

@Data
public class MasterDataLoaderVO {
	
	private String kod;
	private String perkara;
	private String aktif;
	private String urutan;

	
	
	public static MasterDataLoaderVO fromCell(Row row) {
		MasterDataLoaderVO result = new MasterDataLoaderVO();

        result.kod = getStringOfCell(row.getCell(0));
        result.perkara = getStringOfCell(row.getCell(1));
        result.aktif = getStringOfCell(row.getCell(2));
        result.urutan = getStringOfCell(row.getCell(3));
              

        return result;
    }

    @Override
    public String toString() {
        return kod + "\t" + perkara + "\t" + aktif  + "\t" + urutan;
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
