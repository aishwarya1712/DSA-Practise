package datastructures.graphs;


import java.util.ArrayList;

/* works for weighted graphs with negative weights as well as cyclic graphs */
/* TC: O(V.E), more than Djikstra */
public class BellmanFord {
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

    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));
        graph[1].add(new Edge(1, 2, -4));
        graph[2].add(new Edge(2, 3, 2));
        graph[3].add(new Edge(3, 4, 4));
        graph[4].add(new Edge(4, 1, -1));
    }

    public static void bellmanFord(ArrayList<Edge> graph[], int src, int V){
        int[] distance = new int[V];
        for(int i =0; i < V; i++){
            if(i == src){
                distance[i] = 0;
            } else {
                distance[i] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < V - 1; i++){
            for(int j = 0; j < V; j++){
                for(int k = 0; k < graph[j].size(); k++){
                    Edge e = graph[j].get(k);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    if(distance[u] != Integer.MAX_VALUE && distance[u] + wt < distance[v]){
                        distance[v] = distance[u] + wt;
                    }
                }
            }
            for(int l = 0; l < distance.length; l++){
                System.out.print(distance[l] +" ");
            }
            System.out.println();
        }

    }
    public static void main(String[] args){
        int V = 5; // 5 vertices
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        bellmanFord(graph, 1, V);
    }

}
