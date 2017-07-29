public class GenQueueStackTester {
    public static void testQueueStack(GenArrayQueueStack qstk) {
        System.out.println("Pushing 0-10");
        for (int i = 0; i < 10; i++)
            qstk.push(i);

        System.out.println("Popping 0-10");
        for (int i = 0; i < 16; i++)
            System.out.println(qstk.pop());
    }

    public static void main(String[] args) {
        GenArrayQueueStack qstk = new GenArrayQueueStack();
        GenQueueTester.testQueue(qstk);
        testQueueStack(qstk);
    }
}
