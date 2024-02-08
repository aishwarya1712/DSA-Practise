package datastructures.graphs;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class WeightedGraph {
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int src, int dest, int wt){
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));
        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));
        graph[2].add(new Edge(2, 4, 3));
        graph[3].add(new Edge(3, 5, 1));
        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    static class Pair implements Comparable<Pair>{
        // we have implements Comparable because we want a way to tell PQ which basis to sort Pair on.
        int node; // node
        int dist; // distance of node from source

        public Pair(int node, int dist){
            this.node = node;
            this.dist = dist;
        }

        // whenever we implement Comparable, we need to override the compareTo function
        @Override
        public int compareTo(Pair p2) {
            // comparison should be on the basis of distance
            return this.dist - p2.dist; // ascending order
            // return p2.dist - this.dist -> for descending order (but we don't need it here)
        }
    }

    // TC: O(E + ElogV)
    public static void dijkstra(ArrayList<Edge> graph[], int src, int V){
        PriorityQueue<Pair> pq = new PriorityQueue<>(); // since Pair implements Comparable, PQ knows how to prioritize it
        pq.add(new Pair(src, 0));

        int[] distances = new int[V]; // initialize distances matrix
        for(int i = 0; i < distances.length; i++){
            if(i == src){
                distances[i] = 0;
            } else {
                distances[i] = Integer.MAX_VALUE;
            }
        }

        boolean[] visited = new boolean[V];
        while(!pq.isEmpty()){
            Pair curr = pq.remove(); // this pops out the node which has the current shortest node, i.e, node with least distance from source
            if(!visited[curr.node]) {
                visited[curr.node] = true;
                for (int i = 0; i < graph[curr.node].size(); i++) {
                    Edge e = graph[curr.node].get(i);
                    int u = e.src; // or curr.node
                    int v = e.dest;
                    int wt = e.wt;
                    if (distances[u] + wt < distances[v]) {
                        distances[v] = distances[u] + wt;
                        pq.add(new Pair(v, distances[v]));
                    }
                }
            }
        }
        for(int i = 0; i < V; i++){
            System.out.print(distances[i] +" ");
        }
        System.out.println();
    }


    public static void main(String args[]) {
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        dijkstra(graph, 0, V);

    }
}
