module com.michaelstucki.srflashcards {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.michaelstucki.srflashcards to javafx.fxml;
    exports com.michaelstucki.srflashcards;
    exports com.michaelstucki.srflashcards.controller;
    opens com.michaelstucki.srflashcards.controller to javafx.fxml;
    exports com.michaelstucki.srflashcards.util;
    opens com.michaelstucki.srflashcards.util to javafx.fxml;
}