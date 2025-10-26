import java.util.*;

public class Graph {
    private final int id;
    private final List<String> nodes;
    private final List<Edge> edges;
    private final Map<String, List<Edge>> adj = new HashMap<>();

    public Graph(int id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = nodes;
        this.edges = edges;
        buildAdjacency();
    }

    private void buildAdjacency() {
        for (String v : nodes) adj.put(v, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.getFrom()).add(e);
            adj.get(e.getTo()).add(new Edge(e.getTo(), e.getFrom(), e.getWeight()));
        }
    }

    public int getId() { return id; }
    public List<String> getNodes() { return nodes; }
    public List<Edge> getEdges() { return edges; }
    public Map<String, List<Edge>> getAdj() { return adj; }

    public int vertexCount() { return nodes.size(); }
    public int edgeCount() { return edges.size(); }
}
