package validators;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DataValidator {

    public static boolean checkDataLength(TextField inputTextField,
                                          Label inputLabel,
                                          int requiredLength) {

        if (inputTextField.getText().length() < requiredLength) {

            inputLabel.setText(String.format("Text should be at least %d symbols", requiredLength));
            return false;
        }

        return true;
    }

    public static boolean checkEmailFormat(TextField inputTextField, Label inputLabel) {

        if (!inputTextField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com")) {

            inputLabel.setText("Wrong email format");
            return false;
        }

        return true;
    }

    public static boolean checkUsernameFormat(TextField inputTextField, Label inputLabel) {

        if (!inputTextField.getText().matches("^[a-zA-Z0-9_.-]*$")) {

            inputLabel.setText("Wrong username format");
            return false;
        }

        return true;
    }

    public static boolean checkPersonNameFormat(TextField inputTextField, Label inputLabel) {

        if (!inputTextField.getText().matches("^[a-zA-Z]*$")) {

            inputLabel.setText("Wrong name format");
            return false;
        }

        return true;
    }

    public static boolean checkPriceFormat(TextField inputTextField, Label inputLabel) {

        if (!inputTextField.getText().matches("[0-9]+([.][0-9]{1,2})?")) {

            inputLabel.setText("Wrong price format");
            return false;
        }

        return true;
    }

    public static boolean isFieldEmpty(TextField inputTextField, Label inputLabel) {

        if (inputTextField.getText().isEmpty()) {

            inputLabel.setText("The field is empty");
            return false;
        }
        return true;
    }

    public static boolean isTextNumeric(TextField inputTextField, Label inputLabel) {

        if (!inputTextField.getText().matches("[0-9]+")) {

            inputLabel.setText("The field must contain only numbers");
            return false;

        }

        return true;
    }

    public static boolean isTextAlphabetical(TextField inputTextField, Label inputLabel) {

        if (!inputTextField.getText().matches("[a-z A-Z]+")) {

            inputLabel.setText("The field must contain only alphabetical characters");
            return false;
        }

        return true;
    }
}
