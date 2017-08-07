import java.util.*;

public class SheepHeap {
    public static class Sheep implements Comparable<Sheep> {
        private String name;
        private double weight;

        public Sheep(String name, double weight) {
            this.name = name;
            this.weight = weight;
        }

        public int compareTo(Sheep other) {
            if (closeTo(this.weight, other.weight)) return 0;
            else if (this.weight > other.weight) return 1;
            else return -1;
        }

        public String toString() {
            return "(" + name + ", " + weight + " lb)";
        }
    }

    private static boolean closeTo(double x, double y) {
        return Math.abs(x - y) < Double.MIN_VALUE * 2;
    }

    private MinHeapList<Sheep> sheeps;

    public SheepHeap() {
        sheeps = new MinHeapList<Sheep>();
    }

    public void addSheep(Sheep poorSheep) {
        sheeps.insert(poorSheep);
    }

    public void addSheep(String name, double weight) {
        addSheep(new Sheep(name, weight));
    }

    //TODO climbUp is unnecessary because the previous heap implementation does the work

    public Sheep removeSheep() {
        return (Sheep) sheeps.delete(); //TODO figure out why the casting is necessary
    }

    //TODO climbDown is unnecessary for same reason as climbUp

    public void sheepRollCall() {
        System.out.println(strSheepRollCall());
    }

    public String strSheepRollCall() {
        String ret = "{\n";
        for (Object guy : sheeps.getBreadthOrder()) { //TODO figure out why the heap is a heap of Objects not generic T
            ret += "  " + guy + "\n";
        }
        return ret + "}";
    }

    public Sheep[] sheepHeapSort() {
        List<Sheep> sortedList = sheeps.getHeapSort();
        Sheep[] sorted = new Sheep[sortedList.size()];
        for (int i = 0; i < sortedList.size(); i++) {
            sorted[i] = sortedList.get(i);
        }
        return sorted;
    }

    public String strSheepHeapSort() {
        String ret = "{\n";
        for (Sheep guy : sheepHeapSort()) {
            ret += "  " + guy + "\n";
        }
        return ret + "}";
    }

    public void printSheepHeapSort() {
        System.out.println(strSheepHeapSort());
    }
}
