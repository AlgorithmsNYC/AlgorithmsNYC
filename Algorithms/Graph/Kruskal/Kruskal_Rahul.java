import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Kruskal_Rahul {

	public static void main(String[] args) {
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");
		Node f = new Node("f");
		Node g = new Node("g");
		Node h = new Node("h");
		Node i = new Node("i");
		
		Edge[] edges = {
				new Edge(new Node[]{a,b},4),
				new Edge(new Node[]{b,c},8),
				new Edge(new Node[]{a,h},8),
				new Edge(new Node[]{h,g},1),
				new Edge(new Node[]{g,f},2),
				new Edge(new Node[]{f,e},10),
				new Edge(new Node[]{f,d},14),
				new Edge(new Node[]{f,c},4),
				new Edge(new Node[]{e,d},9),
				new Edge(new Node[]{h,i},7),
				new Edge(new Node[]{i,g},6), 
				new Edge(new Node[]{c,d},7),
				new Edge(new Node[]{c,i},2),
				new Edge(new Node[]{h,b},11),
				}; 
		
		List<Edge> sortedEdges = Arrays.asList(edges);
		Collections.sort(sortedEdges);
		
		Node[] nodes = {a,b,c,d,e,f,g,h,i};
		List<Set<Node>> sets = new ArrayList<Set<Node>>();
		for (Node node : nodes) {
			Set<Node> set = new HashSet<Node>();
			set.add(node);
			sets.add(set);
		}
			
		List<Edge> mst = new ArrayList<Edge>();
		
		for (Edge edge : sortedEdges) {
			Set<Node> s1 = getSet(sets, edge.nodes[0]);
 			Set<Node> s2 = getSet(sets, edge.nodes[1]);
 			if(!s1.equals(s2)) {
 				mst.add(edge);
 				sets.remove(s2);
 				sets.remove(s1);
 				Set<Node> set = new HashSet<Node>();
 				set.addAll(s2);
 				set.addAll(s1);
 				sets.add(set);
 			} 
 		}
		
		for (Edge edge : mst) {
			System.out.println(edge);
		}
	}
	
	
	private static Set<Node> getSet(List<Set<Node>> sets, Node node) {
		for (Set<Node> set : sets) {
			if(set.contains(node))
				return set;
		}
		// this should not happen
		return null;
	}


	public static class Node {
		public Node(String name) {
			super();
			this.name = name;
		}
		String name;
		@Override
		public String toString() {
			return  name ;
		}
	}
	
	public static class Edge implements Comparable<Edge>{
		public Edge(Node[] nodes, int weight) {
			super();
			this.nodes = nodes;
			this.weight = weight;
		}
		Node[] nodes;
		int weight;
		
		
		@Override
		public int compareTo(Edge arg0) {
			return this.weight - arg0.weight;
		}


		@Override
		public String toString() {
			return weight + "," + nodes[0] + "," + nodes[1];
		}
		
	}
}

