import java.util.*;

public class LinkGraphNode implements Iterable<LinkGraphNode> {
    private Set<LinkGraphNode> links;
    private String name;

    public LinkGraphNode(String name) {
        this.name = name;
        links = new HashSet<>();
    }

    public String getName() { return name; }

    public void addLink(LinkGraphNode node) {
        links.add(node);
    }

    public Iterator<LinkGraphNode> iterator() {
        return links.iterator();
    }

    public String toString() {
        String ret = "( " + name + " -> ";
        for (LinkGraphNode link : this) {
            ret += link.name + " ";
        }
        return ret + ")";
    }
}
