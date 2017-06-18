package org.birds.service.dao;
/*
Extra util to unmarshall BSON Data from db and some date utils
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.mongodb.DBEncoder;
import com.mongodb.DBObject;
import com.mongodb.DefaultDBEncoder;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.bson.io.BasicOutputBuffer;
import org.bson.io.OutputBuffer;
import org.jongo.marshall.jackson.bson4jackson.MongoBsonFactory;
import sun.util.locale.LocaleUtils;


public class DaoUtils {

    private final static ObjectMapper mapper = new ObjectMapper(MongoBsonFactory.createFactory());

    static {
        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(
                JsonAutoDetect.Visibility.ANY));
    }
    //Convert from BSON to POJO as defined by Class<T>
    public static <T> T getPojo(DBObject o, Class<T> clazz) throws IOException {
        ObjectReader reader = mapper.reader(clazz);
        DBEncoder dbEncoder = DefaultDBEncoder.FACTORY.create();
        OutputBuffer buffer = new BasicOutputBuffer();
        dbEncoder.writeObject(buffer, o);

        T pojo = reader.readValue(buffer.toByteArray());

        return pojo;
    }
    // return date in yyyy-MM-dd format from current Date format
    public static String getDateStr(Date date){
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    private static boolean isStrLenspec(String src, int len){
        if(len == 0 && (src == null || src.trim().length() == 0)){
            return true;
        }

        return (len == src.length());
    }

    private static boolean isStrLenLt(String src, int len){
        if(len == 0 ){
            return false;
        }
        if(src == null || src.trim().length() == 0){
            return false;
        }

        return (len >= src.length());
    }
    //Checks if Date is in supplied format.Here used to check if its in yyyy-MM-dd format
    public static boolean isDateProper(String date, String format){
        if(date == null || null == format){
            return false;
        }
        String[] dateArr = date.split("-");
        if(dateArr.length !=  3){
            return false;
        }
        if(!isStrLenspec(dateArr[0], 4)){
            return false;
        }

        if(!isStrLenLt(dateArr[1],2)){
            return false;
        }

        if(!isStrLenLt(dateArr[2], 2)){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date cdate = sdf.parse(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

}
