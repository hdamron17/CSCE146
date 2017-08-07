import java.util.*;

public class Main {
    public static void main(String[] args) {
        SheepHeap sheeps = new SheepHeap() {{
            addSheep("Jim", 54.2);
            addSheep("Adam", 43.8);
            addSheep("Steve", 49.3);
            addSheep("Chris", 61.0);
            addSheep("Kris", 55.5);
            addSheep("Christina", 38.0);
            addSheep("Jesse", 40.3);
            addSheep("Elvis", 44.5);
            addSheep("Rick", 39.1);
            addSheep("Vladmir", 39.5);
            addSheep("Hans", 42.0);
            addSheep("Joseph", 65.4);
            addSheep("Kenneth", 43.7);
            addSheep("Felix", 47.2);
            addSheep("JJ", 50.0);
        }};

        System.out.println("Printing breakfast roll call");
        sheeps.sheepRollCall();

        for (int i = 0; i < 5; i++) {
            System.out.println("Sold " + sheeps.removeSheep());
        }

        System.out.println("Printing dinner roll call");
        sheeps.sheepRollCall();

        System.out.println("Printing slaughter house reverse order");
        sheeps.printSheepHeapSort();
    }
}
