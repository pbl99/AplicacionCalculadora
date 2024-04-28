package com.palmen.aplicacioncalculadora.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    private void initialize() {
        ponerNumeros();
        controlarBotonesOperadores();
        calculos();
        borrarCaracteres();
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

    //Procedimiento de prueba que comprueba si se pulsa el botón(btnIgualResultado) y utiliza las operaciones para dar el resultado
    public void calculos() {
        btnIgualResultado.setOnMouseClicked(e -> {
            lblResultado.setText(sumaCalculo());
        });
    }

    //Método para la operación de suma
    public String sumaCalculo() {
        String resultadoLabel = "";
        String textoResultado = lblResultado.getText().trim();
        String[] partes = textoResultado.split("\\+");
        int suma = 0;

        for (String parte : partes) {
            parte = parte.trim();
            if (!parte.isEmpty()) {
                suma += Integer.parseInt(parte);
            }
        }
        resultadoLabel = String.valueOf(suma);

        return resultadoLabel;
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
}