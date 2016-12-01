package com.xiuye.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import com.xiuye.util.Logger;

public class HtmlHandler {

	private static Logger log = Logger.getInstance();

	public static boolean getArticleTitle(String inputFile, String outputFile) throws IOException {

		log.debug("beginning...");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));

		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
		PrintWriter pw = new PrintWriter(writer);
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.contains("<span class=\"link_title\">")) {
				// log.debug(line);
				break;
			}
		}
		while ((line = reader.readLine()) != null) {

			if (line.contains("</span>")) {

				// log.debug(line);
				break;

			}
			pw.println("<item>" + line.trim() + "-----" + inputFile.replace('\\', '/') + "</item>");
			log.debug(line);
		}

		pw.flush();
		pw.close();
		reader.close();
		return true;
	}

	public static boolean getArticleContent(String inputFileName, String outputFileName) throws IOException {

		log.debug("beginning...");

		BufferedReader reader = new BufferedReader(new FileReader(inputFileName));

		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
		PrintWriter pw = new PrintWriter(writer);
		String line = null;
		// title
		while ((line = reader.readLine()) != null) {
			if (line.contains("<span class=\"link_title\">")) {
				// pw.println(line);
				pw.println("<style type=\"text/css\" >" + "div{" + "color:#008B8B;" + "}" + "</style>");

				log.debug(line);
				break;
			}
		}
		while ((line = reader.readLine()) != null) {

			if (line.contains("</span>")) {

				// pw.println(line);
				log.debug(line);
				break;

			}
			pw.println("<h1 style='color:green;'>" + line + "</h1>");
		}

		// content
		while ((line = reader.readLine()) != null) {

			if (line.contains("<div id=\"article_content\" class=\"article_content\">")) {
				log.debug("I found it <div>");
				pw.println(line);
				break;
			}
		}
		while ((line = reader.readLine()) != null) {

			if (line.contains("</div>")) {
				log.debug("I found it </div>");
				pw.println(line);
				break;
			}
			pw.println(line + "<br/>");

			log.debug("I read a string := " + line);

		}
		pw.flush();
		pw.close();
		reader.close();
		return true;
	}

	public static boolean handlingHtml(String filePathName) throws SAXException, DocumentException {

		System.out.println("Beginning...");

		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File(filePathName));
		Element root = doc.getRootElement();
		System.out.println("root element name := " + root.getName());
		System.out.println("root element attributes' number := " + root.attributeCount());
		List<String> attributes = root.attributes();
		for (String attribute : attributes) {
			System.out.println("root attribute := " + attribute);
		}
		// System.out.println("root element name := " + root.getName());
		// System.out.println("root element name := " + root.getName());
		// System.out.println("root element name := " + root.getName());
		return true;
	}

}
