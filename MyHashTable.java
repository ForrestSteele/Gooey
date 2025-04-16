import java.util.Arrays;
/**
 * Provides methods to create and manipulate a HashTable.
 *
 * @author forrest
 * @version February 2025
 * @param K
 * @param V
 */
public class MyHashTable<K, V>
{
    private Node<K, V>[] array;
    private int size;
    private int tableSize;
    private double loadFactor;
    
    public MyHashTable() {
        tableSize = 10;
        array = new Node[tableSize];
        size = 0;
        loadFactor = 0.7;
    }
    
    private int hash(K key) {
        return Math.abs(key.hashCode()) % array.length;
    }
    
    /**
     * Associate the specified value with the specified key in this hash table. 
     * 
     * @throws NullPointerException
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        
        if (value == null) {
            throw new NullPointerException();
        }
        
        int location = hash(key);
        
        Node<K,V> oldNode = searchBucket(location, key);
        
        if (oldNode == null) {
            Node<K,V> newNode = new Node(key, value);
            addToBucket(location, newNode); //should only call this if there is actually a new node to be added, use search to assess whether this needs to happen
            size++;
        } else {
            oldNode.setValue(value); //update mapping by reassigning value of this key
        }
        
        if (size > loadFactor * tableSize) {
            expandHashTable();
        }
    }
    
    /**
     * Returns the value to which the specified key is mapped, or null if this hash table contains 
     * no mapping for the key. 
     * 
     * @return     V      the value that is retrived
     * @param      K      the key to be retrieved
     * @throws NullPointerException
     */
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        
        int location = hash(key);
        Node<K, V> node = searchBucket(location, key);
        if (node == null) {
            return null;
        } else {
            V value = node.getValue();
            return value;
        }
    }
    
    /**
     * Removes the key (and its corresponding value) and returns the value from this hash table.
     * 
     * @return     V      the value that is removed
     * @throws NullPointerException
     */
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int location = hash(key); //store the hashed index location of the key
        
        Node<K, V> oldNode = searchBucket(location, key); //get the node pointer that is in this bucket and has this key
        
        if (oldNode == null) { //if it is not found at this bucket (or there bucket is empty), then just return null, no need to go in and check
            return null;
        } else {
            V value = oldNode.getValue(); //collect and store the value of this node to be removed
            removeFromBucket(location, oldNode); //else just go into that bucket and search for the node, for both either if there is only one node in the bucket, or many
            return value;
        } //only calls this if precondition: node exists is met
    }
    
    private Node searchBucket(int bucket, K key) {
        if (array[bucket] == null) {
            return null;
        } else {
            Node node = array[bucket];
            while (node != null && !node.getKey().equals(key)) { //search the next node to see a matching key
                node = node.getNext();
            }
            return node;
        }
    }
    
    //only do this if it truly needs to add a **new node
    private void addToBucket(int bucket, Node newNode) {
        newNode.setNext(array[bucket]); //will set the next of the added node to null if there is no node in this bucket
        array[bucket] = newNode;
    }
    
    //only if node exists
    private void removeFromBucket(int bucket, Node oldNode) {
        //precondition that it is known node is in this bucket
        Node node = array[bucket];
        if (node.equals(oldNode)) {
            array[bucket] = node.getNext(); //will set bucket first node to null if the next value is null
            size--;
            oldNode.clear();
        } else {
            while (!node.getNext().equals(oldNode) && node.getNext().getNext() != null) { //this will only be 
                //reachced if linked list is two or more nodes. Becuase given that node exists in this bucket, 
                //if it is not the first, then there must be at least a second node
                node = node.getNext();
            }
            node.setNext(node.getNext().getNext()); //will set the next of cur node to null if node is penultimate node
            size--;
            oldNode.clear();
        }
    }
    
    private void expandHashTable() {
        Node<K,V>[] oldArray = array;
        tableSize *= 2; //update the new size of the array with the new assignment to array
        array = new Node[tableSize];
        for (int i = 0; i < oldArray.length; i++) { //iterate over all the buckets of the **old array
            Node currNode = oldArray[i]; //starting with the first node in this bucket
            while (currNode != null) {
                Node nextNode = currNode.getNext();
                int newBucket = currNode.hashCode % array.length;
                addToBucket(newBucket, currNode);
                currNode = nextNode;
            }
            oldArray[i] = null; //clearing this bucket of hashTable once all nodes have been transferred
        }
    }
    
    /**
     * Indicates whether hash table is empty.
     * 
     * @return     boolean     whether or not the hashTable is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns number of mappings in hash table.
     * 
     * @return     Integer     the amount of mappings in the hash table
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns the contents of the hash table for testing purposes.
     * 
     * @return     String      the formatted string of the array
     */
    public String toString() {
        return Arrays.toString(array);
    }
    
    private class Node<K, V> {
        private K key;
        private V value;
        private Node next;
        private int hashCode;
        
        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            hashCode = Math.abs(key.hashCode());
        }
        
        private K getKey() {
            return key;
        }
        
        private void setValue(V value) {
            this.value = value;
        }
        
        private V getValue() {
            return value;
        }
        
        private Node<K, V> getNext() {
            return next;
        }
        
        private void setNext(Node next) {
            this.next = next;
        }
        
        private void clear() {
            key = null;
            value = null;
            next = null;
        }
        
        public String toString() {
            if (next == null) {
                return key + "->" + value.toString();
            } else {
                return key + "->" + value.toString() + "|" + next.toString();
            }
        }
    }
}