/*
 * Modified from BortSort.java in lab08 to include check counts and return SortData
 */

import java.util.*;
import java.util.function.*;

@SuppressWarnings("unchecked")
public class ModifiedBortSort {
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

    public static <T> SortData<T> selectionSort(T[] arr, Function<T,Double> makeValue) {
        List<Pair<T,Double>> lst = makeList(arr, makeValue);
        int size = lst.size();
        int count = 0;

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
                count++;
            }

            lst.set(i, min);
            lst.set(minIndex, old);
            count++;
        }

        return new SortData<>(fromList(lst), count);
    }

    public static <T> SortData<T> insertionSort(T[] arr, Function<T,Double> makeValue) {
        List<Pair<T,Double>> lst = makeList(arr, makeValue);
        int size = lst.size();
        int count = 0;

        for (int i = 0; i < size; i++) {
            Pair<T,Double> old = lst.get(i);

            for (int j = i-1; j >= 0; j--) {
                if ((j-1 < 0 || lst.get(j-1).getV() < old.getV())
                                && lst.get(j).getV() >= old.getV()) {
                    lst.add(j, lst.remove(i));
                    break;
                }
                count++;
            }
            count++;
        }

        return new SortData<>(fromList(lst), count);
    }

    public static <T> SortData<T> bubbleSort(T[] arr, Function<T,Double> makeValue) {
        List<Pair<T,Double>> lst = makeList(arr, makeValue);
        int size = lst.size();
        boolean done = false;
        int     count = 0;

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
                count++;
            }
            count++;
        }

        return new SortData<>(fromList(lst), count);
    }

    public static <T> SortData<T> mergeSort(T[] arr, Function<T,Double> makeValue) {
        List<Pair<T,Double>> lst = makeList(arr, makeValue);
        Pair<List<Pair<T,Double>>,Integer> sorted = _mergeSort(lst);
        T[] sortedArr = fromList(sorted.getK());
        int count = sorted.getV();
        return new SortData<>(sortedArr, count);
    }

    private static <T> Pair<List<Pair<T,Double>>,Integer> _mergeSort(List<Pair<T,Double>> lst) {
        int size = lst.size();
        if (lst.size() > 1) {
            int half = size / 2;

            Pair<List<Pair<T,Double>>,Integer> leftWithCount = _mergeSort(lst.subList(0, half));
            List<Pair<T,Double>> left = leftWithCount.getK();
            int leftCount = leftWithCount.getV();

            Pair<List<Pair<T,Double>>,Integer> rightWithCount = _mergeSort(lst.subList(half, size));
            List<Pair<T,Double>> right = rightWithCount.getK();
            int rightCount = rightWithCount.getV();

            List<Pair<T,Double>> newList = new LinkedList<>();
            int newCount = leftCount + rightCount + 1; //Plus 1 for this recursive level

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
                newCount++;
            }

            while (leftI < left.size()) {
                newList.add(left.get(leftI));
                leftI++;
                newCount++;
            }

            while (rightI < right.size()) {
                newList.add(right.get(rightI));
                rightI++;
                newCount++;
            }

            return new Pair<>(newList, newCount);
        } else {
            return new Pair<>(lst, 1);
        }
    }

    public static <T> SortData<T> quickSort(T[] arr, Function<T,Double> makeValue) {
        List<Pair<T,Double>> lst = makeList(arr, makeValue);
        Pair<List<Pair<T,Double>>,Integer> sorted = _quickSort(lst);
        T[] sortedArr = fromList(sorted.getK());
        int count = sorted.getV();
        return new SortData<>(sortedArr, count);
    }

    private static <T> Pair<List<Pair<T,Double>>,Integer> _quickSort(List<Pair<T,Double>> lst) {
        int size = lst.size();

        if (size > 1) {
            Pair<T,Double> pivot = lst.remove(size/2);
            double pivotVal = pivot.getV();
            int left = 0;
            int right = size-2;
            int count = 0;

            while (left != right) {
                while (left < right && lst.get(left).getV() <= pivotVal) {
                    left++;
                    count++;
                }

                while (right > left && lst.get(right).getV() >= pivotVal) {
                    right--;
                    count++;
                }

                Pair<T,Double> oldLeft = lst.get(left);

                Pair<T,Double> oldRight = lst.get(right);

                if (left != right) {
                    lst.set(left, oldRight);
                    lst.set(right, oldLeft);
                }

                count++;
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

            Pair<List<Pair<T,Double>>,Integer> leftSorted = _quickSort(leftSub);
            Pair<List<Pair<T,Double>>,Integer> rightSorted = _quickSort(rightSub);

            count += leftSorted.getV() + rightSorted.getV() + 1; //Plus 1 for this recursive level

            newList.addAll(leftSorted.getK());
            newList.add(pivot);
            newList.addAll(rightSorted.getK());

            return new Pair<>(newList, count);
        } else {
            return new Pair<>(lst, 1);
        }
    }
}
