import java.util.*;

public class KruskalAlgorithm {

    public static class Result {
        public final List<Edge> mstEdges;
        public final int totalCost;
        public final long operations;
        public final double timeMs;

        public Result(List<Edge> mstEdges, int totalCost, long operations, double timeMs) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operations = operations;
            this.timeMs = timeMs;
        }
    }

    private static class DSU {
        private final Map<String, String> parent = new HashMap<>();
        private final Map<String, Integer> rank = new HashMap<>();
        long ops = 0;

        DSU(List<String> nodes) {
            for (String v : nodes) {
                parent.put(v, v);
                rank.put(v, 0);
            }
        }
        String find(String x) {
            ops++;
            if (!parent.get(x).equals(x)) parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }
        boolean union(String a, String b) {
            String ra = find(a), rb = find(b);
            if (ra.equals(rb)) return false;
            int raRank = rank.get(ra), rbRank = rank.get(rb);
            if (raRank < rbRank) parent.put(ra, rb);
            else if (raRank > rbRank) parent.put(rb, ra);
            else { parent.put(rb, ra); rank.put(ra, raRank + 1); }
            ops++;
            return true;
        }
    }

    public static Result run(Graph g) {
        long t0 = System.nanoTime();

        List<Edge> edges = new ArrayList<>(g.getEdges());
        Collections.sort(edges);

        DSU dsu = new DSU(g.getNodes());
        List<Edge> mst = new ArrayList<>();
        int cost = 0;

        for (Edge e : edges) {
            if (dsu.union(e.getFrom(), e.getTo())) {
                mst.add(e);
                cost += e.getWeight();
                if (mst.size() == g.vertexCount() - 1) break;
            }
        }

        double ms = (System.nanoTime() - t0) / 1_000_000.0;
        return new Result(mst, cost, dsu.ops + edges.size(), ms);
    }
}
