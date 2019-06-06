package com.careful.clinic.rs.zno;


import com.careful.clinic.dao.prophylactic.ProphylacticDAO;
import com.careful.clinic.dao.prophylactic.XA_Dream2Dao;
import com.careful.clinic.dao.zno.znoDAO;
import com.careful.clinic.dao.zno.znoDAOBean;
import com.careful.clinic.guid.RandomGUID;
import com.careful.clinic.model.PersonModel;
import com.careful.clinic.upload.interfase.factory.UploadDataFactory;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.mail.Session;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

@javax.ws.rs.Path("/zno")
public class RestServiceZno {
    final String directoryServer = System.getProperty("jboss.home.dir");

    @EJB
    UploadDataFactory uploadFactory;
    @EJB
    znoDAO zno;
    @EJB
    XA_Dream2Dao xa_Dream2Dao;

    @EJB
    private RandomGUID randomGuid;

    @Resource(name = "java:jboss/mail/ofoms")
    private Session session;

    private String UPLOADED_FILE_PATH = "";

    @POST
    @Path("/search_person_zno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<?> searchZno(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
        System.out.println("ZNO RESTSERVICE");
        List<?> df = (List<?>) zno.getInfoZNO(personmodel);

        return	df;
    }
}
