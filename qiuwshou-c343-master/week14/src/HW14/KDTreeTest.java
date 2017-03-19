package HW14;

public class KDTreeTest {
	public static void main(String[] args) {

		KDtree <Integer, String> kdt = new KDtree<Integer, String>(2);
		Integer[] dataA = {40, 45};
		kdt.insert(dataA, "A");
		Integer[] dataB = {15, 70};
		kdt.insert(dataB, "B");
		Integer[] dataC = {70, 10};
		kdt.insert(dataC, "C");
		Integer[] dataD = {69, 50};
		kdt.insert(dataD, "D");
		Integer[] dataE = {66, 85};
		kdt.insert(dataE, "E");
		Integer[] dataF = {85, 95};
		kdt.insert(dataF, "F");

		kdt.preorder();

		Integer[] dataG = {85, 93};
		kdt.insert(dataG, "G");//insert G, close to F

		BinNode<Integer, String> node = kdt.find(dataG); //exact match
		if(node == null) {
			System.out.println("point not found");
		}
		else {
			System.out.println("point found: " + node.toString());
		}

		int r = 3;
		kdt.getNeighbors(dataG, r); //get close neighbors
		
	}
}