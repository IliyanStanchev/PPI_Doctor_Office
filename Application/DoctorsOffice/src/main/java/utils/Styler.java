package utils;

public class Styler {

    public final static String notSelectedExaminationButton = "-fx-padding: 8 10 10 10;\n" +
            "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
            "    -fx-background-radius: 8;\n" +
            "    -fx-background-color:\n" +
            "            linear-gradient(from 0% 93% to 0% 100%, #ffffff 0%, #ffffff 100%),\n" +
            "            #ffffff,\n" +
            "            #ffffff,\n" +
            "            radial-gradient(center 50% 50%, radius 100%, #ffffff, #ffffff);\n" +
            "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
            "    -fx-font-weight: bold;\n" +
            "    -fx-font-size: 1em;";

    public final static String selectedExaminationButton = " -fx-padding: 8 10 10 10;" +
            "             -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
            "             -fx-background-radius: 8;" +
            "             -fx-background-color:" +
            "                     linear-gradient(from 0% 93% to 0% 100%, #58D68D 0%, #58D68D 100%)," +
            "                     #58D68D," +
            "                     #58D68D," +
            "                     radial-gradient(center 50% 50%, radius 100%,  #58D68D,  #58D68D);" +
            "             -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );" +
            "             -fx-font-weight: bold;" +
            "             -fx-font-size: 1em;";

}
