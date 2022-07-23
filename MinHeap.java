import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        // DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if(data == null){
            throw new IllegalArgumentException();
        }
        int capacity = backingArray.length;
        if(size + 1 == capacity){
            T[] newArray = (T[]) new Comparable[capacity * 2];
            for(int i = 1;i < capacity; i++){
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }
        backingArray[size + 1] = data;
        int index = size + 1;
        while(index / 2 > 0){
            if(backingArray[index].compareTo(backingArray[index / 2]) < 0){
                swap(index, index / 2);
                index = index / 2;
                continue;
            }
            break;
        }
        size ++;
    }

    private void swap(int i, int j) {
        T temp = backingArray[i];
        backingArray[i] = backingArray[j];
        backingArray[j] = temp;
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if(size == 0){
            throw new NoSuchElementException();
        }
        T result = backingArray[1];
        if(size == 1){
            size--;
            backingArray[1] = null;
            return result;
        }
        T temp = backingArray[size];
        backingArray[size] = null;
        backingArray[1] = temp;
        size --;
        int index = 1;
        int leftIndex;
        int rightIndex;
        while(index <= size / 2){
            leftIndex = index * 2;
            rightIndex = index * 2 + 1;
            int minIndex = findMinIndex(index, leftIndex, rightIndex);
            if(minIndex == leftIndex){
                swap(index, leftIndex);
                index = leftIndex;
                continue;
            }else if(minIndex == rightIndex){
                swap(index, rightIndex);
                index = rightIndex;
                continue;
            }
            break;
        }
        return result;
    }

    private int findMinIndex(int index, int leftIndex, int rightIndex){
        int min = index;
        if(leftIndex <= size && backingArray[min].compareTo(backingArray[leftIndex]) > 0){
            min = leftIndex;
        }
        if(rightIndex <= size && backingArray[min].compareTo(backingArray[rightIndex])>0){
            min = rightIndex;
        }
        return min;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
