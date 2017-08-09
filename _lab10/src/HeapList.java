import java.util.*;
import java.util.function.*;

@SuppressWarnings("unchecked")
public class HeapList<T extends Comparable<T>> {
    private List<T> heap;
    private BiPredicate<T,T> cmp;

    public HeapList(BiPredicate<T,T> cmp) {
        heap = new ArrayList<>();
        this.cmp = cmp;
    }

    public void insert(T elem) {
        heap.add(elem);

        int pos = heap.size()-1;
        boolean done = false;
        while (!done) {
            int parentPos = (pos-1) / 2;
            T parent = heap.get(parentPos);
            if (cmp.test(elem, parent)) {
                //Flip
                heap.set(pos, parent);
                heap.set(parentPos, elem);
                pos = parentPos;
            } else done = true;
        }
    }

    public T delete() {
        if (heap.size() == 0) {
            return null;
        } else if (heap.size() == 1) {
            return heap.remove(0);
        }

        T ret = heap.get(0);
        T elem = heap.remove(heap.size()-1);
        heap.set(0, elem);

        int pos = 0;
        boolean done = false;
        while (!done) {
            int leftPos = 2*pos+1;
            int rightPos = 2*pos+2;

            if (leftPos >= heap.size()) {
                done = true;
            } else {
                T left = heap.get(leftPos);
                int childPos;
                T child;

                if (rightPos >= heap.size()) {
                    childPos = leftPos;
                    child = left;
                } else {
                    T right = heap.get(rightPos);

                    if (cmp.test(left, right)) {
                        childPos = leftPos;
                        child = left;
                    } else {
                        childPos = rightPos;
                        child = right;
                    }
                }

                if (cmp.test(child, elem)) {
                    heap.set(pos, child);
                    heap.set(childPos, elem);
                    pos = childPos;
                } else done = true;
            }
        }

        return ret;
    }

    public void heapSort() {
        System.out.println(strHeapSort());
    }

    public void print() {
        System.out.println(strBreadthOrder());
    }

    public String strBreadthOrder() {
        String ret = "(";
        for (int i = 0; i < heap.size(); i++) {
            ret += heap.get(i) + (i != heap.size() - 1 ? " " : "");
        }
        return ret + ")";
    }

    public List<T> getBreadthOrder() {
        return heap;
    }

    public String strHeapSort() {
        List<T> sorted = getHeapSort();
        String ret = "(";
        for (int i = 0; i < sorted.size(); i++) {
            ret += sorted.get(i) + (i != sorted.size() - 1 ? " " : "");
        }
        return ret + ")";
    }

    public List<T> getHeapSort() {
        HeapList<T> copy = shallowCopy();
        List<T> sorted = new ArrayList();
        T elem = copy.delete();
        while (elem != null) {
            sorted.add(elem);
            elem = copy.delete();
        }

        return sorted;
    }

    public int getSize() {
        return heap.size();
    }

    public HeapList<T> shallowCopy() {
        HeapList<T> copy = new HeapList<>(this.cmp);
        copy.heap = new ArrayList();
        copy.heap.addAll(this.heap);
        return copy;
    }

    public String toString() {
        return strHeapSort();
    }
}
