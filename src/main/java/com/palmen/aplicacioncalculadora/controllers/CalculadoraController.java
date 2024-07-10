package com.palmen.aplicacioncalculadora.controllers;

import com.palmen.aplicacioncalculadora.services.CalculadoraService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class CalculadoraController {
    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnDivision;

    @FXML
    private Button btnIgualResultado;

    @FXML
    private Button btnMultiplicacion;

    @FXML
    private Button btnNumero1;

    @FXML
    private Button btnNumero2;

    @FXML
    private Button btnNumero3;

    @FXML
    private Button btnNumero4;

    @FXML
    private Button btnNumero5;

    @FXML
    private Button btnNumero6;

    @FXML
    private Button btnNumero9;

    @FXML
    private Button btnNumero0;

    @FXML
    private Button btnNumero8;

    @FXML
    private Button btnNumero7;

    @FXML
    private Button btnParentesisAbierto;

    @FXML
    private Button btnParentesisCerrado;

    @FXML
    private Button btnPorcentaje;

    @FXML
    private Button btnPunto;

    @FXML
    private Button btnResta;

    @FXML
    private Button btnSuma;

    @FXML
    private Label lblResultado;

    @FXML
    private Label lblCerrarApp;

    @FXML
    private Label lblMinimizarApp;

    @FXML
    private AnchorPane anchorPane;

    private Stage stage;

    private double xOffset = 0;

    private double yOffset = 0;

    private CalculadoraService calculadoraService;

    private String operacionActual; // Variable para almacenar la operación actual

    @FXML
    private void initialize() {
        calculadoraService = new CalculadoraService();
        operacionActual = "";
        ponerNumeros();
        controlarBotonesOperadores();
        controlarBotonesEspeciales();
        borrarCaracteres();
    }

    // Métodos para manejar los eventos de los botones de operación (suma, resta, etc.)
    @FXML
    private void handleBotonSuma() {
        operacionActual = "suma";
    }

    @FXML
    private void handleBotonResta() {
        operacionActual = "resta";
    }

    @FXML
    private void handleBotonMultiplica() {
        operacionActual = "multiplica";
    }

    @FXML
    private void handleBotonDivide() {
        operacionActual = "divide";
    }

    @FXML
    private void handleBotonPorcentaje(){
        operacionActual = "porcentaje";
    }

    private boolean esCaracterEspecial(char c) {
        return c == '.' || c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    @FXML
    private void handleBotonIgual() {
        String textoActual = lblResultado.getText().trim();

        // Verificar si los últimos dos caracteres son especiales o si el último es un operador o punto
        char ultimoCaracter = textoActual.charAt(textoActual.length() - 1);
        char penultimoCaracter = textoActual.length() > 1 ? textoActual.charAt(textoActual.length() - 2) : ' ';

        if (esCaracterEspecial(ultimoCaracter) && esCaracterEspecial(penultimoCaracter)) {
            lblResultado.setText("ERROR");
        } else {
            try {
                switch (operacionActual) {
                    case "suma", "multiplica", "resta", "divide", "porcentaje" ->
                            lblResultado.setText(calculadoraService.calcularOperacion(lblResultado));
                }

            } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
                lblResultado.setText("ERROR");
            }
        }
    }

    //Procedimiento para poner los numeros en el visor de la calculadora (lblResultado)
    public void ponerNumeros() {
        ArrayList<Button> botones = new ArrayList<>(Arrays.asList(btnNumero0, btnNumero1, btnNumero2, btnNumero3,
                btnNumero4, btnNumero5, btnNumero6, btnNumero7, btnNumero8, btnNumero9));

        for (Button boton : botones) {
            boton.setOnMouseClicked(e -> {
                String numero = boton.getText();
                String textoActual = lblResultado.getText();

                // Verifica si el texto actual es "0" y lo reemplaza por el número
                if (textoActual.equals("0") || textoActual.equals("ERROR")) {
                    lblResultado.setText(numero);
                } else {
                    lblResultado.setText(textoActual + numero);
                }
            });
        }
    }

    //Procedimiento para poner los operadores en el visor de la calculadora (lblResultado)
    public void controlarBotonesOperadores() {
        ArrayList<Button> botones = new ArrayList<>(Arrays.asList(btnDivision, btnMultiplicacion, btnResta, btnSuma, btnPorcentaje));

        for (Button boton : botones) {
            boton.setOnMouseClicked(e -> {
                String operador = boton.getText();
                String textoActual = lblResultado.getText();


                // Verifica el último carácter del texto actual
                if (!textoActual.isEmpty()) {
                    char ultimoCaracter = textoActual.charAt(textoActual.length() - 1);

                    // Si el último carácter es un número o un paréntesis cerrado, agregar espacio antes de operador
                    if (Character.isDigit(ultimoCaracter) || ultimoCaracter == ')') {
                        lblResultado.setText(textoActual + " " + operador + " ");
                    } else if (ultimoCaracter == '(') {
                        // Si el último carácter es un paréntesis abierto, agregar operador directamente
                        lblResultado.setText(textoActual + operador);
                    } else if (ultimoCaracter == '-' && (textoActual.length() == 1 || !Character.isDigit(textoActual.charAt(textoActual.length() - 2)))) {
                        // Si el último carácter es un signo menos y es el primer carácter o no hay un número precedente
                        lblResultado.setText(textoActual.substring(0, textoActual.length() - 1) + operador);
                    } else {
                        // En cualquier otro caso, agregar operador directamente
                        lblResultado.setText(textoActual + operador);
                    }
                } else {
                    // Si no hay texto actual, agregar operador directamente
                    lblResultado.setText(operador);
                }
                if (textoActual.equals("0") || textoActual.equals("ERROR")) {
                    lblResultado.setText(operador);
                }

            });
        }
    }

    public void controlarBotonesEspeciales() {
        ArrayList<Button> botones = new ArrayList<>(Arrays.asList(btnPunto, btnParentesisAbierto, btnParentesisCerrado));

        for (Button boton : botones) {
            boton.setOnMouseClicked(e -> {
                String btnEspecial = boton.getText();
                String textoActual = lblResultado.getText();

                if (textoActual.equals(".") || textoActual.equals("(") || textoActual.equals(")")) {
                    lblResultado.setText(btnEspecial);
                } else {
                    lblResultado.setText(textoActual + btnEspecial);
                }
                if (textoActual.equals("0") || textoActual.equals("ERROR")) {
                    if(boton.equals(btnParentesisAbierto)){
                        lblResultado.setText(btnEspecial);
                    }else if(boton.equals(btnParentesisCerrado)){
                        lblResultado.setText(btnEspecial);
                    }
                }
            });
        }
    }

    //Procedimiento asociado al botón(btnBorrar) para borrar caracteres
    public void borrarCaracteres() {
        btnBorrar.setOnMouseClicked(e -> {
            String textoActual = lblResultado.getText();
            if (!textoActual.isEmpty()) {
                // Borra el último carácter de la expresión actual
                textoActual = textoActual.substring(0, textoActual.length() - 1);
                lblResultado.setText(textoActual);
            }
        });
    }

    //Procedimiento para la lógica que afectará al movimiento de la aplicación y los labels que usaremos para minimizar y cerrar
    public void setStage(Stage stage) {
        this.stage = stage;

        // Inicializar los controladores de botones
        lblCerrarApp.setOnMouseClicked(event -> stage.close());
        lblMinimizarApp.setOnMouseClicked(event -> stage.setIconified(true));

        // Hacer que la ventana sea arrastrable
        anchorPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        anchorPane.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
}