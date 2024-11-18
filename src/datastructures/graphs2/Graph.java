package datastructures.graphs2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    static class Edge {
        int src;
        int dest;
        public Edge(int src, int dest){
            this.src = src;
            this.dest = dest;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));
        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,3));
        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,4));
        graph[3].add(new Edge(3,1));
        graph[3].add(new Edge(3,4));
        graph[3].add(new Edge(3,5));
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));
        graph[5].add(new Edge(5, 6));
    }

    public static void bfs(ArrayList<Edge>[] graph, boolean[] visited, int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int curr = queue.remove();

            if(!visited[curr]) {
                System.out.print("curr: " + curr + " ");
                visited[curr] = true;
                // find curr's neighbors and add to queue
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
//                    if (!visited[e.dest]) {
                        queue.add(e.dest);
//                    }
                }
            }
        }
        System.out.println();
    }

    public static void dfs(ArrayList<Edge>[] graph, boolean[] visited, int start){
        if(visited[start]){
            return;
        }
        visited[start] = true;
        System.out.print(start + " -> ");
        for(int i = 0; i < graph[start].size(); i++){
            Edge e = graph[start].get(i);
            dfs(graph, visited, e.dest);
        }
    }

    public static void printAllPathsFromSourceToTarget(ArrayList<Edge>[] graph, String path, boolean[] visited, int curr, int target){
        if(curr == target){
            System.out.println(path);
            return;
        }
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!visited[e.dest]){
                visited[curr] = true;
                path += Integer.toString(e.dest);
                printAllPathsFromSourceToTarget(graph, path, visited, e.dest, target);
                visited[curr] = false;
            }
        }
    }

    public static void main(String[] args){
        /* Create Graph */
        int numVertices = 7;
        ArrayList<Edge>[] graph = new ArrayList[numVertices];
        createGraph(graph);

        /* BFS */
        boolean[] visited = new boolean[numVertices];
        for(int i = 0; i < numVertices; i++){
            if(!visited[i]){
                bfs(graph, visited, i);
            }
        }

        /* DFS */
        visited = new boolean[numVertices];
        for(int i = 0; i < numVertices; i++){
            if(!visited[i]) {
                dfs(graph, visited, i);
            }
        }
        System.out.println();

        /* Print paths from source to target */
        visited = new boolean[numVertices];
        printAllPathsFromSourceToTarget(graph, "0", visited, 0, 5);
    }
}
