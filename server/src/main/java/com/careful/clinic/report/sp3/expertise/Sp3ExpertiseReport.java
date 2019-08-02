package com.careful.clinic.report.sp3.expertise;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.careful.clinic.model.AfterDisp3Group;
import com.careful.clinic.model.Sp3RateMo;
import com.careful.clinic.model.WrapSp3;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Stateless
@LocalBean
public class Sp3ExpertiseReport {


	/**
	 *  Формирование отчета рейтинга поликлиник на основе групп рейтинга здоровья
	 *  Отчет сохраняется на файловой системе.
	 * @param ls - коллекция с рейтингом поликлиник
	 * @param user - идификатор для определения пути хранения файлов на файловой системе
	 * @param str1 - дата начала забора информации
	 * @param str2 - дата окончания забора информации
	 * @throws JRException
	 */
	// TODO Реализовать то что делает массив cons с помощью ООП (фабрика или enum или ....)
	public void executeJasperReportRateMoExpertise(List<Sp3RateMo> ls, String user, String str1, String str2, String [] cons) throws JRException{
		System.out.println("jasper report rate mo expertise ");
		String directoryServer = System.getProperty("jboss.home.dir");

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(ls);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DATE", new Date());
		parameters.put("date_start", str1);
		parameters.put("date_end", str2);


		String path = Thread.currentThread().getContextClassLoader().getResource(cons[0]).getPath();
		File f = new File(path);

		JasperDesign jasperDesign = JRXmlLoader.load(f);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);

		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		// TODO: сделать логирование выгрузки
		// специфичная логика
		exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,  directoryServer+cons[1]+LocalDate.now().toString()+"_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-")+".xlsx");
		exporter.exportReport();

		System.out.println("Done expertise_rateMO "+ cons[0]);


	}

	/**
	 * TODO
	 * @param user
	 * @param str2
	 * @param str1
	 * @param ob
	 * @throws JRException
	 */
	public void executeJasperReportExpertise(List<WrapSp3> ls, String prefix, String user, String str1, String str2, String [] mm) throws JRException{
		System.out.println("jasperrrrrr asdreport ");
		String directoryServer = System.getProperty("jboss.home.dir");

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(ls);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DATE", new Date());
		parameters.put("date_start", str1);
		parameters.put("date_end", str2);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		System.out.println("classloader:" + classLoader);
		URL resource = classLoader.getResource(mm[0]);
		System.out.println("RESOURCE:" + resource);
		String path = resource.getPath();


		System.out.println("PATH:" + path);
		File f = new File(path);
		                           
		JasperDesign jasperDesign = JRXmlLoader.load(f);
		//jasperDesign.setPageHeight(200_000); // устанавливаем высоту в зависимости от количества
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);

		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		// TODO: сделать логирование выгрузки
		exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,  directoryServer+mm[1]+LocalDate.now().toString()+"_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-")+prefix+".xlsx");
		exporter.exportReport();

		System.out.println("Done!");

	}

	public void executeJasperAfterDisp3Group(List<AfterDisp3Group> ls, String user, String str1, String str2, String [] cons) throws JRException{
		String directoryServer = System.getProperty("jboss.home.dir");

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(ls);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DATE", new Date());
		parameters.put("date_start", str1);
		parameters.put("date_end", str2);


		String path = Thread.currentThread().getContextClassLoader().getResource(cons[0]).getPath();
		File f = new File(path);

		JasperDesign jasperDesign = JRXmlLoader.load(f);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);

		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		// TODO: сделать логирование выгрузки
		// специфичная логика
		exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,  directoryServer+cons[1]+LocalDate.now().toString()+"_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-")+".xlsx");
		exporter.exportReport();

		System.out.println("Done expertise_after_disp_3_group "+ cons[0]);


	}
}
