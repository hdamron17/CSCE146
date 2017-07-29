public class GroceryList {
    public static void main(String[] args) {
        ToDoList lst = new ToDoList();

        System.out.println("Adding five items TODO");
        lst.insertAfterCurrent("Buy ground beef"); //To demonstrate that insertAfterCurrent acts like addItem when current is null
        lst.addItem("Buy cheese");
        lst.addItem("Buy taco shells");
        lst.addItem("Make tacos");
        lst.addItem("Eat tacos");
        lst.showList();
        System.out.println();

        System.out.println("What should I be working on right now?");
        System.out.println("~ " + (lst.getDataAtCurrent() != "" ? lst.getDataAtCurrent() : "(Nothing)"));
        System.out.println();

        System.out.println("I forgot to get salsa. Let me add that after step 2");
        lst.goToNext();
        lst.goToPrev(); //Offset by another goToNext just to prove that goToPrev() works
        lst.goToNext();
        lst.goToNext();
        lst.insertAfterCurrent("Buy salsa");
        lst.showList();
        System.out.println();

        System.out.println("On second thought I’m in a spicy mood so let’s change salsa to hot sauce.");
        lst.goToNext();
        lst.setDataAtCurrent("Buy hot sauce");
        lst.showList();
        System.out.println();

        System.out.println("How about now? What should I be doing?");
        System.out.println("~ " + lst.getDataAtCurrent());
        System.out.println();

        System.out.println("Do people put guacamole on tacos? I’ll add it after step 3.");
        lst.insertAfterCurrent("Buy guacamole");
        lst.showList();
        System.out.println();

        System.out.println("On second thought I don’t think they do let me take that out.");
        lst.goToNext();
        lst.deleteCurrentNode();
        lst.showList();
        System.out.println();

        //TODO implement test cases
    }
}
