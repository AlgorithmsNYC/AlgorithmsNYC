/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.LinkedList;

/**
 *
 * @author Marcello
 */
public class BFS {
    
    public static void bfs(UndirectedGraph inputGraph, int src) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("Breadth-First Search:\n");
        sb.append("Graph=").append(inputGraph.getName()).append(", source=").append(src).append("\n");
                
        boolean[] done = new boolean[inputGraph.size()];
        Integer[] pred = new Integer[inputGraph.size()];
        int[] dist = new int[inputGraph.size()];

        LinkedList<Integer> queue = new LinkedList<Integer>();        
        queue.add(src);
        
        done[src] = true;
        pred[src] = null;
        dist[src] = 0;
        while(!queue.isEmpty()) {
            
            Integer node = queue.pollFirst();
            
            sb.append("node=").append(node).append(", pred=").append(pred[node]==null ? " " : pred[node]);
            sb.append(", dist/layer=").append(dist[node]).append("\n");
            
            for(int edge : inputGraph.getEdges(node)) {
                if(!done[edge]) {
                    queue.add(edge);
                    
                    done[edge] = true;
                    pred[edge] = node;
                    dist[edge] = dist[node] + 1;
                }
            }
            
        }
        
        System.out.println(sb);
    }
    
    public static void main(String[] args) {
        UndirectedGraph inputGraph = UndirectedGraph.getInstance();
        System.out.println("Input Graph:\n" + inputGraph);
        BFS.bfs(inputGraph, 0);
    }
    
}
