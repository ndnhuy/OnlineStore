package com.ndnhuy.onlinestore.app.service.common;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.sync.Patch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ndnhuy.onlinestore.commonutils.JasperReporter;
import com.ndnhuy.onlinestore.repository.CustomerRepository;

@Controller
public class HomeController {
	
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		
		
		return "WELCOME TO ONLINE STORE SERVICE";
	}

}
