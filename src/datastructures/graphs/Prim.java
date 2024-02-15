package datastructures.graphs;

import java.util.ArrayList;
import java.util.PriorityQueue;

/* Prim's algorithm to find Minimum Spanning Tree (MST)
TC: O(E logE)
 */
public class Prim {
    private static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    private static class Pair implements Comparable<Pair> {
        int parent;
        int node;
        int cost;

        public Pair(int p, int n, int c){
            this.parent = p;
            this.node = n;
            this.cost = c;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost;
        }
    }

    public static void createWeightedUndirectedGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));

        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));

        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));

        graph[2].add(new Edge(3, 0, 30));
        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));
    }

    public static void primsAlgorithm(ArrayList<Edge>[] graph, int V){
        PriorityQueue<Pair> pq = new PriorityQueue<>(); // non-MST set
        pq.add(new Pair(-1, 0,0));
        boolean[] visited = new boolean[V]; // MST set
        int totalCost = 0;
        ArrayList<Edge> mstEdges = new ArrayList<>();
        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!visited[curr.node]){
                visited[curr.node] = true;
                totalCost += curr.cost;
                mstEdges.add(new Edge(curr.parent, curr.node, curr.cost));
                for(int i = 0; i < graph[curr.node].size(); i++){
                    Edge e = graph[curr.node].get(i);
                    if(!visited[e.dest]){
                        pq.add(new Pair(curr.node,  e.dest, e.wt));
                    }
                }
            }
        }
        System.out.println("Final total cost is: " + totalCost);
        for(Edge e: mstEdges){
            System.out.print(e.src +" -> " + e.dest + " wt: " + e.wt);
            System.out.println();
        }

    }

    public static void main(String[] args){
        int V = 4;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createWeightedUndirectedGraph(graph);
        primsAlgorithm(graph, V);
    }
}