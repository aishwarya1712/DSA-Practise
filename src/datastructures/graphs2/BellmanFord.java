package datastructures.graphs2;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {
    /* Works where Dijkstra's algorithm fails: ie for graphs with negative weights */
    /* However, Bellman Ford Algorithm doesn't work for negative weight cycles */
    /* Uses dynamic programming */
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

    public static void createGraph(ArrayList<Edge>[] graph, int numVertices){
        for(int i = 0; i < numVertices; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1,2));
        graph[0].add(new Edge(0,2,4));

        graph[1].add(new Edge(1,2,-4));

        graph[2].add(new Edge(2,3,2));

        graph[3].add(new Edge(3,4,4));

        graph[4].add(new Edge(4,1,-1));
    }

    public static void shortestPathBellmanFord(ArrayList<Edge>[] graph, int numVertices, int src){
        int[] distances = new int[numVertices];
        for(int i = 0; i < distances.length; i++){
            if(i == src){
                distances[i] = 0;
            } else {
                distances[i] = Integer.MAX_VALUE;
            }
        }
        // go to each edge and apply relaxation
        // repeat this V-1 times
        for(int k = 0; k < numVertices - 1; k++){
            for(int i = 0; i < numVertices; i++){
                for(int j = 0; j < graph[i].size(); j++){
                    Edge e = graph[i].get(j);
                    int u = distances[e.src];
                    int v = distances[e.dest];
                    if(u + e.wt < v){
                        distances[e.dest] = u + e.wt;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(distances));
        /* If after running the BFA one more time after K = V-1 times the distance array changes, that's when you know there's a negative weight cycle.
        In that case, the shortest path cannot be found.
         */
    }
    public static void main(String[] args){
        int numVertices = 5;
        ArrayList<Edge>[] graph = new ArrayList[numVertices];
        createGraph(graph, numVertices);
        shortestPathBellmanFord(graph, numVertices,0);
    }
}
