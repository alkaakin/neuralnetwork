import java.util.Arrays;

public class SimpleNeuralNetwork {

    static class SingleLayerNetwork {
        private final Perceptron[] perceptrons;

        // Creates a single-layer network with a layer of perceptrons
        // inputSize: number of inputs to each perceptron
        // outputSize: number of perceptrons in the layer
        public SingleLayerNetwork(int inputSize, int outputSize) {
            this.perceptrons = new Perceptron[outputSize];
            for (int i = 0; i < outputSize; i++) {
                perceptrons[i] = new Perceptron(inputSize);
            }
        }

        public double[] predict(double[] inputs) {
            if (inputs.length != perceptrons[0].getWeights().length) {
                throw new IllegalArgumentException("Input size must match perceptron input size.");
            }

            double[] outputs = new double[perceptrons.length];
            for (int i = 0; i < perceptrons.length; i++) {
                outputs[i] = perceptrons[i].activate(inputs);
            }
            return outputs;
        }

        public double[][] getWeights() {
            double[][] weights = new double[perceptrons.length][];
            for (int i = 0; i < perceptrons.length; i++) {
                weights[i] = perceptrons[i].getWeights();
            }
            return weights;
        }

        public double[] getBias() {
            double[] biases = new double[perceptrons.length];
            for (int i = 0; i < perceptrons.length; i++) {
                biases[i] = perceptrons[i].getBias();
            }
            return biases;
        }

        public Perceptron[] getPerceptrons() {
            return perceptrons.clone();
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
