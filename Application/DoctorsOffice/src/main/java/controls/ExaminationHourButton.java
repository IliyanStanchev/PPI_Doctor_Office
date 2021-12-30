package controls;

import entities.ExaminationHour;
import javafx.scene.control.Button;
import utils.Styler;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExaminationHourButton extends Button {

    private ExaminationHour examinationHour;

    private boolean chosenByCustomer;

    public ExaminationHourButton(ExaminationHour examinationHour) {

        this.examinationHour = examinationHour;

        setText(examinationHour.getStartTime().toString() + " - " + examinationHour.getEndTime().toString());

        if( examinationHour.getDate().isBefore( LocalDate.now() ))
            setDisable(true);

        if( examinationHour.getDate().isEqual( LocalDate.now() ) && examinationHour.getStartTime().isBefore( LocalTime.now() ))
            setDisable(true);

        if (examinationHour.isTaken())
            setDisable(true);

        setStyle( Styler.notSelectedExaminationButton );
    }

    public boolean isChosenByCustomer() {
        return chosenByCustomer;
    }

    public void setChosenByCustomer(boolean chosenByCustomer) {

        this.chosenByCustomer = chosenByCustomer;

        if (chosenByCustomer)
            setStyle( Styler.selectedExaminationButton );

    }

    public ExaminationHour getExaminationHour() {
        return examinationHour;
    }

}
