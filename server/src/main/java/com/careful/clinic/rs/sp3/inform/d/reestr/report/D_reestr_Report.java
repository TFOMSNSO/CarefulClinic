package com.careful.clinic.rs.sp3.inform.d.reestr.report;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.careful.clinic.model.Sp3RateMo;
import com.careful.clinic.model.InformDReestr;

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
public class D_reestr_Report {

	public static void executeJasperReportD_reestr(List<InformDReestr> ls, String prefix, String user, String str1, String str2, String [] mm) throws JRException {

		String directoryServer = System.getProperty("jboss.home.dir");

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(ls);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DATE", new Date());
		parameters.put("date_start", str1);
		parameters.put("date_end", str2);

		String path = Thread.currentThread().getContextClassLoader().getResource(mm[0]).getPath();
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

	public void executeJasperReportRateMo_D_reestr(List<Sp3RateMo> ls_, String user, String date1, String date2,
												   String[] c) {
		// TODO Auto-generated method stub

	}

}
