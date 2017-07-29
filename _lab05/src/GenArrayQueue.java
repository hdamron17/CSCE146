public class GenArrayQueue<T> implements GenQueue<T> {
    private static final int DEFAULT_SIZE = 100;
    private static final String SEPARATOR = " ";

    private T[] queue;
    private int i;

    public GenArrayQueue() {
        this(DEFAULT_SIZE);
    }

    public GenArrayQueue(int size) {
        queue = (T[]) new Object[size];
        i = 0;
    }

    public void enqueue(T item) {
        if (i < queue.length) {
            for (int j = i; j > 0; j--) {
                queue[j] = queue[j-1];
            }
            queue[0] = item;
            i++;
        } //TODO else case queue full
    }

    public T dequeue() {
        if (i > 0) {
            i--;
            T old = queue[i];
            queue[i] = null;
            return old;
        } else return null; //TODO case of nothing to dequeue
    }

    public T peek() {
        if (i > 0)
            return queue[i-1];
        else
            return null;
    }

    public void showQueue() {
        System.out.println(this.toString());
    }

    public String toString() {
        String ret = "";

        for (int j = i-1; j >= 0; j--)
            ret += queue[j] + SEPARATOR;

        return ret;
    }
}
