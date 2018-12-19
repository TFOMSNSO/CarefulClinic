package com.careful.clinic.rs.telephonesurveys;

import com.careful.clinic.dao.telephonesurveys.TelephoneSurveysDAO;
import org.xml.sax.SAXException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

@Path("/telephonesurveys")
public class RestServiceTelephoneSurveys {
    @EJB
    TelephoneSurveysDAO telephoneSurveysDAO;

    @GET
    @Path("/listFilesTable3/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<?> listFilesStatInform(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

        List<?> df = (List<?>) telephoneSurveysDAO.getListFlk1(id);

        return df;
    }

    @GET
    @Path("/listFilesTable3Url/{place}/{id}/{namefile}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/vnd.ms-excel")
    public Response getdownloadFile(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\telephonesurveys\\flk1\\4";

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
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/listFilesTable4/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<?> listFilesStatInformTable4(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

        List<?> df = (List<?>) telephoneSurveysDAO.getListFlk2(id);

        return df;
    }

    @GET
    @Path("/listFilesTable4Url/{place}/{id}/{namefile}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/vnd.ms-excel")
    public Response getdownloadFileTable4(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\telephonesurveys\\flk2\\4";

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
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/listFilesTable5/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<?> listFilesStatInformTable5(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

        List<?> df = (List<?>) telephoneSurveysDAO.getListFlk3(id);

        return df;
    }

    @GET
    @Path("/listFilesTable5Url/{place}/{id}/{namefile}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/vnd.ms-excel")
    public Response getdownloadFileTable5(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\telephonesurveys\\flk3\\4";

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
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/listFilesTable6/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<?> listFilesStatInformTable6(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

        List<?> df = (List<?>) telephoneSurveysDAO.getListFlk4(id);

        return df;
    }

    @GET
    @Path("/listFilesTable6Url/{place}/{id}/{namefile}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/vnd.ms-excel")
    public Response getdownloadFileTable6(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\telephonesurveys\\flk4\\4";

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
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
