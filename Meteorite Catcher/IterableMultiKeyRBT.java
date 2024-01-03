// --== CS400 Fall 2023 File Header Information ==--
// Name: Swetha Vaidyanathan
// Email: vaidyanatha4@wisc.edu
// Group: B17
// TA: Robert Nagel
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class IterableMultiKeyRBT<T extends Comparable<T>> extends RedBlackTree<KeyListInterface<T>>
        implements IterableMultiKeySortedCollectionInterface<T> {
    public Comparable<T> iterationStartPoint; //stores the starting point of the iteration
    public int numKeys = 0;//stores the number of keys in the tree.

    /**
     * Inserts value into tree that can store multiple objects per key by keeping
     * lists of objects in each node of the tree.
     *
     * @param key object to insert
     * @return true if a new node was inserted, false if the key was added into an existing node
     */
    @Override
    public boolean insertSingleKey(T key) {
        //creates a new key object type of key list.
        KeyList<T> newKey = new KeyList<T>(key);
        //creates a new node of the node type keyListInterface
        Node<KeyListInterface<T>> node = this.findNode(newKey);
        if (node == null) {
            this.numKeys++;
            return this.insert(newKey);
        }
        node.data.addKey(key);
        this.numKeys++;
        return false;
    }

    /**
     * @return the number of values in the tree.
     */
    // @Override
    public int numKeys() {
        //this method implements number of keys stored in the tree.
        return this.numKeys;
    }

    /**
     * Returns an iterator that does an in-order iteration over the tree.
     */
    @Override
    public Iterator<T> iterator() {
//anonymous class for iterator
        Iterator<T> iterator = new Iterator<T>() {

            //data fields for the iterator
            protected Stack<Node<KeyListInterface<T>>> stack = getStartStack();
            protected Iterator<T> keyListIterator;
            protected T nextKey = null;

            /**
             * Checks if the tree has any next values in the iterator
             * @return true if has next value
             */
            @Override
            public boolean hasNext() {
                // Check if the current keyListIterator is not null and has more elements
                if (keyListIterator != null && keyListIterator.hasNext()) {
                    return true; // If true, there are more elements in the current KeyList.
                } else if (!stack.empty()) {
                    // If the keyListIterator is exhausted and the stack is not empty,
                    // we need to traverse to the next KeyList in the structure.

                    // Pop the top node from the stack.
                    Node<KeyListInterface<T>> node = stack.pop();
                    KeyList<T> nextKeyList = (KeyList<T>) node.data;
                    keyListIterator = nextKeyList.iterator(); // Initialize the iterator for the next KeyList.

                    // Get the right child node.
                    Node<KeyListInterface<T>> rightChild = node.down[1];

                    if (rightChild != null) {
                        // If there's a right child, add it to the stack.
                        stack.push(rightChild);

                        // Traverse down the right child's leftmost path and add nodes to the stack.
                        Node<KeyListInterface<T>> nextNode = rightChild.down[0];
                        while (nextNode != null) {
                            stack.push(nextNode);
                            nextNode = nextNode.down[0];
                        }
                    }
                    return true;
                }
                return false;
            }


            /**
             * returns the next value in the iterator
             * @return next value in the iterator
             */
            @Override
            public T next() {
                //checks for the next element
                if (this.hasNext()) {
                    this.nextKey = keyListIterator.next();
                } else {
                    //throw an exception if there is no such element
                    throw new NoSuchElementException();
                }
                return nextKey;
            }

        };
        return iterator;
    }

    /**
     * Sets the starting point for iterations. Future iterations will start at the
     * starting point or the key closest to it in the tree. This setting is remembered
     * until it is reset. Passing in null disables the starting point.
     *
     * @param startPoint the start point to set for iterations
     */
    //@Override
    public void setIterationStartPoint(Comparable<T> startPoint) {
        //implementation of SetIterationsStartPoint.
        this.iterationStartPoint = startPoint;
    }

    /**
     * This clears all the contents in the tree.
     */
    @Override
    public void clear() {
        super.clear();
        this.numKeys = 0;
    }

    /**
     * This method creates a stack that holds all the nodes along the path leading to
     * the iterationStartPoint if it is specified, or the stack contains nodes along the path
     * to the smallest node if iterationStartPoint is null.
     * @return
     */

    protected Stack<Node<KeyListInterface<T>>> getStartStack() {
        // Create a stack to store nodes.
        Stack<Node<KeyListInterface<T>>> stack = new Stack<Node<KeyListInterface<T>>>();
        Node<KeyListInterface<T>> node = this.root; // Start from the root node.

        if (this.iterationStartPoint == null) {
            // If no starting point is specified, traverse the leftmost path of the tree.
            while (node != null) {
                stack.add(node); // Add the current node to the stack.
                node = node.down[0]; // Move to the next node in the left child.
            }
        }

        while (node != null) {
            // If a starting point is specified, find the appropriate node to start from.
            if (this.iterationStartPoint.compareTo(node.data.iterator().next()) < 0) {
                // If the starting point is less than the first element of the node's KeyList,
                // add the current node to the stack and move to the left child.
                stack.add(node);
                node = node.down[0];
            } else if (this.iterationStartPoint.compareTo(node.data.iterator().next()) > 0) {
                // If the starting point is greater than the first element of the node's KeyList,
                // move to the right child.
                node = node.down[1];
            } else if (this.iterationStartPoint.compareTo(node.data.iterator().next()) == 0) {
                // If the starting point is equal to the first element of the node's KeyList,
                // add the current node to the stack and stop traversal.
                stack.add(node);
                node = null;
            }
        }
        return stack; // Return the stack containing nodes to start iteration from.
    }

    /**
     * This test checks for insertion of single key into the tree.
     * This also checks for duplicate values in the tree and returns true if the
     * key is successfully inserted, else returns false.
     */
    @Test
    public void testInsertSingleKey() {
        //Creates a variable of IterableMultiKeyRBT type.
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        //Inserts the first key into the tree and checks if it returns true.
        Assertions.assertTrue(tree.insertSingleKey(1));
        //Inserts the second key into the tree and checks if it returns true.
        Assertions.assertTrue(tree.insertSingleKey(2));

    }

    /**
     * This method checks the numKeys() method and counts the number of keys in the tree.
     * This method returns true of the number of keys is the same.
     */
    @Test
    public void testNumKeys() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();

        // Insert multiple keys
        tree.insertSingleKey(5);
        tree.insertSingleKey(6);
        tree.insertSingleKey(7);
        tree.insertSingleKey(8);
        tree.insertSingleKey(7);

        int numKeys = tree.numKeys();

        // Ensure that numKeys returns the number of values in the tree.
        Assertions.assertEquals(5, numKeys);
        Assertions.assertEquals(4, tree.size);//checks distinct keys
    }

    /**
     * This test checks the implementation of the iterator method. The iterator checks the
     * value of the key in the node.
     */
    @Test
    public void testIterator() {
        //local variable to store the IterableMultiKeyRBT datatype.
        IterableMultiKeyRBT<String> tree = new IterableMultiKeyRBT<>();

        // Insert multiple keys
        tree.insertSingleKey("apple");
        tree.insertSingleKey("banana");
        tree.insertSingleKey("cherry");

        // Create an iterator
        Iterator<String> iterator = tree.iterator();

        // Iterate through the keys in order
        Assertions.assertEquals("apple", iterator.next());
        Assertions.assertEquals("banana", iterator.next());
        Assertions.assertEquals("cherry", iterator.next());
        //sets the iterator starting point back to the start of the list.
        //tree.setIterationStartPoint("banana");
        //Assertions.assertEquals("apple", iterator().next());
        //checks the first value in the list.
    }

    /**
     * Checks the The iterator method of the class by adding keys to the tree using the
     * insertSingleKey() method.
     */
    @Test
    public void RBTIteratorTest3() {
//creating the MultiKeyRedBlackTree
        IterableMultiKeyRBT<Character> rbt = new IterableMultiKeyRBT<Character>();
        rbt.insertSingleKey('Z');
        rbt.insertSingleKey('E');
        rbt.insertSingleKey('E');
        rbt.insertSingleKey('E');
        rbt.insertSingleKey('F');

        Iterator<Character> iterator = rbt.iterator();

        {
            Assertions.assertTrue(iterator.hasNext());
            Assertions.assertTrue(iterator.next() == 'E');
        }
        {
            Assertions.assertTrue(iterator.hasNext());
            Assertions.assertTrue(iterator.next() == 'E');
        }
        {
            Assertions.assertTrue(iterator.hasNext());
            Assertions.assertTrue(iterator.next() == 'E');
        }
        {
            Assertions.assertTrue(iterator.hasNext());
            Assertions.assertTrue(iterator.next() == 'F');
        }
        {
            Assertions.assertTrue(iterator.hasNext());
            Assertions.assertTrue(iterator.next() == 'Z');
        }
        {
            Assertions.assertTrue(!iterator.hasNext());
        }
    }
}