import java.util.*;
import java.util.function.*;

public class MoreSort {
    public static <T> SortData<T> sort(T[] arr, String sortMethod, Function<T,Double> makeVal) {
        BiFunction<T[], Function<T,Double>, SortData<T>> sorter;
        switch(sortMethod) {
            case "selection":
                sorter = ModifiedBortSort::selectionSort;
                break;
            case "insertion":
                sorter = ModifiedBortSort::insertionSort;
                break;
            case "bubble":
                sorter = ModifiedBortSort::bubbleSort;
                break;
            case "merge":
                sorter = ModifiedBortSort::mergeSort;
                break;
            case "quick":
                sorter = ModifiedBortSort::quickSort;
                break;
            default:
                return null;
        }

        SortData<T> sorted = sorter.apply(arr, makeVal);
        return sorted;
    }

    public static Integer[] randInts(int size) {
        Integer[] arr = new Integer[size];
        Random gen = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = gen.nextInt(size);
        }

        return arr;
    }

    public static void main(String[] args) {
        Integer[] arr = randInts(1000);
        Function<Integer,Double> caster = (x)->{return (double)x;};

        String[] sortMethods = new String[]{"selection", "insertion", "bubble", "merge", "quick"};

        for (String method : sortMethods) {
            SortData<Integer> sorted = sort(arr, method, caster);
            System.out.println(method + " sort count = " + sorted.getCount());
        }
    }
}
