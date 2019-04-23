package test;

public class BinarySearchTree {

	public static void main(String[] args) {

		Node root = new Node(10); 

		//			 10
		//		   /     \
		//		  3      13
		//		 / \    /  \
		//		2	8  11  15

		root.insert(13);
		root.insert(3);
		root.insert(8);
		root.insert(15);
		root.insert(11);
		root.insert(2);
		root.insert(14);
		root.insert(16);


		System.out.println(root.tree());
		
		System.out.println(root.search(13));

		root.delete(root, 13);

		System.out.println(root.tree());

		System.out.println(root.search(13));
	}

}

class Node {
	Node leftKid;
	Node rightKid;
	int val;

	public Node (int v) { 
		this.val = v; 
	}

	public void insert(int value) {

		if (value < val) {
			if(leftKid == null)
				leftKid = new Node (value); 
			else { 
				leftKid.insert(value);
			}
		} else if (value > val) {
			if (rightKid == null)
				rightKid = new Node (value); 
			else { 
				rightKid.insert(value);
			}
		} 

	}

	public boolean search(int value) { 
		if (value == val) {
			return true; 
		} else if (rightKid == null && leftKid == null) { 
			return false;
		} else if (rightKid == null) {
			return leftKid.search(value);
		} else if (leftKid == null) {
			return rightKid.search(value);
		} else if (value > val) {
			System.out.println(rightKid);
			return rightKid.search(value); 
		} else {
			return leftKid.search(value);  
		}
	}

	public void delete(Node root, int key){ 
		root = recDelete(root, key); 
	} 

	private Node recDelete(Node root, int key){ 
		// if empty
		if (root == null)  return root; 

		// if key is greater than or less than root.key
		if (key < root.val) 
			root.leftKid = recDelete(root.leftKid, key); 
		else if (key > root.val) 
			root.rightKid = recDelete(root.rightKid, key); 

		// if key = root.key
		// delete THIS node
		else { 
			// node with 2 kids
			if (root.leftKid == null && root.rightKid == null)
				return null; 

			// node with 1 kid
			else if (root.leftKid == null) 
				return root.rightKid; 
			else if (root.rightKid == null) 
				return root.leftKid; 

			//if node has two kids  
			root.val = getMin(root.rightKid); 

			// Delete the in order successor 
			root.rightKid = recDelete(root.rightKid, root.val); 
		} 

		return root; 
	} 
	
	// used for delete function
	int getMin(Node root) { 
		int min = root.val; 
		while (root.leftKid != null) { 
			min = root.leftKid.val; 
			root = root.leftKid; 
		} 
		return min; 
	} 

	public String tree() {
		return tree(0);
	}

	public String tree(int x) {
		char ch1, ch2;

		if (x % 3 == 0) {
			ch1 = '(';
			ch2 = ')';
		} else if (x % 3 == 1) {
			ch1 = '[';
			ch2 = ']';
		} else {
			ch1 = '{';
			ch2 = '}';
		}
		if (leftKid == null && rightKid == null) {
			return "" + val;
		} else {
			if (rightKid == null)
				return (val + ":" + ch1 + leftKid.tree(x + 1) + ", " + rightKid + ch2);
			else if (leftKid == null)
				return (val + ":" + ch1 + leftKid + ", " + rightKid.tree(x + 1) + ch2);
			else
				return (val + ":" + ch1 + leftKid.tree(x + 1) + ", " + rightKid.tree(x + 1) + ch2);
		}
	}

	@Override
	public String toString() {
		String left, right;
		if (leftKid != null) {
			left = "" + leftKid.val;
		} else {
			left = null;
		}
		if (rightKid != null) {
			right = "" + rightKid.val;
		} else {
			right = null;
		}
		return ("val: " + val + ", left: " + left + ", right: " + right);

	}

}
