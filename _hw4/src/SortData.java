import java.util.*;

public class SortData<T> {
    private int count;
    private T[] sorted;

    public SortData(T[] sorted, int count) {
        this.sorted = sorted;
        this.count = count;
    }

    public int getCount() { return count; }
    public T[] getSorted() { return sorted; }

    public String toString() {
        return Arrays.toString(sorted) + "~{" + count + "}";
    }
}
