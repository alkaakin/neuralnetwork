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
        //An array of neurons (every neuron of a single layer)
        Neuron[] neurons;
        
        public Layer(int numNeurons, int numInputsPerNeuron) {
            neurons = new Neuron[numNeurons];
            for (int i = 0; i < numNeurons; i++) {
                neurons[i] = new Neuron(numInputsPerNeuron);
            }
        }

        //Function forward creates another array of neurons based on activation of the neurons in the first array
        public double[] forward(double[] inputs) {
            double[] outputs = new double[neurons.length];
            for (int i = 0; i < neurons.length; i++) {
                outputs[i] = neurons[i].activate(inputs);
            }
            return outputs;
        }
    }

    static class NeuralNetwork {
        Layer[] layers;

        //Function creates a neural network of n layers
        public NeuralNetwork(int[] layerSizes) {
            layers = new Layer[layerSizes.length - 1];
            for (int i = 0; i < layers.length; i++) {
                layers[i] = new Layer(layerSizes[i + 1], layerSizes[i]);
            }
        }

        //Function predict returns the outputs (a collection of values from a layer) based on inputs
        public double[] predict(double[] inputs) {
            double[] outputs = inputs;
            for (Layer layer : layers) {
                outputs = layer.forward(outputs);
            }
            return outputs;
        }
    }

     // Testaus
     public static void main(String[] args) {
        // Luodaan neuroverkko: 2 input -> 2 hidden -> 1 output
        NeuralNetwork nn = new NeuralNetwork(new int[]{2, 2, 1});

        // XOR-data
        double[][] inputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[] outputs = {0, 1, 1, 0};

        // Ennusteet
        System.out.println("Ennusteet ennen oppimista:");
        for (double[] input : inputs) {
            double[] prediction = nn.predict(input);
            System.out.println(input[0] + ", " + input[1] + " -> " + prediction[0]);
        }


    }

}

