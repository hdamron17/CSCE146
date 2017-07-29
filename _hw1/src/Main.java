import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    private static final String PRIZE_LIST = "prizeList.txt";
    private static final int ALLOWANCE = 2000;

    private static final String WELCOME_PROMPT = "Welcome to a \'famous price related game show.\' "
            + "You must guess the total cost of five items within $" + ALLOWANCE + " without going over to win.";
    private static final String GUESS_PROMPT = "Enter your guess: ";
    private static final String CONTINUE_PROMPT = "Would you like to play again? Enter 'no' to quit. ";
    private static final String ITEMS_PROMPT = "Your items today are:";

    private static final String WIN_PROMPT = "Congratulations! You win! You guessed $%.2f and the real price is $%.2f.";
    private static final String LOSE_PROMPT = "I'm sorry. Your guess was not quite good enough. "
            + "You guessed $%.2f but the real price is $%.2f";

    public static Map<String,Double> loadPrizes() {
        Map<String,Double> items = new HashMap<String,Double>();
        Scanner reader;

        try {
            reader = new Scanner(new File(PRIZE_LIST));

            while (reader.hasNext()) {
                boolean valid = true;
                String[] pair = reader.nextLine().split("\t", 2);
                if (pair.length == 2) {
                    String key = pair[0];

                    double value = 0;
                    try {
                        value = Double.parseDouble(pair[1]);
                    } catch (NumberFormatException e) {
                        valid = false;
                    }

                    if (valid)
                        items.put(key, value);
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        return items;
    }

    public static void printWelcome() {
        System.out.println(WELCOME_PROMPT);
    }

    public static void printItems(Map<String,Double> items) {
        System.out.println(ITEMS_PROMPT);
        for (String item : items.keySet()) {
            System.out.println(" * " + item);
        }
    }

    public static double userGuess(Scanner input) {
        System.out.print(GUESS_PROMPT);
        double guess = input.nextDouble();
        return guess;
    }

    public static boolean userContinue(Scanner input) {
        System.out.print(CONTINUE_PROMPT);
        boolean answer = !input.next().equals("no");
        return answer;
    }

    public static Map<String,Double> randSelect(Map<String,Double> map, int num) {
        Map<String,Double> subMap = new HashMap<String,Double>();
        String[] keys = map.keySet().toArray(new String[0]);
        Random gen = new Random();

        for (int i = 0; i < num; i++) {
            int index = gen.nextInt(keys.length);
            String key = keys[index];
            subMap.put(key, map.get(key));
        }

        return subMap;
    }

    public static double costSum(Map<String,Double> items) {
        int total = 0;
        for (double val : items.values()) {
            total += val;
        }
        return total;
    }

    public static void game(Scanner input) {
        boolean playing = true;
        Map<String,Double> items = loadPrizes();

        while (playing) {
            Map<String,Double> fiveItems = randSelect(items, 5);
            double cost = costSum(fiveItems);

            printWelcome();
            printItems(fiveItems);
            double guess = userGuess(input);
            boolean win = guess < cost && Math.abs(cost-guess) < 2000;
            System.out.println(String.format((win ? WIN_PROMPT : LOSE_PROMPT), guess, cost));
            playing = userContinue(input);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        game(input);
        input.close();
    }
}
