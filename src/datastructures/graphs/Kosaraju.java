package datastructures.graphs;

import java.util.ArrayList;
import java.util.Stack;

// TC: O(V+E)
// applicable for directed graphs only
// algorithm to find strongly connected components: SCC
public class Kosaraju {
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));

        graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 4));

    }

    public static void topSort(ArrayList<Edge>[] graph, int curr, Stack<Integer> s, boolean[] visited){
        visited[curr] = true;
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!visited[e.dest]){
                topSort(graph, e.dest, s, visited);
            }
        }
        s.push(curr);
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] visited){
        visited[curr] = true;
        System.out.print(curr + " ");
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!visited[e.dest]){
                dfs(graph, e.dest, visited);
            }
        }
    }
    public static void kosarajuAlgo(ArrayList<Edge>[] graph, int V){
        // Step 1: Create top sort stack
        Stack<Integer> s = new Stack<>();
        boolean[] visited = new boolean[V];
        for(int i = 0; i < visited.length; i++){
            if(!visited[i]){
                topSort(graph, i, s, visited);
            }

        }

        // Step 2: Create a transpose of the graph so we can traverse it in the reverse order
        ArrayList<Edge> transpose[] = new ArrayList[V];
        for(int i = 0; i < transpose.length; i++){
            transpose[i] = new ArrayList<Edge>();
        }
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph[i].size(); j++){
                Edge e = graph[i].get(j);
                transpose[e.dest].add(new Edge(e.dest, e.src));
            }
        }

        boolean[] newVisited = new boolean[V]; // can create a new visited array or repurpose the old one

        // Step 3: Pop elements from the Stack one-by-one and perform DFS on the transpose graph
        int sccCount = 0;
        while(!s.isEmpty()){
            int curr = s.pop();

            if(!newVisited[curr]){
                System.out.print("SCC #" + ++sccCount + ": ");
                dfs(transpose, curr, newVisited);
                System.out.println();
            }

        }


    }

    public static void main(String[] args){
        int V = 5;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);
        kosarajuAlgo(graph, V);


    }
}
