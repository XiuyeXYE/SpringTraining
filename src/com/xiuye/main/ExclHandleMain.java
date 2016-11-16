package com.xiuye.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.xiuye.excel.ExcelHandler;
import com.xiuye.excel.JDBCHandler;

public class ExclHandleMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, ParseException {

		// String path = "D:\\正在学习中\\app\\台州公积金APP项目2016年人员情况表.xls";
		//
		// List<List<String>> result = ExcelHandler.HSSFReadFile(path);
		//
		// for(List<String> l : result){
		// for(String s : l){
		// System.out.print(s+"\t");
		// }
		// System.out.println();
		// }
		// String path = "D:\\正在学习中\\app\\url_list_morgan_updated.xlsx";
		// path = "C:\\Users\\Administrator\\Desktop\\HOUSINGFUND_PERSON.csv";

		/*
		 * List<List<String>> result = ExcelHandler.XSSFReadFile(path);
		 *
		 * for(List<String> l : result){ for(String s : l){
		 * System.out.print(s+"\t"); } System.out.println(); }
		 *
		 * }
		 */
		// String path = "D:\\正在学习中\\app\\归集.csv";
		//
		// List<List<String>> result = ExcelHandler.readCSVFile(path);
		//
		// for (List<String> l : result) {
		// for (String s : l) {
		// System.out.print(s + "\t");
		// }
		// System.out.println();
		// }
		//readCsv();
		DBImportAccountData();


	}

	private static double toDouble(String string) {
		return Double.parseDouble(string);
	}

	public static int toInt(String s) {
		return Integer.parseInt(s);
	}

	public static void readCsv() throws IOException{
		String path = "D:\\正在学习中\\app\\归集.csv";
		Map<String, Object> map = ExcelHandler.readCsv(path);
		String[] headers = (String[]) map.get("headers");
		for (int i = 0; i < headers.length; i++) {
			System.out.print(headers[i] + "\t");
		}
		System.out.println();
		List<String[]> result = (List<String[]>) map.get("data");
		for (String[] l : result) {
			for (String s : l) {

				System.out.print(s + "\t");
				if(s == null || s.isEmpty()){
					System.out.println("blank");
					break;
				}
			}
			System.out.println();
		}
	}

	public static void DBImportAccountData() throws ClassNotFoundException, SQLException, IOException, ParseException {
		String sql = "insert into HOUSINGFUND_PERSON(CENTNO,GSBM,GRZH,GRXM,SFZH,HJYE,YJJZE,GRZTMC,JZNY,YHMC,DWMC) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
//		String url = "jdbc:oracle:thin:@139.129.217.124:1521:ora11g";
		String url = "jdbc:oracle:thin:@42.123.87.52:1521:orcl";

//		PreparedStatement ps = JDBCHandler.preparedStatement(sql, "oracle.jdbc.OracleDriver", url, "TZAPP", "TZAPP");
		PreparedStatement ps = JDBCHandler.preparedStatement(sql, "oracle.jdbc.OracleDriver", url, "tzapp", "123456");

		DateFormat format = new SimpleDateFormat("yyyyMM");

		String path = "D:\\正在学习中\\app\\归集.csv";
		Map<String, Object> map = ExcelHandler.readCsv(path);
		List<String[]> result = (List<String[]>) map.get("data");

		int i = 0;

		System.out.println("insert data begins!");

		for (String[] s : result) {

			ps.setString(1, s[0]);
			ps.setString(2, s[1]);
			ps.setString(3, s[2]);
			ps.setString(4, s[3]);
			ps.setString(5, s[4]);
			ps.setDouble(6, toDouble(s[5]));
			ps.setDouble(7, toDouble(s[6].isEmpty()?"0":s[6]));
			ps.setString(8, s[7]);
			if(s[8] == null || s[8].isEmpty()){
				ps.setDate(9, null);
			}
			else{
				Date d = new Date(format.parse(s[8]).getTime());
				ps.setDate(9, d);
			}


			ps.setString(10, s[9]);
			ps.setString(11, s[10]);

			ps.addBatch();
			if (i % 10000 == 0) {
				int[] r = ps.executeBatch();
				System.out.println(r.length == 10000 ? "Adding 10000 pieces of data successfully" : "failed insert data by 10000!");
			}
			i++;
		}
		int[] r = ps.executeBatch();
		System.out.println("Finally adding " + r.length + " pieces of data successfully");
	}

	public static void DBImportLoanData() throws ClassNotFoundException, SQLException, IOException, ParseException {
		String sql = "insert into HOUSINGFUND_LOAN(JKR,SFZH,HTH,DKYE,BQHJE,BQQC,XCBJRQ,YHMC,YHDM,FFE,DKQX,ZTMC,HKFSMC,YQQC,YQBX) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String url = "jdbc:oracle:thin:@42.123.87.52:1521:orcl";

		PreparedStatement ps = JDBCHandler.preparedStatement(sql, "oracle.jdbc.OracleDriver", url, "tzapp", "123456");

		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

		String path = "D:\\正在学习中\\app\\贷款.csv";

		List<List<String>> result = ExcelHandler.readCSVFile(path);

		// for (List<String> l : result) {
		// for (String s : l) {
		// System.out.print(s + "\t");
		// }
		// System.out.println();
		// }

		int i = 0;

		System.out.println("insert data begins!");

		for (List<String> l : result) {
			if (i == 0) {
				i++;
				continue;

			}
			ps.setString(1, l.get(0));
			ps.setString(2, l.get(1));
			ps.setString(3, l.get(2));
			ps.setDouble(4, toDouble(l.get(3)));
			ps.setDouble(5, toDouble(l.get(4)));
			ps.setInt(6, toInt(l.get(5)));
			ps.setDate(7, new Date(format.parse(l.get(6)).getTime()));
			ps.setString(8, l.get(7));
			ps.setString(9, l.get(8));
			ps.setDouble(10, toDouble(l.get(9)));
			ps.setInt(11, toInt(l.get(10)));
			ps.setString(12, l.get(11));
			ps.setString(13, l.get(12));
			ps.setString(14, l.get(13));
			ps.setString(15, "空");
			ps.addBatch();
			if (i % 1000 == 0) {
				int[] r = ps.executeBatch();
				System.out.println(r.length == 1000 ? "success 100" : "failed 100!");
			}
			i++;
		}
		int[] r = ps.executeBatch();
		System.out.println("success ended := " + r.length);

	}

}
