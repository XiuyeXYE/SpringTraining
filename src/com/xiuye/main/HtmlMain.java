package com.xiuye.main;

import java.io.File;
import java.io.IOException;

import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

import com.xiuye.handler.HtmlHandler;
import com.xiuye.util.Logger;

public class HtmlMain {

	private static Logger log = Logger.getInstance();

	public static void main(String[] args) {

		try {

			File article = new File("article");
			File []fs = article.listFiles();
//			for(File f : fs){
//				log.debug("name := " + f.getName());
//				log.debug("path := " + f.getPath());
//				log.debug("canonicalPath := " + f.getCanonicalPath());
//				HtmlHandler.getArticleTitle(f.getPath(),"titles.txt");
//			}
			for(File f : fs){
				HtmlHandler.getArticleContent(f.getPath(), "outputArticle"+File.separator+f.getName());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
