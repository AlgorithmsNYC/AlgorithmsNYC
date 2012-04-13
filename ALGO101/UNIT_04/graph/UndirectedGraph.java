/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Marcello
 */
public class UndirectedGraph {
    
    private String name;
    private HashMap<Integer, ArrayList<Integer>> data;
    
    public UndirectedGraph(String name) {
        this.name = name;
        data = new HashMap<Integer, ArrayList<Integer>>();
    }
    
    public void addNode(Integer node) {
        data.put(node, new ArrayList<Integer>());
    }
    
    public void addEdge(Integer src, Integer dest) {
        data.get(src).add(dest);
        data.get(dest).add(src);
    }
    
    public ArrayList<Integer> getEdges(int node) {
        return data.get(node);
    }
    
    public int size() {
        return data.size();
    }
    
    public static UndirectedGraph getInstance() {
        UndirectedGraph graph = new UndirectedGraph("CourseraBFS");
        
        for(int node = 0; node < 6; node++)
            graph.addNode(node);
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        
        return graph;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph: ").append(name).append(", size=").append(size()).append("\n");
        
        for(Entry<Integer, ArrayList<Integer>> entry : data.entrySet())
            sb.append("node=").append(entry.getKey()).append(", edges= ").append(entry.getValue()).append("\n");
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        UndirectedGraph graph = UndirectedGraph.getInstance();
        System.out.println(graph);
    }

    public String getName() {
        return name;
    }
    
}
