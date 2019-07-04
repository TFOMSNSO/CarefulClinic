package com.careful.clinic.dao.moschedules;

import com.careful.clinic.model.ResponseDescription;
import com.careful.clinic.model.schedulemodels.table1.DISP_TABLE1;
import com.careful.clinic.model.schedulemodels.table1.DISP_TABLE1_V2;
import com.careful.clinic.model.schedulemodels.table1.DISP_TABLE_DT;
import com.careful.clinic.model.schedulemodels.table2.DISP_TABLE2;
import com.careful.clinic.model.schedulemodels.table2.DISP_TABLE2_V2;
import com.careful.clinic.model.schedulemodels.table3.DISP_TABLE3;
import com.careful.clinic.model.schedulemodels.table3.DISP_TABLE3_V2;
import com.careful.clinic.model.schedulemodels.table4.DISP_TABLE4;
import com.careful.clinic.model.schedulemodels.table4.DISP_TABLE4_V2;
import com.careful.clinic.model.schedulemodels.table5.DISP_TABLE5;
import com.careful.clinic.model.schedulemodels.table5.DISP_TABLE5_V2;
import com.careful.clinic.model.schedulemodels.table6.DISP_TABLE6;
import com.careful.clinic.model.schedulemodels.table6.DISP_TABLE6_V2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@Stateless
public class scheduleDAOBean implements scheduleDAO {
    private String days[] = {"Понедельник","Вторник","Среда","Четверг","Пятница","Суббота","Воскресенье"};


    @PersistenceContext(unitName = "WebOfoms")
    EntityManager webofoms;


    @Override
    public List<DISP_TABLE1> getAllTable1() {
        TypedQuery<DISP_TABLE1> tq = webofoms.createNamedQuery("findAllt1",DISP_TABLE1.class);

        List<DISP_TABLE1> list = tq.getResultList();
        if(!list.isEmpty()){
            System.out.println(list.get(0));
        }
        return list;
    }

    @Override
    public List<DISP_TABLE2> getAllTable2() {
        TypedQuery<DISP_TABLE2> tq = webofoms.createNamedQuery("findAllt2",DISP_TABLE2.class);

        List<DISP_TABLE2> list = tq.getResultList();
        if(!list.isEmpty()){
            System.out.println(list.get(0));
        }
        return list;
    }

    @Override
    public List<DISP_TABLE3> getAllTable3() {
        TypedQuery<DISP_TABLE3> tq = webofoms.createNamedQuery("findAllt3",DISP_TABLE3.class);

        List<DISP_TABLE3> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println(list.get(0));
        }
        return list;
    }

    @Override
    public List<DISP_TABLE4> getAllTable4() {
        TypedQuery<DISP_TABLE4> tq = webofoms.createNamedQuery("findAllt4",DISP_TABLE4.class);

        List<DISP_TABLE4> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println(list.get(0));
        }
        return list;
    }

    @Override
    public List<DISP_TABLE5> getAllTable5() {
        TypedQuery<DISP_TABLE5> tq = webofoms.createNamedQuery("findAllt5",DISP_TABLE5.class);

        List<DISP_TABLE5> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println("DISP_TABLE5 all:" + list.size());
        }
        return list;
    }

    @Override
    public List<DISP_TABLE6> getAllTable6() {
        TypedQuery<DISP_TABLE6> tq = webofoms.createNamedQuery("findAllt6",DISP_TABLE6.class);

        List<DISP_TABLE6> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println("DISP_TABLE6 all:" + list.size());
        }
        return list;
    }

    @Override
    public ResponseDescription exportTable1(List<DISP_TABLE1_V2> list) {
        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
        String current_time_str = time_formatter.format(System.currentTimeMillis());
        String filename = "График_работы_МО_"+current_time_str+".xlsx";
        try {
            return writeListToFile1(filename, list);
        }catch(Exception ex){
            System.out.println(ex);
            return new ResponseDescription("Не удалось сохранить файл.");
        }
    }

    @Override
    public ResponseDescription exportTable2(List<DISP_TABLE2_V2> list) {
        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
        String current_time_str = time_formatter.format(System.currentTimeMillis());
        String filename = "Контактное_лицо_"+current_time_str+".xlsx";
        try {
            return writeListToFile2(filename, list);
        }catch(Exception ex){
            System.out.println(ex);
            return new ResponseDescription("Не удалось сохранить файл.");
        }
    }

    @Override
    public ResponseDescription exportTable4(List<DISP_TABLE4_V2> list) {
        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
        String current_time_str = time_formatter.format(System.currentTimeMillis());
        String filename = "График_работы_бригад_"+current_time_str+".xlsx";
        try {
            return writeListToFile4(filename, list);
        }catch(Exception ex){
            System.out.println(ex);
            return new ResponseDescription("Не удалось сохранить файл.");
        }
    }

    @Override
    public ResponseDescription exportTable5(List<DISP_TABLE5_V2> list) {
        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
        String current_time_str = time_formatter.format(System.currentTimeMillis());
        String filename = "Сроки_доставки"+current_time_str+".xlsx";
        try {
            return writeListToFile5(filename, list);
        }catch(Exception ex){
            System.out.println(ex);
            return new ResponseDescription("Не удалось сохранить файл.");
        }
    }

    @Override
    public ResponseDescription exportTable6(List<DISP_TABLE6_V2> list) {
        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
        String current_time_str = time_formatter.format(System.currentTimeMillis());
        String filename = "Выделенные_дни"+current_time_str+".xlsx";
        try {
            return writeListToFile6(filename, list);
        }catch(Exception ex){
            System.out.println(ex);
            return new ResponseDescription("Не удалось сохранить файл.");
        }
    }


    @Override
    public ResponseDescription exportTable3(List<DISP_TABLE3_V2> list) {
        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
        String current_time_str = time_formatter.format(System.currentTimeMillis());
        String filename = "Порядок маршрутизации"+current_time_str+".xlsx";
        try {
            return writeListToFile3(filename, list);
        }catch(Exception ex){
            System.out.println(ex);
            return new ResponseDescription("Не удалось сохранить файл.");
        }
    }

    private ResponseDescription writeListToFile3(String fileName, List<DISP_TABLE3_V2> list) throws Exception {

        SXSSFWorkbook workbook = null;
        if(fileName.endsWith("xlsx")){
            workbook = new SXSSFWorkbook(-1);
        }else{
            throw new Exception("invalid file name, should be xls or xlsx");
        }

        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Данные");
        Row row = sheet.createRow(0);

        sheet.setColumnWidth(1,4000);
        sheet.setColumnWidth(2,4000);
        sheet.setColumnWidth(5,4000);

        row.createCell(0).setCellValue("Код МО");
        row.createCell(1).setCellValue("Код отделения");
        row.createCell(2).setCellValue("Услуга");
        row.createCell(3).setCellValue("Кабинет");
        row.createCell(4).setCellValue("Тип медицинского осмотра");
        row.createCell(5).setCellValue("Примечание");
        for(int i = 0; i < 7; i++){
            row.createCell(i+7).setCellValue(days[i]);
            sheet.setColumnWidth(i+7,5000);
        }



        Iterator<DISP_TABLE3_V2> iterator = list.iterator();
        int rowIndex = 1;
        DISP_TABLE3 temp;
        while(iterator.hasNext()){
            temp = iterator.next();
            Row row1 = sheet.createRow(rowIndex++);
            row1.createCell(0).setCellValue(temp.getLpuId());
            row1.createCell(1).setCellValue(temp.getKodOtd());
            row1.createCell(2).setCellValue(temp.getUsl());
            row1.createCell(3).setCellValue(temp.getKab());
            row1.createCell(4).setCellValue(temp.getTypeMo());
            row1.createCell(5).setCellValue(temp.getPrim());

            List<DISP_TABLE_DT> week = temp.getDates();
            for(int i = 0; i < week.size(); i++){
                DISP_TABLE_DT day = week.get(i);
                String value = "c " + day.getTimeBegin() + " до " + day.getTimeEnd();
                row1.createCell(i+7).setCellValue(value.contains("null") ? "нет" : value);
            }

            if(rowIndex % 100 == 0) {
                (sheet).flushRows(100); // retain 100 last rows and flush all others
            }
        }

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination =  "\\content\\donwload\\" + list.get(0).getCurrentUser().trim();


        directoryDestination = directoryServer+directoryDestination;
        String filepath = Paths.get(directoryDestination, fileName).toString();

        FileOutputStream fos = new FileOutputStream(filepath);
        workbook.write(fos);
        fos.close();


        workbook.dispose();
        return new ResponseDescription(fileName);
    }


    private ResponseDescription writeListToFile5(String fileName, List<DISP_TABLE5_V2> list) throws Exception {

        SXSSFWorkbook workbook = null;
        if(fileName.endsWith("xlsx")){
            workbook = new SXSSFWorkbook(-1);
        }else{
            throw new Exception("invalid file name, should be xls or xlsx");
        }

        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Данные");
        Row row = sheet.createRow(0);
        sheet.setColumnWidth(1,7000);
        sheet.setColumnWidth(2,5000);
        sheet.setColumnWidth(3,5000);
        sheet.setColumnWidth(4,5000);
        sheet.setColumnWidth(5,5000);
        sheet.setColumnWidth(7,7000);

        row.createCell(0).setCellValue("Код МО");
        row.createCell(1).setCellValue("Населенный пункт");
        row.createCell(2).setCellValue("Дата начала");
        row.createCell(3).setCellValue("Дата окончания");
        row.createCell(4).setCellValue("Время сбора");
        row.createCell(5).setCellValue("Время обратного прибытия");
        row.createCell(6).setCellValue("Тип медицинского осмотра");
        row.createCell(7).setCellValue("Примечание");



        Iterator<DISP_TABLE5_V2> iterator = list.iterator();
        int rowIndex = 1;
        DISP_TABLE5 temp;
        while(iterator.hasNext()){
            temp = iterator.next();
            Row row1 = sheet.createRow(rowIndex++);
            row1.createCell(0).setCellValue(temp.getLpuId());
            row1.createCell(1).setCellValue(temp.getAddress());
            row1.createCell(2).setCellValue(temp.getDateBegin());
            row1.createCell(3).setCellValue(temp.getDateEnd());
            row1.createCell(4).setCellValue(temp.getTimeBegin());
            row1.createCell(5).setCellValue(temp.getTimeEnd());
            row1.createCell(6).setCellValue(temp.getTypeMo());
            row1.createCell(7).setCellValue(temp.getPrim());
            if(rowIndex % 100 == 0) {
                (sheet).flushRows(100); // retain 100 last rows and flush all others
            }
        }

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination =  "\\content\\donwload\\" + list.get(0).getCurrentUser().trim();


        directoryDestination = directoryServer+directoryDestination;
        String filepath = Paths.get(directoryDestination, fileName).toString();

        FileOutputStream fos = new FileOutputStream(filepath);
        workbook.write(fos);
        fos.close();


        workbook.dispose();
        return new ResponseDescription(fileName);
    }

    private ResponseDescription writeListToFile2(String fileName, List<DISP_TABLE2_V2> list) throws Exception {
        System.out.println("aaaaaaaaaaaaaaa" + list.get(0));
        SXSSFWorkbook workbook = null;
        if(fileName.endsWith("xlsx")){
            workbook = new SXSSFWorkbook(-1);
        }else{
            throw new Exception("invalid file name, should be xls or xlsx");
        }

        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Данные");
        Row row = sheet.createRow(0);
        sheet.setColumnWidth(1,10000);
        sheet.setColumnWidth(2,11000);
        sheet.setColumnWidth(3,4500);
        sheet.setColumnWidth(3,7000);

        Cell cell0 = row.createCell(0);
        cell0.setCellValue("Код МО");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("Контактное лицо(ФИО)");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("Должность");
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("Телефон");
        Cell cell7 = row.createCell(4);
        cell7.setCellValue("Примечание");



        Iterator<DISP_TABLE2_V2> iterator = list.iterator();
        int rowIndex = 1;
        DISP_TABLE2 temp;
        while(iterator.hasNext()){
            temp = iterator.next();
            Row row1 = sheet.createRow(rowIndex++);
            row1.createCell(0).setCellValue(temp.getLpuId());
            row1.createCell(1).setCellValue(temp.getFio());
            row1.createCell(2).setCellValue(temp.getDol());
            row1.createCell(3).setCellValue(temp.getPhone());
            row1.createCell(4).setCellValue(temp.getPrim());
            if(rowIndex % 100 == 0) {
                (sheet).flushRows(100); // retain 100 last rows and flush all others
            }
        }

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination =  "\\content\\donwload\\" + list.get(0).getCurrentUser().trim();


        directoryDestination = directoryServer+directoryDestination;
        String filepath = Paths.get(directoryDestination, fileName).toString();

        FileOutputStream fos = new FileOutputStream(filepath);
        workbook.write(fos);
        fos.close();


        workbook.dispose();
        return new ResponseDescription(fileName);
    }


    private ResponseDescription writeListToFile4(String fileName, List<DISP_TABLE4_V2> list) throws Exception {
        System.out.println("user:" + list.get(0).getCurrentUser());
        SXSSFWorkbook workbook = null;
        if(fileName.endsWith("xlsx")){
            workbook = new SXSSFWorkbook(-1);
        }else{
            throw new Exception("invalid file name, should be xls or xlsx");
        }

        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Данные");
        Row row = sheet.createRow(0);
        sheet.setColumnWidth(1,7000);
        sheet.setColumnWidth(7,7000);

        for(int i = 2; i < 6; i++){
            sheet.setColumnWidth(i,3500);
        }

        Cell cell0 = row.createCell(0);
        cell0.setCellValue("Код МО");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("Адрес проведения");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("Дата начала");
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("Дата окончания");
        Cell cell4 = row.createCell(4);
        cell4.setCellValue("Время начала");
        Cell cell5 = row.createCell(5);
        cell5.setCellValue("Время окончания");
        Cell cell6 = row.createCell(6);
        cell6.setCellValue("Тип МО");
        Cell cell7 = row.createCell(7);
        cell7.setCellValue("Примечание");



        Iterator<DISP_TABLE4_V2> iterator = list.iterator();
        int rowIndex = 1;
        DISP_TABLE4 temp;
        while(iterator.hasNext()){
            temp = iterator.next();
            Row row1 = sheet.createRow(rowIndex++);
            row1.createCell(0).setCellValue(temp.getLpuId());
            row1.createCell(1).setCellValue(temp.getAddress());
            row1.createCell(2).setCellValue(temp.getDateBegin());
            row1.createCell(3).setCellValue(temp.getDateEnd());
            row1.createCell(4).setCellValue(temp.getTimeBegin());
            row1.createCell(5).setCellValue(temp.getTimeEnd());
            row1.createCell(6).setCellValue(temp.getTypeMo());
            row1.createCell(7).setCellValue(temp.getPrim());
            if(rowIndex % 100 == 0) {
                (sheet).flushRows(100); // retain 100 last rows and flush all others
            }
        }

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination =  "\\content\\donwload\\" + list.get(0).getCurrentUser().trim();


        directoryDestination = directoryServer+directoryDestination;
        String filepath = Paths.get(directoryDestination, fileName).toString();

        FileOutputStream fos = new FileOutputStream(filepath);
        workbook.write(fos);
        fos.close();


        workbook.dispose();
        return new ResponseDescription(fileName);
    }

    private ResponseDescription writeListToFile6(String fileName, List<DISP_TABLE6_V2> list) throws Exception {
        System.out.println("2 user:" + list.get(0));
        SXSSFWorkbook workbook = null;
        if(fileName.endsWith("xlsx")){
            workbook = new SXSSFWorkbook(-1);
        }else{
            throw new Exception("invalid file name, should be xls or xlsx");
        }

        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Данные");
        Row row = sheet.createRow(0);
        sheet.setColumnWidth(1,7000);
        sheet.setColumnWidth(2,7000);
        sheet.setColumnWidth(3,7000);
        sheet.setColumnWidth(5,3500);
        sheet.setColumnWidth(4,3500);
        sheet.setColumnWidth(7,7000);

        row.createCell(0).setCellValue("Код МО");
        row.createCell(1).setCellValue("Отделение");
        row.createCell(2).setCellValue("Кабинет");
        row.createCell(3).setCellValue("Дата проведения");
        row.createCell(4).setCellValue("Время начала");
        row.createCell(5).setCellValue("Время окончания");
        row.createCell(6).setCellValue("Тип медицинского осмотра");
        row.createCell(7).setCellValue("Примечание");



        Iterator<DISP_TABLE6_V2> iterator = list.iterator();
        int rowIndex = 1;
        DISP_TABLE6 temp;
        while(iterator.hasNext()){
            temp = iterator.next();
            Row row1 = sheet.createRow(rowIndex++);
            row1.createCell(0).setCellValue(temp.getLpuId());
            row1.createCell(1).setCellValue(temp.getKodOtd());
            row1.createCell(2).setCellValue(temp.getKab());
            row1.createCell(3).setCellValue(temp.getDate());
            row1.createCell(4).setCellValue(temp.getTimeBegin());
            row1.createCell(5).setCellValue(temp.getTimeEnd());
            row1.createCell(6).setCellValue(temp.getTypeMo());
            row1.createCell(7).setCellValue(temp.getPrim());
            if(rowIndex % 100 == 0) {
                (sheet).flushRows(100); // retain 100 last rows and flush all others
            }
        }

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination =  "\\content\\donwload\\" + list.get(0).getCurrentUser().trim();


        directoryDestination = directoryServer+directoryDestination;
        String filepath = Paths.get(directoryDestination, fileName).toString();

        FileOutputStream fos = new FileOutputStream(filepath);
        workbook.write(fos);
        fos.close();


        workbook.dispose();
        return new ResponseDescription(fileName);
    }

    private ResponseDescription writeListToFile1(String fileName, List<DISP_TABLE1_V2> list) throws Exception {
        System.out.println("user:" + list.get(0).getCurrentUser());
        SXSSFWorkbook workbook = null;
        if(fileName.endsWith("xlsx")){
            workbook = new SXSSFWorkbook(-1);
        }else{
            throw new Exception("invalid file name, should be xls or xlsx");
        }

        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Данные");
        Row row = sheet.createRow(0);
        sheet.setColumnWidth(1,7000);
        sheet.setColumnWidth(2,7000);
        sheet.setColumnWidth(3,7000);
        sheet.setColumnWidth(5,7000);
        sheet.setColumnWidth(6,7000);




        Cell cell0 = row.createCell(0);
        cell0.setCellValue("Код МО");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("Код отделения");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("Адрес");
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("Телефон");
        Cell cell4 = row.createCell(4);
        cell4.setCellValue("Тип МО");
        Cell cell5 = row.createCell(5);
        cell5.setCellValue("№ кабинета профилактики");
        Cell cell6 = row.createCell(6);
        cell6.setCellValue("Примечание");
        for(int i = 0; i < 7; i++){
            row.createCell(i+7).setCellValue(days[i]);
            sheet.setColumnWidth(i+7,5000);
        }


        Iterator<DISP_TABLE1_V2> iterator = list.iterator();
        int rowIndex = 1;
        DISP_TABLE1 temp;
        while(iterator.hasNext()){
            temp = iterator.next();
            Row row1 = sheet.createRow(rowIndex++);
            row1.createCell(0).setCellValue(temp.getLpuId());
            row1.createCell(1).setCellValue(temp.getOtdId());
            row1.createCell(2).setCellValue(temp.getAddress());
            row1.createCell(3).setCellValue(temp.getPhone());
            row1.createCell(4).setCellValue(temp.getTypeMo());
            row1.createCell(5).setCellValue(temp.getProf());
            row1.createCell(6).setCellValue(temp.getPrim());


            List<DISP_TABLE_DT> week = temp.getDates();
            for(int i = 0; i < week.size(); i++){
                DISP_TABLE_DT day = week.get(i);
                String value = "c " + day.getTimeBegin() + " до " + day.getTimeEnd();
                row1.createCell(i+7).setCellValue(value.contains("null") ? "нет" : value);
            }

            if(rowIndex % 100 == 0) {
                (sheet).flushRows(100); // retain 100 last rows and flush all others
            }
        }

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination =  "\\content\\donwload\\" + list.get(0).getCurrentUser().trim();


        directoryDestination = directoryServer+directoryDestination;
        String filepath = Paths.get(directoryDestination, fileName).toString();

        FileOutputStream fos = new FileOutputStream(filepath);
        workbook.write(fos);
        fos.close();


        workbook.dispose();
        return new ResponseDescription(fileName);
    }
}
