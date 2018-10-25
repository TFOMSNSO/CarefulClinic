package com.careful.clinic.rs.prophylactic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.spi.FileSystemProvider;
import java.sql.SQLSyntaxErrorException;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.ParserConfigurationException;

import com.lowagie.text.Row;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.exception.SQLGrammarException;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.xml.sax.SAXException;

import com.careful.clinic.dao.prophylactic.ProphylacticDAO;
import com.careful.clinic.dao.prophylactic.ProphylacticDAOBean;
import com.careful.clinic.dao.prophylactic.XA_Dream2Dao;
import com.careful.clinic.exceptions.CheckStructureExcelException;
import com.careful.clinic.exceptions.CheckTypizineExcelException;
import com.careful.clinic.exceptions.ParseDataExcelException;
import com.careful.clinic.guid.RandomGUID;
import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.SearchKeysModel;
import com.careful.clinic.model.WrapRespSerarchKeys;
import com.careful.clinic.upload.interfase.IDataUploadType;
import com.careful.clinic.upload.interfase.factory.UploadDataFactory;


@javax.ws.rs.Path("/prophylactic")
public class RestServiceProphylactic {
	
	final String directoryServer = System.getProperty("jboss.home.dir");
	
	@EJB
	UploadDataFactory  uploadFactory; 
	@EJB
	ProphylacticDAO prophylacticDAO;
	@EJB
	XA_Dream2Dao xa_Dream2Dao;

	@EJB
	private RandomGUID randomGuid;

    @Resource(name = "java:jboss/mail/ofoms")
    private Session session;

	private String UPLOADED_FILE_PATH = "";

	public byte[] getBytesFromInputStream(InputStream is) throws IOException
	{
	    try (ByteArrayOutputStream os = new ByteArrayOutputStream();)
	    {
	        byte[] buffer = new byte[0xFFFF];

	        for (int len; (len = is.read(buffer)) != -1;)
	            os.write(buffer, 0, len);

	        os.flush();

	        return os.toByteArray();
	    }
	}
	
	/**
	 * Производим загрузку файла на сервер в зависимости от типа пользователя
	 * @param headers	
	 * @param input входящие данные 
	 * @return информацию о статусе выполнененой загрузки
	 * @throws IOException 
	 * @throws CheckStructureExcelException 
	 * @throws CheckTypizineExcelException 
	 */
	@POST
	@Path("/upload") 
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@Context HttpHeaders headers, MultipartFormDataInput input) throws IOException, CheckStructureExcelException, CheckTypizineExcelException {
		
		String fileName = "";

		List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
		if(Integer.valueOf(authHeaders.get(0)) == 777) UPLOADED_FILE_PATH = "\\content\\upload\\777\\process\\";
		if(Integer.valueOf(authHeaders.get(0)) == 1)	UPLOADED_FILE_PATH = "\\content\\upload\\1\\process\\";
		if(Integer.valueOf(authHeaders.get(0)) == 2)	UPLOADED_FILE_PATH = "\\content\\upload\\2\\process\\";
		if(Integer.valueOf(authHeaders.get(0)) == 4)	UPLOADED_FILE_PATH = "\\content\\upload\\4\\process\\";

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadFile");
		Response.ResponseBuilder builder = null;

		try {
			for (InputPart inputPart : inputParts) {
						MultivaluedMap<String, String> header = inputPart.getHeaders();
						fileName = getFileName(header);
						System.out.println("fileName_Test "+fileName);
						//convert the uploaded file to inputstream
						InputStream inputStream = inputPart.getBody(InputStream.class,null);
						byte [] bytes = getBytesFromInputStream(inputStream);
	
						//constructs upload file path
						fileName = directoryServer + UPLOADED_FILE_PATH + fileName;
						writeFile(bytes,fileName);
			}

			/*Блок загрузки в базу
			 * Парсим и загружаем в базу отработанный файл 
			 * */
			
			IDataUploadType data = uploadFactory.getInstansUploadData(fileName);
			List<String> listOfQueryies =  null;
			if (data != null ) {
				listOfQueryies =  data.orderingParsingProcess();
			}
			else{
				// ВРЕМЕННО когда полностью реализуется паттерн фабрика (в первом if)
				listOfQueryies = prophylacticDAO.processingExcelFile(fileName); 
			}
			if(listOfQueryies == null) throw new ParseDataExcelException("Ошибка в шаблоне. Не удается определить шаблон.");
			//data.checkOuTroughDB(listOfQueryies)
			send(Integer.valueOf(authHeaders.get(0)),DateFormat.getTimeInstance(DateFormat.DATE_FIELD),fileName);
			if(xa_Dream2Dao.insertDataFromExcel(listOfQueryies,data)){deleteNativeFileFromUser(null, fileName, authHeaders); }
			//TODO////////////////////////////////////////////////
			//if(resultEKMPDAO.insertDataFromExcel(listOfQueryies,data)){	deleteNativeFileFromUser(null, fileName, authHeaders); }
			///////////////////////////////////////////////////
			 String info =  new String("Файл успешно загружен");
			 builder = Response.status(Response.Status.OK);
		     builder.entity( info );
		     return builder.build();
		     
		}catch (IOException e) {
			deleteNativeFileFromUser(e,fileName,authHeaders);
			  e.printStackTrace();
			  builder = Response.status(Response.Status.OK);
			     builder.entity("Произошла ошибка загрузки файла. При отсутствии причины ошибки в протоколе необходимо связаться с разработчиком");
			  return builder.build();
		  }
		catch (InvalidFormatException e) {
			deleteNativeFileFromUser(e,fileName,authHeaders);
			  e.printStackTrace();
			  builder = Response.status(Response.Status.OK);
			     builder.entity("Произошла ошибка загрузки файла. При отсутствии причины ошибки в протоколе необходимо связаться с разработчиком");
			  return builder.build();
		}
		catch (ParseException e) {
			deleteNativeFileFromUser(e,fileName,authHeaders);
			  e.printStackTrace();
			  builder = Response.status(Response.Status.OK);
			     builder.entity("Произошла ошибка загрузки файла. При отсутствии причины ошибки в протоколе необходимо связаться с разработчиком");
			  return builder.build();
		}
		catch(CheckTypizineExcelException e){
			e.printStackTrace();
			deleteNativeFileFromUser(e,fileName,authHeaders);
			
			  builder = Response.status(Response.Status.OK);
			     builder.entity(e.getMessage());
			  return builder.build();
		}
		catch (ParseDataExcelException e) {
			e.printStackTrace();
			deleteNativeFileFromUser(e,fileName,authHeaders);
			  builder = Response.status(Response.Status.OK);
			     builder.entity(e.getMessage());
			  return builder.build();
		}
		catch (CheckStructureExcelException e) {
			e.printStackTrace();
			deleteNativeFileFromUser(e,fileName,authHeaders);
		    builder = Response.status(Response.Status.OK);
		     builder.entity(e.getMessage());
		   return builder.build();
		}
	}
	
	/**
	 *  Отправка сообщения по почте
	 * @param valueOf
	 * @param timeInstance
	 * @param fileName
	 */
	private void send(Integer valueOf, DateFormat timeInstance, String filename) throws InvalidFormatException, IOException {

		String addresses = "moi@ofoms.sibnet.ru,aiv@ofoms.sibnet.ru,mev@ofoms.sibnet.ru,klw@ofoms.sibnet.ru,esa@ofoms.sibnet.ru";
		String addresses2 = "guv@ofoms.sibnet.ru,sja@ofoms.sibnet.ru,esa@ofoms.sibnet.ru";
		String addresses3 = "esa@ofoms.sibnet.ru,kin@ofoms.sibnet.ru";
				String topic= "ПРОИЗОШЛА ЗАГРУЗКА ДАННЫХ В ЕИР НСО";
		OPCPackage pkg = OPCPackage.open(new File(filename));
		XSSFWorkbook workbook = new XSSFWorkbook(pkg);

		DataFormatter formatter = new DataFormatter();
		Sheet sheet =  workbook.getSheetAt(0);
		org.apache.poi.ss.usermodel.Row row = sheet.getRow(0);
		// из загружаемого файла обпределяем тип данных которые грузятся.
		String  type = row.getCell(1).getStringCellValue().trim().toUpperCase();
    	//String textMessage = "Test Message Text";
 
        try {
 
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@eir.tfoms.nso"));
            /*if(xa_Dream2Dao.str.contains("RESULT_EKMP")){
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses2));}
            if(xa_Dream2Dao.doubleStr().contains("RESULT_EKMP")==false){
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
			}*/
			///////////////////
			if(type.trim().equalsIgnoreCase("РЕЗУЛЬТАТЫ ЭКМП")){
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses2));
			}else if((type.trim().equalsIgnoreCase("ОПРОС ОТКАЗ ДИСПАНСЕРИЗАЦИИ"))||(type.trim().equalsIgnoreCase("ОПРОС УДОВЛЕТВОРЕННОСТИ ДИСПАНСЕРИЗАЦИИ"))||(type.trim().equalsIgnoreCase("ПОДАЧА СОГЛАСИЯ ЗЛ"))||(type.trim().equalsIgnoreCase("АННУЛИРОВАНИЕ&ОТЗЫВ СОГЛАСИЯ ЗЛ"))){
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses3));
			}
			else{
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
			}
			///////////////////
            message.setSubject(topic, "UTF-8");
           // message.setText(textMessage);
            BodyPart messageBodyPart = new MimeBodyPart();
            //messageBodyPart.setText("Это текст на русском яхыке<br> and English lang... ");
            messageBodyPart.setContent("<p>ЭТО АВТОМАТИЧЕСКАЯ РАССЫЛКА ПРИЛОЖЕНИЯ ЕИР НСО.</p><br>Пользователь "+valueOf+" совершил загрузку '"+type+"' в Единое информационное пространство в "+timeInstance.format(new Date())+"<br>Загруженная информация во вложении", "text/html; charset=utf-8");
            
         // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
         //   String filename = "C:/Temp/ghjg.xlsx";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("file.xlsx");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);
            
 
            Transport.send(message);
 
        } catch (MessagingException e) {
            e.printStackTrace();
        }
		
		
	}

	/**
	 * Основная идея метода - это переименование загруженного на файловую систему файла.
	 * Так как загружаемый user'ом файл может грузиться с одним и тем же именем, что приведет к неразберихе и возможным
	 * ошибкам, мы удаляем загруженный файл, предварительно скопировав содержимое в новый с уникальным именем (в основе GUID)
	 *  
	 * @param e  исключение которое возникло в ходе парсига загруженного экселя или  если успешаня загрузка то Файл "+tmp_val+".xlsx успешно загружен. 
	 * @param fileName  имя файла
	 * @param authHeaders  атрибуты которые пришли из запроса
	 * @throws IOException
	 */
	private void  deleteNativeFileFromUser(Throwable e,String fileName, List<String> authHeaders) throws IOException{

		String tmp_val = randomGuid.valueAfterMD5;
		//if(e == null) e = new Throwable("Файл "+fileName.replace(directoryServer+UPLOADED_FILE_PATH, "")+" успешно загружен "+getTimeStamp()+". Количество принятых записей: "+xa_Dream2Dao.rowsValue()+". Количество не принятых записей: "+xa_Dream2Dao.doubleValue());
        if((e == null)&&(xa_Dream2Dao.doubleStr().contains("pm_i"))&&(prophylacticDAO.countStrProphylactic()==0)) e = new Throwable("Файл "+fileName.replace(directoryServer+UPLOADED_FILE_PATH, "")+" успешно загружен "+getTimeStamp()+". Количество не принятых записей (дублей): "+xa_Dream2Dao.doubleValue()+xa_Dream2Dao.doubleStr().replace("insert into pm_i p        values('',", "\r\n"));
		if((e == null)&&(xa_Dream2Dao.doubleStr().contains("pm_a"))&&(prophylacticDAO.countStrProphylactic()==0)) e = new Throwable("Файл "+fileName.replace(directoryServer+UPLOADED_FILE_PATH, "")+" успешно загружен "+getTimeStamp()+". Количество не принятых записей (дублей): "+xa_Dream2Dao.doubleValue()+xa_Dream2Dao.doubleStr().replace("insert into pm_a  p       (ID,FAM,IM,OT,DR,D_INFO,TYPE_INFO,PRIM,SMO,DATA,D_INSERT)  values('',", "\r\n"));
		if((e == null)&&(xa_Dream2Dao.doubleStr().contains("RESULT_EKMP"))&&(prophylacticDAO.countStrProphylactic()==0)) e = new Throwable("Файл "+fileName.replace(directoryServer+UPLOADED_FILE_PATH, "")+" успешно загружен "+getTimeStamp()+". Количество не принятых записей (дублей): "+xa_Dream2Dao.doubleValue()+xa_Dream2Dao.doubleStr().replace("insert into RESULT_EKMP   values('',", "\r\n"));
		if(e == null) e = new Throwable("Файл "+fileName.replace(directoryServer+UPLOADED_FILE_PATH, "")+" успешно загружен "+getTimeStamp());
		String toName = directoryServer + getPathTo(authHeaders.get(0)) + tmp_val+"_"+getTimeStamp()+".xlsx";
		
		File from_file = new File(fileName);
		File to_file = new File(toName);
		java.nio.file.Path p1 = from_file.toPath();
		java.nio.file.Path p2 = to_file.toPath();
		Files.copy(p1, p2);
		
		// удаляем "старый" файл, то есть который загрузил user со своим именем и назначаем служебное имя.
		
		java.nio.file.Path f = Paths.get(toName.replaceAll(".xlsx", ".txt"));
		  Files.deleteIfExists(f);
		  java.nio.file.Path out = Files.createFile(Paths.get(toName.replaceAll(".xlsx", ".txt")));
  	      Files.write(Paths.get(out.toUri()), e.getMessage().getBytes(), StandardOpenOption.APPEND);
	}
	
	private String getPathTo (String v){
		 String TRANSVER_UPLOADED_FILE_PATH ="";
		 
		if(Integer.valueOf(v) == 777) return  TRANSVER_UPLOADED_FILE_PATH  = "\\content\\upload\\777\\process\\";
		if(Integer.valueOf(v) == 1)	return TRANSVER_UPLOADED_FILE_PATH = "\\content\\upload\\1\\process\\";
		if(Integer.valueOf(v) == 2)	return TRANSVER_UPLOADED_FILE_PATH = "\\content\\upload\\2\\process\\";
		if(Integer.valueOf(v) == 4)	return TRANSVER_UPLOADED_FILE_PATH = "\\content\\upload\\4\\process\\";
		
		return TRANSVER_UPLOADED_FILE_PATH;
	}
	
	
	/**
	 * Одновременный поиск в ГЭР и РСЕРЗ
	 * @param personmodel критерии поиска
	 * @return Коллекция, где первым элементом является объект из базы застрахованных, вторым элементом объект из гэра  
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	@POST
	@Path("/search_person_ger")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchGer(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) prophylacticDAO.getInfoProphylactic(personmodel);
		
		return df;
	}
	
	@POST
	@Path("/insert_pm_a")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response insert_pm_a(String sql) {
	try{	
		new Throwable();
		Response.ResponseBuilder response = Response.ok();
		xa_Dream2Dao.pasteResultPm_a(sql);	        
		return response.build();
	   } catch (Throwable e) {
	        e.printStackTrace();
	        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	    }
	}
	
	
	
	/**
	 * Поиск в базе застрахованных (РС ЕРЗ)
	 * @param personmodel критерии поиска
	 * @return объект из базы застрахованных.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	@POST
	@Path("/search_person_insur")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchInsur(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) prophylacticDAO.getInfoInsur(personmodel);
		
		return	df;
	}
	
	/**
	 * Поиск застрахованных по ключам
	 * @param personmodel - объект с заданными параметрами для поиска в базе
	 * @return - застрахованные лица которые удавлетворяют поиску
	 * @throws Exception 
	 */
	@POST
	@Path("/search_person_keys")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchInsurkeys(SearchKeysModel personmodel) throws Exception {
		List<?> df = (List<?>) prophylacticDAO.getInfoInsurkeys(personmodel);
		
		return	df;
	}
	
	/**
	 * Экспорт в эксель через кнопку выгрузить в эксель
	 * @param wrapRespSerarchKeys
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/exportToexcel")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> exportToExcel(ArrayList<WrapRespSerarchKeys> wrapRespSerarchKeys) throws Exception {
		
		List<?> df = (List<?>) prophylacticDAO.exportToExcel(wrapRespSerarchKeys);
		
		return	df;
	}
	
	
	
	/**
	 * Поиск в базе АИС ГЭР 
	 * @param personmodel критерии поиска
	 * @return объект из базы АИС ГЭР.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	@POST
	@Path("/search_ger")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchG(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) xa_Dream2Dao.getInfoG(personmodel);
		
		return df;

	}

	/**
	 * Поиск в базе информированных
	 * @param personmodel критерии поиска
	 * @return объект из базы информированных
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	@POST
	@Path("/search_informed")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchInform(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		return xa_Dream2Dao.getInfoInform(personmodel);
	}
	
	@POST
	@Path("/survey_inform")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> surveyInform(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		return xa_Dream2Dao.getSurveyInform(personmodel);
	}
	
	
	@POST
	@Path("/search_plan_informed/{adressid}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchPlanInform(@PathParam("adressid") String adressid) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) xa_Dream2Dao.getInfoPlanInform(Integer.valueOf(adressid));
		
		return df;

	}



/**
 * 
 * @param id
 * @return возвращает список имен файлов xlsx
 * @throws ParserConfigurationException
 * @throws SAXException
 * @throws IOException
 * @throws ParseException
 */
@GET
@Path("/listExcelFiles/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Collection<?> listExcelFiles(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {
	
	List<?> df = (List<?>) prophylacticDAO.getListExcelFiles(id);
	
	return df;
}

@GET
@Path("/listUpload/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Collection<?> listUploadedFiles(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {
	
	List<?> df = (List<?>) prophylacticDAO.getListUploadedFiles(id);
	
	return df;
}

@GET
@Path("/download/{place}/{id}/{namefile}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
public Response getdownloadFile(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {
	
	String directoryServer = System.getProperty("jboss.home.dir");
	String directoryDestination = "";
	if(place.equals("download")){
		if(id == 777) directoryDestination = "\\content\\donwload\\777";
		if(id == 1)	directoryDestination = "\\content\\donwload\\1";
		if(id == 2)	directoryDestination = "\\content\\donwload\\2";
		if(id == 4)	directoryDestination = "\\content\\donwload\\4";	
	}
	if(place.equals("upload")){
		if(id == 777) directoryDestination = "\\content\\upload\\777\\process";
		if(id == 1)	directoryDestination = "\\content\\upload\\1\\process";
		if(id == 2)	directoryDestination = "\\content\\upload\\2\\process";
		if(id == 4)	directoryDestination = "\\content\\upload\\4\\process";
	}
	
	directoryDestination = directoryServer+directoryDestination+File.separator+namefile;
	
    File file = new File(directoryDestination);
    try {
        String contentType = Files.probeContentType(file.toPath());
        
        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment; filename="+file.getName());
        response.header("Content-Type", contentType);
        response.header("Content-Length", file.length());
        return response.build();
    } catch (IOException e) {
        e.printStackTrace();
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    }
}

/**
 * header sample
 * {
 * 	Content-Type=[image/png],
 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
 * }
 * @throws IOException 
 **/
//get uploaded filename, is there a easy way in RESTEasy?
private String getFileName(MultivaluedMap<String, String> header) throws IOException {

	String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

	for (String filename : contentDisposition) {
		if ((filename.trim().startsWith("filename"))) {

			String[] name = filename.split("=");

			String finalFileName = URLDecoder.decode(name[1].trim().replaceAll("\"", ""), "UTF-8");
			return finalFileName;
		}
	}
	return "unknown";
}

//save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}
	
	private String getTimeStamp(){
		return  LocalDate.now().toString()+"_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-");
	}

}