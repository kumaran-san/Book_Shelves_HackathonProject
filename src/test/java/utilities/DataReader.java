package utilities;


//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.ss.util.NumberToTextConverter;
//
//public class ExcelReader {

//	public List<Map<String, String>> getData(String excelFilePath, String sheetName)
//			throws InvalidFormatException, IOException {
//		Sheet sheet = getSheetByName(excelFilePath, sheetName);
//		return readSheet(sheet);
//	}
//
//	public List<Map<String, String>> getData(String excelFilePath, int sheetNumber)
//			throws InvalidFormatException, IOException {
//		Sheet sheet = getSheetByIndex(excelFilePath, sheetNumber);
//		return readSheet(sheet);
//	}
//
//	private Sheet getSheetByName(String excelFilePath, String sheetName) throws IOException, InvalidFormatException {
//		Sheet sheet = getWorkBook(excelFilePath).getSheet(sheetName);
//		return sheet;
//	}
//
//	private Sheet getSheetByIndex(String excelFilePath, int sheetNumber) throws IOException, InvalidFormatException {
//		Sheet sheet = getWorkBook(excelFilePath).getSheetAt(sheetNumber);
//		return sheet;
//	}
//
//	private Workbook getWorkBook(String excelFilePath) throws IOException, InvalidFormatException {
//		return WorkbookFactory.create(new File(excelFilePath));
//	}
//
//	private List<Map<String, String>> readSheet(Sheet sheet) {
//		Row row;
//		int totalRow = sheet.getPhysicalNumberOfRows();
//		List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
//		int headerRowNumber = getHeaderRowNumber(sheet);
//		if (headerRowNumber != -1) {
//			int totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();
//			int setCurrentRow = 1;
//			for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
//				row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
//				LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
//				for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
//					columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
//				}
//				excelRows.add(columnMapdata);
//			}
//		}
//		return excelRows;
//	}
//
//	private int getHeaderRowNumber(Sheet sheet) {
//		Row row;
//		int totalRow = sheet.getLastRowNum();
//		for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) {
//			row = getRow(sheet, currentRow);
//			if (row != null) {
//				int totalColumn = row.getLastCellNum();
//				for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
//					Cell cell;
//					cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//					if (cell.getCellType() == CellType.STRING) {
//						return row.getRowNum();
//
//					} else if (cell.getCellType() == CellType.NUMERIC) {
//						return row.getRowNum();
//
//					} else if (cell.getCellType() == CellType.BOOLEAN) {
//						return row.getRowNum();
//					} else if (cell.getCellType() == CellType.ERROR) {
//						return row.getRowNum();
//					}
//				}
//			}
//		}
//		return (-1);
//	}
//
//	private Row getRow(Sheet sheet, int rowNumber) {
//		return sheet.getRow(rowNumber);
//	}
//
//	private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
//		LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
//		Cell cell;
//		if (row == null) {
//			if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
//					.getCellType() != CellType.BLANK) {
//				String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
//						.getStringCellValue();
//				columnMapdata.put(columnHeaderName, "");
//			}
//		} else {
//			cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//			if (cell.getCellType() == CellType.STRING) {
//				if (sheet.getRow(sheet.getFirstRowNum())
//						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
//						.getCellType() != CellType.BLANK) {
//					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
//							.getStringCellValue();
//					columnMapdata.put(columnHeaderName, cell.getStringCellValue());
//				}
//			} else if (cell.getCellType() == CellType.NUMERIC) {
//				if (sheet.getRow(sheet.getFirstRowNum())
//						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
//						.getCellType() != CellType.BLANK) {
//					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
//							.getStringCellValue();
//					columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
//				}
//			} else if (cell.getCellType() == CellType.BLANK) {
//				if (sheet.getRow(sheet.getFirstRowNum())
//						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
//						.getCellType() != CellType.BLANK) {
//					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
//							.getStringCellValue();
//					columnMapdata.put(columnHeaderName, "");
//				}
//			} else if (cell.getCellType() == CellType.BOOLEAN) {
//				if (sheet.getRow(sheet.getFirstRowNum())
//						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
//						.getCellType() != CellType.BLANK) {
//					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
//							.getStringCellValue();
//					columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
//				}
//			} else if (cell.getCellType() == CellType.ERROR) {
//				if (sheet.getRow(sheet.getFirstRowNum())
//						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
//						.getCellType() != CellType.BLANK) {
//					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
//							.getStringCellValue();
//					columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
//				}
//			}
//		}
//		return columnMapdata;
//	}
	

	import java.io.FileInputStream;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;

	import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class DataReader {
		
		public static HashMap<String, String> storeValues = new HashMap<String, String>();

		public static List<HashMap<String, String>> data(String filepath, String sheetName) {
			
			List<HashMap<String, String>> mydata = new ArrayList<>();
			
			try {
				FileInputStream fs = new FileInputStream(filepath);
				XSSFWorkbook workbook = new XSSFWorkbook(fs);
				XSSFSheet sheet = workbook.getSheet(sheetName);
				Row HeaderRow = sheet.getRow(0);
				DataFormatter formatter = new DataFormatter();
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) 
					{
					Row currentRow = sheet.getRow(i);
					HashMap<String, String> currentHash = new HashMap<String, String>();
					for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) 
						{
						Cell currentCell = currentRow.getCell(j);

						switch(currentCell.getCellType()) {
						case STRING:
							currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
							break;
						case NUMERIC:
							currentHash.put(HeaderRow.getCell(j).getStringCellValue(), formatter.formatCellValue(currentCell) );
							break;
							
						default:
							break;
						}
						
						}
					mydata.add(currentHash);
					System.out.println(currentHash);
					}
				workbook.close();
				fs.close();
				} catch (Exception e) {
				e.printStackTrace();
			}
			return mydata;
		}
	}



	

