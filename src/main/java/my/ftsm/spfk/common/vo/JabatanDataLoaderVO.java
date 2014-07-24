package my.ftsm.spfk.common.vo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import lombok.Data;

@Data
public class JabatanDataLoaderVO {
	
	private String kod;
	private String nama;
	private String perihal;
	private String pusatPengajian;
	private String aktif;
	private String blok;


	
	
	public static JabatanDataLoaderVO fromCell(Row row) {
		JabatanDataLoaderVO result = new JabatanDataLoaderVO();

        result.kod = getStringOfCell(row.getCell(0));
        result.nama = getStringOfCell(row.getCell(1));
        result.perihal = getStringOfCell(row.getCell(2));
        result.pusatPengajian = getStringOfCell(row.getCell(3));
        result.blok = getStringOfCell(row.getCell(4));


        return result;
    }

    @Override
    public String toString() {
        return kod + "\t" + nama + "\t" + perihal  + "\t" + pusatPengajian + "\t" + blok ;
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
