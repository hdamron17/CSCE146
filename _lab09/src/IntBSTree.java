public class IntBSTree {
    private static class Node {
        public int data;
        public Node leftChild;
        public Node rightChild;

        public Node(int data, Node leftChild, Node rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public String toString() {
            return "{" + this.data + "}";
        }
    }

    private Node root;

    public void insert(int elem) {
        Node newChild = new Node(elem, null, null);

        if (this.root == null) {
            this.root = newChild;
        } else {
            Node current = this.root;
            while (true) {
                Node next;
                if (elem < current.data) {
                    next = current.leftChild;
                    if (next == null) {
                        current.leftChild = newChild;
                        return;
                    }
                } else if (elem > current.data) {
                    next = current.rightChild;
                    if (next == null) {
                        current.rightChild = newChild;
                        return;
                    }
                } else {
                    return;
                }
                current = next;
            }
        }
    }

    public void remove(int elem) {
        _remove(this.root, null, elem, true, true);
    }

    //boolean takeFrom designates left or right in case of two children -> alternates in recursion; true:left
    //boolean parentTakeFrom is equivalent of takeFrom for parent to root (ignored if parent is null)
    public void _remove(Node root, Node rootParent, int elem, boolean takeFrom, boolean parentTakeFrom) {
        Node current = root;
        Node parent = null;

        while (current.data != elem) {
            parent = current;
            current = (elem < current.data) ? current.leftChild : current.rightChild;

            if (current == null) {
                return;
            }
        }

        Node replacement;

        if (current.leftChild == null && current.rightChild == null) {
            replacement = null;
        } else if (current.leftChild == null) {
            replacement = current.rightChild;
        } else if (current.rightChild == null) {
            replacement = current.leftChild;
        } else {
            Node child = takeFrom ? current.leftChild : current.rightChild;
            Node nextChild = child;
            Node currentChild = null;

            while (nextChild != null) {
                currentChild = nextChild;
                nextChild = takeFrom ? nextChild.rightChild : nextChild.leftChild;
            }

            boolean newParentTakeFrom = child.data < current.data;
            current.data = currentChild.data;
            _remove(child, current, currentChild.data, !takeFrom, newParentTakeFrom);
            return;
        }

        if (parent != null) {
            if (current.data < parent.data) {
                parent.leftChild = replacement;
            } else {
                parent.rightChild = replacement;
            }
        } else if (rootParent != null) {
            //Do replacement on root if parent si null
            if (parentTakeFrom) {
                rootParent.leftChild = replacement;
            } else {
                rootParent.rightChild = replacement;
            }
        } else {
            this.root = replacement;
        }
    }

    public void printPreorder() {
        _printPreorder(this.root);
    }

    public void _printPreorder(Node current) {
        if (current != null) {
            System.out.println(current.data);
            _printPreorder(current.leftChild);
            _printPreorder(current.rightChild);
        }
    }

    public int getDepth(int elem) {
        Node current = this.root;
        int depth = 0;

        while (current.data != elem) {
            current = (elem < current.data) ? current.leftChild : current.rightChild;
            depth++;

            if (current == null)
                return -1;
        }

        return depth;
    }

    public String toString() {
        return "( " +  _inOrder(this.root, 0) + ")";
    }

    public String _inOrder(Node current, int depth) {
        if (current == null) {
            return "";
        } else {
            String ret = "";
            ret += _inOrder(current.leftChild, depth+1);
            ret += current.data + " ";
            ret += _inOrder(current.rightChild, depth+1);
            return ret;
        }
    }
}
