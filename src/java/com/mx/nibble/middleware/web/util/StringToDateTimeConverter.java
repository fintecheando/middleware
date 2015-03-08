/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.web.util;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 *
 * @author 43507296
 */
public class StringToDateTimeConverter extends StrutsTypeConverter {

    private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public Object convertFromString(Map context, String[] strings, Class toClass) {     
        if (strings == null || strings.length == 0 || strings[0].trim().length() == 0) {
            return null;
        }

        try {
            return DATE_FORMAT.parse(strings[0]);
        } catch (ParseException e) {
            throw new TypeConversionException("Unable to convert given object to date: " + strings[0]);
        }
    }

    public String convertToString(Map context, Object date) {
        if (date != null && date instanceof Date) {         
            return DATE_FORMAT.format(date);
        } else {
            return null;
        }
    }
}