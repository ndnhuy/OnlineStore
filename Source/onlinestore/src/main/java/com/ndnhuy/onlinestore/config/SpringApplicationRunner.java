package com.ndnhuy.onlinestore.config;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@ComponentScan("com.ndnhuy.onlinestore")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages="com.ndnhuy.onlinestore.repository")
@EntityScan("com.ndnhuy.onlinestore.domain.entity")
public class SpringApplicationRunner extends SpringBootServletInitializer {
	
	private static final Logger logger = Logger.getLogger(SpringApplicationRunner.class);
	
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		
		return builder.sources(SpringApplicationRunner.class);
	}
	
	 public static void main(String[] args) throws JRException, IOException, SQLException {
	    ApplicationContext ctx = SpringApplication.run(SpringApplicationRunner.class, args);
	    
	    
//	    File file = new File("D:\\JREmp1.jrxml");
//	    
//	    System.out.println(file);
	    
	    DataSource ds = (DataSource) ctx.getBean("dataSource");
	    System.out.println("DataSource is " + ds);
	    Connection conn = ds.getConnection();
	    
	    HashMap<String,Object> hmParams=new HashMap<String,Object>();
	    
        hmParams.put("noy", new Integer(5000));

        hmParams.put("Title", "Employees working more than "+ 5000 + " Years");
        
        
              
        JasperReport jasperReport = getCompiledFile("JREmp1"); 
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hmParams, conn);
        generateReportHtml(jasperPrint);
	 }
	 
	 private static JasperReport getCompiledFile(String fileName) throws JRException {
	    File reportFile = new File("D:\\JREmp1.jasper");
	    // If compiled file is not found, then compile XML template
	    if (!reportFile.exists()) {
	               JasperCompileManager.compileReportToFile("D:\\JREmp1.jrxml", "D:\\JREmp1.jasper");
	        }
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	       return jasperReport;
	 } 
	 
	 private static void generateReportHtml( JasperPrint jasperPrint) throws JRException, IOException {
		 
		 File file = new File("D:\\JREmp1.html");
		 if (!file.exists()) {
			 file.createNewFile();
		 }
		 
         HtmlExporter exporter=new HtmlExporter();
         List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
         jasperPrintList.add(jasperPrint);
         exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
         exporter.setExporterOutput( new SimpleHtmlExporterOutput(file));
         SimpleHtmlReportConfiguration configuration =new SimpleHtmlReportConfiguration();
         exporter.setConfiguration(configuration);
          exporter.exportReport();
 
    }
}
