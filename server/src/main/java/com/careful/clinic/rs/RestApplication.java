package com.careful.clinic.rs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by marco on 11.03.17.
 */
@ApplicationPath("/rest")
public class RestApplication extends Application{
}
