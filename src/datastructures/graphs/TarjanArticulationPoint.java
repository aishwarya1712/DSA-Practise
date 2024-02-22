package datastructures.graphs;

import java.util.ArrayList;

public class TarjanArticulationPoint {
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));
        graph[0].add(new Edge(0,3));

        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,2));

        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,1));

        graph[3].add(new Edge(3,0));
        graph[3].add(new Edge(3,4));
        graph[3].add(new Edge(3,5));

        graph[4].add(new Edge(4,3));
        graph[4].add(new Edge(4,5));

        graph[5].add(new Edge(5,3));
        graph[5].add(new Edge(5,4));
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, int parent, int[] dt, int[] low, int time, boolean[] visited, boolean[] isArticulationPoint){
        visited[curr] = true;
        dt[curr] = low[curr] = ++time;
        int children = 0;
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            int neighbor = e.dest;
            if(neighbor == parent){
                continue;
            }
            else if(visited[neighbor]){
                /* has been visited by another node -> back-edge condition */
                low[curr] = Math.min(low[curr], dt[neighbor]);
            } else {
                dfs(graph, neighbor, curr, dt, low, time, visited, isArticulationPoint);
                low[curr] = Math.min(low[curr], low[neighbor]);
                if(dt[curr] <= low[neighbor] && parent != -1){
                    isArticulationPoint[curr] = true;
                    children++;
                }
            }
        }
        if(parent == -1 && children > 1){
            isArticulationPoint[curr] = true;
        }
    }
    public static void getArticulationPoint(ArrayList<Edge>[] graph, int V){
        int dt[] = new int [V];
        int low[] = new int [V];
        int time = 0;
        boolean[] visited = new boolean[V];
        boolean[] isArticulationPoint = new boolean[V];

        for(int i = 0; i < V; i++){
            if(!visited[i]){
                dfs(graph, i, -1, dt, low, time, visited, isArticulationPoint);
            }
        }

        for(int i = 0; i < V; i++){
            if(isArticulationPoint[i]){
                System.out.println("Found articulation point: " + i);
            }
        }
    }

    public static void main(String[] args){
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        getArticulationPoint(graph, V);
    }
}
