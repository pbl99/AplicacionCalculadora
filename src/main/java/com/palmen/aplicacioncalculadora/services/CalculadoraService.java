package com.palmen.aplicacioncalculadora.services;

import javafx.scene.control.Label;

import java.util.Locale;

public class CalculadoraService {

    //Método sumaCalculo que utiliza al método calcular para generar el resultado del label
    public String sumaCalculo(Label lblResultado) {
        String resultadoLabel;
        String textoResultado = lblResultado.getText().trim();
        Double suma = calcular(textoResultado);
        resultadoLabel = formatearResultado(suma);
        return resultadoLabel;
    }

    //Método restaCalculo que utiliza al método calcular para generar el resultado del label
    public String restaCalculo(Label lblResultado) {
        String resultadoLabel;
        String textoResultado = lblResultado.getText().trim();
        Double resta = calcular(textoResultado);
        resultadoLabel = formatearResultado(resta);
        return resultadoLabel;
    }

    public String multiplicaCalculo(Label lblResultado) {
        String resultadoLabel;
        String textoResultado = lblResultado.getText().trim();
        Double multiplicacion = calcular(textoResultado);
        resultadoLabel = formatearResultado(multiplicacion);
        return resultadoLabel;
    }

    public String divideCalculo(Label lblResultado) {
        String resultadoLabel;
        String textoResultado = lblResultado.getText().trim();
        Double division = calcular(textoResultado);
        resultadoLabel = formatearResultado(division);
        return resultadoLabel;
    }

    //Método calcular que usaremos para poder controlar la lógica necesaria a la hora de realizar las operaciones
    private Double calcular(String texto) {
        String[] partes = texto.split("\\s+");
        Double resultado = (double) 0;
        Double numTemporal = (double) 0;
        char operacion = '+';

        for (String parte : partes) {
            if (parte.equals("+") || parte.equals("-") || parte.equals("x") || parte.equals("÷")) {
                operacion = parte.charAt(0);
            } else {
                Double num = Double.parseDouble(parte);
                if (operacion == '+') {
                    resultado += numTemporal;
                    numTemporal = num;
                } else if (operacion == '-') {
                    numTemporal -= num;
                } else if (operacion == 'x') {
                    numTemporal = numTemporal * num;
                } else {
                    numTemporal = numTemporal / num;
                }
            }
        }
        resultado += numTemporal;
        return resultado;
    }

    private String formatearResultado(double resultado) {
        // Verifica si el resultado es un número entero o no
        if (resultado == (int) resultado) {
            // Si es un número entero, lo convierte a una cadena de texto sin decimales
            return String.valueOf((int) resultado);
        } else {
            // Si es un número decimal, calcula la longitud máxima de decimales entre todos los operandos
            int maxDecimales = String.valueOf(resultado).split("\\.")[1].length();
            // Formatea el resultado con la longitud máxima de decimales encontrada
            // este utiliza el formato del "." también se podria utilizar el método replace para reemplazar las comas por puntos
            return String.format(Locale.US, "%." + maxDecimales + "f", resultado);
        }
    }
}
