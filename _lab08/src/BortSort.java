import java.util.*;
import java.util.function.*;

@SuppressWarnings("unchecked")
public class BortSort {
    public static <T> List<Pair<T,Double>> makeList(T[] arr, Function<T,Double> makeValue) {
        List<Pair<T,Double>> lst = new LinkedList<>();

        for (T item : arr) {
            lst.add(new Pair(item, makeValue.apply(item)));
        }

        return lst;
    }

    public static <T,U> T[] fromList(List<Pair<T,U>> lst) {
        T[] arr = (T[]) new Object[lst.size()];

        for (int i = 0; i < lst.size(); i++) {
            arr[i] = lst.get(i).getK();
        }

        return arr;
    }

    public static <T> T[] selectionSort(T[] arr, Function<T,Double> makeValue) {
        List<Pair<T,Double>> lst = makeList(arr, makeValue);
        int size = lst.size();

        for (int i = 0; i < size; i++) {
            Pair<T,Double> old = lst.get(i);
            Pair<T,Double> min = old;
            int minIndex = i;

            for (int j = i; j < size; j++) {
                Pair<T,Double> test = lst.get(j);
                if (lst.get(j).getV() < min.getV()) {
                    min = test;
                    minIndex = j;
                }
            }

            lst.set(i, min);
            lst.set(minIndex, old);
        }

        return fromList(lst);
    }

    public static <T> T[] insertionSort(T[] arr, Function<T,Double> makeValue) {
        List<Pair<T,Double>> lst = makeList(arr, makeValue);
        int size = lst.size();

        for (int i = 0; i < size; i++) {
            Pair<T,Double> old = lst.get(i);

            for (int j = i-1; j >= 0; j--) {
                if ((j-1 < 0 || lst.get(j-1).getV() < old.getV())
                                && lst.get(j).getV() >= old.getV()) {
                    lst.add(j, lst.remove(i));
                }
            }
        }

        return fromList(lst);
    }

    public static <T> T[] bubbleSort(T[] arr, Function<T,Double> makeValue) {
        List<Pair<T,Double>> lst = makeList(arr, makeValue);
        int size = lst.size();
        boolean done = false;

        while (!done) {
            done = true;
            for (int i = 0; i < size-1; i++) {
                Pair<T,Double> first = lst.get(i);
                Pair<T,Double> second = lst.get(i+1);

                if (first.getV() > second.getV()) {
                    lst.set(i, second);
                    lst.set(i+1, first);
                    done = false;
                }
            }
        }

        return fromList(lst);
    }

    public static <T> T[] mergeSort(T[] arr, Function<T,Double> makeValue) {
        List<Pair<T,Double>> lst = makeList(arr, makeValue);
        return fromList(_mergeSort(lst));
    }

    private static <T> List<Pair<T,Double>> _mergeSort(List<Pair<T,Double>> lst) {
        int size = lst.size();
        if (lst.size() > 1) {
            int half = size / 2;

            List<Pair<T,Double>> left = _mergeSort(lst.subList(0, half));
            List<Pair<T,Double>> right = _mergeSort(lst.subList(half, size));
            List<Pair<T,Double>> newList = new LinkedList<>();

            int leftI = 0, rightI = 0;
            while (leftI < left.size() && rightI < right.size()) {
                Pair<T,Double> leftItem = left.get(leftI);
                Pair<T,Double> rightItem = right.get(rightI);

                if (leftItem.getV() < rightItem.getV()) {
                    newList.add(leftItem);
                    leftI++;
                } else {
                    newList.add(rightItem);
                    rightI++;
                }
            }

            while (leftI < left.size()) {
                newList.add(left.get(leftI));
                leftI++;
            }

            while (rightI < right.size()) {
                newList.add(right.get(rightI));
                rightI++;
            }

            return newList;
        } else {
            return lst;
        }
    }

    public static <T> T[] quickSort(T[] arr, Function<T,Double> makeValue) {
        List<Pair<T,Double>> lst = makeList(arr, makeValue);
        return fromList(_quickSort(lst));
    }

    private static <T> List<Pair<T,Double>> _quickSort(List<Pair<T,Double>> lst) {
        int size = lst.size();

        if (size > 1) {
            Pair<T,Double> pivot = lst.remove(size/2);
            double pivotVal = pivot.getV();
            int left = 0;
            int right = size-2;

            while (left != right) {
                while (left < right && lst.get(left).getV() <= pivotVal)
                    left++;

                while (right > left && lst.get(right).getV() >= pivotVal)
                    right--;

                Pair<T,Double> oldLeft = lst.get(left);

                Pair<T,Double> oldRight = lst.get(right);

                if (left != right) {
                    lst.set(left, oldRight);
                    lst.set(right, oldLeft);
                }
            }

            if (pivotVal > lst.get(left).getV()) {
                lst.add(left+1, pivot); //Move pivot to center or end if larger than last item
                left++;
            } else {
                lst.add(left, pivot); //Move pivot to center
            }

            List<Pair<T,Double>> leftSub = new LinkedList<>(lst.subList(0, left));
            List<Pair<T,Double>> rightSub = new LinkedList<>(lst.subList(left+1, size));

            List<Pair<T,Double>> newList = new LinkedList<>();
            newList.addAll(_quickSort(leftSub));
            newList.add(pivot);
            newList.addAll(_quickSort(rightSub));

            return newList;
        } else {
            return lst;
        }
    }

    public static int stringCount(String whole, String substring) {
        whole = whole.toLowerCase();
        substring = substring.toLowerCase();
        int count = 0;

        for (int i = 0; i <= whole.length() - substring.length(); i++) {
            for (int j = 0; j < substring.length() && whole.charAt(i+j) == substring.charAt(j); j++) {
                if (j == substring.length()-1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{
            "Bort",
            "asdf",
            "asdfbort",
            "asdfasdfbortBortBORTbort",
            "bortbortbortbortBORTBORTBORTBORT",
            "qwertqwertqwertqwertbortBORT",
            "bortzilla",
            "bortderbort",
            "bored"
        };

        Function<String,Double> bortFun = (str)->{return (double)stringCount(str,"bort");};

/*
        Integer[] arr = new Integer[]{1,0,1,4,8,2,1,2,0};
        Function<Integer,Double> bortFun = (i)->{return (double)i;};
*/

        System.out.println("ORIGINAL ARRAY\n==============\n" + Arrays.toString(arr) + "\n");

        System.out.println("SELECTION SORT\n==============\n" + Arrays.toString(selectionSort(arr, bortFun)) + "\n");
        System.out.println("INSERTION SORT\n==============\n" + Arrays.toString(insertionSort(arr, bortFun)) + "\n");
        System.out.println("BUBBLE SORT\n===========\n" + Arrays.toString(bubbleSort(arr, bortFun)) + "\n");
        System.out.println("MERGE SORT\n==========\n" + Arrays.toString(mergeSort(arr, bortFun)) + "\n");
        System.out.println("QUICKSORT\n=========\n" + Arrays.toString(quickSort(arr, bortFun)) + "\n");
    }
}
