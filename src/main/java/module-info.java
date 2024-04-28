module com.example.aplicacioncalculadora {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.palmen.aplicacioncalculadora to javafx.fxml;
    exports com.palmen.aplicacioncalculadora;
}