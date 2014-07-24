package my.ftsm.spfk.common.vo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import lombok.Data;

@Data
public class SubjekDataLoaderVO {
	
	private String jabatan;
	private String kod;
	private String namaEng;
	private String namaBM;
	private String jenisSubjek;
	private String globalSubjek;
	private String aktif;
	private String tempId;
	

	
	
	public static SubjekDataLoaderVO fromCell(Row row) {
		SubjekDataLoaderVO result = new SubjekDataLoaderVO();

        result.jabatan = getStringOfCell(row.getCell(0));
        result.kod = getStringOfCell(row.getCell(1));
        result.namaEng = getStringOfCell(row.getCell(2));
        result.namaBM = getStringOfCell(row.getCell(3));
        result.jenisSubjek = getStringOfCell(row.getCell(4));
        result.globalSubjek = getStringOfCell(row.getCell(5));
        result.aktif = getStringOfCell(row.getCell(6)); 
        result.tempId = getStringOfCell(row.getCell(7)); 

        return result;
    }

    @Override
    public String toString() {
        return jabatan + "\t" + kod + "\t" + namaEng  + "\t" + namaBM + "\t" + jenisSubjek + "\t" + globalSubjek + "\t" + aktif  + "\t" + tempId ;
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
