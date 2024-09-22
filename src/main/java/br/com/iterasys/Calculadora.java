package br.com.iterasys;

public class Calculadora {

    public double somarDoisNumeros(double n1, double n2){
        return n1 + n2;
    }

    public double subtrairDoisNumeros(double n1, double n2){
        return n1 - n2;
    }

    public double multiplicarDoisNumeros(double n1, double n2){
        return n1 * n2;
    }

    public double dividirDoisNumeros(double n1, double n2){
        if (n2 == 0){
            throw new ArithmeticException("Divisão por zero");
        }
        return n1 / n2;
    }
}
