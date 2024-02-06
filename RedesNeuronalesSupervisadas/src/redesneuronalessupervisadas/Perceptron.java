package redesneuronalessupervisadas;
import java.util.Arrays;

public class Perceptron {

    private double[] pesos;
    private double tasaAprendizaje;
    private int epocas;
    private int[] errores;

    public Perceptron(int tamanioEntrada, double tasaAprendizaje, int epocas) {
        this.pesos = new double[tamanioEntrada + 1];
        this.tasaAprendizaje = tasaAprendizaje;
        this.epocas = epocas;
        this.errores = new int[epocas];
    }

    private int funcionActivacion(double x) {
        return x >= 0 ? 1 : 0; // Operador ternario
    }

    public int predecir(int[] entradas) {
        double sumaPonderada = pesos[0];
        for (int i = 0; i < entradas.length; i++) {
            sumaPonderada += entradas[i] * pesos[i + 1];
        }
        System.out.println("Suma Ponderada: " + sumaPonderada);
        return funcionActivacion(sumaPonderada);
    }
    
    public void entrenar(int[][] entradasEntrenamiento, int[] etiquetas) {
        int iteracion = 0;
        for (int epoch = 0; epoch < epocas; epoch++) {
            iteracion++;
            System.out.println("Iteracion: " + iteracion + "\n");
            int errores = 0;
            for (int i = 0; i < entradasEntrenamiento.length; i++) {
                int[] entradas = entradasEntrenamiento[i];
                int etiqueta = etiquetas[i];

                int prediccion = predecir(entradas);
                double actualizacion = tasaAprendizaje * (etiqueta - prediccion);

                pesos[0] += actualizacion;
                for (int j = 0; j < entradas.length; j++) {
                    pesos[j + 1] += actualizacion * entradas[j];
                }

                errores += actualizacion != 0.0 ? 1 : 0;

                System.out.println("Predicción: " + prediccion);
                System.out.println("Actualización: " + tasaAprendizaje + " * (" + etiqueta + " - " + prediccion + ") = " + actualizacion);
                System.out.println("Pesos: " + Arrays.toString(pesos));
                System.out.println("Errores: " + errores);
                System.out.println("|" + "----".repeat(20) + "|");
            }
            this.errores[epoch] = errores;
        }
    }
    
    public int[] getErrores() {
        return errores;
    }
}
