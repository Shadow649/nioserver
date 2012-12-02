package com.shadow649soft.server.impl.http;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HttpUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat( "EEE, dd MMM yyyy HH:mm:ss zzz" );


    public static String toHTTPDate(Date date) {
        return dateFormat.format(date);
    }

}
