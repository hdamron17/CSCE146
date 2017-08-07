public class Main {
    public static void main(String[] args) {
        int[] toInsert = {8,13,3,4,18,19,10,1,9,2};
        IntBSTree tree = new IntBSTree();

        System.out.println("Testing the tree");

        for (int elem : toInsert) {
            System.out.println("Inserting " + elem);
            tree.insert(elem);
        }

        System.out.println("Printing the preorder traversal");
//        tree.printPreorder();
        System.out.println(tree);

        System.out.println("Removing the number x");
        tree.remove(13);

        System.out.println("The depth of 9 is " + tree.getDepth(9));
//        tree.printPreorder();
        System.out.println(tree);

        System.out.println("Done!");
    }
}
