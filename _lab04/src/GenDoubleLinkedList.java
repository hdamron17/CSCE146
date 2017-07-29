public class GenDoubleLinkedList<T> {

    private class ListNode {
        public T data;
        public ListNode prevLink;
        public ListNode nextLink;

        public ListNode() {
            data = null;
            prevLink = null;
            nextLink = null;
        }

        public ListNode(T data, ListNode prevLink, ListNode nextLink) {
            this.data = data;
            this.prevLink = prevLink;
            this.nextLink = nextLink;
        }

        public String toString() {
            return "(" + data + ")[" + (prevLink == null ? "null" : prevLink.address()) + "-" + this.address() + "-" + (nextLink == null ? "null" : nextLink.address()) + "]";
        }

        public String address() {
            return Integer.toHexString(System.identityHashCode(this));
        }
    }

    private ListNode head;
    private ListNode current;

    public GenDoubleLinkedList() {
        head = new ListNode();
        current = head;
    }

    public void goToNext() {
        if (current.nextLink != null) {
            current = current.nextLink;
        }
    }

    public void goToPrev() {
        if (current.prevLink != null) {
            current = current.prevLink;
        }
    }

    public T getDataAtCurrent() {
        return current.data;
    }

    public void setDataAtCurrent(T data) {
        current.data = data;
    }

    public void insertNodeAfterCurrent(T data) {
        ListNode after = current.nextLink;
        ListNode newNode = new ListNode(data, current, after);
        current.nextLink = newNode;
        if (after != null)
            after.prevLink = newNode;
    }

    public void deleteCurrentNode() {
        ListNode before = current.prevLink;
        ListNode after = current.nextLink;
        if (before != null)
            before.nextLink = after;
        if (after != null)
            after.prevLink = before;
        if (head == current)
            head = current.nextLink;
        current = current.nextLink;
    }

    public void showList() {
        System.out.println(this.toString());
    }

    public boolean inList(T data) {
        ListNode node = head;

        while (node != null) {
            if (node.data.equals(data))
                return true;

            node = node.nextLink;
        }
        return false;
    }

    public String toString() {
        ListNode node = head;
        String ret = "";
        while (node != null) {
            //ret += "* " + node.toString();
            ret += "* " + node.data;

            if (node.nextLink != null)
                ret += "\n";

            node = node.nextLink;
        }
        return ret;
    }
}
