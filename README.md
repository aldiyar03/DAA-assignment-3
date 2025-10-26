[Assignment3-REPORT-Academic.md](https://github.com/user-attachments/files/23146140/Assignment3-REPORT-Academic.md)
# Assignment 3 — Optimization of a City Transportation Network (Minimum Spanning Tree)

**Course:** Design and Analysis of Algorithms  
**Student:** Amangaliyev Aldiyar  
**Date:** 2025-10-26

This project implements Prim’s and Kruskal’s algorithms to build a Minimum Spanning Tree (MST) for a city transportation network modeled as a weighted undirected graph.  
The code reads graphs from `ass_3_input.json`, computes MSTs with both algorithms, measures operation count and execution time, and writes the structured summary to `ass_3_output.json`.

---

## Project Structure

```
src/main/java/
  ├─ Main.java
  ├─ Graph.java
  ├─ Edge.java
  ├─ Node.java
  ├─ PrimAlgorithm.java
  └─ KruskalAlgorithm.java
ass_3_input.json
ass_3_output.json
pom.xml
README.md
```

---

## Objective

The goal of this assignment is to find the Minimum Spanning Tree (MST) for the given transportation network and compare the efficiency of Prim’s and Kruskal’s algorithms.  
The MST connects all districts (vertices) with the minimum total construction cost.

---

## Input Summary (`ass_3_input.json`)

- Graph 1: 5 vertices, 7 edges  
- Graph 2: 4 vertices, 5 edges

Each edge weight represents the cost of constructing a road between two districts.

---

## Results (`ass_3_output.json`)

The program was executed on the provided input, and the following results were obtained.

### Graph 1 (V = 5, E = 7)

| Algorithm | MST Edges | Total Cost | Operations | Time (ms) |
|------------|------------|-------------|-------------|-----------|
| Prim | B–C(2), A–C(3), B–D(5), D–E(6) | 16 | 42 | 1.52 |
| Kruskal | B–C(2), A–C(3), B–D(5), D–E(6) | 16 | 37 | 1.28 |

### Graph 2 (V = 4, E = 5)

| Algorithm | MST Edges | Total Cost | Operations | Time (ms) |
|------------|------------|-------------|-------------|-----------|
| Prim | A–B(1), B–C(2), C–D(3) | 6 | 29 | 0.87 |
| Kruskal | A–B(1), B–C(2), C–D(3) | 6 | 31 | 0.92 |

For both graphs, the MST total cost is identical for Prim’s and Kruskal’s algorithms, as expected.  
Differences appear only in operation counts and execution times.

---

## Interpretation and Comparison

- **Correctness:** Both algorithms produce identical total costs for each graph, confirming correctness.  
- **Efficiency:**  
  - On Graph 1, Kruskal performs fewer operations and runs slightly faster.  
  - On Graph 2, the runtimes are nearly equal, with Prim performing fewer operations.  
- **Algorithmic Preference:**  
  - Prim’s algorithm is generally preferred for dense graphs (many edges).  
  - Kruskal’s algorithm is more efficient for sparse graphs due to its global edge-sorting and simple union-find structure.

---

## Conclusions

1. Both Prim’s and Kruskal’s algorithms successfully minimize the total cost of the MST.  
2. The difference in performance depends on graph density and data structure implementation.  
3. For dense graphs, Prim’s algorithm is more suitable.  
4. For sparse graphs, Kruskal’s algorithm provides better efficiency.  
5. The total MST cost must always be the same, even if the structure differs.

---

## How to Run

1. Open the project in IntelliJ IDEA as a Maven project.  
2. Ensure that `ass_3_input.json` is in the project root.  
3. Run `Main.java`.  
4. The program will generate `ass_3_output.json` with results.

Command-line option (if needed):

```
mvn -q -DskipTests=true package
java -cp target/*-SNAPSHOT.jar Main
```

---

## References

- Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2022). *Introduction to Algorithms* (4th ed.). MIT Press.  
- GeeksforGeeks. (n.d.). Difference between Prim’s and Kruskal’s Algorithm.
