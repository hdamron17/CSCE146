import java.util.Arrays;
import java.util.Random;

public class Searches07 {
    public static int[] sortedRandom(int size) {
        int[] arr = new int[size];
        Random gen = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = gen.nextInt(size);
        }

        Arrays.sort(arr);
        return arr;
    }

    public static void printMsg(String searchType, int goal, int index, int iterations, boolean found) {
        System.out.println(String.format("%s search %d %sFOUND at %d in %d iterations", searchType, goal, (found ? "" : "NOT "), index, iterations));
    }

    //Note: returns the number of iterations not the location; prints whether found
    public static int linearSearch(int[] arr, int goal) {
        int iterations = 0;
        boolean found = false;
        int i = 0;

        for (;i < arr.length; i++) {
            iterations++;
            if (arr[i] == goal) {
                found = true;
                break;
            }
        }

        printMsg("Linear", goal, i, iterations, found);
        return iterations;
    }

    //Note: same applies as in linearSearch -> return is iterations (or recurses) not index
    public static int binarySearch(int[] arr, int goal) {
        int front = 0;
        int back = arr.length;
        int iterations = 0;

        while (back - front > 1) {
            int middle = front + (back - front) / 2; //using integer division because indexes are ints

            if (arr[middle] > goal)
                back = middle;
            else
                front = middle;

            iterations++;
        }

        boolean found = arr[front] == goal;
        printMsg("Binary", goal, front, iterations, found);
        return iterations;
    }
}
