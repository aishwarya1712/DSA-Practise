package datastructures.graphs2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    public static void createUndirectedCyclicGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,4));

        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,2));
        graph[1].add(new Edge(1,4));

        graph[2].add(new Edge(2,1));
        graph[2].add(new Edge(2,3));

        graph[3].add(new Edge(3,2));

        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 4));
    }

    public static void createDirectedCyclicGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,2));

        graph[1].add(new Edge(1,0));

        graph[2].add(new Edge(2,3));

        graph[3].add(new Edge(3,0));
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

    public static boolean undirectedGraphHasCycle(ArrayList<Edge>[] graph, int curr, int parent, boolean[] visited){
        /* Modified DFS approach */
        visited[curr] = true;
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(visited[e.dest] && e.dest != parent){
                return true;
            }
            if(!visited[e.dest]){
                if(undirectedGraphHasCycle(graph, e.dest, curr, visited)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean directedGraphHasCycle(ArrayList<Edge>[] graph, boolean[] visited, boolean[] recStack, int curr){
        visited[curr] = true;
        recStack[curr] = true;
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(recStack[e.dest]){
                return true;
            }
            if(!visited[e.dest]){
                if(directedGraphHasCycle(graph, visited, recStack, e.dest)){
                    return true;
                }
            }

        }
        recStack[curr] = false;
        return false;
    }

    public static void createDAG(ArrayList<Edge>[] dag){
        /* DAG = directed graph with no cycles */
        for(int i = 0; i < dag.length; i++){
            dag[i] = new ArrayList<>();
        }
        dag[2].add(new Edge(2, 3));
        dag[3].add(new Edge(3, 1));
        dag[4].add(new Edge(4 ,0));
        dag[4].add(new Edge(4, 1));
        dag[5].add(new Edge(5 ,0));
        dag[5].add(new Edge(5 ,2));
    }

    private static void topologicalSortHelper(ArrayList<Edge>[] graph, boolean[] visited, int curr, Stack<Integer> stack){
        visited[curr] = true;
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!visited[e.dest]){
                topologicalSortHelper(graph, visited, e.dest, stack);
            }
        }
        stack.push(curr);
    }
    
    public static void topologicalSort(ArrayList<Edge>[] graph, int numVertices){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[numVertices];
        for(int i = 0; i < numVertices; i++){
            if(!visited[i]){
                topologicalSortHelper(graph, visited, i, stack);
            }
        }
        while(!stack.isEmpty()){
            Integer element = stack.pop();
            System.out.print(element +" -> ");
        }
        System.out.println();


    }
    public static void main(String[] args){
        /* Create Graph */
//        int numVertices = 7;
//        ArrayList<Edge>[] graph = new ArrayList[numVertices];
//        createGraph(graph);
//
//        /* BFS */
//        boolean[] visited = new boolean[numVertices];
//        for(int i = 0; i < numVertices; i++){
//            if(!visited[i]){
//                bfs(graph, visited, i);
//            }
//        }
//
//        /* DFS */
//        visited = new boolean[numVertices];
//        for(int i = 0; i < numVertices; i++){
//            if(!visited[i]) {
//                dfs(graph, visited, i);
//            }
//        }
//        System.out.println();
//
//        /* Print paths from source to target */
//        visited = new boolean[numVertices];
//        printAllPathsFromSourceToTarget(graph, "0", visited, 0, 5);

//        /* Undirected graph has cycle */
//        ArrayList<Edge>[] undirectedCyclicGraph = new ArrayList[6];
//        createUndirectedCyclicGraph(undirectedCyclicGraph);
//        System.out.println(undirectedGraphHasCycle(undirectedCyclicGraph, 0, -1, new boolean[6]));

        /* Directed graph has cycle */
//        int numVertices = 4;
//        ArrayList<Edge>[] directedCyclicGraph = new ArrayList[numVertices];
//        createDirectedCyclicGraph(directedCyclicGraph);
//        boolean[] visited = new boolean[numVertices];
//        for(int i = 0; i < numVertices; i++){
//            if(!visited[i]){
//                boolean hasCycle = directedGraphHasCycle(directedCyclicGraph, visited, new boolean[4], i);
//                if(hasCycle){
//                    System.out.println("Has cycle: "+ hasCycle);
//                    return;
//                }
//
//            }
//        }

        /* DAG - topological sort */
        int numVertices = 6;
        ArrayList<Edge>[] dag = new ArrayList[numVertices];
        createDAG(dag);
        topologicalSort(dag, numVertices);

    }
}
