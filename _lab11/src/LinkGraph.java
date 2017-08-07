import java.util.*;

public class LinkGraph {
    private HashSet<LinkGraphNode> nodes;

    public LinkGraph() {
        nodes = new HashSet<>();
    }

    public void addNodes(String[][] keys) {
        HashMap<String,LinkGraphNode> nodeMap = new HashMap<>();
        for (String[] nodeKeys : keys) {
            if (nodeKeys.length > 0) {
                nodeMap.put(nodeKeys[0], new LinkGraphNode(nodeKeys[0]));
            } //TODO else case when improperly formatted
        }

        for (String[] nodeKeys : keys) {
            if (nodeKeys.length > 0) {
                LinkGraphNode current = nodeMap.get(nodeKeys[0]);
                for (int i = 1; i < nodeKeys.length; i++) {
                    LinkGraphNode link = nodeMap.get(nodeKeys[i]);
                    if (link != null) {
                        current.addLink(link);
                    } //TODO else case of invalid link
                }
            }
        }

        nodes.addAll(nodeMap.values());
    }

    public List<List<String>> getCycles() {
        List<List<String>> cycles = new ArrayList<>();
        for (LinkGraphNode current : nodes) {
            ArrayList<LinkGraphNode> currentExplored = new ArrayList<>();
            currentExplored.add(current);
            cycles.addAll(_getCycles(current, current, currentExplored));
        }

        return cycles;
    }

    public List<List<String>> _getCycles(LinkGraphNode current, LinkGraphNode goal, List<LinkGraphNode> explored) {
        List<List<String>> cycles = new ArrayList<>();

        for (LinkGraphNode link : current) {
            if (!explored.contains(link)) {
                List<LinkGraphNode> subExplored = new ArrayList<>(explored);
                subExplored.add(link);
                cycles.addAll(_getCycles(link, goal, subExplored));
            } else if (explored.size() > 1 && link == goal) {
                List<String> foundCycle = new ArrayList<>();
                for (LinkGraphNode node : explored) {
                    foundCycle.add(node.getName());
                }
                foundCycle.add(goal.getName());
                cycles.add(foundCycle);
            }
        }

        return cycles;
    }

    public String strCycles() {
        String ret = "{\n";
        for (List<String> cycle : getCycles()) {
            ret += "  ";
            for (String node : cycle) {
                ret += node + " ";
            }
            ret += "\n";
        }
        return ret + "}";
    }

    public String toString() {
        String ret = "{\n";
        for (LinkGraphNode node : nodes) {
            ret += "  " + node + "\n";
        }
        return ret + "}";
    }
}
