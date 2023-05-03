package courseProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MainLab12 {
	public static void main(String[] args) {
		AdjacencyMapGraph<String, String> graph = new AdjacencyMapGraph<>(true);
		
		Vertex<String>  SFO= graph.insertVertex("SFO");
		Vertex<String>  LAX= graph.insertVertex("LAX");
		Vertex<String>  ORD= graph.insertVertex("ORD");
		Vertex<String>  DFW= graph.insertVertex("DFW");
		Vertex<String>  BOS= graph.insertVertex("BOS");
		Vertex<String>  JFK= graph.insertVertex("JFK");
		Vertex<String>  MIA= graph.insertVertex("MIA");
		
		
		Edge<String> SW45= graph.insertEdge(JFK, SFO, "SW45");
		graph.insertEdge(BOS, JFK, "NW35");
		graph.insertEdge(LAX, ORD, "UA120");
		graph.insertEdge(ORD, DFW, "UA877");
		graph.insertEdge(DFW, ORD, "DL355");
		graph.insertEdge(DFW, LAX, "AA49");
		graph.insertEdge(JFK, DFW, "AA1387");
		graph.insertEdge(MIA, LAX, "AA411");
		graph.insertEdge(MIA, DFW, "AA523");
		graph.insertEdge(JFK, MIA, "AA903");
		graph.insertEdge(BOS, MIA, "DL247");
		
		Vertex<String>  vvvv= graph.insertVertex("MIA");

		Set<Vertex<String>> known = new HashSet<>();
		Map<Vertex<String>,Edge<String>> forest = new HashMap<>();
		DFS(graph,  BOS, known,  forest);
		System.out.println("1-----------------------------");
		System.out.println("if BOS & MIA in the same connected graph  "+ check(known, MIA));
		System.out.println("is -JFK, MIA- connected directly or indirectly " + isDirectly(graph,JFK, MIA));
		System.out.println("is -JFK, LAX- connected directly or indirectly " + isDirectly(graph,JFK, LAX));
		System.out.println("2-----------------------------");
		Set<Vertex<String>> known1b = new HashSet<>();
		StringBuilder st = new StringBuilder();
		System.out.println("The path from BOS to LAX  \nBOS "+printPath(graph,known1b,BOS, LAX,st));
		System.out.println("3-----------------------------");
		AdjacencyMapGraph<String, String> graph2 = new AdjacencyMapGraph<>(false);
		
		Set<Vertex<String>> known2 = new HashSet<>();

		Vertex<String>  v1= graph2.insertVertex("v1");
		Vertex<String>  v2= graph2.insertVertex("v2");
		Vertex<String>  v3= graph2.insertVertex("v3");
		Vertex<String>  v4= graph2.insertVertex("v4");
		Vertex<String>  v5= graph2.insertVertex("v5");
		graph.insertEdge(v1, v2, "123");
		graph.insertEdge(v3, v2, "345");
		System.out.println("Number of connected Components of graph2 is : "+connectedComponents(graph2));
		System.out.println("4-----------------------------");
		AdjacencyMapGraph<String, String> graph3 = new AdjacencyMapGraph<>(true);
		Set<Vertex<String>> known3 = new HashSet<>();
		Vertex<String>  g1= graph2.insertVertex("g1");
		Vertex<String>  g2= graph2.insertVertex("g2");
		
		graph.insertEdge(g1, g2, "123");
		System.out.println("Graph 3 is has a Cyclic "+isCyclic(graph3,g1,known3));
		System.out.println("Graph 2 is has a Cyclic "+isCyclic(graph2,v1,known2));
		Set<Vertex<String>> known1c = new HashSet<>();
		System.out.println("Graph 1 is has a Cyclic "+isCyclic(graph,ORD,known1c));
		

	}
	public static <V,E> boolean isCyclic(Graph<V,E> g, Vertex<V> u, Set<Vertex<V>> known) {
		      known.add(u); // u has been discovered
		      for (Edge<E> e : g.outgoingEdges(u)) { // for every outgoing edge from u
		          Vertex<V> v = g.opposite(u, e);
		          if(known.contains(v)) {
		        	  return true;
		          }
		          else if (!known.contains(v)&&isCyclic(g, v, known )) {
		        	  return true; // recursively explore from v
		            }
		          
		        }
			return false;
		    }
	
	public static int connectedComponents(Graph<String,String> g) {
		Set<Vertex<String>> known2 = new HashSet<>();
		Map<Vertex<String>,Edge<String>> forest = new HashMap<>();
		int counter=0;
		for (Vertex<String> u : g.vertices( )) {

			 if (!known2.contains(u)) {
				 counter++;
				 DFS(g, u, known2, forest);
			 }
		}
		return counter;
		
	}

	public static <V,E> void DFS(Graph<V,E> g, Vertex<V> u,
		      Set<Vertex<V>> known, Map<Vertex<V>,Edge<E>> forest) {
		      known.add(u); // u has been discovered
		      for (Edge<E> e : g.outgoingEdges(u)) { // for every outgoing edge from u
		          Vertex<V> v = g.opposite(u, e);
		          if (!known.contains(v)) {
		              forest.put(v, e); // e is the tree edge that discovered v
		              DFS(g, v, known, forest); // recursively explore from v
		            }
		        }
		    }
	public static <V> String printPath(Graph<String,String> g,Set<Vertex<String>> known,Vertex<String> vert, Vertex<String> wanted,StringBuilder st) {
		known.add(vert);
		
		for (Edge<String> e : g.outgoingEdges(vert)) {
			 Vertex<String> v =g.opposite(vert, e);
			 if(v==wanted &&!known.contains(v)) {
				 st.append("-> "+wanted.getElement()) ;
			 	return st.toString();
			}else if(v!=wanted &&!known.contains(v)) {
				st.append("-> "+v.getElement());
				return printPath(g,known,v,  wanted,st);
			}
		}
		return null;
		
	}
	public static <V> boolean isDirectly(Graph<String,String> g,Vertex<String> vert, Vertex<String> wanted) {
		
		for (Edge<String> e : g.outgoingEdges(vert)) {
			if(g.opposite(vert, e)==wanted) {
				return true;
			}
		}
		for (Edge<String> e : g.incomingEdges(vert)) {
			if(g.opposite(vert, e)==wanted) {
				return true;
				}
		}
		return false;
		
	}
	
	public static <V> boolean check(Set<Vertex<String>> list, Vertex<String> wanted) {
		if (list.contains(wanted))
			return true;
		else
			return false;
	}
//	public static <V, E> void DFS(Graph<Integer, Integer> g, Vertex<Integer> u, Vertex<Integer> wanted) {
//		list1.add(u);// u has been discovered
//		for (Edge<Integer> e : g.outgoingEdges(u)) { // for every outgoing edge from u
//			Vertex<Integer> v = g.opposite(u, e);
//			if (!list1.contains(v)) {
//				DFS(g, v, wanted); // recursively explore from v
//			}
//		}
//		
//		
//	}

}