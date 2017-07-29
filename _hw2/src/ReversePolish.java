import java.util.*;
import java.util.function.*;
import java.io.*;

public class ReversePolish {
    private static final String WELCOME_PROMPT = "Welcome to the Reverse Polish Caclulator";
    private static final String FILENAME_PROMPT = "Enter the filename of the script to be evaluated (default example.rpl): ";
    private static final String DEFAULT_FILENAME = "example.rpl";

    private static final Map<String, BinaryOperator<Double>> OPERATORS
            = new HashMap<String, BinaryOperator<Double>>() {{
                put("+", (x,y)->{return x+y;});
                put("-", (x,y)->{return x-y;});
                put("*", (x,y)->{return x*y;});
                put("/", (x,y)->{return x/y;});
            }};

    private static class NonPolishException extends Exception {
        private String msg;

        public NonPolishException(String msg) {
            super();
            this.msg = msg;
        }

        public String toString() {
            return "NonPolish Error: " + msg;
        }
    }

    private static boolean closeTo(double x, double y) {
        return Math.abs(x-y) < 2 * Double.MIN_VALUE;
    }

    public static double calc(String expr) throws NonPolishException {
        GenQueueStack<Double> nums = new GenArrayQueueStack<Double>();
        String[] tokens = expr.split("\\s");

        for (String token : tokens) {
            if (!token.equals("")) {
                try {
                    double num = Double.parseDouble(token);
                    nums.push(num);
                } catch (NumberFormatException e) {
                    if (OPERATORS.containsKey(token)) {
                        Double second = nums.pop();
                        Double first = nums.pop();

                        if (first == null || second == null)
                            throw new NonPolishException("Malformatted expression - operator surplus");

                        if (closeTo(second, 0) && token.equals("/"))
                            throw new NonPolishException("Attempting to divide by zero");

                        nums.push(OPERATORS.get(token).apply(first, second));
                    } else {
                        throw new NonPolishException("Unknown token " + token);
                    }
                }
            }
        }

        Double result = nums.pop();

        if (result == null)
            throw new NonPolishException("Malformatted expression - operator surplus");

        if (nums.peek() != null)
            throw new NonPolishException("Malformed expression - number surplus");

        return result;
    }

    public static String formattedCalc(String expr) {
        String ret = expr + " = ";
        try {
            double value = calc(expr);
            ret += value;
        } catch (NonPolishException e) {
            ret += "{" + e + "}";
        }
        return ret;
    }

    public static void printCalc(String expr) {
        System.out.println(formattedCalc(expr));
    }

    public static String execFile(String filename) {
        try {
            Scanner input = new Scanner(new File(filename));
            String ret = "";

            while (input.hasNext()) {
                String line = input.nextLine();
                if (line != "") {
                    ret += formattedCalc(line) + "\n";
                }
            }

            return ret;
        } catch (FileNotFoundException e) {
            return "Error: Unable to locate file '" + filename + "'";
        }
    }

    public static String userFilename(Scanner input) {
        System.out.print(FILENAME_PROMPT);
        String filename = input.nextLine();
        if (filename.trim().length() <= 0)
            filename = DEFAULT_FILENAME;
        return filename;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println(WELCOME_PROMPT);
        String filename = userFilename(input);
        System.out.println(execFile(filename));

        input.close();
    }
}
