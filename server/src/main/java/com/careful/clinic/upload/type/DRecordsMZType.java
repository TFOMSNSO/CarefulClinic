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
import java.util.Date;
import java.util.List;

public class DRecordsMZType  extends AbstractDataUploadType{

    private SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
    private SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat df3 = new SimpleDateFormat("dd.MM.yyyy");

    public DRecordsMZType(OPCPackage pkg, String fileName) throws IOException {
        super.set(pkg,fileName);
    }

    @Override
    IDataUploadType getInstansUploadData() {
        return null;
    }

    /**Метод проверяет корректность значений в ячейках Excel файла*/
    @Override
    public void checkOutStructure() throws IOException, ParseDataExcelException, CheckStructureExcelException, CheckTypizineExcelException {
        System.out.println("Process Typizine (DRecordsMZType) "+this.getClass().getName());

        DataFormatter formatter = new DataFormatter();
        XSSFWorkbook workbook = super.getXSSFWorkbook();
        Sheet sheet =  workbook.getSheetAt(0);
        Row row = null;
        StringBuilder strb = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        Date date = new Date();
        Date date2 = new Date(System.currentTimeMillis() - 5184000000l*108);

        for(int j=1; j< sheet.getPhysicalNumberOfRows(); j++){

            row = sheet.getRow(j);
            try {
                strb = super.checkDataFormat(row, new Integer[]{4}) ? strb.append("") : strb.append("ERROR Неверный формат даты рождения. Строка "+ (j+1)+"\r\n");
                strb = super.checkDataFormat(row, new Integer[]{7}) ? strb.append("") : strb.append("ERROR Неверный формат поля 'Взят на учет'. Строка "+ (j+1)+"\r\n");
                strb = super.checkDataFormat(row, new Integer[]{13}) ? strb.append("") : strb.append("ERROR Неверный формат поля 'Дата планируемого визита'. Строка "+ (j+1)+"\r\n");
                if(((row.getCell(4).getDateCellValue()).after(date))||((row.getCell(4).getDateCellValue().after(date2))))
                {
                    strb.append("ERROR В поле 'Дата рождения' некорректная дата либо это ребёнок. Строка "+ (j+1)+"\r\n. ");
                }
                if(((row.getCell(7).getDateCellValue()).after(date)))
                {
                    strb.append("ERROR некорректная дата в поле 'Взят на учет'. Строка "+ (j+1)+"\r\n");
                }
                if(row.getCell(2).getStringCellValue().length()>6)
                {
                    strb.append("ERROR В поле 'МКБ' не может быть более 6 символов. Строка "+ (j+1)+"\r\n. ");
                }
                if(row.getCell(2).getStringCellValue().matches("[^A-Z].+"))
                {
                    strb.append("ERROR В поле 'МКБ' не корректное значение. Строка "+ (j+1)+"\r\n. ");
                }
            }catch (Exception e){
                strb.append("ERROR Непредвиденная ошибка. Строка "+ (j+1)+"\r\n");
            }
            boolean bl = super.isLastRowCustom(formatter,row);
            if(bl) break;

        }

        if(strb.toString().contains("ERROR")) { System.out.println(strb.toString()); throw new CheckTypizineExcelException(strb.toString()); }

    }

    @Override
    public void checkOutTypizine() throws IOException, CheckTypizineExcelException, ParseException {

    }

    /**Метод по строчно парсит Excel файл и создаёт insert каждой строки*/
    @Override
    public void constructQuery() throws ParseException, IOException {
        DataFormatter formatter = new DataFormatter();
        XSSFWorkbook workbook = super.getXSSFWorkbook();
        Sheet sheet =  workbook.getSheetAt(0);
        Row row = null;
        int row_first = 1; // начало считывания таблцы
        StringBuilder sb = new StringBuilder();

        for(int j=row_first; j< sheet.getPhysicalNumberOfRows(); j++){
            row = sheet.getRow(j);

            sb.append("insert into D_RECORDS_MZ  values(   ");
            for(int i=1;  i < 16; i++){
                sb.append("'");
                if(i == 4){
                    try{
                        sb.append(df2.format(df1.parse(row.getCell(i).toString())));
                    }catch ( java.text.ParseException e) {
                        sb.append(df2.format(df3.parse(row.getCell(i).toString())));
                    }
                }
                else if(i == 7){
                    try{
                        sb.append(df2.format(df1.parse(row.getCell(i).toString())));
                    }catch ( java.text.ParseException e) {
                        sb.append(df2.format(df3.parse(row.getCell(i).toString())));
                    }
                }
                else if(i == 13){
                    try{
                        sb.append(df2.format(df1.parse(row.getCell(i).toString())));
                    }catch ( java.text.ParseException e) {
                        sb.append(df2.format(df3.parse(row.getCell(i).toString())));
                    }
                }
                else{
                    sb.append(formatter.formatCellValue(row.getCell(i)).toUpperCase());
                }
                sb.append("'");
                sb.append(",");
            }


            sb.append("");

            sb.append("sysdate)");

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

    }

    /**Метод создаёт запросы на проверку дублей каждой мтроки*/
    @Override
    public String construct_querySelect(String str) {
        StringBuilder  sb = new StringBuilder();
        String tmp_m[] = null, tmp = null;
        tmp = str.replace("insert into", "select  count(*) from");
        sb.append(tmp.substring(0, 34));

        tmp_m = tmp.substring(46).split(",");
        sb.append(" where ");
        sb.append("LPU=");
        sb.append(tmp_m[0]);
        sb.append(" and MKB=");
        sb.append(tmp_m[1]);
        sb.append(" and FIO=");
        sb.append(tmp_m[2]);
        sb.append(" and DR=");
        sb.append(tmp_m[3]);
        sb.append(" and DATE_PLAN=");
        sb.append(tmp_m[12]);
        return sb.toString();
    }
}
