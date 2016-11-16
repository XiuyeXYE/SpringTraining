package com.xiuye.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.csvreader.CsvReader;

/**
 * HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls
 * XSSFWorkbook:是操作Excel2007的版本，扩展名是.xlsx
 *
 * @author Administrator
 *
 */
public class ExcelHandler {

	public static List<List<String>> HSSFReadFile(String path) throws IOException {
		FileInputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkBook = new HSSFWorkbook(is);
		List<List<String>> result = new ArrayList<>();

		for (int numSheet = 0; numSheet < hssfWorkBook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkBook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				int minColIx = hssfRow.getFirstCellNum();
				int maxColIx = hssfRow.getLastCellNum();
				List<String> rowList = new ArrayList<>();
				for (int colIx = minColIx; colIx < maxColIx; colIx++) {
					HSSFCell cell = hssfRow.getCell(colIx);
					if (cell == null) {
						continue;
					}
					rowList.add(cell.toString());
				}
				result.add(rowList);
			}
		}
		return result;
	}

	public static List<List<String>> XSSFReadFile(String path) throws IOException {
		FileInputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkBook = new XSSFWorkbook(is);
		List<List<String>> result = new ArrayList<>();

		for (int numSheet = 0; numSheet < xssfWorkBook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkBook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				int minColIx = xssfRow.getFirstCellNum();
				int maxColIx = xssfRow.getLastCellNum();
				List<String> rowList = new ArrayList<>();
				for (int colIx = minColIx; colIx < maxColIx; colIx++) {
					XSSFCell cell = xssfRow.getCell(colIx);
					if (cell == null) {
						continue;
					}
					rowList.add(cell.toString());
				}
				result.add(rowList);
			}
		}
		return result;
	}

	public static List<List<String>> readCSVFile(String path) throws IOException {

		LineNumberReader lnr = new LineNumberReader(new FileReader(path));
		List<List<String>> result = new ArrayList<>();
		String line;
		while ((line = lnr.readLine()) != null) {
			Pattern p = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
			Matcher m = p.matcher(line);
			List<String> cells = new ArrayList<>();

			while (m.find()) {
				String str = m.group();
				str = str.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
				str = str.replaceAll("(?sm)(\"(\"))", "$2");
				cells.add(str);
				System.out.print(str + "\t");
			}
			result.add(cells);
			System.out.println();
		}

		return result;
	}

	public static List<List<String>> readCSVFile(String path, String encoding) throws IOException {

		LineNumberReader lnr = new LineNumberReader(new InputStreamReader(new FileInputStream(path), encoding));
		List<List<String>> result = new ArrayList<>();
		String line;
		while ((line = lnr.readLine()) != null) {
			Pattern p = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
			Matcher m = p.matcher(line);
			List<String> cells = new ArrayList<>();

			while (m.find()) {
				String str = m.group();
				str = str.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
				str = str.replaceAll("(?sm)(\"(\"))", "$2");
				cells.add(str);
			}
			result.add(cells);
		}

		return result;
	}

	public static Map<String,Object> readCsv(String path) throws IOException {
		CsvReader reader = new CsvReader(path);
		reader.readHeaders();
		String[] headers = reader.getHeaders();

		List<String[]> list = new ArrayList<>();
		while (reader.readRecord()) {
			list.add(reader.getValues());
		}
		Map<String,Object> map = new HashMap<>();
		map.put("headers", headers);
		map.put("data", list);
		return map;



	}

	public static void readCsv(String path, char delimiter, String encoding) throws IOException {

		CsvReader reader = new CsvReader(path, delimiter, Charset.forName(encoding));
		reader.readHeaders();
		String[] headers = reader.getHeaders();
		List<String[]> list = new ArrayList<>();
		while (reader.readRecord()) {
			list.add(reader.getValues());
		}
		String[][] datas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = list.get(i);
		}
		for (int i = 0; i < headers.length; i++) {
			System.out.print(headers[i] + "\t");
		}
		System.out.println("");

		for (int i = 0; i < datas.length; i++) {
			Object[] data = datas[i]; // 取出一组数据
			for (int j = 0; j < data.length; j++) {
				Object cell = data[j];
				System.out.print(cell + "\t");
			}
			System.out.println("");
		}

	}

}
