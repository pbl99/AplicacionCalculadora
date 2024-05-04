package com.palmen.aplicacioncalculadora.services;

import javafx.scene.control.Label;

public class CalculadoraService {

    //Método sumaCalculo que utiliza al método calcular para generar el resultado del label
    public String sumaCalculo(Label lblResultado) {
        String resultadoLabel;
        String textoResultado = lblResultado.getText().trim();
        int suma = calcular(textoResultado);
        resultadoLabel = String.valueOf(suma);
        return resultadoLabel;
    }

    //Método restaCalculo que utiliza al método calcular para generar el resultado del label
    public String restaCalculo(Label lblResultado) {
        String resultadoLabel;
        String textoResultado = lblResultado.getText().trim();
        int resta = calcular(textoResultado);
        resultadoLabel = String.valueOf(resta);
        return resultadoLabel;
    }

    //Método calcular que usaremos para poder controlar la lógica necesaria a la hora de realizar las operaciones
    private int calcular(String texto) {
        String[] partes = texto.split("\\s+");
        int resultado = 0;
        int numTemporal = 0;
        char operacion = '+';

        for (String parte : partes) {
            if (parte.equals("+") || parte.equals("-")) {
                operacion = parte.charAt(0);
            } else {
                int num = Integer.parseInt(parte);
                if (operacion == '+') {
                    resultado += numTemporal;
                    numTemporal = num;
                } else {
                    numTemporal -= num;
                }
            }
        }
        resultado += numTemporal;
        return resultado;
    }
}
