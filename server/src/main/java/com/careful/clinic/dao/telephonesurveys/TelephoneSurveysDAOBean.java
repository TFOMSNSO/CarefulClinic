package com.careful.clinic.dao.telephonesurveys;

import com.careful.clinic.model.ListExcelFiles;

import javax.ejb.Stateless;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Stateless
public class TelephoneSurveysDAOBean implements TelephoneSurveysDAO {

    //метод addDate считывает дату последнего изменения файла
    private String addDate(String directoryDestination, String ob) {

        File file = new File(directoryDestination + File.separator + ob);
        final long lastModified = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy-");
        String x = sdf.format(new Date(lastModified));
        return x;
    }

    @Override
    public Collection<?> getListFlk1(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if (id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\777";
        if (id == 1) directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\1";
        if (id == 2) directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\2";
        if (id == 4) directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\4";

        directoryDestination = directoryServer + directoryDestination;

        File path = new File(directoryDestination);
        String ob[] = path.list();
        List<String> lsn = new ArrayList<>();
        List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
        for (int i = 0; i < ob.length; i++) {
            String x =addDate(directoryDestination, ob[i]);
            lsn.add(x + ob[i]);
        }
        for (int j = 0; j < lsn.size(); j++) {
            ls.add(new ListExcelFiles(lsn.get(j), directoryDestination + File.separator + lsn.get(j)));
        }
        return ls;
    }

    @Override
    public Collection<?> getListFlk2(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if (id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\777";
        if (id == 1) directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\1";
        if (id == 2) directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\2";
        if (id == 4) directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\4";

        directoryDestination = directoryServer + directoryDestination;

        File path = new File(directoryDestination);
        String ob[] = path.list();
        List<String> lsn = new ArrayList<>();
        List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
        for (int i = 0; i < ob.length; i++) {
            String x =addDate(directoryDestination, ob[i]);
            lsn.add(x + ob[i]);
        }
        for (int j = 0; j < lsn.size(); j++) {
            ls.add(new ListExcelFiles(lsn.get(j), directoryDestination + File.separator + lsn.get(j)));
        }
        return ls;
    }

    @Override
    public Collection<?> getListFlk3(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if (id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\777";
        if (id == 1) directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\1";
        if (id == 2) directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\2";
        if (id == 4) directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\4";

        directoryDestination = directoryServer + directoryDestination;

        File path = new File(directoryDestination);
        String ob[] = path.list();
        List<String> lsn = new ArrayList<>();
        List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
        for (int i = 0; i < ob.length; i++) {
            String x =addDate(directoryDestination, ob[i]);
            lsn.add(x + ob[i]);
        }
        for (int j = 0; j < lsn.size(); j++) {
            ls.add(new ListExcelFiles(lsn.get(j), directoryDestination + File.separator + lsn.get(j)));
        }
        return ls;
    }

    @Override
    public Collection<?> getListFlk4(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if (id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\777";
        if (id == 1) directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\1";
        if (id == 2) directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\2";
        if (id == 4) directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\4";

        directoryDestination = directoryServer + directoryDestination;

        File path = new File(directoryDestination);
        String ob[] = path.list();
        List<String> lsn = new ArrayList<>();
        List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
        for (int i = 0; i < ob.length; i++) {
            String x =addDate(directoryDestination, ob[i]);
            lsn.add(x + ob[i]);
        }
        for (int j = 0; j < lsn.size(); j++) {
            ls.add(new ListExcelFiles(lsn.get(j), directoryDestination + File.separator + lsn.get(j)));
        }
        return ls;
    }


}
