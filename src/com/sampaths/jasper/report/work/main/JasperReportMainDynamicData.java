package com.sampaths.jasper.report.work.main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sampaths.jasper.report.work.sample.TestTable;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JasperReportMainDynamicData {

	public static void main(String[] args) throws Exception {
		
		InputStream inputStream = new FileInputStream("templates\\dynamic-table.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		Map parameters = new HashMap();
		List<TestTable> testTableList = new ArrayList<>();
		TestTable testTable = new TestTable();
		testTable.setSampleOne("TEST ONE");
//		testTable.setSampleTwo("TEST TWO");
		testTable.setSampleTwo(null); // dynamic data test
		testTable.setSampleThree("TEST THREE");
		testTableList.add(testTable);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(testTableList);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, "templates\\test_jasper1.pdf");
		
	}

}
