package courseProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Project {
      //      Alaa Abuzer and Mahmoud Najajreh
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdjacencyMapGraph<String, Double> graph = new AdjacencyMapGraph<>(false);
		try {
			File file = new File("project.txt");
			Scanner sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				String s1 = sc.next();
				String s2 = sc.next();
				String s3 = sc.next();
				Double num = Double.parseDouble(s3);
				Vertex<String> v1 = graph.getVertex(s1);
				if(v1==null)
					v1 =graph.insertVertex(s1);
				Vertex<String> v2 = graph.getVertex(s2);
				if(v2==null)
					v2=graph.insertVertex(s2);
				
				
				graph.insertEdge(v1, v2, num);
				
			}
			
		}catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 1st city");
		String city1 = input.nextLine();
		System.out.println("Enter 2ed city");
		String city2 = input.nextLine();
	    
		System.out.println("The fare is : "+ leastFare(graph, graph.getVertex(city1), graph.getVertex(city2)) );
		
	}
	public static Double leastFare(AdjacencyMapGraph<String, Double> g, Vertex<String> v1,Vertex<String> v2) {
		
		Map<Vertex<String>, Double> map = new HashMap<>();
		
		Set<Vertex<String>> known = new HashSet<Vertex<String>>();
		
		for (Vertex<String> v : g.vertices()) {
			if (v == v1) {
				map.put(v, 0.0);
			} else {
				map.put(v, Double.MAX_VALUE);
				known.add(v);
			}
		}
		for (Edge<Double> e : g.outgoingEdges(v1)) {
			map.put(g.opposite(v1, e), e.getElement());
		}
		
		Set<Vertex<String>> set = new HashSet<Vertex<String>>();
		int size = known.size();
		
		while (set.size() < size) {
			
			Double min = Double.MAX_VALUE;
			Vertex<String> y = null;
			for (Vertex<String> v : known) {
				Double x = map.get(v);
				
				if (x < min) {
					min = x;
					y = v;
				}
			}
			for (Edge<Double> e : g.outgoingEdges(y)) {
				
				Vertex<String> o = g.opposite(y, e);
				if (known.contains(o)) {
					Double s = map.get(y) + e.getElement();
					if (s < map.get(o))
						map.put(o, s);
				}
			}
			
			set.add(y);
			known.remove(y);
		}
		return map.get(v2);
		
	}
}