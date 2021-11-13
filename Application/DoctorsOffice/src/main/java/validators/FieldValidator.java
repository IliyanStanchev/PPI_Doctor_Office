package validators;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FieldValidator {

    public static boolean validateAlphabetical(TextField field, Label validationLabel) {

        if (DataValidator.isFieldEmpty(field, validationLabel)) {

            return DataValidator.isTextAlphabetical(field, validationLabel);
        }

        return true;
    }

    public static boolean validateEmail(TextField field, Label validationLabel) {

        if (DataValidator.isFieldEmpty(field, validationLabel)) {

            return DataValidator.checkEmailFormat(field, validationLabel);
        }
        return true;

    }

    public static boolean isPasswordSame(TextField password, TextField confirmPassword, Label validationLabel) {

        final String passwordValue = password.getText();
        final String confirmPasswordValue = confirmPassword.getText();

        if (!passwordValue.equals(confirmPasswordValue)) {

            validationLabel.setText("Passwords are not the same");
            return false;
        }

        return true;
    }

    public static boolean validateNumericField(TextField field, Label validationLabel) {
        if (DataValidator.isFieldEmpty(field, validationLabel)) {

            return DataValidator.isTextNumeric(field, validationLabel);
        }
        return true;
    }

    public static boolean validateFieldLength(TextField field, Label validationLabel, int lenght) {
        if (DataValidator.isFieldEmpty(field, validationLabel)) {

            return DataValidator.checkDataLength(field, validationLabel, lenght);
        }

        return true;
    }

    public static boolean validatePrice(TextField field, Label validationLabel) {

        if (DataValidator.isFieldEmpty(field, validationLabel)) {

            return DataValidator.checkPriceFormat(field, validationLabel);
        }

        return true;

    }

    public static boolean validateUsername(TextField field, Label validationLabel) {
        if (DataValidator.isFieldEmpty(field, validationLabel)) {

            return DataValidator.checkUsernameFormat(field, validationLabel);
        }

        return true;
    }

    public static boolean validatePersonName(TextField field, Label validationLabel) {
        if (DataValidator.isFieldEmpty(field, validationLabel)) {

            return DataValidator.checkPersonNameFormat(field, validationLabel);
        }

        return true;
    }
}
