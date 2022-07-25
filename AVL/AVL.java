package AVL;

import java.util.NoSuchElementException;

/**
 * Your implementation of the AVL tree rotations.
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if(data == null){
            throw new IllegalArgumentException();
        }
        root = addHelper(root, data);
    }

    private AVLNode<T> addHelper(AVLNode<T> currentNode, T data){
        if(currentNode == null){
            size ++;
            return new AVLNode<T>(data) ;
        }
        T currentData = currentNode.getData();
        if(currentData.compareTo(data) > 0){
            currentNode.setLeft(addHelper(currentNode.getLeft(), data));
        }else if(currentData.compareTo(data)< 0){
            currentNode.setRight(addHelper(currentNode.getRight(), data));
        }
        currentNode = balance(currentNode);
        return currentNode;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     *    simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     *    replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     *    replace the data, NOT predecessor. As a reminder, rotations can occur
     *    after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If the data is null.
     * @throws java.util.NoSuchElementException   If the data is not found.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if(data == null){
            throw new IllegalArgumentException();
        }
        if(root == null){
            throw new NoSuchElementException();
        }
        AVLNode<T> removeRef = new AVLNode<T>(null);
        root = removeHelper(root, data, removeRef);
        size --;
        return removeRef.getData();
    }

    private AVLNode<T> removeHelper(AVLNode<T> currentNode,T data, AVLNode<T> removeRef){
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
            AVLNode<T> removeRef2 = new AVLNode<T>(null);
            currentNode.setRight(removeMinNode(currentNode.getRight(), removeRef2));
            currentNode.setData(removeRef2.getData());
        }
        currentNode = balance(currentNode);
        return currentNode;
    }

    // Remove the node with min value
    private AVLNode<T> removeMinNode(AVLNode<T> currentNode, AVLNode<T> removeRef){
        if(currentNode.getLeft() == null){
            removeRef.setData(currentNode.getData());
            return currentNode.getRight() == null? null:currentNode.getRight();
        }
        currentNode.setLeft(removeMinNode(currentNode.getLeft(),removeRef));
        currentNode = balance(currentNode);
        return currentNode;
    }

    /**
     * Updates the height and balance factor of a node using its
     * setter methods.
     *
     * Recall that a null node has a height of -1. If a node is not
     * null, then the height of that node will be its height instance
     * data. The height of a node is the max of its left child's height
     * and right child's height, plus one.
     *
     * The balance factor of a node is the height of its left child
     * minus the height of its right child.
     *
     * This method should run in O(1).
     * You may assume that the passed in node is not null.
     *
     * This method should only be called in rotateLeft(), rotateRight(),
     * and balance().
     *
     * @param currentNode The node to update the height and balance factor of.
     */
    private void updateHeightAndBF(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        currentNode.setHeight(calculateHeight(currentNode));
        if(currentNode.getLeft() == null && currentNode.getRight() == null){
            currentNode.setBalanceFactor(0);
        }else if(currentNode.getLeft() == null){
            currentNode.setBalanceFactor(-1 - currentNode.getRight().getHeight());
        }else if(currentNode.getRight() == null){
            currentNode.setBalanceFactor(currentNode.getLeft().getHeight() + 1);
        }else{
            currentNode.setBalanceFactor(currentNode.getLeft().getHeight() - currentNode.getRight().getHeight());
        }
    }

    // Okay.. The test cases demand only the currentNode's height is updated. Child nodes should not be updated...
    private int calculateHeight(AVLNode<T> node){
        // To avoid unnecessary calculations
        if(node.getHeight() != 0){
            // return node.getHeight();
        }
        if(node.getLeft() == null && node.getRight() == null){
            // node.setHeight(0);
            return 0;
        }else if(node.getLeft() == null){
            // node.setHeight(calculateHeight(node.getRight()) + 1);
            return calculateHeight(node.getRight()) + 1;
        }else if(node.getRight() == null){
            // node.setHeight(calculateHeight(node.getLeft()) + 1);
            return calculateHeight(node.getLeft()) + 1;
        }else{
            // node.setHeight(Math.max(calculateHeight(node.getLeft()), calculateHeight(node.getRight())) + 1);
            return Math.max(calculateHeight(node.getLeft()), calculateHeight(node.getRight())) + 1;
        }
    }

    /**
     * Method that rotates a current node to the left. After saving the
     * current's right node to a variable, the right node's left subtree will
     * become the current node's right subtree. The current node will become
     * the right node's left subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is right heavy. Therefore, you do not need to
     * perform any preliminary checks, rather, you can immediately perform a
     * left rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        AVLNode<T> rightNote = currentNode.getRight();
        currentNode.setRight(rightNote.getLeft());
        rightNote.setLeft(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(rightNote);
        return rightNote;
    }

    /**
     * Method that rotates a current node to the right. After saving the
     * current's left node to a variable, the left node's right subtree will
     * become the current node's left subtree. The current node will become
     * the left node's right subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is left heavy. Therefore, you do not need to perform
     * any preliminary checks, rather, you can immediately perform a right
     * rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        AVLNode<T> leftNode = currentNode.getLeft();
        currentNode.setLeft(leftNode.getRight());
        leftNode.setRight(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(leftNode);
        return leftNode;
    }

    /**
     * This is the overarching method that is used to balance a subtree
     * starting at the passed in node. This method will utilize
     * updateHeightAndBF(), rotateLeft(), and rotateRight() to balance
     * the subtree. In part 2 of this assignment, this balance() method
     * will be used in your add() and remove() methods.
     *
     * The height and balance factor of the current node is first recalculated.
     * Based on the balance factor, a no rotation, a single rotation, or a
     * double rotation takes place. The current node is returned.
     *
     * You may assume that the passed in node is not null. Therefore, you do
     * not need to perform any preliminary checks, rather, you can immediately
     * check to see if any rotations need to be performed.
     *
     * This method should run in O(1).
     *
     * @param cur The current node under inspection.
     * @return The AVLNode that the caller should return.
     */
    private AVLNode<T> balance(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        /* First, we update the height and balance factor of the current node. */
        updateHeightAndBF(currentNode);
        

        if ( /* Condition for a right heavy tree. */ currentNode.getBalanceFactor() < -1 ) {
            updateHeightAndBF(currentNode.getRight());
            if ( /* Condition for a right-left rotation. */ currentNode.getRight().getBalanceFactor() >= 1) {
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            currentNode = rotateLeft(currentNode);
        } else if ( /* Condition for a left heavy tree. */currentNode.getBalanceFactor() > 1 ) {
            updateHeightAndBF(currentNode.getLeft());
            if ( /* Condition for a left-right rotation. */ currentNode.getLeft().getBalanceFactor() <= -1) {
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            currentNode = rotateRight(currentNode);
        }
        return currentNode;
    }

        /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree.
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}