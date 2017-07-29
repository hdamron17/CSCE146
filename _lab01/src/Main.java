import java.util.Scanner;
import java.util.Random;

public class Main {
    private static final String SIZE_PROMPT = "Enter the integer size of of a square matrix: ";
    private static final String ORIGINAL_PROMPT = "This is the original matrix:";
    private static final String MULTIPLIED_PROMPT = "This is the multiplied matrix:";

    private static final int MULTIPLIER = 2;

    public static int userSize(Scanner input) {
        System.out.print(SIZE_PROMPT);
        return input.nextInt();
    }

    public static int[][] randMatrix(int size) {
        int[][] mat = new int[size][size];
        Random gen = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mat[i][j] = gen.nextInt(10);
            }
        }
        return mat;
    }

    public static int[][] multiplyMatrix(int[][] mat, int multiplier) {
        //Note: only works in the special case of square matrix as in this assignment
        int size = mat.length;
        int newSize = size * multiplier;
        int[][] newMat = new int[newSize][newSize];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int original = mat[i][j];
                for (int iPlus = 0; iPlus < multiplier; iPlus++)
                    for (int jPlus = 0; jPlus < multiplier; jPlus++)
                        newMat[multiplier * i + iPlus][multiplier * j + jPlus] = original;
            }
        }

        return newMat;
    }

    public static String matToString(int[][] mat) {
        //Note: only works in the special case of square matrix as in this assignment
        int size = mat.length;
        String str = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                str += mat[i][j] + " ";
            }
            if (i != size-1) str += "\n";
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        Scanner input = new Scanner(System.in);
        int size = userSize(input);
        int[][] mat = randMatrix(size);
        System.out.println(ORIGINAL_PROMPT + "\n" + matToString(mat));
        System.out.println(MULTIPLIED_PROMPT + "\n" + matToString(multiplyMatrix(mat, MULTIPLIER)));
        input.close();
    }
}
