import java.util.*;

public class PrimAlgorithm {

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

    public static Result run(Graph g) {
        long ops = 0;
        long t0 = System.nanoTime();

        Set<String> used = new HashSet<>();
        List<Edge> mst = new ArrayList<>();
        int cost = 0;

        String start = g.getNodes().get(0);
        used.add(start);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge e : g.getAdj().get(start)) { pq.add(e); ops++; }

        while (!pq.isEmpty() && used.size() < g.vertexCount()) {
            Edge e = pq.poll(); ops++;
            if (used.contains(e.getTo())) continue;

            used.add(e.getTo());
            mst.add(new Edge(e.getFrom(), e.getTo(), e.getWeight()));
            cost += e.getWeight();

            for (Edge ne : g.getAdj().get(e.getTo())) {
                if (!used.contains(ne.getTo())) { pq.add(ne); ops++; }
            }
        }

        double ms = (System.nanoTime() - t0) / 1_000_000.0;
        return new Result(mst, cost, ops, ms);
    }
}
