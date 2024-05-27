module com.example.aplicacioncalculadora {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.palmen.aplicacioncalculadora to javafx.fxml;
    exports com.palmen.aplicacioncalculadora;
    exports com.palmen.aplicacioncalculadora.controllers;
    opens com.palmen.aplicacioncalculadora.controllers to javafx.fxml;
}