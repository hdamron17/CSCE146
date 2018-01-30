public class Process implements Comparable<Process> {
    public static int unnamedNum = 0;

    private int priority;
    private double time;
    private String name;

    public Process() {
        priority = 0;
        time = 0;
        name = "Unnamed " + (unnamedNum++);
    }

    public Process(String name, int priority, double time) {
        this.name = name;
        this.priority = priority;
        this.time = time;
    }

    public int getPriority() { return priority; }
    public double getTime() { return time; }
    public String getName() { return name; }

    public void setPriority(int priority) { this.priority = priority; }
    public void setTime(double time) { this.time = time; }
    public void setName(String name) { this.name = name; }

    public String toString() {
        return "('" + name + "',priority=" + priority + ",time=" + time + ")";
    }

    public int compareTo(Process other) {
        return other.priority - this.priority;
    }
}
