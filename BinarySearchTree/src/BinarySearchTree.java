// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
		if( isEmpty( ) )
			throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
		if( isEmpty( ) )
			throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }
    
    /***************************************************
    * FUNCTION numLeaves : 
    * Serves as a public access method for numLeaves
    * 
    * OUTPUT : *
    * Returns the number of leaf nodes in the tree. 
     ****************************************************/ 

    public int numLeaves() {
    	return numLeaves(root);
    }

    /***************************************************
    * FUNCTION numTwoChildNodes : 
    * Serves as a public access method for numTwoChildNodes.
    * 
    * OUTPUT : 
    * Returns the number of full nodes in the tree. 
     ****************************************************/ 
    public int numTwoChildNodes() {
    	return numTwoChildNodes(root);
    }
    
    /***************************************************
    * FUNCTION levelOrder :
    * Serves as a public access method for levelOrder.
    * OUTPUT : *
    * Prints the tree in level traversal order. 
     ****************************************************/ 
    public void levelOrder() {
    	for (int level = 1; level <= height(root); level++) {
    		levelOrder(root, level);
    	}
    }
    
    /***************************************************
    * FUNCTION printBetween : 
    * Serves as a public access method for printBetween.
    * INPUT PARAMETERS : *
    * k1, the low end of the values to find elements between. 
    * k2, the high end of the values to find elements between. 
    * OUTPUT : *
    * Prints all values in the tree between k1 and k2.
     ****************************************************/ 
    public void printBetween(AnyType k1, AnyType k2) {
    	printBetween(root, k1, k2);
    }
    
    /***************************************************
    * FUNCTION numTwoChildNodes : (function name) *
    * Find the number of nodes with two children in the tree. 
    * INPUT PARAMETERS : *
    * t, the root of the subtree. 
    * OUTPUT : *
    * Returns the number of full nodes in the tree.
     ****************************************************/ 
    private int numTwoChildNodes(BinarySearchTree.BinaryNode<AnyType> t) {
    	if (t == null) {
    		return 0;
    	} 
    	int count = 0;
    	if (t.left != null && t.right != null) {
    		count = 1;
    	}
    	return count + numTwoChildNodes(t.left) + numTwoChildNodes(t.right);
	}

    /**
     * Finds the number of nodes with only one child in the tree.
     * @return the number of nodes with one child.
     */
    public int numOneChildNodes() {
    	return numOneChildNodes(root);
    }
    
    /**
     * Finds the number of nodes with only one child in the tree.
     * @param t the node that roots the subtree.
     * @return the number of nodes with one child.
     */
    private int numOneChildNodes(BinarySearchTree.BinaryNode<AnyType> t) {
    	if (t == null) {
    		return 0;
    	} 
    	int count = 0;
    	if (t.left != null && t.right == null
    		|| t.left == null && t.right != null) {
    		count = 1;
    	}
    	return count + numOneChildNodes(t.left) + numOneChildNodes(t.right);
	}
    
	/**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<AnyType>( x, null, null );
		
		int compareResult = x.compareTo( t.element );
			
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
			
		int compareResult = x.compareTo( t.element );
			
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
			
		int compareResult = x.compareTo( t.element );
			
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /***************************************************
     * FUNCTION printTree : 
     * Internal method that prints the tree in descending order. 
     * INPUT PARAMETERS : *
     * t, the node that roots the subtree.
     * OUTPUT : *
     * Prints the tree in descending order. 
      ****************************************************/ 
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.right );
            System.out.println( t.element );
            printTree( t.left );
        }
    }
    
    /***************************************************
     * FUNCTION printBetween : 
     * Serves as a public access method for printBetween.
     * INPUT PARAMETERS : *
     * k1, the low end of the values to find elements between. 
     * k2, the high end of the values to find elements between. 
     * t, the node that roots the subtree. 
     * OUTPUT : *
     * Prints all values in the tree between k1 and k2.
      ****************************************************/ 
    private void printBetween(BinaryNode<AnyType> t, AnyType k1, AnyType k2) {
    	if (t != null) {
    		if (t.element.compareTo(k1) > 0) {
    			printBetween(t.left, k1, k2);
    		} else if (t.element.compareTo(k1) >= 0 && t.element.compareTo(k2) <= 0) {
    			System.out.println(t.element);
    		} else if (t.element.compareTo(k2) < 0) {
    			printBetween(t.right, k1, k2);
    		}
    	}
    }
    
    /***************************************************
     * FUNCTION levelOrder : 
     * Internal method that prints the tree in level traversal order.
     * INPUT PARAMETERS : *
     * t, the node that roots the subtree.
     * level, the current level of the tree being traversed. 
     * OUTPUT : *
     * Prints the tree in level traversal order.
      ****************************************************/ 
    private void levelOrder( BinaryNode<AnyType> t, int level)
    {
        
        if (t != null) {
        	if (level == 1) {
        		System.out.println(t.element);
        	} else if (level > 1) {
        		levelOrder(t.left, level--);
        		levelOrder(t.right, level--);
        	}
        }
    }
    
    /***************************************************
     * FUNCTION numLeaves : 
     * Internal method that counts the number of leaf nodes in the tree. 
     * INPUT PARAMETERS : *
     * t, the node that roots the subtree.
     * OUTPUT : *
     * Returns the number of leaf nodes in the tree. 
      ****************************************************/ 
    public int numLeaves(BinaryNode<AnyType> t) {
    	if (t == null) {
    		return 0;
    	}
    	if(t.left == null && t.right == null) {
    		return 1;
    	}
    	return numLeaves(t.left) + numLeaves(t.right);
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
			return -1;
		else
			return 1 + Math.max( height( t.left ), height( t.right ) );	
    }
	
    // Basic node stored in unbalanced binary search trees
    static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;

}
