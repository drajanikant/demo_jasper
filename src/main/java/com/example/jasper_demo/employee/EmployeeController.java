package com.example.jasper_demo.employee;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RestController
@RequestMapping(value="/")
public class EmployeeController {

	@Autowired
	private IEmployeeService employee_service;
	
	@Autowired
	private ApplicationContext application_context;
	
	@GetMapping
	public String getIndex() {
		return "index";
	}
	
	@GetMapping(value="report")
	public void getReport(HttpServletResponse response) throws JRException, IOException {
		 InputStream jasperStream = this.getClass().getResourceAsStream("/reports/report1.jrxml");
		 
		 JasperDesign design = JRXmlLoader.load(jasperStream);
		 
		 JasperReport jasperReport = JasperCompileManager.compileReport(design);
		 
		 Map<String,Object> params = new HashMap<>();
		    
		 List<Employee> list =    employee_service.getEmployees();
		 
		 JRDataSource data = new JRBeanCollectionDataSource(list);
		 
		 params.put("datasource", data);
		    
		 JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, data);

		    response.setContentType("application/x-pdf");
		    response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

		    final OutputStream outStream = response.getOutputStream();
		    JasperExportManager	.exportReportToPdfStream(jasperPrint, outStream);
	}
	
	@GetMapping(value="report2", produces= org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<?> getReport2() throws JRException, IOException {
		 InputStream jasperStream = this.getClass().getResourceAsStream("/reports/report1.jrxml");
		 
		 JasperDesign design = JRXmlLoader.load(jasperStream);
		 
		 JasperReport jasperReport = JasperCompileManager.compileReport(design);
		 
		 Map<String,Object> params = new HashMap<>();
		    
		 List<Employee> list =    employee_service.getEmployees();
		 
		 JRDataSource data = new JRBeanCollectionDataSource(list);
		 
		 params.put("datasource", data);
		    
		 JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, data);

		 byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		
		 HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
	                .body(bytes);
	}
}
