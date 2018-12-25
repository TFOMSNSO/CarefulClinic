package com.careful.clinic.rs.statinform;

import com.careful.clinic.dao.statinform.StatInformDAO;
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

@javax.ws.rs.Path("/statinform")
public class RestServiceStatinform {

    @EJB
    StatInformDAO statInformDAO;

    @GET
    @Path("/listFilesTable3/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<?> listFilesStatInform(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

        List<?> df = (List<?>) statInformDAO.getListStatInform(id);

        return df;
    }

    @GET
    @Path("/listFilesTable3Url/{place}/{id}/{namefile}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/vnd.ms-excel")
    public Response getdownloadFile(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\statinform\\report_info_1in3\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\statinform\\report_info_1in3\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\statinform\\report_info_1in3\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\statinform\\report_info_1in3\\4";

        directoryDestination = directoryServer+directoryDestination+File.separator+namefile.substring(11, namefile.length());

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

        List<?> df = (List<?>) statInformDAO.getListStatInformTable4(id);

        return df;
    }

    @GET
    @Path("/listFilesTable4Url/{place}/{id}/{namefile}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/vnd.ms-excel")
    public Response getdownloadFileTable4(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\statinform\\report_info_1in2\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\statinform\\report_info_1in2\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\statinform\\report_info_1in2\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\statinform\\report_info_1in2\\4";

        directoryDestination = directoryServer+directoryDestination+File.separator+namefile.substring(11, namefile.length());

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

        List<?> df = (List<?>) statInformDAO.getListStatInformTable5(id);

        return df;
    }

    @GET
    @Path("/listFilesTable5Url/{place}/{id}/{namefile}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/vnd.ms-excel")
    public Response getdownloadFileTable5(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\statinform\\report_info_2stage\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\statinform\\report_info_2stage\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\statinform\\report_info_2stage\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\statinform\\report_info_2stage\\4";

        directoryDestination = directoryServer+directoryDestination+File.separator+namefile.substring(11, namefile.length());

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

        List<?> df = (List<?>) statInformDAO.getListStatInformTable6(id);

        return df;
    }

    @GET
    @Path("/listFilesTable6Url/{place}/{id}/{namefile}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/vnd.ms-excel")
    public Response getdownloadFileTable6(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

        String directoryServer = System.getProperty("jboss.home.dir");
        String directoryDestination = "";

        if(id == 777) directoryDestination = "\\content\\report\\statinform\\report_info_D_control\\777";
        if(id == 1)	directoryDestination = "\\content\\report\\statinform\\report_info_D_control\\1";
        if(id == 2)	directoryDestination = "\\content\\report\\statinform\\report_info_D_control\\2";
        if(id == 4)	directoryDestination = "\\content\\report\\statinform\\report_info_D_control\\4";

        directoryDestination = directoryServer+directoryDestination+File.separator+namefile.substring(11, namefile.length());

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
