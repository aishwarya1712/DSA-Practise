package datastructures.graphs2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    /* Greedy algorithm */
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

    static class Pair implements Comparable<Pair>{
        int node;
        int dist;

        public Pair(int node, int dist){
            this.node = node;
            this.dist = dist;
        }
        @Override
        public int compareTo(Pair p2) {
            return this.dist - p2.dist;
        }
    }

    public static void createWeightedGraph(ArrayList<Edge>[] graph, int numVertices){
        for(int i = 0; i < numVertices; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 2, 1));
        graph[1].add(new Edge(1, 3, 7));

        graph[2].add(new Edge(2, 4, 3));

        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    public static int[] dijkstraShortestPathAlgorithm(ArrayList<Edge>[] graph, int src, int V){
        /* Modified BFS + Relaxation */
        /* TC: O(E + ElogV) */
        boolean[] visited = new boolean[V];

        /* Initialize distances matrix */
        int[] distances = new int[V];
        for(int i = 0; i < V; i++){
            if(i == src){
                distances[i] = 0;
            } else {
                distances[i] = Integer.MAX_VALUE;
            }
        }

        /* Create and initialize priority queue */
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!visited[curr.node]){
                visited[curr.node] = true;
                for(int i = 0; i < graph[curr.node].size(); i++){
                    Edge e = graph[curr.node].get(i);
                    /* Perform relaxation */
                    int u = distances[e.src];
                    int v = distances[e.dest];
                    if(u + e.wt < v){
                        distances[e.dest] = u + e.wt;
                        pq.add(new Pair(e.dest, distances[e.dest]));
                    }

                }
            }
        }
        System.out.println(Arrays.toString(distances));
        return distances;
    }
    public static void main(String[] args){
        int numVertices = 6;
        ArrayList<Edge>[] graph = new ArrayList[numVertices];
        createWeightedGraph(graph, numVertices);
        dijkstraShortestPathAlgorithm(graph, 0, numVertices);
    }
}
