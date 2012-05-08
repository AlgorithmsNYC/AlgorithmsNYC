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
public class SimpleWeightedGraph {
    
    /**
     * Adjacency Matrix
     */
    Integer[][] data;
    
    
    
    public SimpleWeightedGraph(int vNumber) {
        data = new Integer[vNumber][vNumber];
    }
    
    public void addEdge(char s1, char s2) {
        addEdge(s1, s2, 1);
    }
    
    public void addEdge(char s1, char s2, int weight) {
        data[ chToIdx(s1) ][ chToIdx(s2) ] = weight;
    }
    
    public int getEdgeWeight(char a, char b) {
        return data[chToIdx(a)][chToIdx(b)];
    }
    
    public Set<Character> getAdj(char node) {
        HashSet<Character> result = new HashSet<Character>();

        Integer[] adjiacent = data[chToIdx(node)];
        for(int i = 0; i < adjiacent.length; i++) {
            if(adjiacent[i] != null)
                result.add(idxToChar(i));
        }
        
        return result;
    }
    
    private static char idxToChar(int i) {
        return (char)(i + 'a');
    }
    
    private static int chToIdx(char c) {
        return (char)(c - 'a');
    }
    
    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("SimpleWeightedGraph\n");
        for(int i = 0; i < data.length; i++) {
            sb.append("V=").append(idxToChar(i)).append(" -> Adj={");
            for(int j = 0; j < data.length; j++) {
                if(data[i][j] != null)
                    sb.append("[").append(idxToChar(j)).append(",").append(data[i][j]).append("]");
            }
            sb.append("}\n");
        }
        System.out.println(sb);
    }
    
    public static SimpleWeightedGraph getGraphInstance() {
        SimpleWeightedGraph graph = new SimpleWeightedGraph(9);
        
        graph.data[chToIdx('a')][chToIdx('b')] = 4; graph.data[chToIdx('a')][chToIdx('h')] = 8;
        graph.data[chToIdx('b')][chToIdx('a')] = 4; graph.data[chToIdx('b')][chToIdx('c')] = 8;  graph.data[chToIdx('b')][chToIdx('h')] = 11;
        graph.data[chToIdx('c')][chToIdx('b')] = 8; graph.data[chToIdx('c')][chToIdx('d')] = 7;  graph.data[chToIdx('c')][chToIdx('f')] = 4;  graph.data[chToIdx('c')][chToIdx('i')] = 2;
        graph.data[chToIdx('d')][chToIdx('c')] = 7; graph.data[chToIdx('d')][chToIdx('e')] = 9;  graph.data[chToIdx('d')][chToIdx('f')] = 14;
        graph.data[chToIdx('e')][chToIdx('d')] = 9; graph.data[chToIdx('e')][chToIdx('f')] = 10;
        graph.data[chToIdx('f')][chToIdx('c')] = 4; graph.data[chToIdx('f')][chToIdx('d')] = 14; graph.data[chToIdx('f')][chToIdx('e')] = 10; graph.data[chToIdx('f')][chToIdx('g')] = 2;
        graph.data[chToIdx('g')][chToIdx('f')] = 2; graph.data[chToIdx('g')][chToIdx('h')] = 1;  graph.data[chToIdx('g')][chToIdx('i')] = 6;
        graph.data[chToIdx('h')][chToIdx('a')] = 8; graph.data[chToIdx('h')][chToIdx('b')] = 11; graph.data[chToIdx('h')][chToIdx('g')] = 1;  graph.data[chToIdx('h')][chToIdx('i')] = 7;
        graph.data[chToIdx('i')][chToIdx('c')] = 2; graph.data[chToIdx('i')][chToIdx('g')] = 6;  graph.data[chToIdx('i')][chToIdx('h')] = 7;
        
        return graph;
    }

}
