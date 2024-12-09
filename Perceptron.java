import java.util.Arrays;

public class Perceptron {
    private double[] weights;
    private double bias;
    private double learningRate;

    public Perceptron(int inputSize, double learningRate) {
        this.weights = new double[inputSize];
        this.bias = 0.0;
        this.learningRate = learningRate;

        // Инициализация весов случайными значениями
        for (int i = 0; i < inputSize; i++) {
            weights[i] = Math.random();
        }
    }

    public int predict(double[] inputs) {
        double weightedSum = bias;
        for (int i = 0; i < inputs.length; i++) {
            weightedSum += weights[i] * inputs[i];
        }
        return activationFunction(weightedSum);
    }

    public void train(double[][] trainingInputs, int[] labels, int epochs) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < trainingInputs.length; i++) {
                double[] input = trainingInputs[i];
                int label = labels[i];

                // Прогнозирование
                int prediction = predict(input);

                // Обновление весов и смещения
                double error = label - prediction;
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += learningRate * error * input[j];
                }
                bias += learningRate * error;
            }
        }
    }

    private int activationFunction(double sum) {
        return sum >= 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        // Обучение логической функции "И"
        double[][] andInputs = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
        };
        int[] andLabels = {0, 0, 0, 1};

        Perceptron andPerceptron = new Perceptron(2, 0.1);
        andPerceptron.train(andInputs, andLabels, 10);

        System.out.println("AND Function:");
        for (double[] input : andInputs) {
            System.out.println(Arrays.toString(input) + " => " + andPerceptron.predict(input));
        }

        // Обучение логической функции "ИЛИ"
        double[][] orInputs = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
        };
        int[] orLabels = {0, 1, 1, 1};

        Perceptron orPerceptron = new Perceptron(2, 0.1);
        orPerceptron.train(orInputs, orLabels, 10);

        System.out.println("\nOR Function:");
        for (double[] input : orInputs) {
            System.out.println(Arrays.toString(input) + " => " + orPerceptron.predict(input));
        }

        // Обучение логической функции "НЕ"
        double[][] notInputs = {
            {0},
            {1}
        };
        int[] notLabels = {1, 0};

        Perceptron notPerceptron = new Perceptron(1, 0.1);
        notPerceptron.train(notInputs, notLabels, 10);

        System.out.println("\nNOT Function:");
        for (double[] input : notInputs) {
            System.out.println(Arrays.toString(input) + " => " + notPerceptron.predict(input));
        }
    }
}
