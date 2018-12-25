package com.careful.clinic.dao.statinform;

import com.careful.clinic.model.ListExcelFiles;

import javax.ejb.Stateless;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Stateless
public class StatInformDAOBean implements StatInformDAO {

    private String addDate(String directoryDestination, String ob) {

        File file = new File(directoryDestination + File.separator + ob);
        final long lastModified = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy-");
        String x = sdf.format(new Date(lastModified));
        return x;
    }

    @Override
    public Collection<?> getListStatInform(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\statinform\\report_info_1in3\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\statinform\\report_info_1in3\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\statinform\\report_info_1in3\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\statinform\\report_info_1in3\\4";

        directoryDestination = directoryServer+directoryDestination;

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
    public Collection<?> getListStatInformTable4(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\statinform\\report_info_1in2\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\statinform\\report_info_1in2\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\statinform\\report_info_1in2\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\statinform\\report_info_1in2\\4";

        directoryDestination = directoryServer+directoryDestination;

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
    public Collection<?> getListStatInformTable5(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\statinform\\report_info_2stage\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\statinform\\report_info_2stage\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\statinform\\report_info_2stage\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\statinform\\report_info_2stage\\4";

        directoryDestination = directoryServer+directoryDestination;

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
    public Collection<?> getListStatInformTable6(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\statinform\\report_info_D_control\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\statinform\\report_info_D_control\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\statinform\\report_info_D_control\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\statinform\\report_info_D_control\\4";

        directoryDestination = directoryServer+directoryDestination;

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