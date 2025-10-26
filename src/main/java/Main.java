import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {

    static class InputFile { List<InputGraph> graphs; }
    static class InputGraph { int id; List<String> nodes; List<InputEdge> edges; }
    static class InputEdge { String from; String to; int weight; }

    static class OutputRoot { List<OutputItem> results = new ArrayList<>(); }
    static class OutputItem {
        @SerializedName("graph_id") int graphId;
        @SerializedName("input_stats") Map<String, Integer> inputStats;
        Section prim; Section kruskal;
        static class Section {
            @SerializedName("mst_edges") List<InputEdge> mstEdges;
            @SerializedName("total_cost") int totalCost;
            @SerializedName("operations_count") long operationsCount;
            @SerializedName("execution_time_ms") double executionTimeMs;
        }
    }

    public static void main(String[] args) throws Exception {
        Path in = Paths.get("ass_3_input.json");
        Path out = Paths.get("ass_3_output.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        InputFile input = gson.fromJson(Files.newBufferedReader(in), InputFile.class);

        OutputRoot root = new OutputRoot();

        for (InputGraph ig : input.graphs) {
            List<Edge> edges = new ArrayList<>();
            for (InputEdge e : ig.edges) edges.add(new Edge(e.from, e.to, e.weight));
            Graph g = new Graph(ig.id, ig.nodes, edges);

            PrimAlgorithm.Result pr = PrimAlgorithm.run(g);
            KruskalAlgorithm.Result kr = KruskalAlgorithm.run(g);

            OutputItem item = new OutputItem();
            item.graphId = g.getId();
            item.inputStats = new LinkedHashMap<>();
            item.inputStats.put("vertices", g.vertexCount());
            item.inputStats.put("edges", g.edgeCount());

            item.prim = new OutputItem.Section();
            item.prim.mstEdges = toIoEdges(pr.mstEdges);
            item.prim.totalCost = pr.totalCost;
            item.prim.operationsCount = pr.operations;
            item.prim.executionTimeMs = round2(pr.timeMs);

            item.kruskal = new OutputItem.Section();
            item.kruskal.mstEdges = toIoEdges(kr.mstEdges);
            item.kruskal.totalCost = kr.totalCost;
            item.kruskal.operationsCount = kr.operations;
            item.kruskal.executionTimeMs = round2(kr.timeMs);

            root.results.add(item);

            System.out.printf("Graph %d -> Prim cost=%d, Kruskal cost=%d%n",
                    g.getId(), pr.totalCost, kr.totalCost);
        }

        try (Writer w = Files.newBufferedWriter(out)) {
            gson.toJson(root, w);
        }
        System.out.println("Saved results to " + out.toAbsolutePath());
    }

    private static List<InputEdge> toIoEdges(List<Edge> list) {
        List<InputEdge> out = new ArrayList<>();
        for (Edge e : list) {
            InputEdge ie = new InputEdge();
            ie.from = e.getFrom();
            ie.to = e.getTo();
            ie.weight = e.getWeight();
            out.add(ie);
        }
        return out;
    }
    private static double round2(double x) { return Math.round(x * 100.0) / 100.0; }
}
