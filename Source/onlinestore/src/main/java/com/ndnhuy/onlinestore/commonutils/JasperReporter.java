package com.ndnhuy.onlinestore.commonutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

public class JasperReporter {
	public static JasperReport getCompiledFile(String fileName) throws JRException {
	    File reportFile = new File("D:\\report\\" + fileName + ".jasper");
	    // If compiled file is not found, then compile XML template
	    if (!reportFile.exists()) {
	               JasperCompileManager.compileReportToFile("D:\\report\\" + fileName + ".jrxml", "D:\\report\\" + fileName + ".jasper");
	        }
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	       return jasperReport;
	 } 
	 
	public static void generateReportHtml(JasperPrint jasperPrint, HttpServletResponse response, String fileName) throws JRException, IOException {
		 
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		
		 File file = new File("D:\\report\\" + fileName + ".html");
		 if (!file.exists()) {
			 file.createNewFile();
		 }
		 
         HtmlExporter exporter=new HtmlExporter();
         List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
         jasperPrintList.add(jasperPrint);
         exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
         exporter.setExporterOutput( new SimpleHtmlExporterOutput(response.getWriter()));
         SimpleHtmlReportConfiguration configuration =new SimpleHtmlReportConfiguration();
         exporter.setConfiguration(configuration);
          exporter.exportReport();
 
    }
}
