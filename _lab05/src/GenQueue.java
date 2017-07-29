public interface GenQueue<T> {
    public void enqueue(T item);
    public T dequeue();
    public T peek();
    public void showQueue();
}
