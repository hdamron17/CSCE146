import java.util.Arrays;
import java.util.Random;

public class Main {
    public static final int SIZE=1000;

    public static void main(String[] args) {
        int[] arr = Searches07.sortedRandom(SIZE);
        int goal = new Random().nextInt(SIZE);

        System.out.println("Linear search iterations: " + Searches07.linearSearch(arr, goal));
        System.out.println("Binary search iterations: " + Searches07.binarySearch(arr, goal));
    }
}
