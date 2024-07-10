package com.palmen.aplicacioncalculadora.services;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculadoraServiceTest {

    private CalculadoraService calculadoraService;


    @BeforeAll
    //Necesitamos crear este método para que JavaFX se pueda inicializar en Junit
    public static void initJFX() {
        new JFXPanel();
    }

    @BeforeEach
    public void setUp() {
        calculadoraService = new CalculadoraService();
    }

    // Procedimiento de prueba para verificar si funciona la longitud requerida de los decimales
    @Test
    public void testFormatearResultado_LongitudDecimales() {
        Label lblResultado = new Label("10.5 + 20.7");
        //Prueba con un número entero
        String resultado = calculadoraService.calcularOperacion(lblResultado);
        assertEquals("31.20", resultado);
    }

    @Test
    // Procedimiento para probar si las operaciones entre paréntesis son ejecutadas primero
    public void testOperacionConParentesis() {
        Label lblResultado = new Label("(5 + 5) x 5");

        //Llamada secuencial a los métodos para procesar la expresión con paréntesis
        String resultado = calculadoraService.calcularOperacion(lblResultado);
        assertEquals("50", resultado);
    }

    @Test
    // Procedimiento igual al anterior pero sin paréntesis para verificar resultados distintos con mismos números
    public void testOperacionSinPartentesis() {
        Label lblResultado = new Label("5 + 5 x 5");

        String resultado = calculadoraService.calcularOperacion(lblResultado);
        assertEquals("30", resultado);
    }

    @Test
    // Procedimiento de combinación de multiplicación y división
    public void testCombinacionMultiplicacionDivision() {
        Label lblResultado = new Label("10 x 5 ÷ 2");

        String resultado = calculadoraService.calcularOperacion(lblResultado);
        assertEquals("25", resultado);

    }

    @Test
    // Procedimiento de combinación de multiplicación y división
    public void testMultiplicacionSignosNegativos() {
        Label lblResultado = new Label("-10 x -5");

        String resultado = calculadoraService.calcularOperacion(lblResultado);
        assertEquals("50", resultado);

    }

    @Test
    // Procedimiento de combinación de multiplicación y división
    public void testPorcentajes() {
        Label lblResultado = new Label("5 % 100");

        String resultado = calculadoraService.calcularOperacion(lblResultado);
        assertEquals("5", resultado);

    }

    @Test
    public void testSignosNegativosParentesis(){
        Label lblResultado = new Label("-5 - (-5)");

        String resultado = calculadoraService.calcularOperacion(lblResultado);
        assertEquals("0", resultado);
    }

    @Test
    public void testSignosNegativosSinParentesis(){
        Label lblResultado = new Label("-5 - -5");

        String resultado = calculadoraService.calcularOperacion(lblResultado);
        assertEquals("0", resultado);
    }

    @Test
    /*Este test para estar bien tiene que tirar el error numberformatexception que se ha creado en el service expresamente para controlar
    este tipo de caso*/
    public void testExpresionErroneas(){
        Label lblResultado = new Label("5 + 5 +");

        String resultado = calculadoraService.calcularOperacion(lblResultado);
        assertEquals("10", resultado);
    }

    @Test
    //Tiene que dar error por múltiples puntos
    public void testExpresionErroneas2(){
        Label lblResultado = new Label("5..2 + 5");

        String resultado = calculadoraService.calcularOperacion(lblResultado);
        assertEquals("10", resultado);
    }

    @Test
    //Tiene que dar error no puede haber un punto al principio
    public void testExpresionesConPunto(){
        Label lblResultado = new Label(".25 + 2");

        String resultado = calculadoraService.calcularOperacion(lblResultado);
        assertEquals("10", resultado);
    }

}