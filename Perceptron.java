import java.util.Random;

public class Perceptron {
    private double[] weights;
    private double bias;

    // Constructor: creates a perceptron with given input size
    public Perceptron(int inputSize) {
        this.weights = new double[inputSize];
        this.bias = 0.0;
        initializeParameters();
    }

    // Constructor: creates a perceptron with given weights and bias
    public Perceptron(double[] weights, double bias) {
        this.weights = weights.clone();
        this.bias = bias;
    }

    // Initialize weights and bias with random values between -1 and 1
    private void initializeParameters() {
        Random random = new Random();
        for (int i = 0; i < weights.length; i++) {
            weights[i] = random.nextDouble() * 2 - 1;
        }
        this.bias = random.nextDouble() * 2 - 1;
    }

    // Activation function: step function (returns 1 if sum > 0, else 0)
    public int activate(int[] inputs) {
        double sum = 0.0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * inputs[i];
        }
        sum += bias;
        return (sum > 0) ? 1 : 0;
    }

    // Activation function: returns the weighted sum + bias (for linear output)
    public double activate(double[] inputs) {
        double sum = 0.0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * inputs[i];
        }
        return sum + bias;
    }

    // Getters
    public double[] getWeights() {
        return weights.clone();
    }

    public double getBias() {
        return bias;
    }

    // Setters
    public void setWeights(double[] weights) {
        this.weights = weights.clone();
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
