package space.springbok.punkbeerservice.utils;

import lombok.experimental.UtilityClass;
import org.mapstruct.Named;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class DateUtils {

    public static Date stringToDate(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat("MM/yyyy");
        return format.parse(dateString);
    }

    public static  String dateToString(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        return sdf.format(date);
    }
}
