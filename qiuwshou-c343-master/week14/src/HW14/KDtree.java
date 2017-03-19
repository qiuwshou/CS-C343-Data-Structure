package HW14;

public class KDtree<Key extends Comparable<?super Key>, E> {
	private BinNode<Key, E> root;
	private int totalNode;
	private BinNode<Key, E> curr;   //works with find()
	private String enumStr;         //for enumeration
	private int dim;   //dimension of the key
	private int level; //which level; important for insertion & search
	public KDtree(int d) {
		root = curr = null;
		totalNode = 0;
		dim = d;
		level = 0;
	
	}
	public BinNode<Key, E> find(Key[] k) {
		if(root == null) return null;
		else {
			return find(root, 0, k);
		}
	}
	public BinNode<Key, E> find(BinNode<Key, E> entry, int thislevel, Key[] k) {
		if(entry == null) return null;
		curr = entry;
		level = thislevel; //update level
		if(entry.getKey() == k) {
			return curr;
		}
		else {
			if(entry.isLeaf()) return null;
			Key[] key2 = entry.getKey();
			if (k[level % dim].compareTo(key2[level % dim]) >= 0) { //make sure the "right" key is used
				return find(entry.getRight(), thislevel + 1, k); //note thislevel + 1
			}
			else {
				return find(entry.getLeft(), thislevel + 1, k);
			}
		}
	}
	public void insert(Key[] k, E v) {
		BinNode<Key, E> node = new BinNode <Key, E>(k, v);
		insert(node);
		//insert(root, node);
	}
	public void insert(BinNode<Key, E> node) {
		find(node.getKey());
		if(curr == null) {
			root = node;
		}
		else {
			Key[] key1 = node.getKey();
			Key[] key2 = curr.getKey();
			if (key1[level % dim].compareTo(key2[level % dim]) >= 0) {
				if(curr.getRight() != null) node.setRight(curr.getRight());
				curr.setRight(node);
			}
			else {
				if(curr.getLeft() != null) node.setLeft(curr.getLeft());
				curr.setLeft(node);
			}
		}
		totalNode ++;
	}
	public void preorder() {
		enumStr = "";
		System.out.println("Total node = " + totalNode);
		if(root != null) preorder(root);
		System.out.println("Preorder enumeration: " + enumStr);
	}
	private void preorder(BinNode<Key, E> node) {	
		if(node != null) System.out.println("root " + node.toString());
		if(node.getLeft() != null) System.out.println("   left " + node.getLeft().toString());
		if(node.getRight() != null) System.out.println("   right " + node.getRight().toString());

		if(node != null) {
			enumStr += node.toString();
		}
 		if(node.getLeft() != null) preorder(node.getLeft());
		if(node.getRight() != null) preorder(node.getRight());
	}
	
	private double getDistance(BinNode<Key, E> node1, BinNode<Key, E> node2){ //calculate distance between two nodes
		Key[] key1 = node1.getKey();
		Key[] key2 = node2.getKey();
		int x1 = Integer.valueOf(key1[0].toString());
		int y1 = Integer.valueOf(key1[1].toString());
		int x2 = Integer.valueOf(key2[0].toString());
		int y2 = Integer.valueOf(key2[1].toString());
		double d = Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
		return d;
	}
	
	
	public void getNeighbors(Key[] key, int r) {
		BinNode<Key, E> node=find(key);
		while(root!=null){
			root=root.getRight();
			double d = getDistance(node,root);	
			if (d<r){System.out.println(root.toString()+" is the nbhd of "+node.toString());
			}
		}
		
		
		
		
	}
}

		
		