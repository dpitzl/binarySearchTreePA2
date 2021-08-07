import java.util.Scanner;


/***************************************************
 * Program Title: BinarySearchUI *
 * Author: Andrew Pitzl *
 * Class: CSCI3320, Summer 2021 *
 * Assignment #2 *
 ****************************************************/
public class BinarySearchTreeUI {
	
	private static BinarySearchTree<Integer> binarySearchTree;
	
	
	/***************************************************
	* FUNCTION main : 
	* Serves as UI for the BinarySearchTree methods created.
	* OUTPUT : 
	* void
	 ****************************************************/ 

	public static void main(String[] args) {
		
		int input = 0;
		
		Scanner kbd = new Scanner(System.in);
		
		while (input != 8) {
			System.out.println("Enter choice [1-8] from menu below: "
					+ "\n1)Construct a tree  "
					+ "\n2)Print tree in a descending order  "
					+ "\n3)Print number of leaves in tree "
					+ "\n4)Print the number of nodes in T that contain only one child "
					+ "\n5)Print the number of nodes in T that contain only two children "
					+ "\n6)Print the level order traversal of the tree "
					+ "\n7)Print all elements in the tree between k1 and k2 "
					+ "\n8)Exit program ");
			
			input = kbd.nextInt();
			
			switch (input) {
			case 1: 
				binarySearchTree = new BinarySearchTree<>();
				System.out.println("How many numeric values do you want to enter? ");
				int numValues = kbd.nextInt();
				for (int i = 0; i < numValues; i++) {
					System.out.println("Enter initial elements: ");
					binarySearchTree.insert(kbd.nextInt());
				}
				break;
				
			case 2: 
				binarySearchTree.printTree();
				break;
				
			case 3: 
				System.out.println(binarySearchTree.numLeaves());
				break;
				
			case 4: 
				System.out.println(binarySearchTree.numOneChildNodes());
				break;
				
			case 5: 
				System.out.println(binarySearchTree.numTwoChildNodes());
				break;
				
			case 6: 
				binarySearchTree.levelOrder();
				break;
				
			case 7: 
				System.out.println("Enter k1: ");
				int k1 = kbd.nextInt();
				System.out.println("Enter k2: ");
				int k2 = kbd.nextInt();
				binarySearchTree.printBetween(k1, k2);
				break;
				
			case 8:
				break;
				
			}
		}
		
		kbd.close();
		
	}
	
}
