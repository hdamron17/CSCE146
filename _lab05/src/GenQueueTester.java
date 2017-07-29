public class GenQueueTester {
    public static void testQueue(GenQueue<Integer> q) {
        System.out.println("Enqueue'ing 10 numbers 0-9");
        for (int i = 0; i < 10; i++)
            q.enqueue(i);

        System.out.println("Dequeue'ing everything and printing each");
        for (int i = 0; i < 10; i++)
            System.out.println(q.dequeue());

        System.out.println("Repopulating");
        int[] repopulation = {5,0,2,4,6,8};
        for (int i : repopulation)
            q.enqueue(i);

        System.out.println("Testing peek");
        System.out.println(q.peek());

        System.out.println("Testing showQueue");
        q.showQueue();
    }

    public static void main(String[] args) {
        System.out.println("Testing GenArrayQueue");
        testQueue(new GenArrayQueue<Integer>());
        System.out.println();

        System.out.println("Testing GenLLQueue");
        testQueue(new GenLLQueue<Integer>());
    }
}
