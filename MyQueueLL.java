//345678901234567890123456789012345678901234567890123456789012345678901234567890012345678901234567890

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.EmptyStackException;

/**
 * Provides methods to construct a MyQueue based on a LinkedList
 *
 * @author forrest
 * @version November 2024
 */
public class MyQueueLL<E>
{
    private int front;
    private int back;
    private MyLinkedList<E> queue;
    private int count;
    /**
     * constructs a new MyQueueLL object
     */
    public MyQueueLL()
    {
        queue = new MyLinkedList<E>();
        front = 0;
        back = 0;
    }
    
    /**
     * adds a new value to the end of the queue
     *
     * @param  num the value to be added
     */
    public void enqueue(E num) {
        queue.addTail(num);
        count++;
        back++;
    }
    
    /**
     * takes a value off from the beginning of the queue
     *
     * @return    the value taken off
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E value = queue.getHead();
        queue.removeHead();
        front++;
        count--;
        return value;
    }
    
    /**
     * returns the value at the front of the queue
     *
     * @return    the value at the beginning of the queue
     */
    public E front() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue.getHead();
    }
    
    /**
     * returns the number of values in the queue currently
     *
     * @return    the integer size of the queue
     */
    public int size() {
        return count;
    }
    
    /**
     * returns whether or not the queue is empty
     *
     * @return    boolean whether the queue is empty or not
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * returns a string that reports the values in the queue
     *
     * @return    string formatting the values in the queue
     */
    public String toString() {
        return queue.toString();
    }
    
}