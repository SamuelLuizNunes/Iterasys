package br.com.iterasys.unitTest;

import br.com.iterasys.Calculadora;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    void somarDoisNumeros() {
        double n1 = 10;
        double n2 = 15;
        double resultadoEsperado = 25;

        Calculadora calculadora = new Calculadora();
        double resultadoAtual = calculadora.somarDoisNumeros(n1, n2);

        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,1,2",
            "2,2,4",
            "3,3,6"
    }, delimiter = ','
    )
    void somarDoisNumerosComLista(double n1, double n2, double resultadoEsperado) {
        Calculadora calculadora = new Calculadora();
        double resultadoAtual = calculadora.somarDoisNumeros(n1, n2);

        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/csv/csvCalculadora", numLinesToSkip = 1, delimiter = ',')
    void somarDoisNumerosComListaDeArquivo(double n1, double n2, double resultadoEsperado) {
        Calculadora calculadora = new Calculadora();
        double resultadoAtual = calculadora.somarDoisNumeros(n1, n2);

        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    void subtrairDoisNumeros() {
        double n1 = 10;
        double n2 = 2;
        double resultadoEsperado = 8;

        Calculadora calculadora = new Calculadora();
        double resultadoAtual = calculadora.subtrairDoisNumeros(n1, n2);

        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    void multiplicarDoisNumeros(){
        double n1 = 10;
        double n2 = 10;
        double resultadoEsperado = 100;

        Calculadora calculadora = new Calculadora();
        double resultadoAtual = calculadora.multiplicarDoisNumeros(n1, n2);

        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public
    void dividirDoisNumeros() {
        double n1 = 10;
        double n2 = 2;
        double resultadoEsperado = 5;
        Calculadora calculadora = new Calculadora();
        double resultadoAtual = calculadora.dividirDoisNumeros(n1, n2);

        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public
    void divisaoPorZero() {
        double n1 = 10;
        double n2 = 0;
        Calculadora calculadora = new Calculadora();

        assertThrows(ArithmeticException.class, () -> calculadora.dividirDoisNumeros(n1, n2));
    }
}