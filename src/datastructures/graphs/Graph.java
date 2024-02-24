package datastructures.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Graph {
    static class Edge{
        int source;
        int destination;
        public Edge(int source, int destination){
            this.source = source;
            this.destination = destination;
        }
    }
    public static void createGraph(ArrayList<Edge> graph[]){
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

    public static void bfs(ArrayList<Edge> graph[], boolean[] visited, Integer startingPoint){
        Queue<Integer> q  = new LinkedList<>();
        q.add(startingPoint);
        while(!q.isEmpty()){
            Integer curr = q.remove();
            if(visited[curr] == false){
                System.out.print(curr);
                visited[curr] = true;
                ArrayList<Edge> edges = graph[curr];
                for(Edge e: edges){
                    q.add(e.destination);
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge> graph[], boolean[] visited, int curr){
        System.out.print(curr);
        visited[curr] = true;
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!visited[e.destination]){
                dfs(graph, visited, e.destination);
            }
        }
    }

    public static void printAllPathsFromSourceToTarget(ArrayList<Edge> graph[], boolean[] visited, int target, int curr, String path){
        if(curr == target){
            System.out.println(path);
            return;
        }
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!visited[e.destination]){
                visited[curr] = true;
                path = path + " -> " + e.destination;
                printAllPathsFromSourceToTarget(graph, visited, target, e.destination, path);
                visited[curr] = false;
            }

        }

    }

    public static void createDirectedGraph(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();

        }
        graph[0].add(new Edge(0,2));
        graph[1].add(new Edge(1,0));
        graph[2].add(new Edge(2,3));
        graph[3].add(new Edge(3,0));
    }

    public static void hasCycle(ArrayList<Edge>[] directedGraph){
        int numVertices = directedGraph.length;
        boolean[] visited = new boolean[numVertices];
        for(int i = 0; i < visited.length; i++){
            if(!visited[i]){
               if(modifiedDfsHasCycle(directedGraph, i, visited, new boolean[numVertices])){
                   System.out.println("Directed Graph Has Cycle!");
                   return;
               }
            }
        }
        System.out.println("Directed Graph Does Not Have Cycle!");
    }

    public static boolean modifiedDfsHasCycle(ArrayList<Edge>[] directedGraph, int curr, boolean[] visited, boolean[] recursionStack){
        visited[curr] = true;
        recursionStack[curr] = true;
        for(int i = 0; i < directedGraph[curr].size(); i++){
            Edge e = directedGraph[curr].get(i);
            if(recursionStack[e.destination] == true){
                return true;
            }
            else if(!visited[e.destination]){
                return modifiedDfsHasCycle(directedGraph, e.destination, visited, recursionStack);
            }
        }
        recursionStack[curr] = false;
        return false;
    }


    public static void createDAG(ArrayList<Edge>[] dag){
        /* directed graph with no cycles */
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

    public static void topologicalSortDfs(ArrayList<Edge>[] dag, int curr, boolean[] visited, Stack<Integer> stack){
        visited[curr] = true;
        for(int i = 0 ; i < dag[curr].size();i++){
            Edge e = dag[curr].get(i);
            if(!visited[e.destination]){
                topologicalSortDfs(dag, e.destination, visited, stack);
            }
        }
        stack.push(curr);
    }
    public static void main(String[] args){
        int V = 7;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);
        boolean[] visited = new boolean[graph.length];
        for(int i = 0 ; i < visited.length; i++){
            if(!visited[i]){
                bfs(graph, visited, i);
            }
        }
        System.out.println();
        boolean[] newVisited = new boolean[graph.length];
        for(int i = 0; i < newVisited.length; i++){
            if(!newVisited[i]) {
                dfs(graph, newVisited, i);
            }
        }
        printAllPathsFromSourceToTarget(graph, new boolean[graph.length], 0, 5, "0");

        ArrayList<Edge>[] directedGraph = new ArrayList[4 ];
        createDirectedGraph(directedGraph);
        hasCycle(directedGraph);

        ArrayList<Edge>[] dag = new ArrayList[6];
        Stack<Integer> dagStack = new Stack<>();
        createDAG(dag);
        boolean visitedDag[] = new boolean[dag.length];
        for(int i = 0; i < visitedDag.length; i++){
            if(!visitedDag[i]){
                topologicalSortDfs(dag, i, visitedDag, dagStack);
            }
        }
        while(!dagStack.isEmpty()){
            Integer curr = dagStack.pop();
            System.out.print(curr + " ");
        }
        System.out.println();
    }
}
