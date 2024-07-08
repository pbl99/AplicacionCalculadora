package com.palmen.aplicacioncalculadora;

import com.palmen.aplicacioncalculadora.controllers.CalculadoraController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class CalculadoraApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculadoraApplication.class.getResource("calculadora-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        //Imagen para el icono
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/palmen/img/icon.png")));
        stage.getIcons().add(image);

        // Pasar la referencia del Stage al controlador
        CalculadoraController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}