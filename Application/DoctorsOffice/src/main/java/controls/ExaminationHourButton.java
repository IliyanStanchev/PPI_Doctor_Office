package controls;

import entities.ExaminationHour;
import javafx.scene.control.Button;

public class ExaminationHourButton extends Button {

    private ExaminationHour examinationHour;

    private boolean chosenByCustomer;

    public ExaminationHourButton(ExaminationHour examinationHour) {

        this.examinationHour = examinationHour;

        setText(examinationHour.getStartTime().toString() + " - " + examinationHour.getEndTime().toString());

        if (examinationHour.isTaken())
            setDisable(true);

        setStyle(" -fx-padding: 8 10 10 10;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color:\n" +
                "            linear-gradient(from 0% 93% to 0% 100%, #ffffff 0%, #ffffff 100%),\n" +
                "            #ffffff,\n" +
                "            #ffffff,\n" +
                "            radial-gradient(center 50% 50%, radius 100%, #ffffff, #ffffff);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;"
        );
    }

    public boolean isChosenByCustomer() {
        return chosenByCustomer;
    }

    public void setChosenByCustomer(boolean chosenByCustomer) {

        this.chosenByCustomer = chosenByCustomer;

        if (chosenByCustomer)
            setStyle(" -fx-padding: 8 10 10 10;\n" +
                    "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                    "    -fx-background-radius: 8;\n" +
                    "    -fx-background-color:\n" +
                    "            linear-gradient(from 0% 93% to 0% 100%, #58D68D 0%, #58D68D 100%),\n" +
                    "            #58D68D,\n" +
                    "            #58D68D,\n" +
                    "            radial-gradient(center 50% 50%, radius 100%,  #58D68D,  #58D68D);\n" +
                    "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                    "    -fx-font-weight: bold;\n" +
                    "    -fx-font-size: 1.1em;"
            );

    }

    public ExaminationHour getExaminationHour() {
        return examinationHour;
    }

}
