package com.careful.clinic.test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.careful.clinic.exceptions.CheckStructureExcelException;
import com.careful.clinic.exceptions.CheckTypizineExcelException;
import com.careful.clinic.exceptions.ParseDataExcelException;
import com.careful.clinic.upload.interfase.IDataUploadType;
import com.careful.clinic.upload.interfase.factory.UploadDataFactory;

public class TestService {

	private static IDataUploadType iDataUploadType;
	private static UploadDataFactory udf;

	@BeforeClass
	public static void initCalculator() throws InvalidFormatException, IOException {
		 udf = new UploadDataFactory();
		//iDataUploadType =  udf.getInstansUploadData("resources/test/test_d_reestr.xlsx");
	}
	
	@Before
	public void beforeEachTest() {
		System.out.println("This is executed before each Test");
	}
	
	@After
	public void afterEachTest() {
		System.out.println("This is exceuted after each Test");
	}
	
	@Ignore
	@Test
	public void test_assent() throws ParseException, IOException, InvalidFormatException, ParseDataExcelException, CheckStructureExcelException, CheckTypizineExcelException {
		String path = Thread.currentThread().getContextClassLoader().getResource("test/test_assent.xlsx").getPath();
		iDataUploadType =  udf.getInstansUploadData(path);
		iDataUploadType.orderingParsingProcess();
		//int result = calculator.sum(3, 4);
		//	assertEquals(7, result);
	}
	
	
	@Ignore
	@Test
	public void test_d_reestr() throws ParseException, IOException, InvalidFormatException, ParseDataExcelException, CheckStructureExcelException, CheckTypizineExcelException {
		String path = Thread.currentThread().getContextClassLoader().getResource("test/test_d_reestr.xlsx").getPath();
		iDataUploadType =  udf.getInstansUploadData(path);
		List<String> insets = iDataUploadType.orderingParsingProcess();
		for(String str : insets){
			System.out.println(iDataUploadType.construct_querySelect(str));
		}
	}
	
	    //@Ignore
		@Test
		public void test_RenouncementDisp() throws ParseException, IOException, InvalidFormatException, ParseDataExcelException, CheckStructureExcelException, CheckTypizineExcelException {
			String path = Thread.currentThread().getContextClassLoader().getResource("test/test_renouncement.xlsx").getPath();
			iDataUploadType =  udf.getInstansUploadData(path);
			List<String> insets = iDataUploadType.orderingParsingProcess();
			for(String str : insets){
				System.out.println(iDataUploadType.construct_querySelect(str));
			}
		}
		
		 @Ignore
		@Test
		public void test_ContentmentDisp() throws ParseException, IOException, InvalidFormatException, ParseDataExcelException, CheckStructureExcelException, CheckTypizineExcelException {
			String path = Thread.currentThread().getContextClassLoader().getResource("test/contentmentDisp_test.xlsx").getPath();
			iDataUploadType =  udf.getInstansUploadData(path);
			List<String> insets = iDataUploadType.orderingParsingProcess();
			for(String str : insets){
				System.out.println(iDataUploadType.construct_querySelect(str));
			}
		}

	@Ignore
	@Test
	public void testDivison() {
		/*try {
			int result = calculator.divison(10, 2);
			assertEquals(5, result);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}*/
	}

	@Ignore
	@Test(expected = Exception.class)
	public void testDivisionException() throws Exception {
	}

	@Ignore
	@Test
	public void testEqual() {
	}

	@Ignore
	@Test
	public void testSubstraction() {
	}

}
