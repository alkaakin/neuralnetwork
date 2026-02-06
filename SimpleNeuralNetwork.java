import java.util.Arrays;
import java.util.Random;

public class SimpleNeuralNetwork {

    static class SingleLayerNetwork {
        private final double[][] weights;
        private final double[] bias;

        // y = W x + b
        public SingleLayerNetwork(int inputSize, int outputSize) {
            this.weights = new double[outputSize][inputSize];
            this.bias = new double[outputSize];
            initializeParameters();
        }

        private void initializeParameters() {
            Random random = new Random();
            for (int outputIndex = 0; outputIndex < weights.length; outputIndex++) {
                for (int inputIndex = 0; inputIndex < weights[outputIndex].length; inputIndex++) {
                    weights[outputIndex][inputIndex] = random.nextDouble() * 2 - 1;
                }
                bias[outputIndex] = random.nextDouble() * 2 - 1;
            }
        }

        public double[] predict(double[] inputs) {
            if (inputs.length != weights[0].length) {
                throw new IllegalArgumentException("Input size must match weight columns.");
            }

            double[] outputs = new double[weights.length];
            for (int outputIndex = 0; outputIndex < weights.length; outputIndex++) {
                double sum = 0.0;
                for (int inputIndex = 0; inputIndex < inputs.length; inputIndex++) {
                    sum += weights[outputIndex][inputIndex] * inputs[inputIndex];
                }
                outputs[outputIndex] = sum + bias[outputIndex];
            }
            return outputs;
        }

        public double[][] getWeights() {
            return weights;
        }

        public double[] getBias() {
            return bias;
        }
    }

    public static void main(String[] args) {
        SingleLayerNetwork network = new SingleLayerNetwork(3, 2);

        double[] input = {1.0, 0.5, -1.2};
        double[] output = network.predict(input);

        System.out.println("Input:  " + Arrays.toString(input));
        System.out.println("Weights: " + Arrays.deepToString(network.getWeights()));
        System.out.println("Bias:   " + Arrays.toString(network.getBias()));
        System.out.println("Output: " + Arrays.toString(output));
    }
}
