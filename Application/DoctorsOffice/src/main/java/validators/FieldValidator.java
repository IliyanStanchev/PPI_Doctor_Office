package validators;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import security.BCryptPasswordEncoderService;

public class FieldValidator {

    public static boolean validateAlphabetical(TextField field, Label validationLabel) {

        validationLabel.setText("");

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.isTextAlphabetical(field, validationLabel))
            return false;

        return true;
    }

    public static boolean validateEmail(TextField field, Label validationLabel) {

        validationLabel.setText("");

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.checkEmailFormat(field, validationLabel))
            return false;

        return true;
    }

    public static boolean comparePassword(TextField password, TextField confirmPassword, Label validationLabel) {

        validationLabel.setText("");

        final String passwordValue = password.getText();
        final String confirmPasswordValue = confirmPassword.getText();

        if (!passwordValue.equals(confirmPasswordValue)) {

            validationLabel.setText("Passwords are not the same");
            return false;
        }

        return true;
    }

    public static boolean compareOldPassword( String password, String userPassword, Label validationLabel) {

        validationLabel.setText("");

        BCryptPasswordEncoderService bCryptPasswordEncoderService = new BCryptPasswordEncoderService();

        if (!bCryptPasswordEncoderService.matches( password, userPassword ) ){

            validationLabel.setText("The old password you have entered is incorrect");
            return false;
        }

        return true;
    }

    public static boolean validateNumericField(TextField field, Label validationLabel) {

        validationLabel.setText("");

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.isTextNumeric(field, validationLabel))
            return false;

        return true;
    }

    public static boolean validateFieldLength(TextField field, Label validationLabel, int length) {

        validationLabel.setText("");

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.checkDataLength(field, validationLabel, length))
            return false;

        return true;
    }

    public static boolean validateUsername(TextField field, Label validationLabel) {

        validationLabel.setText("");

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.checkUsernameFormat(field, validationLabel))
            return false;

        return true;
    }

    public static boolean validatePersonName(TextField field, Label validationLabel) {

        validationLabel.setText("");

        if (!DataValidator.isFieldEmpty(field, validationLabel))
            return false;

        if (!DataValidator.checkPersonNameFormat(field, validationLabel))
            return false;

        return true;
    }
}
