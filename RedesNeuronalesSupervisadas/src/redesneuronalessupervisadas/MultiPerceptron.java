package redesneuronalessupervisadas;

public class MultiPerceptron {

    private double[] pesos;

    public MultiPerceptron(int numeroEntradas) {
        pesos = new double[numeroEntradas];
        // Inicializamos los pesos de manera aleatoria
        for (int i = 0; i < numeroEntradas; i++) {
            pesos[i] = Math.random();
        }
    }

    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    // Función para entrenar la red neuronal utilizando el algoritmo de retropropagación
    public void entrenar(int[][] entradasEntrenamiento, int[] resultadosDeseados, double tasaAprendizaje, int epochs) {
        if (entradasEntrenamiento.length != resultadosDeseados.length) {
            throw new IllegalArgumentException("Número de ejemplos de entrenamiento incorrecto");
        }

        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < entradasEntrenamiento.length; i++) {
                int[] entrada = entradasEntrenamiento[i];
                int resultadoDeseado = resultadosDeseados[i];

                // Realizamos la predicción
                double prediccion = predecir(entrada);

                // Calculamos el error
                double error = resultadoDeseado - prediccion;

                // Actualizamos los pesos utilizando el algoritmo de retropropagación
                for (int j = 0; j < pesos.length; j++) {
                    pesos[j] += tasaAprendizaje * error * entrada[j] * (1 - sigmoid(prediccion)) * sigmoid(prediccion);
                }
            }
        }
    }

    public double predecir(int[] entradas) {
        if (entradas.length != pesos.length) {
            throw new IllegalArgumentException("Número de entradas incorrecto");
        }

        double sumaPonderada = 0;

        for (int i = 0; i < entradas.length; i++) {
            sumaPonderada += entradas[i] * pesos[i];
        }

        return sigmoid(sumaPonderada);
    }
}
