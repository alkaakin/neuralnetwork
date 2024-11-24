import java.util.Scanner;

public class Perceptron {
    private int[] inputs;
    private int[] weights;
    private int bias;

    public static void main(String args[]) throws java.io.IOException {
        int[] answerInputs = new int[3];
        Perceptron perceptron = new Perceptron();
        perceptron.run(answerInputs);
        perceptron.setInputs(answerInputs);
        int output = perceptron.activate();
        System.out.println("Perceptron output: " + output);
    }

    public Perceptron(){
        int wWeather = 4; //Weight for the current weather
        int wCompany = 2; //Weight for the availability of company
        int wProximity = 2; //Weight for the proximity of a movie theater
        this.bias = -5; //Only go to the movies when the weather is good, which shows in the bias
        this.weights = new int [] { wWeather, wCompany, wProximity };
        this.inputs = new int[3];
    }
    
    public void run(int[] answerInputs) {    
        Scanner scanner = new Scanner(System.in);
        String[] questions = {
                "Is the weather good? (yes or no)",
                 "Do you have company? (yes or no)",
                "Is the theatre close by? (yes or no)"
            };
        for (int i = 0; i < questions.length; i++) {
            answerInputs[i] = askQuestion(scanner, questions[i]);
            }
        }
            
        private int askQuestion(Scanner scanner, String question) {
            System.out.print(question + " ");
            String answer = scanner.nextLine().trim().toLowerCase();
            return answer.equals("yes") ? 1 : 0;
        }
     
        public void setInputs(int[] answerInputs) {
            this.inputs = answerInputs;
        }

        public int activate() {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += weights[i] * inputs[i]; // Weighted sum of inputs
            }
            sum += bias; // Add bias
            return (sum > 0) ? 1 : 0; // Activation function (step function)
        }
}