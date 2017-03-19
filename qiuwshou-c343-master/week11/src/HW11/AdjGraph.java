package HW11;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Iterator;

public class AdjGraph implements Graph {
	private boolean digraph;
	private int totalNode;
	private Vector<String> nodeList;
	private int totalEdge;
	private Vector<LinkedList<Integer>>  adjList; //Adjacency list
	private Vector<Boolean> visited;
	private Vector<Integer> nodeEnum; //list of nodes pre visit
	public AdjGraph() {
		init();
	}
	public AdjGraph(boolean ifdigraph) {
		init();
		digraph =ifdigraph;
	}
	public void init() {
		nodeList = new Vector<String>(); 
		adjList = new Vector<LinkedList<Integer>>(); 
		visited = new Vector<Boolean>();
		nodeEnum = new Vector<Integer>();
		totalNode = totalEdge = 0;
		digraph = false;
	}
	//set vertices
	public void setVertex(String[] nodes) {
		for(int i = 0; i < nodes.length; i ++) {
			nodeList.add(nodes[i]);
			adjList.add(new LinkedList<Integer>());
			visited.add(false);
			totalNode ++;
		}
	}
	//add a vertex
	public void addVertex(String label) {
		nodeList.add(label);
		adjList.add(new LinkedList<Integer>());
	}
	public int getNode(String node) {
		for(int i = 0; i < nodeList.size(); i ++) {
			if(nodeList.elementAt(i).equals(node)) return i;
		}
		return -1;
	}
	//return the number of vertices
	public int length() {
		return nodeList.size();
	}
	//add edge from v1 to v2
	public void setEdge(int v1, int v2, int weight) {
		LinkedList<Integer> tmp = adjList.elementAt(v1);
		if(adjList.elementAt(v1).contains(v2) == false) {
			tmp.add(v2);
			adjList.set(v1,  tmp);
			totalEdge ++;
		}
	}
	public void setEdge(String v1, String v2, int weight) {
		if((getNode(v1) != -1) && (getNode(v2) != -1)) {
			//add edge from v1 to v2
			setEdge(getNode(v1), getNode(v2), weight);
			//for digraph, add edge from v2 to v1 as well
			if(digraph == false) setEdge(getNode(v2), getNode(v1), weight);
		}
	}

	//it is important to keep track if a vertex is visited or not (for traversal)
	public void setVisited(int v) {
		visited.set(v, true);
		nodeEnum.add(v);
	}
	public boolean ifVisited(int v) {
		return visited.get(v);
	}
	public void clearWalk() {
		//clean up before traverse
		nodeEnum.clear();
		for(int i = 0; i < nodeList.size(); i ++) visited.set(i, false);
	}
	public void walk(String method) {
		clearWalk();
		//traverse the graph 
		for(int i = 0; i < nodeList.size(); i ++) {
			if(ifVisited(i) == false) {
				if(method.equals("BFS")) BFS(i); //i as the start node
				else if(method.equals("DFS")) DFS(i); //i as the start node
				else {
					System.out.println("unrecognized traversal order: " + method);
					System.exit(0);
				}
			}
		}
		System.out.println(method + ":");
		displayEnum();
	}
	public void DFS(int v) {
		setVisited(v);
		LinkedList<Integer> neighbors = adjList.elementAt(v);
		for(int i = 0; i < neighbors.size(); i ++) {
			int v1 = neighbors.get(i);
			if(ifVisited(v1) == false) DFS(v1); 
		}
	}
	public void BFS(int s) {
		ArrayList<Integer> toVisit = new ArrayList<Integer>();
		toVisit.add(s);
		while(toVisit.size() > 0) {
			int v = toVisit.remove(0); //first-in, first-visit
			setVisited(v);
			LinkedList<Integer> neighbors = adjList.elementAt(v);
			for(int i = 0; i < neighbors.size(); i ++) {
				int v1 = neighbors.get(i);
				if((ifVisited(v1) == false) && (toVisit.contains(v1) == false)) {
					toVisit.add(v1);
				}
			}
		}
	}
	public void display() {
		System.out.println("total nodes: " + totalNode);
		System.out.println("total edges: " + totalEdge);
	}
	public void displayEnum() {
		for(int i = 0; i < nodeEnum.size(); i ++) {
			System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
		}
		System.out.println();
	}
	//topological sort using queue -- to be implemented
	public void topSort() {
		Vector<String> L = new Vector<String>();//contains sorted vertices
		Vector<Integer> C = new Vector<Integer>(); //contains indegrees for each of the vertices
		for(int k = 0; k<nodeList.size(); k++){
		   C.add(0);
		}
		
		for(int i = 0; i<nodeList.size(); i++){ //use a double loop find the degree of each vertices
			for(int j =0; j<nodeList.size(); j++){
				if(adjList.elementAt(i).contains(j) !=false){//if v1 and v2 are connected, then increase the degree of v2 
					C.set(j,C.get(j)+1);
				}
			}
		}
		
		Queue<String> Q = new LinkedList<String>();//contains eligible vertices 
		Iterator<String> it= Q.iterator();
		for(int l = 0; l<C.size()-1; l++){ // find the starting node and push to Q
			if(C.get(l)==0){
				Q.add(nodeList.elementAt(l));
			}	
		}
		
		while(it.hasNext()){ //when Q is nonempty
			String u= it.next();
			L.add(u); //push u to L
			Q.remove();
			setVisited(getNode(u));
			LinkedList<Integer> nbhd = adjList.elementAt(getNode(u));
			for(int z = 0; z< nbhd.size(); z++){
				int v1= nbhd.get(z);
				if(ifVisited(v1)==false){
				String v = nodeList.elementAt(nbhd.get(z));
				Q.add(v);
				}
			}
		}
	
		if(L.size()==nodeList.size()){ 
			System.out.println(L);
		}
		else{
			System.out.println("Solution not found");
		}
	
		
		
		

	}

}
