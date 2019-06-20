package com.careful.clinic.dao.zno;

import com.careful.clinic.model.*;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.enterprise.inject.Typed;
import javax.persistence.*;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Stateless
public class znoDAOBean implements znoDAO{

    @PersistenceContext(unitName = "testunit")
    private EntityManager oracletestem;

    @PersistenceContext(unitName = "MEDEXP")
    private EntityManager medExp;

    public Collection<?> getInfoZNO(PersonSmoModel personmodel) throws ParseException {
        System.out.println("znoDAOBean.java:getInfoZNO:lulcs" + personmodel);
        String tempSmo = personmodel.getSmo().trim();
        TypedQuery<ZNO_PERSON> qr;

        if(tempSmo.equals("777")) {
            qr = oracletestem.createNamedQuery("personzno.findbyname", ZNO_PERSON.class)
                    .setParameter("personFirstname", personmodel.getFirstname().toUpperCase().trim())
                    .setParameter("personSurname", personmodel.getSurname().toUpperCase().trim())
                    .setParameter("personLastname", personmodel.getLastname().toUpperCase().trim())
                    .setParameter("birthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));
        }else{
             qr = oracletestem.createNamedQuery("personzno.findbynamesmo", ZNO_PERSON.class)
                    .setParameter("personFirstname", personmodel.getFirstname().toUpperCase().trim())
                    .setParameter("personSurname", personmodel.getSurname().toUpperCase().trim())
                    .setParameter("personLastname", personmodel.getLastname().toUpperCase().trim())
                    .setParameter("birthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()))
                    .setParameter("smo", tempSmo);
        }




        List<?> lq = qr.getResultList();


        return lq;
    }

    @Override
    public Collection<?>  getTreatmentById(String id) {
        System.out.println("Id:" + id);
        TypedQuery<ZNO_TREATMENT> tr = oracletestem.createNamedQuery("treatmentZno.findbyid1",ZNO_TREATMENT.class)
                .setParameter("p_id",id.trim().replaceAll("\"",""));

        List<ZNO_TREATMENT> ll = tr.getResultList();
        return ll;
    }

    @Override
    public Expertise getExpertiseById(PersonExpModel person) throws ParseException {
        System.out.println( "person:" + person);
        Expertise e = null;
        TypedQuery<Expertise> tq = medExp.createNamedQuery("Expertise.findex",Expertise.class)
                .setParameter("personSurname",person.getSurname().toUpperCase().trim())
                .setParameter("personName",person.getFirstname().toUpperCase().trim())
                .setParameter("personLastname",person.getLastname().toUpperCase().trim())
                .setParameter("birthday", new SimpleDateFormat("dd.MM.yyyy").parse(person.getBithday()))
                .setParameter("begintreat", new SimpleDateFormat("dd.MM.yyyy").parse(person.getDateBegin()))
                .setParameter("endtreat", new SimpleDateFormat("dd.MM.yyyy").parse(person.getDateEnd()));

        try {
            System.out.println("a------------------------------------------------------------------------------------");
            e = tq.getSingleResult();
            System.out.println("b------------------------------------------------------------------------------------");
            System.out.println(e);
            return e;
        }catch(NonUniqueResultException ex){
            return e;
        }catch(NoResultException ex){
            System.out.println("No expertise found for  " + person);
            return null;
        }
    }

    @Override
    public Collection<?> exportToExcel(List<ZNO_PERSON> list) throws Exception {
        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
        String current_time_str = time_formatter.format(System.currentTimeMillis());

        writeListToFile("org_" + list.get(0).getSmo() + "_"+current_time_str+".xlsx",list);
        return new ArrayList<>();
    }

    @Override
    public void writeListToFile(String fileName, List<ZNO_PERSON> list) throws Exception {
        System.out.println("filename:" + fileName);
        for(int i =0; i < list.size(); i++){
            System.out.println(list.get(i));
        }


        SXSSFWorkbook workbook = null;

        if(fileName.endsWith("xlsx")){
            workbook = new SXSSFWorkbook(-1);
        }else{
            throw new Exception("invalid file name, should be xls or xlsx");
        }

        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Данные");
        Row row = sheet.createRow(0);
        for(int i =0; i < 5; i++){
            sheet.setColumnWidth(i,7000);
        }

        Cell cell0 = row.createCell(0);
        cell0.setCellValue("Фамилия");

        Cell cell1 = row.createCell(1);
        cell1.setCellValue("Имя");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("Отчество");
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("Дата рождения");
        Cell cell4 = row.createCell(4);
        cell4.setCellValue("Полных лет");


        Iterator<ZNO_PERSON> iterator = list.iterator();
        int rowIndex = 1;
        ZNO_PERSON person;
        while(iterator.hasNext()){
            person = iterator.next();
            Row row1 = sheet.createRow(rowIndex++);
            row1.createCell(0).setCellValue(person.getPersonSurname());
            row1.createCell(1).setCellValue(person.getPersonKindfirstname());
            row1.createCell(2).setCellValue(person.getPersonKindlastname());
            row1.createCell(3).setCellValue(person.getPersonBirthday());
            row1.createCell(4).setCellValue(person.getYears());
            if(rowIndex % 100 == 0) {
                (sheet).flushRows(100); // retain 100 last rows and flush all others
            }
        }

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";
        if(list.get(0).getCurrentUser() == 777 ) directoryDestination = "\\content\\donwload\\777";
        if(list.get(0).getCurrentUser()  == 1 ) directoryDestination = "\\content\\donwload\\1";
        if(list.get(0).getCurrentUser()  == 2 ) directoryDestination = "\\content\\donwload\\2";
        if(list.get(0).getCurrentUser()  == 4 ) directoryDestination = "\\content\\donwload\\4";


        directoryDestination = directoryServer+directoryDestination;
        String filepath = Paths.get(directoryDestination, fileName).toString();

        FileOutputStream fos = new FileOutputStream(filepath);
        workbook.write(fos);
        fos.close();


        workbook.dispose();
        System.out.println("loaded");
    }


    @Override
    public Collection<?> getInfoZNOKeys(SearchZnoKeysModel keysmodel) {
        System.out.println(keysmodel);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT z.ID1,z.ID2,z.FAM,Z.IM,Z.OT,Z.DR,Z.SMO z FROM ZNO_PERSON z ");
        if(keysmodel.getCurrentUser() != 777){
            sb.append(" WHERE z.SMO = ");
            sb.append(keysmodel.getCurrentUser());
        }
        sb.append(" FETCH FIRST ");
        sb.append(keysmodel.getCount_notes());
        sb.append(" ROWS WITH TIES");

        System.out.println(sb);
        Query query = oracletestem.createNativeQuery(sb.toString());
        List<Object[]> list = query.getResultList();
        List<ZNO_PERSON> zp = new ArrayList<>();

        if(!list.isEmpty()) {
            for (Object[] obj : list) {
                ZNO_PERSON p = new ZNO_PERSON();
                p.setId1(obj[0].toString());
                p.setId2(obj[1].toString());
                p.setPersonSurname((String) obj[2]);
                p.setPersonKindfirstname((String) obj[3]);
                p.setPersonKindlastname((String) obj[4]);
                p.setPersonBirthday((Date) obj[5]);
                p.setSmo(obj[6].toString());
                zp.add(p);
            }
        }
        return zp;
    }
}
