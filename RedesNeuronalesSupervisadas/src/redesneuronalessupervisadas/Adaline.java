package redesneuronalessupervisadas;

import java.util.Arrays;

public class Adaline {

    private double tasaAprendizaje;
    private int epocas;
    private double[] pesos;
    private double bias;
    private Object[] resultados;

    public Adaline(double tasaAprendizaje, int epocas) {
        this.tasaAprendizaje = tasaAprendizaje;
        this.epocas = epocas;
        this.pesos = null;
        this.bias = 0;
        this.resultados = new Object[epocas];
    }

    public void entrenar(double[][] X, int[] y) {
        int numCaracteristicas = X[0].length;
        pesos = new double[numCaracteristicas];
        Arrays.fill(pesos, 0.0);

        for (int epoca = 0; epoca < epocas; epoca++) {
            double[] entradaNeta = entradaNeta(X);
            double[] errores = new double[y.length];
            for (int i = 0; i < y.length; i++) {
                errores[i] = y[i] - entradaNeta[i];
            }

            for (int i = 0; i < pesos.length; i++) {
                pesos[i] += tasaAprendizaje * sumaProductoPunto(X, errores, i);
            }

            bias += tasaAprendizaje * sumarElementos(errores);
            double costo = sumarCuadrados(errores) / 2;

            Object[] resultado = new Object[]{X, y, epoca, costo};
            resultados[epoca] = resultado;
        }
    }

    private double[] entradaNeta(double[][] X) {
        double[] resultado = new double[X.length];
        for (int i = 0; i < X.length; i++) {
            resultado[i] = productoPunto(X[i], pesos) + bias;
        }
        return resultado;
    }

    private double productoPunto(double[] vector1, double[] vector2) {
        double resultado = 0;
        for (int i = 0; i < vector1.length; i++) {
            resultado += vector1[i] * vector2[i];
        }
        return resultado;
    }

    private double sumaProductoPunto(double[][] matriz, double[] vector, int indice) {
        double resultado = 0;
        for (int i = 0; i < matriz.length; i++) {
            resultado += matriz[i][indice] * vector[i];
        }
        return resultado;
    }

    private double sumarElementos(double[] vector) {
        double resultado = 0;
        for (double elemento : vector) {
            resultado += elemento;
        }
        return resultado;
    }

    private double sumarCuadrados(double[] vector) {
        double resultado = 0;
        for (double elemento : vector) {
            resultado += Math.pow(elemento, 2);
        }
        return resultado;
    }

    public Object[] getResultados() {
        return resultados;
    }
}
