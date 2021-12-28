package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileNameGenerator {

    public static String generateCurrentTimeStampName(final String nameStarter) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());

        return nameStarter + timeStamp;
    }
}
