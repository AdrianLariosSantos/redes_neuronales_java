package redesneuronalessupervisadas;

import java.util.Arrays;
import java.util.Scanner;

public class RedesNeuronalesSupervisadas {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una red neuronal:");
            System.out.println("1. Perceptrón");
            System.out.println("2. Adaline");
            System.out.println("3. MultiPerceptrón");
            System.out.println("0. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ejecutarPerceptron();
                    break;
                case 2:
                    ejecutarAdaline();
                    break;
                case 3:
                    ejecutarMultiPerceptron();
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
            }
        }
    }

    private static void ejecutarPerceptron() {
        int[][] entradasEntrenamiento = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] etiquetas = {0, 0, 0, 1};
        Perceptron perceptron = new Perceptron(2, 0.1, 10);
        perceptron.entrenar(entradasEntrenamiento, etiquetas);
    }

    private static void ejecutarAdaline() {
        double[][] X = {
            {1, 0, 0},
            {1, 0, 1},
            {1, 1, 0},};
        int[] y = {1, -1, 1};
        Adaline adaline = new Adaline(0.01, 10);
        adaline.entrenar(X, y);
        for (Object resultado : adaline.getResultados()) {
            System.out.println(Arrays.deepToString((Object[]) resultado));
        }
    }

    private static void ejecutarMultiPerceptron() {
        int[][] entradasEntrenamiento = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] resultadosDeseados = {0, 0, 0, 1};
        // Crear una red neuronal con 2 entradas
        MultiPerceptron redNeuronal = new MultiPerceptron(2);
        redNeuronal.entrenar(entradasEntrenamiento, resultadosDeseados, 0.1, 10000);
        for (int i = 0; i < entradasEntrenamiento.length; i++) {
            double prediccion = redNeuronal.predecir(entradasEntrenamiento[i]);
            System.out.println("Entradas: " + Arrays.toString(entradasEntrenamiento[i]) + ", Predicción: " + prediccion);
        }
    }
}
