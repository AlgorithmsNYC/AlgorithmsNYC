/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prim_gevorghari;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Marcello
 */
public class Prim {
    
    /**
     * A graph instance where to calculate the MST using Prim's algorithm
     */
    private SimpleWeightedGraph graph;

    /**
     * Key value of each Vertex in the graph
     * Infinite will be represented with Integer.MAX_VALUE = 2^31 + 1
     */
    private int[] keys;
    
    /**
     * For each Vertex it holds its predecessor
     * To represent null I used: '!'
     */
    private char[] predecessor;
    
    /**
     * The Priority Queue used by the algorithm
     * Since we're using a low level implementation of the graph this has been adapted and implemented with a HashSet
     */
    private HashSet<Character> Q;
    
    
    /**
     * Gets an instance of the graph
     * Initializes 'keys', 'predecessor', and 'Q'
     */
    public Prim() {
        graph = SimpleWeightedGraph.getGraphInstance();

        int size = graph.data.length;
        keys = new int[size];
        predecessor = new char[size];
        Q = new HashSet<Character>();
        
        /**
         * All Vertices are added to Q
         * Every Vertex will start having a key of infinite
         * No predecessors are set yet (all of them are null '!')
         */
        for(char c = 'a'; c < 'a' + size; c++) {
            Q.add(c);
            keys[chToIdx(c)] = Integer.MAX_VALUE;
            predecessor[chToIdx(c)] = '!';
        }
    }
    
    /**
     * Given a starting Vertex, it grows a MST from the graph
     * @param start The Vertex where we start generating Prim's MST
     */
    public void calculateMST(char start) {
        
        /*
         * The key of the starting Vertex is set to 0
         */
        keys[chToIdx(start)] = 0;

        /**
         * Till we have at least one Vertex in the queue Q
         */
        while(!Q.isEmpty()) {
            
            char node = extractMin(Q, keys);

            Set<Character> adjacent = graph.getAdj(node);
            for(Character adj : adjacent) {
                if(Q.contains(adj) && graph.getEdgeWeight(node, adj) < keys[chToIdx(adj)]) {
                    predecessor[chToIdx(adj)] = node;
                    keys[chToIdx(adj)] = graph.getEdgeWeight(node, adj);
                }
            }
        }
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        Prim prim = new Prim();
        
        prim.printGraph();
        
        prim.calculateMST('a');
        
        prim.printMST();
    
    }
    
    
    /**
     * Print the result of the algorithm
     */
    public void printMST() {
        
        StringBuilder sb = new StringBuilder("MST_PRIM={");
        for(int i = 0; i < keys.length; i++) {
            if(predecessor[i] != '!') {
                sb.append("[").append(predecessor[i]).append(",").append((char)(i + 'a')).append("]");
                sb.append("(").append(keys[i]).append(")").append(", ");
            }
        }
        sb = sb.replace(sb.length() - 2, sb.length(), "");
        sb.append("}");
        
        System.out.println(sb);
    }
    
    /**
     * Given an array index it returns the character representing a Vertex
     * @param idx Index of the array that determines the Vertex (EG 0 = 'a')
     * @return The char that is the Vertex name
     */
    private static char idxToCh(int idx) {
        return (char)(idx + 'a');
    }
    
    /**
     * Given a char representing a Vertex name it returns its position in an array (EG 3 = 'd')
     * @param ch The char representing the Vertex name
     * @return The array index position of the Vertex 
     */
    private static int chToIdx(char ch) {
        return (char)(ch - 'a');
    }

    /**
     * A simulation of a "poll" of a priority queue. It removes and returns the smallest element in Q
     * @param Q The Set containing Vertices (all of them at the very first iteration of the algorithm)
     * @param keys The value keys associated with each Vertex
     * @return The Vertex name with the smallest key value
     */
    private Character extractMin(HashSet<Character> Q, int[] keys) {
        Character result = null;
        int currentMin = Integer.MAX_VALUE;
        
        for(int index = 0; index < keys.length; index++) {
            if(keys[index] < currentMin && Q.contains(idxToCh(index))) {
                currentMin = keys[index];
                result = idxToCh(index);
            }
        }
        
        if(result != null)
            Q.remove(result);
        
        return result;
    }

    private void printGraph() {
        graph.print();
    }

}
