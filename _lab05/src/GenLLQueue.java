public class GenLLQueue<T> implements GenQueue<T> {
    private static final String SEPARATOR = " ";

    private class ListNode {
        public T data;
        public ListNode link;

        public ListNode() {
            data = null;
            link = null;
        }
    }

    private ListNode head;
    private ListNode tail;

    public GenLLQueue() {
        head = null;
        tail = null;
    }

    public void enqueue(T item) {
        ListNode newNode = new ListNode();
        newNode.data = item;

        if (head == null)
            head = newNode;

        if (tail != null)
            tail.link = newNode;

        tail = newNode;
    }

    public T dequeue() {
        if (head != null) {
            ListNode oldHead = head;
            head = head.link;
            return oldHead.data;
        } else return null; //TODO case when queue has been depleted
    }

    public T peek() {
        return head.data;
    }

    public void showQueue() {
        System.out.println(this.toString());
    }

    public String toString() {
        String ret = "";
        ListNode node = head;

        while (node != null) {
            ret += node.data + SEPARATOR;
            node = node.link;
        }

        return ret;
    }
}
