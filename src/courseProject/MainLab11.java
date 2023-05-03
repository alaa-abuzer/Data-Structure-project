package courseProject;

import java.util.ArrayList;
import java.util.List;
public class MainLab11 {




		public static void main(String[] args) {

			AdjacencyMapGraph1<String, String> graph = new AdjacencyMapGraph1<>(true);
			
			
			//Vertex<Airport>  SFO= graph.insertVertex(new Airport("SFO","San Francisco"));
			
			
			
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
			
			System.out.println("-------------print graph----------------");
			System.out.println(graph);
			System.out.println("----------------------------------------");
	        System.out.println("number of vertices:"+graph.numVertices());
	        System.out.println("----------------------------------------");
	        System.out.println("number of edges:"+graph.numEdges());
	        System.out.println("----------------------------------------");
			System.out.println("in Degreeof ORD:"+graph.inDegree(ORD));
			System.out.println("----------------------------------------");
			System.out.println("out Degreeof ORD:"+graph.outDegree(ORD));
			System.out.println("----------------------------------------");
			
			
			System.out.println("vertices:");
			
			Iterable<Vertex<String>>  vertices =graph.vertices();
			
			for (Vertex<String> vertex : vertices) {
				System.out.print(vertex.getElement()+" ,");
			}
			
			System.out.println();
			
			
			System.out.println("----------------------------------------");
			System.out.println("edges:");
			
			
			Iterable<Edge<String>>  edges =graph.edges();
			
			for (Edge<String> edge : edges) {
				System.out.print(edge.getElement()+" ,");
			}
			
			
			System.out.println();
			
		
			System.out.println("----------------------------------------");
			System.out.println("edge of JFK and MIA is: "+graph.getEdge(JFK, MIA).getElement());
	        
			
			System.out.println("----------------------------------------");
			Vertex<String>[] ver= graph.endVertices(SW45);
			System.out.println("vertices of SW45 :" + ver[0].getElement()+ "," + ver[1].getElement());
			
			
			
			System.out.println("----------------------------------------");
			System.out.println(("vertex of SW45 and MIA is :" +graph.opposite(JFK, SW45).getElement()));
			
			
			
			System.out.println();
			System.out.println("---------------removeEdge(SW45)-----------------");
			graph.removeEdge(SW45);
			System.out.println(graph);
			
			
			System.out.println();
			System.out.println("---------------removeVertex(SFO);-----------------");
			graph.removeVertex(SFO);
			System.out.println(graph);
			
			
//					System.out.println();
//					System.out.println("---------------removeVertex(MIA);-----------------");
//					graph.removeVertex(MIA);
//					System.out.println(graph);
			
			
		}

	
}
