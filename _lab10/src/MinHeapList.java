import java.util.function.*;

public class MinHeapList<T extends Comparable<T>> extends HeapList {
    public MinHeapList() {
        super(new BiPredicate<T,T>() {
            public boolean test(T x, T y) {
                return x.compareTo(y) < 0;
            }
        });
    }
}
