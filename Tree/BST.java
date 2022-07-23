package Tree;

import java.util.NoSuchElementException;
/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if(data == null){
            throw new IllegalArgumentException();
        }
        root = addHelper(root, data);
    }

    private BSTNode<T> addHelper(BSTNode<T> currentNode, T data){
        if(currentNode == null){
            size ++;
            return new BSTNode<T>(data) ;
        }
        T currentData = currentNode.getData();
        if(currentData.compareTo(data) > 0){
            currentNode.setLeft(addHelper(currentNode.getLeft(), data));
        }else if(currentData.compareTo(data)< 0){
            currentNode.setRight(addHelper(currentNode.getRight(), data));
        }
        return currentNode;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if(data == null){
            throw new IllegalArgumentException();
        }
        if(root == null){
            throw new NoSuchElementException();
        }
        BSTNode<T> removeRef = new BSTNode<T>(null);
        root = removeHelper(root, data, removeRef);
        size --;
        return removeRef.getData();
    }

    private BSTNode<T> removeHelper(BSTNode<T> currentNode,T data, BSTNode<T> removeRef){
        if(currentNode == null) {
            throw new NoSuchElementException();
        }
        T currentData = currentNode.getData();
        if(currentData.compareTo(data) > 0){
            currentNode.setLeft(removeHelper(currentNode.getLeft(), data, removeRef));
        }else if(currentData.compareTo(data) < 0){
            currentNode.setRight(removeHelper(currentNode.getRight(), data, removeRef));
        }else{
            //Found the node!
            removeRef.setData(currentData);
            if(currentNode.getLeft()== null & currentNode.getRight() == null){
                return null;
            }
            if(currentNode.getLeft() == null){
                return currentNode.getRight();
            }
            if(currentNode.getRight() == null){
                return currentNode.getLeft();
            }
            BSTNode<T> removeRef2 = new BSTNode<T>(null);
            currentNode.setRight(removeMinNode(currentNode.getRight(), removeRef2));
            currentNode.setData(removeRef2.getData());
            return currentNode;
        }
        return currentNode;
    }

    // Remove the node with min value and return that node
    private BSTNode<T> removeMinNode(BSTNode<T> currentNode, BSTNode<T> removeRef){
        if(currentNode.getLeft() == null){
            removeRef.setData(currentNode.getData());
            return currentNode.getRight() == null? null:currentNode.getRight();
        }
        currentNode.setLeft(removeMinNode(currentNode.getLeft(),removeRef));
        return currentNode;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}