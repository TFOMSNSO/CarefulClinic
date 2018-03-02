package com.careful.clinic.report.sp3.expertise;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;

import com.careful.clinic.model.DataBean;
import com.careful.clinic.model.Wrap3a_b_Expertise;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Stateless
@LocalBean
public class Sp3ExpertiseReport {
	
	
	
	 /**
	  * TODO 
	 * @param user 
	 * @param str2 
	 * @param str1 
	 * @param ob
	 * @throws JRException 
	 */
	public void executeJasperReport3aExpertise(List<Wrap3a_b_Expertise> ls, String prefix, String user, String str1, String str2) throws JRException{
		    String directoryServer = System.getProperty("jboss.home.dir");
	     
	        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(ls);
	        Map<String, Object> parameters = new HashMap<String, Object>(); 
	        parameters.put("DATE", new Date()); 
	        parameters.put("date_start", str1);
	        parameters.put("date_end", str2);

	        String path = Thread.currentThread().getContextClassLoader().getResource("reports/sp3/expertise/3a_expertise.jrxml").getPath();
	        File f = new File(path);
			
			JasperDesign jasperDesign = JRXmlLoader.load(f);
			//jasperDesign.setPageHeight(200_000); // устанавливаем высоту в зависимости от количества
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
			
			JRXlsxExporter exporter = new JRXlsxExporter();
	        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
	       // TODO: сделать логирование выгружки
	        exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,  directoryServer+"\\content\\report\\sp3\\expert\\"+user+"\\3a_expertise_"+LocalDate.now().toString()+"_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-")+prefix+".xlsx");
	        exporter.exportReport();
			
			System.out.println("Done!");

	 }

	public void executeJasperReport3bExpertise(List<Wrap3a_b_Expertise> ls, String prefix, String user, String str1, String str2) throws JRException {
		
		 	String directoryServer = System.getProperty("jboss.home.dir");
	     
	        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(ls);
	        Map<String, Object> parameters = new HashMap<String, Object>(); 
	        parameters.put("DATE", new Date()); 
	        parameters.put("date_start", str1);
	        parameters.put("date_end", str2);

	        String path = Thread.currentThread().getContextClassLoader().getResource("reports/sp3/expertise/3b_expertise.jrxml").getPath();
	        File f = new File(path);
			
			JasperDesign jasperDesign = JRXmlLoader.load(f);
			//jasperDesign.setPageHeight(200_000); // устанавливаем высоту в зависимости от количества
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
			
			JRXlsxExporter exporter = new JRXlsxExporter();
	        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
	       // TODO: сделать логирование выгружки
	        exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,  directoryServer+"\\content\\report\\sp3\\expert\\"+user+"\\3b_expertise_"+LocalDate.now().toString()+"_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-")+prefix+".xlsx");
	        exporter.exportReport();
			
			System.out.println("Done!");
		
	}

	public void executeJasperReport3a3bExpertise(List<Wrap3a_b_Expertise> ls, String prefix, String user, String str1, String str2) throws JRException {

		String directoryServer = System.getProperty("jboss.home.dir");
	     
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(ls);
        Map<String, Object> parameters = new HashMap<String, Object>(); 
        parameters.put("DATE", new Date()); 
        parameters.put("date_start", str1);
        parameters.put("date_end", str2);

        String path = Thread.currentThread().getContextClassLoader().getResource("reports/sp3/expertise/3a3b_expertise.jrxml").getPath();
        File f = new File(path);
		
		JasperDesign jasperDesign = JRXmlLoader.load(f);
		//jasperDesign.setPageHeight(200_000); // устанавливаем высоту в зависимости от количества
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
		
		JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
       // TODO: сделать логирование выгружки
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,  directoryServer+"\\content\\report\\sp3\\expert\\"+user+"\\3a3b_expertise_"+LocalDate.now().toString()+"_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-")+prefix+".xlsx");
        exporter.exportReport();
		
		System.out.println("Done!");
		
	}

	
}
