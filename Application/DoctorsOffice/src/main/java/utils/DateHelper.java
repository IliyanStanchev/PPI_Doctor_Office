package utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class DateHelper {

    private static final int NEW_UCN_MONTH_FORMAT = 40;
    private static final int YEAR_VALUE_POSITION = 0;
    private static final int MONTH_VALUE_POSITION = 2;
    private static final int DAY_VALUE_POSITION = 4;

    public static boolean SetDateFromIdentifier(String personalIdentifier, Label resultLabel, DatePicker datePicker) {

        final String yearString = personalIdentifier.substring(YEAR_VALUE_POSITION, YEAR_VALUE_POSITION + 2);
        final String monthString = personalIdentifier.substring(MONTH_VALUE_POSITION, MONTH_VALUE_POSITION + 2);
        final String dayString = personalIdentifier.substring(DAY_VALUE_POSITION, DAY_VALUE_POSITION + 2);

        int decadesValue    = Integer.parseInt(yearString);
        int monthValue      = Integer.parseInt(monthString);
        int dayValue        = Integer.parseInt(dayString);

        int yearValue = 1900;

        if (monthValue >= NEW_UCN_MONTH_FORMAT) {

            monthValue -= NEW_UCN_MONTH_FORMAT;
            yearValue = 2000;
        }

        if (monthValue < 1 || monthValue > 12) {
            resultLabel.setText("Wrong identifier. Month cannot be " + monthValue);
            return false;
        }

        if (dayValue < 1 || dayValue > 31) {
            resultLabel.setText("Wrong identifier. Day cannot be " + dayValue);
            return false;
        }

        yearValue += decadesValue;

        datePicker.setValue(LocalDate.of(yearValue, monthValue, dayValue));

        return true;
    }
}
