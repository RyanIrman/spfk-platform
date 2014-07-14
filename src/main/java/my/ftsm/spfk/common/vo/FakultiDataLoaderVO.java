package my.ftsm.spfk.common.vo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import lombok.Data;

@Data
public class FakultiDataLoaderVO {
	
	private String kod;
	private String nama;
	private String perihal;
	private String alamat;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private String bandar;
	private String negeri;
	

	
	
	public static FakultiDataLoaderVO fromCell(Row row) {
		FakultiDataLoaderVO result = new FakultiDataLoaderVO();

        result.kod = getStringOfCell(row.getCell(0));
        result.nama = getStringOfCell(row.getCell(1));
        result.perihal = getStringOfCell(row.getCell(2));
        result.alamat = getStringOfCell(row.getCell(3));
        result.alamat2 = getStringOfCell(row.getCell(4));
        result.alamat3 = getStringOfCell(row.getCell(5));
        result.poskod = getStringOfCell(row.getCell(6)); 
        result.bandar = getStringOfCell(row.getCell(7));
        result.negeri = getStringOfCell(row.getCell(8));

        return result;
    }

    @Override
    public String toString() {
        return kod + "\t" + nama + "\t" + perihal  + "\t" + alamat + "\t" + alamat2 + "\t" + alamat3 + "\t" + poskod + "\t" + bandar  + "\t" + negeri;
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
