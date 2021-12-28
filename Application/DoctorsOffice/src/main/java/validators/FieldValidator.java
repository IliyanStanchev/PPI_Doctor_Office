package validators;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FieldValidator {

    public static boolean validateAlphabetical(TextField field, Label validationLabel) {

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.isTextAlphabetical(field, validationLabel))
            return false;

        return true;
    }

    public static boolean validateEmail(TextField field, Label validationLabel) {

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.checkEmailFormat(field, validationLabel))
            return false;

        return true;
    }

    public static boolean comparePassword(TextField password, TextField confirmPassword, Label validationLabel) {

        final String passwordValue = password.getText();
        final String confirmPasswordValue = confirmPassword.getText();

        if (!passwordValue.equals(confirmPasswordValue)) {

            validationLabel.setText("Passwords are not the same");
            return false;
        }

        return true;
    }

    public static boolean validateNumericField(TextField field, Label validationLabel) {

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.isTextNumeric(field, validationLabel))
            return false;

        return true;
    }

    public static boolean validateFieldLength(TextField field, Label validationLabel, int length) {

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.checkDataLength(field, validationLabel, length))
            return false;

        return true;
    }

    public static boolean validatePrice(TextField field, Label validationLabel) {

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.checkPriceFormat(field, validationLabel))
            return false;

        return true;
    }

    public static boolean validateUsername(TextField field, Label validationLabel) {

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.checkUsernameFormat(field, validationLabel))
            return false;

        return true;
    }

    public static boolean validatePersonName(TextField field, Label validationLabel) {

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.checkPersonNameFormat(field, validationLabel))
            return false;

        return true;
    }
}
