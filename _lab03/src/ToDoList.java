public class ToDoList {

    private class ListNode {
        public String data;
        public ListNode link;

        public ListNode() {
            this.data = "";
            this.link = null;
        }

        public ListNode(String data, ListNode link) {
            this.data = data;
            this.link = link;
        }
    }

    private ListNode head;
    private ListNode current;
    private ListNode previous;

    public ToDoList() {
        head = null;
        current = head;
        previous = head;
    }

    public void goToNext() {
        if (current != null) {
            if (current.link != null) {
                ListNode oldCurrent = current;
                current = current.link;
                previous = oldCurrent;
            }
        } else {
            current = head;
        }
    }

    public void goToPrev() {
        ListNode node = head;

        if (node == null || previous == null) {
            return;
        }

        while (node.link != previous) {
            node = node.link;
        }

        current = previous;
        previous = node;
    }

    public String getDataAtCurrent() {
        if (current != null) {
            return current.data;
        } else return "";
    }

    public void setDataAtCurrent(String data) {
        if (current != null) {
            current.data = data;
        }
    }

    public void addItem(String data) {
        ListNode lastNode = last();
        if (lastNode != null) {
            ListNode newLast = new ListNode(data, null);
            lastNode.link = newLast;
        } else {
            head = new ListNode(data, null);
        }
    }

    public void insertAfterCurrent(String data) {
        if (current != null) {
            ListNode oldLink = current.link;
            ListNode newLink = new ListNode(data, oldLink);
            current.link = newLink;
        } else {
            //Current is before beginning so insert at front
            ListNode newHead = new ListNode(data, head);
            head = newHead;
        }
    }

    public void deleteCurrentNode() {
        if (current != null) {
            if (head == current) {
                head = current.link;
            }

            current = current.link;

            if (previous != null) {
                previous.link = current;
            }
        }
    }

    public void showList() {
        System.out.println(this.toString());
    }

    public String toString() {
        String ret = "TODO List:\n";

        ListNode node = head;
        while (node != null) {
            ret += "* " + node.data;
            if (node.link != null) ret += "\n";
            node = node.link;
        }

        return ret;
    }

    private ListNode last() {
        ListNode node = head;

        if (node == null)
            return node;

        while (node.link != null) {
            node = node.link;
        }

        return node;
    }
}
