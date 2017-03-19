package HW11;


public class GraphDemo  {

	public static void main(String[] args) {
		//set to digraph
				boolean digraph = true;
				AdjGraph g = new AdjGraph(digraph);

				//build the graph
				String[] nodes = {"J1", "J2", "J3", "J4", "J5", "J6", "J7"};
				int weight = 1;
				g.setVertex(nodes);
				g.setEdge("J1", "J2", weight);
				g.setEdge("J1", "J3", weight);
				g.setEdge("J2", "J4", weight);
				g.setEdge("J3", "J4", weight);
				g.setEdge("J2", "J6", weight);
				g.setEdge("J2", "J5", weight);
				g.setEdge("J4", "J5", weight);
				g.setEdge("J5", "J7", weight);

				//graph traverse
				g.walk("BFS");
				g.walk("DFS");

				//g is an acyclic graph (so there is solution to topological sort)
				//now test your topological sort method
				g.topSort();

				//add an edge to the graph
				g.setEdge("J5", "J1", weight); 
				//the graph now contains a cycle (so there should be no solution)
				g.topSort();

	}

}
