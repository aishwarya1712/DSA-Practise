package datastructures.graphs2;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimMinimumSpanningTree {
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    static class Pair implements Comparable<Pair> {
        int node;
        int dist;

        public Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair p2){
            return this.dist - p2.dist;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph, int numVertices){
        /* Weighted, undirected */
        for(int i = 0; i < numVertices; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1,10));
        graph[0].add(new Edge(0,2,15));
        graph[0].add(new Edge(0,3,30));

        graph[1].add(new Edge(1,0,10));
        graph[1].add(new Edge(1,3,40));

        graph[2].add(new Edge(2,0,15));
        graph[2].add(new Edge(2,3,50));

        graph[3].add(new Edge(3,1,40));
        graph[3].add(new Edge(3,2,50));

    }


    public static void findCostOfMST(ArrayList<Edge>[] graph, int numVertices){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0));
        boolean[] visited = new boolean[numVertices];
        int cost = 0;
        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            int currNode = curr.node;
            if(!visited[currNode]){
                visited[currNode] = true;
                cost = cost + curr.dist;
                for(int i = 0; i < graph[currNode].size(); i++){
                    Edge neighborNode = graph[currNode].get(i);
                    if(!visited[neighborNode.dest]){
                        pq.add(new Pair(neighborNode.dest, neighborNode.wt));
                    }
                }
            }
        }
        System.out.println("Cost of MST is: " + cost);

    }

    public static void main(String[] args){
        int numVertices = 4;
        ArrayList<Edge>[] graph = new ArrayList[numVertices];
        createGraph(graph, numVertices);
        findCostOfMST(graph, numVertices);

    }
}
