public class ProcessHeap {
    private static int DEBUG = 0; //Levels 0-5 with 5 being most verbose
    private static void debug(int level, Object toPrint) {
        if (DEBUG >= level) {
            System.out.println(toPrint.toString());
        }
    }
    private static void debug(Object toPrint) { debug(3, toPrint); }

    private static int DEFAULT_SIZE = 100;

    private Process[] heap;
    private int size;

    public ProcessHeap() {
        this(DEFAULT_SIZE);
    }

    public ProcessHeap(int size) {
        heap = new Process[size];
        size = 0;
    }

    public ProcessHeap(Process[] heap) {
        this(heap, heap.length);
    }

    public ProcessHeap(Process[] heap, int size) {
        this.size = size < heap.length ? size : heap.length;
        this.heap = new Process[size];
        for (int i = 0; i < this.size; i++) {
            this.heap[i] = heap[i];
        }
    }

    public void insert(Process elem) {
        int x = 2; //TODO remove
        debug(x,":Inserting " + elem);

        if (size >= heap.length) {
            System.err.println("CANNOT INSERT : HEAP FULL");
            return;
        }

        heap[size] = elem;
        int currentPos = size;
        size++;

        boolean done = false;
        while (!done) {
            int parentPos = (currentPos - 1) / 2;
            if (currentPos == 0) {
                done = true;
            } else if (cmp(elem, heap[parentPos])) {
                debug(x,"::Swapping heap["+currentPos+"]="+heap[currentPos]+" and heap["+parentPos+"]="+heap[parentPos]);
                heap[currentPos] = heap[parentPos];
                heap[parentPos] = elem;
                currentPos = parentPos;
            } else {
                done = true;
            }
        }

        debug(x,"::Heap is now \n>" + this.strBreadthOrder());
        debug(x,"::Sorted heap is \n~" + this);
        debug(x,":Done inserting " + elem);
    }

    public Process peek() {
        return (size > 0) ? heap[0] : null;
    }

    public Process remove() {
        int x = 2; //TODO remove
        if (isEmpty()) {
            System.err.println("CANNOT REMOVE : HEAP EMPTY");
            return null;
        }

        debug(x,":Removing " + heap[0]);

        //Remove item to return and replace it with last item
        Process ret = heap[0];
        heap[0] = heap[size-1];
        heap[size-1] = null;
        size--;

        //Move item down from top
        int currentPos = 0;
        boolean done = false;
        while (!done) {
            int leftChildPos = currentPos * 2 + 1;
            int rightChildPos = leftChildPos + 1;
            debug(x,":::Testing swap of indexes "+currentPos+" and ("+leftChildPos+"  "+rightChildPos+")");

            if (leftChildPos >= size) {
                debug(x,":::Removal done because leftChild out of bounds");
                done = true;
            } else {
                int childPos;

                if (rightChildPos >= size) {
                    childPos = leftChildPos;
                } else {
                    debug(x,":::Comparing children "+heap[leftChildPos]+" and "+heap[rightChildPos]);
                    childPos = cmp(heap[leftChildPos], heap[rightChildPos]) ? leftChildPos : rightChildPos;
                }

                Process old = heap[currentPos];
                debug(x,":::Comparing heap[childPos:"+childPos+"]="+heap[childPos]+" and heap[currentPos:"+currentPos+"]="+old);
                if (cmp(heap[childPos], old)) {
                    debug(x,"::Swapping heap["+currentPos+"]="+heap[currentPos]+" and heap["+childPos+"]="+heap[childPos]);
                    heap[currentPos] = heap[childPos];
                    heap[childPos] = old;
                    currentPos = childPos;
                } else {
                    debug(x,":::Removal done because heap[childPos:"+childPos+"]="+heap[childPos]+" > heap[currentPos:"+currentPos+"]="+heap[currentPos]);
                    done = true;
                }
            }

            done = true; //TODO
        }

        debug(x,"::Heap is now \n>" + strBreadthOrder());
        debug(x,"::Heap is now \n~" + this);
        debug(x,":Done removing " + ret);
        return ret;
    }

    //Prints heap in breadth order
    public void printHeap() {
        System.out.println(strBreadthOrder());
    }

    public String strBreadthOrder() {
        String ret = "{\n";
        for (int i = 0; i < size; i++) {
            ret += "  " + heap[i] + "\n";
        }
        return ret + "}";
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    //Prints heap after sorting
    public static void heapSort(ProcessHeap heap) {
        System.out.println(heap.strHeapSorted());
    }

    public String strHeapSorted() {
        String ret = "{\n";
        for (Process elem : getHeapSorted()) {
            ret += "  " + elem + "\n";
        }
        return ret + "}";
    }

    public Process[] getHeapSorted() {
        int oldDebug = DEBUG; DEBUG=0; //TODO

        ProcessHeap copy = shallowCopy();
        Process[] sorted = new Process[copy.size];
        int i = 0;

        while (!copy.isEmpty()) {
            sorted[i] = copy.remove();
            i++;
        }

        DEBUG = oldDebug; //TODO

        return sorted;
    }

    public ProcessHeap shallowCopy() {
        return new ProcessHeap(this.heap, this.size);
    }

    public String toString() {
        return strHeapSorted(); //TODO possibly use strBreadthOrder
    }

    public static boolean cmp(Process p1, Process p2) {
        return p1.compareTo(p2) > 0;
    }
}
