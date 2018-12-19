package com.careful.clinic.dao.telephonesurveys;

import com.careful.clinic.model.ListExcelFiles;

import javax.ejb.Stateless;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
public class TelephoneSurveysDAOBean implements TelephoneSurveysDAO {
    @Override
    public Collection<?> getListFlk1(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\4";

        directoryDestination = directoryServer+directoryDestination;

        File path = new File(directoryDestination);
        String ob[] = path.list();
        List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
        for (int i = 0; i < ob.length; i++) {
            ls.add(new ListExcelFiles(ob[i], directoryDestination + File.separator + ob[i]));
        }
        return ls;
    }

    @Override
    public Collection<?> getListFlk2(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\4";

        directoryDestination = directoryServer+directoryDestination;

        File path = new File(directoryDestination);
        String ob[] = path.list();
        List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
        for (int i = 0; i < ob.length; i++) {
            ls.add(new ListExcelFiles(ob[i], directoryDestination + File.separator + ob[i]));
        }
        return ls;
    }

    @Override
    public Collection<?> getListFlk3(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\4";

        directoryDestination = directoryServer+directoryDestination;

        File path = new File(directoryDestination);
        String ob[] = path.list();
        List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
        for (int i = 0; i < ob.length; i++) {
            ls.add(new ListExcelFiles(ob[i], directoryDestination + File.separator + ob[i]));
        }
        return ls;
    }

    @Override
    public Collection<?> getListFlk4(Integer id) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\4";

        directoryDestination = directoryServer+directoryDestination;

        File path = new File(directoryDestination);
        String ob[] = path.list();
        List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
        for (int i = 0; i < ob.length; i++) {
            ls.add(new ListExcelFiles(ob[i], directoryDestination + File.separator + ob[i]));
        }
        return ls;
    }
}
