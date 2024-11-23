import java.util.Random;

public class SimpleNeuralNetwork {
    
    static class Neuron {
        //Weights determine how strongly each input affects the neuron's output
        double[] weights;
        double bias;
    }
    /*Neuron output computation:
    Linear combination of inputs and weights plus bias
    f(w*x+b), where 
    f() = activation function
    w = weights (array of size equal to the number of inputs)
    x = inputs to the neuron (array of size equal to the number of inputs)
    b= bias
    */
        public Neuron(int numInputs) {
            Random random = new Random();
            //Weights = an array of doubles
            weights = new double[numInputs];
            for (int i = 0; i < numInputs; i++) {
                //Randomized the array values
                weights[i] = random.nextDouble() * 2 - 1; // Between [-1, 1]
            }
            //Bias shifts the output up or down even when all inputs would be zero
            bias = random.nextDouble() * 2 - 1;
        }

        public double activate(double[] inputs) { // Activation function
            double sum = 0.0;
            //The loop sums all of the values of the inputs array together, weighted (w*x)
            for (int i = 0; i < inputs.length; i++) {
                sum += inputs[i] * weights[i];
            }
            sum += bias; // Add bias b
            return sigmoid(sum); 
        }

        //Sigmoid function squashes the real number input (sum) between 0 and 1
        private double sigmoid(double x) {
            return 1 / (1 + Math.exp(-x)); 
        }

    static class Layer {
        Neuron[] neurons;

    }
}

