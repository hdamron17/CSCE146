public class GenArrayQueueStack<T> extends GenArrayQueue<T> implements GenQueueStack<T> {
    public void push(T item) {
        if (i < queue.length) {
            queue[i] = item;
            i++;
        }
    }

    public T pop() {
        return dequeue();
    }
}
