package com.palmen.aplicacioncalculadora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;

public class CalculadoraController {
    @FXML
    private Button BtnBorrar;

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
    private void initialize() {
        ponerNumeros();
        controlarBotonesOperadores();
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
                if (textoActual.equals("0")) {
                    lblResultado.setText(numero);
                } else {
                    lblResultado.setText(textoActual + numero);
                }
            });
        }
    }

    //Procedimiento para poner los operadores en el visor de la calculadora (lblResultado)
    public void controlarBotonesOperadores() {
        ArrayList<Button> botones = new ArrayList<>(Arrays.asList(btnDivision, btnMultiplicacion, btnResta, btnSuma));

        for (Button boton : botones) {
            boton.setOnMouseClicked(e -> {
                String operador = boton.getText();
                String textoActual = lblResultado.getText();

                // Verifica si el texto actual es un operador
                if (textoActual.equals("÷") || textoActual.equals("x") || textoActual.equals("-") || textoActual.equals("+")) {
                    lblResultado.setText(operador);
                } else {
                    lblResultado.setText(textoActual + " " + operador + " ");
                }
            });
        }
    }
}