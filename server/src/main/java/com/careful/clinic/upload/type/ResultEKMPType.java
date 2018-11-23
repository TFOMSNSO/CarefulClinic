package com.careful.clinic.upload.type;

import com.careful.clinic.exceptions.CheckStructureExcelException;
import com.careful.clinic.exceptions.CheckTypizineExcelException;
import com.careful.clinic.exceptions.ParseDataExcelException;
import com.careful.clinic.upload.interfase.IDataUploadType;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ResultEKMPType extends AbstractDataUploadType{

    private SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
    private SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat df3 = new SimpleDateFormat("dd.MM.yyyy");

    public ResultEKMPType(OPCPackage pkg, String fileName) throws IOException {
        super.set(pkg,fileName);
    }

    @Override
    public void checkOutStructure() throws IOException, ParseDataExcelException, CheckStructureExcelException {
        XSSFWorkbook workbook = super.getXSSFWorkbook();
        Sheet sheet =  workbook.getSheetAt(0);
        Row row = null;
        StringBuilder strb = new StringBuilder();

        strb  = sheet.getPhysicalNumberOfRows() < 50_000 ? strb.append("VALID Количество строк допустимо. \r\n") : strb.append("ERROR Превышено допустимое количество строк в загружаемом файле. Не более 50 000 строк. \r\n");
        strb  = sheet.getRow(0).getCell(0).getStringCellValue().trim().equalsIgnoreCase("Тип запроса") ? strb.append("VALID Тэг 'Тип запроса' - корректно. \r\n") : strb.append("ERROR Тэг 'Тип запроса' - некорректно. \r\n");
        strb  = sheet.getRow(1).getCell(0).getStringCellValue().trim().equalsIgnoreCase("Дата формирования") ? strb.append("VALID Тэг 'Дата формирования' - корректно \r\n") : strb.append("ERROR Тэг 'Дата формирования' - некорректно \r\n");
        strb  = sheet.getRow(2).getCell(0).getStringCellValue().trim().equalsIgnoreCase("Смо") ? strb.append("VALID Тэг 'Смо'  - корректно.  \r\n") : strb.append("ERROR Тэг 'Смо' - некорректно. \r\n");

        /*strb  = sheet.getRow(3).getCell(0).getStringCellValue().trim().equalsIgnoreCase("код МО") ? strb.append("VALID Тэг 'код МО' - корректно.  \r\n") : strb.append("ERROR Тэг 'код МО' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(1).getStringCellValue().trim().equalsIgnoreCase("Наименование МО") ? strb.append("VALID Тэг 'Наименование МО' - корректно.  \r\n") : strb.append("ERROR Тэг 'Наименование МО' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(2).getStringCellValue().trim().equalsIgnoreCase("Фамилия") ? strb.append("VALID Тэг 'Фамилия' - корректно.  \r\n") : strb.append("ERROR Тэг 'Фамилия' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(3).getStringCellValue().trim().equalsIgnoreCase("Имя") ? strb.append("VALID Тэг 'Имя' - корректно.  \r\n") : strb.append("ERROR Тэг 'Имя' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(4).getStringCellValue().trim().equalsIgnoreCase("Отчество") ? strb.append("VALID Тэг 'Отчество' - корректно.  \r\n") : strb.append("ERROR Тэг 'Отчество' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(5).getStringCellValue().trim().equalsIgnoreCase("Дата рождения") ? strb.append("VALID Тэг 'Дата рождения' - корректно.  \r\n") : strb.append("ERROR Тэг 'Дата рождения' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(6).getStringCellValue().trim().equalsIgnoreCase("Группа здоровья (22 - 3а; 23 - 3б)") ? strb.append("VALID Тэг 'Группа здоровья (22 - 3а; 23 - 3б)' - корректно.  \r\n") : strb.append("ERROR Тэг 'Группа здоровья (22 - 3а; 23 - 3б)' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(7).getStringCellValue().trim().equalsIgnoreCase("Результат ЭКМП  (0 - нарушения не выявлены, 1 - нарушения выявлены)") ? strb.append("VALID Тэг 'Результат ЭКМП  (0 - нарушения не выявлены, 1 - нарушения выявлены)' - корректно.  \r\n") : strb.append("ERROR Тэг 'Результат ЭКМП  (0 - нарушения не выявлены, 1 - нарушения выявлены)' - некорректно. \r\n");

        strb  = sheet.getRow(3).getCell(8).getStringCellValue().trim().equalsIgnoreCase("в случае выявления нарушений: кол-во нарушений") ? strb.append("VALID Тэг 'в случае выявления нарушений: кол-во нарушений' - корректно.  \r\n") : strb.append("ERROR Тэг 'в случае выявления нарушений: кол-во нарушений' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(9).getStringCellValue().trim().equalsIgnoreCase("в случае выявления нарушений: код нарушения") ? strb.append("VALID Тэг 'в случае выявления нарушений: код нарушения' - корректно.  \r\n") : strb.append("ERROR Тэг 'в случае выявления нарушений: код нарушения' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(10).getStringCellValue().trim().equalsIgnoreCase("в случае выявления нарушений: код нарушения") ? strb.append("VALID Тэг 'в случае выявления нарушений: код нарушения' - корректно.  \r\n") : strb.append("ERROR Тэг 'в случае выявления нарушений: код нарушения' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(11).getStringCellValue().trim().equalsIgnoreCase("в случае выявления нарушений: код нарушения") ? strb.append("VALID Тэг 'в случае выявления нарушений: код нарушения' - корректно.  \r\n") : strb.append("ERROR Тэг 'в случае выявления нарушений: код нарушения' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(12).getStringCellValue().trim().equalsIgnoreCase("в случае выявления нарушений: причина, по которой выставлено нарушение") ? strb.append("VALID Тэг 'в случае выявления нарушений: причина, по которой выставлено нарушение' - корректно.  \r\n") : strb.append("ERROR Тэг 'в случае выявления нарушений: причина, по которой выставлено нарушение' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(13).getStringCellValue().trim().equalsIgnoreCase("в случае отсутствия нарушений: причина, по которой отсутствует нарушение") ? strb.append("VALID Тэг 'в случае отсутствия нарушений: причина, по которой отсутствует нарушение' - корректно.  \r\n") : strb.append("ERROR Тэг 'в случае отсутствия нарушений: причина, по которой отсутствует нарушение' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(14).getStringCellValue().trim().equalsIgnoreCase("индивидуальное согласие (1 - дано, нет - 2)") ? strb.append("VALID Тэг 'индивидуальное согласие (1 - дано, нет - 2)' - корректно.  \r\n") : strb.append("ERROR Тэг 'индивидуальное согласие (1 - дано, нет - 2)' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(15).getStringCellValue().trim().equalsIgnoreCase("осуществлялось индивидуальное информирование (1 - да, 2 - нет)") ? strb.append("VALID Тэг 'осуществлялось индивидуальное информирование (1 - да, 2 - нет)' - корректно.  \r\n") : strb.append("ERROR Тэг 'осуществлялось индивидуальное информирование (1 - да, 2 - нет)' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(16).getStringCellValue().trim().equalsIgnoreCase("Поставлен на Д-учет после индивидуального информирования (1 - да, 2 - нет)") ? strb.append("VALID Тэг 'Поставлен на Д-учет после индивидуального информирования (1 - да, 2 - нет)' - корректно.  \r\n") : strb.append("ERROR Тэг 'Поставлен на Д-учет после индивидуального информирования (1 - да, 2 - нет)' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(17).getStringCellValue().trim().equalsIgnoreCase("Госпитализирован после индивидуального информирования (1 - да, 2 - нет)") ? strb.append("VALID Тэг 'Госпитализирован после индивидуального информирования (1 - да, 2 - нет)' - корректно.  \r\n") : strb.append("ERROR Тэг 'Госпитализирован после индивидуального информирования (1 - да, 2 - нет)' - некорректно. \r\n");
        strb  = sheet.getRow(3).getCell(18).getStringCellValue().trim().equalsIgnoreCase("примечание") ? strb.append("VALID Тэг 'примечание' - корректно.  \r\n") : strb.append("ERROR Тэг 'примечание' - некорректно. \r\n");
*/
        if(strb.toString().contains("ERROR")) { System.out.println(strb.toString()); throw new CheckStructureExcelException(strb.toString()); }

    }

    @Override
    public void checkOutTypizine() throws IOException, CheckTypizineExcelException, ParseException {
        System.out.println("Process Typizine (ResultEKMPType) "+this.getClass().getName());

        DataFormatter formatter = new DataFormatter();
        XSSFWorkbook workbook = super.getXSSFWorkbook();
        Sheet sheet =  workbook.getSheetAt(0);
        Row row = null;
        StringBuilder strb = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        strb = super.checkDataFormat(sheet.getRow(1),1) ? strb.append("") : strb.append("ERROR Неверный формат даты Поле 'Дата формирования'. "+"\r\n");
        strb = sheet.getRow(2).getCell(1).getNumericCellValue() > 4 || sheet.getRow(2).getCell(1).getNumericCellValue() < 1 ? strb.append("ERROR Неверно указан id смо. Поле Смо. "+"\r\n") : strb.append("");

        for(int j=4; j< sheet.getPhysicalNumberOfRows(); j++){

            row = sheet.getRow(j);
            try {
            strb = (super.processNumericCell(row, new Integer[]{12}) || super.processNumericCell(row, new Integer[]{13}))  ? strb.append("") : strb.append("ERROR В столбце 13 или 14 формат ячеек не является числом. Строка "+(j+1)+"\r\n");
            strb = super.processNumericCell(row, new Integer[]{0,6,7,8,14,15,16,17})  ? strb.append("") : strb.append("ERROR В одном или нескольких столбцах формат ячеек не является числом. Строка "+(j+1)+"\r\n");
            strb = row.getCell(7).getNumericCellValue() > 1 || row.getCell(7).getNumericCellValue() < 0  ? strb.append("ERROR Неверно значение в поле 'Результат ЭКМП' . Строка "+ (j+1)+"\r\n") : strb.append("");

            strb = super.checkDataFormat(row, new Integer[]{5}) ? strb.append("") : strb.append("ERROR Неверный формат даты рождения. Строка "+ (j+1)+"\r\n");
            }catch (Exception e){
                strb.append("ERROR Непредвиденная ошибка. Строка "+ (j+1)+"\r\n");
            }
            boolean bl = super.isLastRowCustom(formatter,row);
            if(bl) break;

        }

        if(strb.toString().contains("ERROR")) { System.out.println(strb.toString()); throw new CheckTypizineExcelException(strb.toString()); }
    }

    @Override
    public void constructQuery() throws ParseException, IOException {
        DataFormatter formatter = new DataFormatter();
        XSSFWorkbook workbook = super.getXSSFWorkbook();
        Sheet sheet =  workbook.getSheetAt(0);
        Row row = null;
        int row_first = 4; // начало считывания таблцы
        StringBuilder sb = new StringBuilder();
        String smo_id = String.valueOf((int)sheet.getRow(2).getCell(1).getNumericCellValue());

        for(int j=row_first; j< sheet.getPhysicalNumberOfRows(); j++){
            row = sheet.getRow(j);

            sb.append("insert into RESULT_EKMP   values('',");
            //(MO,NAME_MO,SURNAME,FIRST_NAME,SEC_NAME,BIRTHDAY,REZOBR,RES_EKMP,COUNT_VIOLATION,CODE_VIOLATION1,CODE_VIOLATION2,CODE_VIOLATION3,CAUSE_VIOLATION,CAUSE_NULL_VIOLATION,INDIVID_ACCORD,INDIVID_INFORMING,ON_D_CONTROL,HOSPITALIZED,NOTE,SMO,DATE_DOC,D_INSERT)
            for(int i=0;  i < 19; i++){
                sb.append("'");
                if(i == 5){
                    try{
                        sb.append(df2.format(df1.parse(row.getCell(i).toString())));
                    }catch ( java.text.ParseException e) {
                        sb.append(df2.format(df3.parse(row.getCell(i).toString())));
                    }
                }else{
                    sb.append(formatter.formatCellValue(row.getCell(i)).toUpperCase());
                }
                sb.append("'");
                sb.append(",");
            }
            sb.append("");

            sb.append(smo_id+",trunc(sysdate),sysdate)");

            super.getListInsertQueries().add(sb.toString());
            sb.delete(0, sb.length());


            boolean bl = isLastRowCustom(formatter,row);
            if(bl) break;
        }
    }

    @Override
    public void checkSinchronizeData() {

    }

    @Override
    public void checkSinchronizeData(List<String> listquery) {
        // TODO Auto-generated method stub
    }

    @Override
    public String construct_querySelect(String str) {
        StringBuilder  sb = new StringBuilder();
        String tmp_m[] = null, tmp = null;
        tmp = str.replace("insert into", "select  count(*) from");
        sb.append(tmp.substring(0, 34));

        tmp_m = tmp.substring(29).split(",");
        sb.append(" where ");
        sb.append("mo=");
        sb.append(tmp_m[1]);
        sb.append(" and name_mo=");
        sb.append(tmp_m[2]);
        sb.append(" and surname=");
        sb.append(tmp_m[3]);
        sb.append(" and first_name=");
        sb.append(tmp_m[4]);
        sb.append(" and sec_name=");
        sb.append(tmp_m[5]);
        sb.append(" and birthday=");
        sb.append(tmp_m[6]);
        sb.append(" and rezobr=");
        sb.append(tmp_m[7]);
        sb.append(" and res_ekmp=");
        sb.append(tmp_m[8]);
        if(tmp_m[9].contains("''")){
            sb.append(" and count_violation");
            tmp_m[9]=" is null";
        }else{
            sb.append(" and count_violation=");
        }
        sb.append(tmp_m[9]);
        if(tmp_m[10].contains("''")){
            sb.append(" and code_violation1");
            tmp_m[10]=" is null";
        }else{
            sb.append(" and code_violation1=");
        }
        sb.append(tmp_m[10]);
        if(tmp_m[11].contains("''")){
            sb.append(" and code_violation2");
            tmp_m[11]=" is null";
        }else {
            sb.append(" and code_violation2=");
        }
        sb.append(tmp_m[11]);
        /////////////////////
        if(tmp_m[12].contains("''")){
            sb.append(" and code_violation3");
            tmp_m[12]=" is null";
        }else{
            sb.append(" and code_violation3=");
        }
        sb.append(tmp_m[12]);
        /////////////////////////
        /*sb.append(" and cause_violation=");
        sb.append(tmp_m[13]);*/
        ////////////////////////
        if(tmp_m[13].contains("''")){
            sb.append(" and cause_violation");
            tmp_m[13]=" is null";
        }else{
            sb.append(" and cause_violation=");
        }
        sb.append(tmp_m[13]);
        //////////////////////////////
        sb.append(" and cause_null_violation=");
        sb.append(tmp_m[14]);
        sb.append(" and individ_accord=");
        sb.append(tmp_m[15]);
        sb.append(" and individ_informing=");
        sb.append(tmp_m[16]);
        sb.append(" and on_d_control=");
        sb.append(tmp_m[17]);
        sb.append(" and hospitalized=");
        sb.append(tmp_m[18]);
   /*     sb.append(" and r.note=");
        sb.append(tmp_m[19]);*/
        sb.append(" and smo=");
        sb.append(tmp_m[20]);
/*        sb.append(" and r.date_doc=");
        sb.append(tmp_m[21]);
        sb.append(" and r.d_insert=");
        sb.append(tmp_m[22]);
        sb.append(" and r.note=");
        sb.append(tmp_m[23]);*/
        return sb.toString();
    }

    @Override
    IDataUploadType getInstansUploadData() {
        return this;
    }
}