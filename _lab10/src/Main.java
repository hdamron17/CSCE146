public class Main {
    public static void main(String[] args) {
        HeapList<Integer> heap = new MinHeapList<Integer>();

        int[] toInsert = {21,37,49,11,23,1,13,16,33,17};
        System.out.println("Populating heap with values");
        for (int elem : toInsert) {
            System.out.print(elem + " ");
            heap.insert(elem);
        }
        System.out.println();

        System.out.println("Printing heap");
        heap.print();

        System.out.println("Testing heap sort");
        heap.heapSort();

        System.out.println("Removing an element from the heap");
        System.out.println("The element removed is " + heap.delete() + " and the heap is now");
        heap.print();

    }
}
