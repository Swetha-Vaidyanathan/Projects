// --== CS400 Fall 2023 File Header Information ==--
// Name: <Swetha Vaidyanathan>
// Email: <vaidyanatha4@wisc.edu>
// Group: <B17>
// TA: <Robert Nagel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This class implements and checks the Red Black tree properties of a BST.
 * @param <T> A generic datatype parameter.
 */
public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * A class that defines instances of nodes properties
     * @param <T> a generic datatype parameter.
     */
    protected static class RBTNode<T> extends Node<T> {
        //initializes the black height of the node
        public int blackHeight = 0;
        public RBTNode(T data) { super(data); }
        public RBTNode<T> getUp() { return (RBTNode<T>)this.up; }
        public RBTNode<T> getDownLeft() { return (RBTNode<T>)this.down[0]; }
        public RBTNode<T> getDownRight() { return (RBTNode<T>)this.down[1]; }
    }

    /**
     * This class resolves and red property violations of the Red Black tree
     * by inserting a new node into the tree.
     * @param redNode takes a reference of a newly added red node.
     */

    protected void enforceRBTreePropertiesAfterInsert (Node redNode) {
//base case: redNode is root color it black.
        if(redNode == root){
            ((RBTNode<T>) root).blackHeight = 1;
            return;
        }
        //store the parent, grandParent and aunt of the redNode.

        RBTNode<T> parent = (RBTNode<T>)redNode.up;
        if (parent == null) {
            return; // Parent is null, can't enforce properties.
        }
        RBTNode<T> grandparent = parent.getUp();
        if (grandparent == null) {
            return; // Grandparent is null, can't enforce properties.
        }

        //get aunt accordingly
        RBTNode<T> auntNode = (parent == grandparent.getDownLeft()) ?
                grandparent.getDownRight() : grandparent.getDownLeft();

        // Case 1: If the parent is black, no violation.
        if (parent.blackHeight == 1) {
            return;
        }

        // Case 2: If the auntNode is red, recolor parent, aunt, and grandparent.
        //check if the aunt is red and not null
        if(auntNode != null && auntNode.blackHeight == 0){
            parent.blackHeight = 1;
            auntNode.blackHeight = 1;
            grandparent.blackHeight = 0;
            //check for Red Black Tree properties for grandparent node.
            enforceRBTreePropertiesAfterInsert(grandparent);
            return;
        }

        //case 3: Aunt is black or null, and node is opposite child of parent to parent of grandparent.

        if (redNode.isRightChild() && parent.isLeftChild()) {
            rotate(redNode, parent);
            redNode = parent;
            parent = (RBTNode<T>)redNode.up;
        } else if (redNode.isLeftChild() && parent.isRightChild()) {
            rotate(redNode, parent);
            redNode = parent;
            parent = (RBTNode<T>)redNode.up;
        }

        //Case 4: Aunt is black node or null, and node, parent and grandparent are all in same side.

        // If parent is the right child, do left rotation, or else do right rotation.
        if (parent.isLeftChild()) {
            rotate(parent, grandparent);
        } else {
            rotate(parent,grandparent);
        }

	parent.blackHeight = 1;
	grandparent.blackHeight = 0;


    }

    /**
     * This class inserts the new node into the Red Black tree by calling
     * insertHelper() method of BinarySearchTree class.
     * @param data the new node to be inserted into the tree.
     * @return true if the node was inserted successfully, else false.
     * @throws NullPointerException
     */
    @Override
    public boolean insert(T data) throws NullPointerException {
        if (data == null)
            throw new NullPointerException("Cannot insert data value null into the tree.");
// Create a new RBTNode and insert it into the tree using the superclass method
        RBTNode<T> newNode = new RBTNode<>(data);
        boolean inserted = super.insertHelper(newNode);

        // Call enforceRBTreePropertiesAfterInsert for the new node
        if (inserted) {
            enforceRBTreePropertiesAfterInsert(newNode);
            ((RBTNode<T>) root).blackHeight = 1;
        }
        return inserted;
    }
/**
 *This test adds a node into an empty tree and checks that the root is Black.
 */
    @Test
    public void emptyTreeInsertion(){
        //initializing a new tree.
        RedBlackTree<Integer> testTree = new RedBlackTree<>();
        testTree.insert(7);
        //getting the root node of the tree.
        RBTNode<Integer> rootNode = (RBTNode<Integer>) testTree.root;
        //Checking if the root is black.
        Assertions.assertEquals(1,rootNode.blackHeight);
    }

    /**
     *This test inserts a right child into a tree whose aunt is also red.
     * Returns true if the inserted node is red.
     */
    @Test
    public void redAuntInsertion() {
        RedBlackTree<Integer> testTree = new RedBlackTree<>();
        testTree.insert(2);
        testTree.insert(1);
        testTree.insert(3);
        testTree.insert(4);
        //gets RBTNode of the inserted node.

        RBTNode<Integer> rightChild = ((RBTNode<Integer>) testTree.root).getDownRight().getDownRight();
        //Checks the parentNode of the inserted node.
        RBTNode<Integer> parentNode = ((RBTNode<Integer>) testTree.root).getDownRight();
        //Checks the aunt node of the inserted node.
        RBTNode<Integer> auntNode= ((RBTNode<Integer>) testTree.root).getDownLeft();
        //Checks if the inserted node is a red node.
        Assertions.assertEquals(0,rightChild.blackHeight);
        Assertions.assertEquals(1,parentNode.blackHeight);
        Assertions.assertEquals(1,auntNode.blackHeight);
        Assertions.assertEquals("[ 2, 1, 3, 4 ]",testTree.toLevelOrderString());
    }

    /**
     * This test checks the insertion of a right child into a tree with a black aunt.
     * This insertion will perform a rotation.
     */


    @Test
    public void blackAuntInsertion(){
        RedBlackTree<Integer> testTree = new RedBlackTree<>();
        testTree.insert(5);
        testTree.insert(1);
        testTree.insert(9);
        testTree.insert(8);
        testTree.insert(10);
        testTree.insert(15);
        testTree.insert(20);
        //This gets the RBTNode of the inserted node after insertion.
        RBTNode<Integer> childNode = ((RBTNode<Integer>) testTree.root).getDownRight().getDownRight().getDownRight();
        //After rotation and color swap, this checks if the inserted node is red
        Assertions.assertEquals(0,childNode.blackHeight);
        Assertions.assertEquals("[ 5, 1, 9, 8, 15, 10, 20 ]",testTree.toLevelOrderString());
    }
}
