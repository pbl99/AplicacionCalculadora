package com.palmen.aplicacioncalculadora.services;

import javafx.scene.control.Label;

import java.util.Locale;

public class CalculadoraService {
    //Método calculo operación que utiliza al método calcular para generar el resultado del label
    public String calcularOperacion(Label lblResultado) {
        String resultadoLabel;
        String textoResultado = lblResultado.getText().trim();
        Double calculo = calcular(textoResultado);
        resultadoLabel = formatearResultado(calculo);
        return resultadoLabel;
    }

    //Método calcular que usaremos para poder controlar la lógica necesaria a la hora de realizar las operaciones
    private Double calcular(String texto) {
        // Verificar si el último carácter es un operador, un punto o un paréntesis
        char ultimoCaracter = texto.charAt(texto.length() - 1);
        if (ultimoCaracter == '+' || ultimoCaracter == '-' || ultimoCaracter == 'x' || ultimoCaracter == '÷' || ultimoCaracter == '%' || ultimoCaracter == '.') {
            throw new NumberFormatException("Expresión inválida: termina con un operador o punto.");
        }

        // Verificar si el texto contiene paréntesis, encuentra y evalúa el contenido más interno primero
        int startIndex = texto.lastIndexOf('(');
        if (startIndex != -1) {
            int endIndex = texto.indexOf(')', startIndex);
            if (endIndex == -1) {
                throw new NumberFormatException("Expresión inválida: paréntesis no balanceados.");
            }
            String dentroParentesis = texto.substring(startIndex + 1, endIndex);
            Double resultadoDentro = calcular(dentroParentesis);
            texto = texto.substring(0, startIndex) + resultadoDentro.toString() + texto.substring(endIndex + 1);
            return calcular(texto);  // Llama recursivamente para evaluar el texto con el resultado
        }

        String[] partes = texto.split("\\s+");
        Double resultado = 0.0;
        Double numTemporal = 0.0;
        char operacion = '+';

        for (String parte : partes) {
            if (parte.equals("+") || parte.equals("-") || parte.equals("x") || parte.equals("÷") || parte.equals("%")) {
                operacion = parte.charAt(0);
            } else {
                Double num = Double.parseDouble(parte);
                switch (operacion) {
                    case '+':
                        resultado += numTemporal;
                        numTemporal = num;
                        break;
                    case '-':
                        numTemporal -= num;
                        break;
                    case 'x':
                        numTemporal *= num;
                        break;
                    case '÷':
                        numTemporal /= num;
                        break;
                    case '%':
                        numTemporal = numTemporal * (num / 100);
                        break;
                }
            }
        }
        resultado += numTemporal;
        return resultado;
    }

    private String formatearResultado(double resultado) {
        // Redondea el resultado a dos decimales
        double roundedResult = Math.round(resultado * 100.0) / 100.0;

        // Verifica si el resultado redondeado es un número entero
        if (roundedResult == (int) roundedResult) {
            // Si es un número entero, lo convierte a una cadena de texto sin decimales
            return String.valueOf((int) roundedResult);
        } else {
            // Formatea el resultado con dos decimales
            return String.format(Locale.US, "%.2f", roundedResult);
        }
    }
}
