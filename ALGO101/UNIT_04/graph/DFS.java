/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;


/**
 *
 * @author MarcelloPro
 */
public class DFS {
    
    private static int time;
    
    public static void dfs(UndirectedGraph graph) {
        time = -1;
        
        System.out.println(graph);
        
        StringBuilder sb = new StringBuilder();
        sb.append("Depth-First Search:\n");
        sb.append("Graph=").append(graph.getName()).append("\n");
        
        boolean[] done = new boolean[graph.size()];
        Integer[] pred = new Integer[graph.size()];
        int[][] timing = new int[graph.size()][2];
                
        for(Integer node : graph.getNodes()) {
            if(!done[node]) {
                sb.append("node=").append(node).append(", ").append("pred= \n");
                dfs(graph, node, done, pred, sb, timing);
            }
        }
  
        sb.append("\nTiming:\n");
        for(int i = 0; i < timing.length; i++) {
            sb.append("Node").append(i).append(", Start=").append(timing[i][0]).append(", End=").append(timing[i][1]).append("\n");
        }
        
        System.out.println(sb);

    }
    
    private static void dfs(UndirectedGraph graph, int node, boolean[] done, Integer[] pred, StringBuilder sb, int[][] timing) {
        done[node] = true;
        time++;
        timing[node][0] = time;
        
        for(int adj : graph.getEdges(node)) {
            if(!done[adj]) {
                pred[adj] = node;
                sb.append("node=").append(adj).append(", ").append("pred=").append(node).append("\n");
                dfs(graph, adj, done, pred, sb, timing);                 
            }
        }
        time++;
        timing[node][1] = time;
        
    }
    
    
    public static void main(String[] args) {
        dfs(UndirectedGraph.getInstance());
    }
}
