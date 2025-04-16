import java.util.NoSuchElementException;

/**
 * Provides methods to construct and modify a LinkedList
 *
 * @author forrest
 * @version November 2024
 */
public class MyLinkedList<E>
{
    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    /**
     * returns the element value of generic type E at the very front of the LinkedList
     *
     * @return    the element at the head
     */
    public E getHead() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.getElement();
    }
    
    /**
     * adds an element node to the start of the LinekdList
     *
     * @param  value the value of the node to be added
     */
    public void addHead(E value) {
        if (head == null) {
            Node<E> node = new Node(value);
            head = node;
            tail = node;
        } else {
            Node<E> node = new Node(value);
            node.setNext(head);
            head = node;
        }
        size++;
    }
    
    /**
     * returns the value of the node removed at the front of the list
     *
     * @return    the value of the node removed head
     */
    public E removeHead() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<E> tempNode = head;
        head = head.getNext();
        size--;
        tempNode.setNext(null);
        return (E) tempNode.getElement();
    }
    
    /**
     * adds a new node at the tail with an inputted value
     *
     * @param  value the value of the new tail node
     */
    public void addTail(E value) {
        if (size == 0) {
            addHead(value);
            return;
        }
        Node<E> node = new Node(value);
        tail.setNext(node);
        tail = node;
        size++;
    }
    
    /**
     * returns whether or not the list currently has no values stored in nodes
     *
     * @return    boolean whether the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * returns the number of nodes currently in the list
     *
     * @return    the integer size of the list
     */
    public int size() {
        return size;
    }
    
    /**
     * provides a string formatting the values of all the nodes in the list
     *
     * @return    a string reporting the values of the nodes
     */
    public String toString() {
        String report = "";
        Node<E> node = head;
        if (node != null) {
            report += node.getElement();
            node = node.getNext();
        }
        while (node != null) {
            report += ", ";
            report += node.getElement();
            node = node.getNext();
        }
        return report;
    }
    
    private class Node<E> {
        private E element;
        private Node<E> next;

        private Node (E value) {
            element = value;
        }
        
        private E getElement() {
            return element;
        }
        
        private Node<E> getNext() {
            return next;
        }
        
        private void setNext(Node<E> node) {
            next = node;
        }
    }
}