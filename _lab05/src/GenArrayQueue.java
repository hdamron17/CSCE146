
public class GenArrayQueue<T> implements GenQueue<T> {
    protected static final int DEFAULT_SIZE = 100;
    protected static final String SEPARATOR = " ";

    protected T[] queue;
    protected int i;

    public GenArrayQueue() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
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
