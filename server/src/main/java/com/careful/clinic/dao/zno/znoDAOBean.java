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
import java.lang.reflect.Type;
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

    @PersistenceContext(unitName = "ZNOTEST")
    private EntityManager zno;


    public Collection<?> getInfoZNO(PersonSmoModel personmodel) throws ParseException {
        String tempSmo = personmodel.getSmo().trim();
        TypedQuery<ZNO_PERSON> qr;

        if(tempSmo.equals("777")) {
            qr = zno.createNamedQuery("personzno.findbyname", ZNO_PERSON.class)
                    .setParameter("personFirstname", personmodel.getFirstname().toUpperCase().trim())
                    .setParameter("personSurname", personmodel.getSurname().toUpperCase().trim())
                    .setParameter("personLastname", personmodel.getLastname().toUpperCase().trim())
                    .setParameter("birthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));
        }else{
             qr = zno.createNamedQuery("personzno.findbynamesmo", ZNO_PERSON.class)
                    .setParameter("personFirstname", personmodel.getFirstname().toUpperCase().trim())
                    .setParameter("personSurname", personmodel.getSurname().toUpperCase().trim())
                    .setParameter("personLastname", personmodel.getLastname().toUpperCase().trim())
                    .setParameter("birthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()))
                    .setParameter("smo", tempSmo);
        }

        List<?> lq = qr.getResultList();
        for(Object p : lq){
            System.out.println(p);
        }

        return lq;
    }

    @Override
    public Collection<?>  getTreatmentById(String id) {
        System.out.println("Id:" + id);
        TypedQuery<ZNO_TREATMENT> tr = zno.createNamedQuery("treatmentZno.findbyid1",ZNO_TREATMENT.class)
                .setParameter("p_id",id.trim().replaceAll("\"",""));


        List<ZNO_TREATMENT> ll = tr.getResultList();
        Set<ZNO_TREATMENT> setlist= new HashSet<>(ll);
        System.out.println("list:" + ll.size() + " set:" + setlist.size());
/*        for(ZNO_TREATMENT treatment : setlist){
            System.out.println(treatment);
        }*/
        return setlist;
    }

    @Override
    public Expertise getExpertiseById(PersonExpModel person) throws ParseException {
        System.out.println( "person:" + person);
        Expertise e = null;
        TypedQuery<Expertise> tq = zno.createNamedQuery("Expertise.findex",Expertise.class)
                .setParameter("personSurname",person.getSurname().toUpperCase().trim())
                .setParameter("personName",person.getFirstname().toUpperCase().trim())
                .setParameter("personLastname",person.getLastname().toUpperCase().trim())
                .setParameter("birthday", new SimpleDateFormat("dd.MM.yyyy").parse(person.getBithday()))
                .setParameter("begintreat", new SimpleDateFormat("dd.MM.yyyy").parse(person.getDateBegin()))
                .setParameter("endtreat", new SimpleDateFormat("dd.MM.yyyy").parse(person.getDateEnd()));

        try {
            //System.out.println("a------------------------------------------------------------------------------------");
            e = tq.getSingleResult();
            //System.out.println("b------------------------------------------------------------------------------------");
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
    public ResponseDescription exportToExcel(List<ZNO_PERSON_YEARS> list) throws Exception {
        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
        String current_time_str = time_formatter.format(System.currentTimeMillis());
        try {
            return writeListToFile("org_" + list.get(0).getSmo() + "_" + current_time_str + ".xlsx", list);
        }catch (Exception ex){
            return new ResponseDescription("Ошибка во время сохранения файла!");
        }
    }

    @Override
    public ResponseDescription writeListToFile(String fileName, List<ZNO_PERSON_YEARS> list) throws Exception {

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


        Iterator<ZNO_PERSON_YEARS> iterator = list.iterator();
        int rowIndex = 1;
        ZNO_PERSON_YEARS person;
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
        return new ResponseDescription("Файл готов к загрузке.");
    }


    @Override
    public Collection<?> getInfoZNOKeys(SearchZnoKeysModel keysmodel) {
        System.out.println(keysmodel);
        StringBuilder sb = new StringBuilder();
        sb.append("FROM ZNO_PERSON z ");
        if(keysmodel.getCurrentUser() != 777){
            sb.append("WHERE z.smo = ");
            sb.append(keysmodel.getCurrentUser());
//            sb.append(" AND ");
        }else{
            ArrayList<String> smos = new ArrayList<>();
            if(keysmodel.isIngos()){
                smos.add("4");
            }
            if(keysmodel.isSimaz()){
                smos.add("1");
            }
            if(keysmodel.isVtb()){
                smos.add("2");
            }
            if(!smos.isEmpty()){
                sb.append(" WHERE ");
                for(int i = 0; i< smos.size()-1;i++)
                    sb.append(" z.smo = " + smos.get(i) + " OR ");

                sb.append(" z.smo = "  + smos.get(smos.size()-1));
            }
        }
        System.out.println(zno);
        TypedQuery<ZNO_PERSON> result = zno.createQuery(sb.toString(),ZNO_PERSON.class);
        if(keysmodel.getCount_notes() != null || !keysmodel.getCount_notes().trim().equals("")) {
            result = result.setMaxResults(Integer.parseInt(keysmodel.getCount_notes()));
        }
        List<ZNO_PERSON> zp = result.getResultList();

        System.out.println("length:" + zp.size());
        return zp;
    }



    public void configureEntityManager(){
        zno = Persistence.createEntityManagerFactory("ZNOTEST").createEntityManager();
    }
}
