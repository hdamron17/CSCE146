public class Main {
    public static void main(String[] args) {
        LinkGraph graph = new LinkGraph();
        graph.addNodes(new String[][]{
            {"v1", "v5"},
            {"v2", "v1"},
            {"v3", "v1"},
            {"v4", "v2"},
            {"v5", "v2", "v3", "v7"},
            {"v6", "v3"},
            {"v7", "v4", "v6"}
        });

        System.out.println("Printing Graph");
        System.out.println(graph);

        System.out.println("Printing cycles");
        System.out.println(graph.strCycles());
    }
}
