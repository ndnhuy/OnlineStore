package com.ndnhuy.onlinestore.app.service.report;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.commonutils.JasperReporter;

@RestController
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping(value="/buyer", method=RequestMethod.GET)
	public void getBuyerReport(@RequestParam("min") Double min, HttpServletResponse response) throws JRException, IOException, SQLException {
		HashMap<String,Object> hmParams=new HashMap<String,Object>();
	    
		hmParams.put("min", min);

		
      JasperReport jasperReport = JasperReporter.getCompiledFile("report8"); 
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hmParams, dataSource.getConnection());
      JasperReporter.generateReportHtml(jasperPrint, response, "report8");
	}
}
