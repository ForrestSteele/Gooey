import java.util.ArrayList;
import java.util.NoSuchElementException;

//345678901234567890123456789012345678901234567890123456789012345678901234567890012345678901234567890

/**
 * Provides methods to create and manipulate a GraphMap. 
 *
 * @author Forrest Steele
 * @version April 2025
 */
public class MyGraphMap
{
    MyHashTable<String,Vertex> vertices;
    
    /**
     * Constructs a new graph.
     */
    public MyGraphMap() {
        vertices = new MyHashTable<String,Vertex>();
    }
    
    /**
     * Adds vertex to graph.
     * @param   label   value to associate with vertex
     * @throws  NullPointerException    when label is null
     * @throws  IllegalArgumentException    when label is a duplicate
     */
    public void addVertex(String label, String value) {
        if (label == null) {
            throw new NullPointerException();
        }
        
        if (vertices.get(label) != null) {
            throw new IllegalArgumentException();
        }
        
        Vertex vertex = new Vertex(label, value);
        vertices.put(label, vertex);
    }
    
    /**
     * Adds directed edge between vertices of graph.
     * @param   fromLabel   from vertex
     * @param   toLabel     to vertex
     * @throws  NullPointerException    when either label is null
     * @throws  NoSuchElementException  when either vertex does not exist
     * @throws  IllegalArgumentException    when edge is a duplicate
     */
    public void addEdge(String fromLabel, String toLabel) {
        if (fromLabel == null || toLabel == null) {
            throw new NullPointerException();
        }
        
        if (vertices.get(fromLabel) == null || vertices.get(toLabel) == null) {
            throw new NoSuchElementException();
        }
        
        if (vertices.get(fromLabel).getAdjacent().contains(vertices.get(toLabel))) {
            throw new IllegalArgumentException();
        }
        
        Vertex fromVertex = vertices.get(fromLabel);
        Vertex toVertex = vertices.get(toLabel);
        
        fromVertex.addConnection(toVertex);
    }
    
    public void setVertex(String label, String value) {
        vertices.get(label).setValue(value);
    }
    
    public boolean noAdjDups(String startLabel) {
        for (Vertex vertex : vertices.get(startLabel).getAdjacent()) {
            if (vertex.getValue().equals(vertices.get(startLabel).getValue())) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Performs a breadth-first traversal of the graph.
     * @param   fromLabel   start vertex for the traversal
     * @return  list of labels in traversal order
     * @throws  NullPointerException    when label is null
     * @throws  NoSuchElementException  when vertex does not exist
     */
    public ArrayList<String> breadthFirstTraversal(String fromLabel) {
        if (fromLabel == null) {
            throw new NullPointerException();
        }
        
        Vertex vertex = vertices.get(fromLabel);
        
        if (vertex == null) {
            throw new NoSuchElementException();
        }
        
        MyQueueLL<String> operations = new MyQueueLL<String>();
        ArrayList<String> visited = new ArrayList<String>();
        
        operations.enqueue(vertex.getLabel()); //start off with putting the first vertex (fromlabel) 
        //to be processed first
        
        while (operations.size() > 0) { //keep going until no more vertices(labels) to process
            String currLabel = operations.dequeue(); //take off the current vertex(label) 
            //to be processed
            if (!visited.contains(currLabel)) { //if this vertex(label) hasn't already been process, 
                //then go in
                visited.add(currLabel); 
                Vertex ver = vertices.get(currLabel);
                ArrayList<Vertex> adjacent = ver.getAdjacent(); 
                for (Vertex v : adjacent) {
                    operations.enqueue(v.getLabel()); //add the label onto the operations queue
                }
            }
        }
        
        return visited;
    }
    
    /**
     * Performs a depth-first traversal of the graph.
     * @param   fromLabel   start vertex for the traversal
     * @return  list of labels in traveral order
     * @throws  NullPointerException    when label is null
     * @throws  NoSuchElementException  when vertex does not exist
     */
    public ArrayList<String> depthFirstTraversal(String fromLabel) {
        if (fromLabel == null) {
            throw new NullPointerException();
        }
        
        Vertex vertex = (Vertex) vertices.get(fromLabel);
        
        if (vertex == null) {
            throw new NoSuchElementException();
        }
        
        ArrayList<String> visited = new ArrayList<String>();

        depthFirstTraversal(vertex, visited);
        
        return visited;
    }
    
    private void depthFirstTraversal(Vertex vertex, ArrayList<String> visited) {
        if (visited.contains(vertex.getLabel())) {
            return;
        } else {
            visited.add(vertex.getLabel());
            for (Vertex v : vertex.getAdjacent()) {
                depthFirstTraversal(v, visited);
            }
        }
    }
    
    /**
     * @param   fromLabel   label of from vertex
     * @param   toLabel     label of to vertex
     * @return  distance from fromLabel to toLabel, -1 if from vertex and to vertex are not connected
     * @throws  NullPointerException    when either label is null
     * @throws  NoSuchElementException  when either vertex does not exist
     */
    public int distance(String fromLabel, String toLabel) {
        if (fromLabel == null || toLabel == null) {
            throw new NullPointerException();
        }
        
        Vertex fromVertex = vertices.get(fromLabel);
        Vertex toVertex = vertices.get(toLabel);
        
        if (fromVertex == null || toVertex == null) {
            throw new NoSuchElementException();
        }
                
        TraversalNode node = new TraversalNode(fromLabel, 0);
        
        MyQueueLL<TraversalNode> operations = new MyQueueLL<TraversalNode>();
        ArrayList<String> visited = new ArrayList<String>();
        
        operations.enqueue(node); 
        
        while (operations.size() > 0) { //keep going until no more vertices(labels) to process
            TraversalNode currNode = operations.dequeue(); 
            if (currNode.getLabel().equals(toLabel)) {
                return currNode.getDistance();
            } else if (!visited.contains(currNode.getLabel())) { //node not visited yet
                visited.add(currNode.getLabel()); 
                ArrayList<Vertex> adjacent = vertices.get(currNode.getLabel()).getAdjacent(); 
                for (Vertex v : adjacent) { 
                    TraversalNode newNode = new TraversalNode(v.getLabel(), currNode.getDistance() + 1);
                    operations.enqueue(newNode); //add the label onto the operations queue
                }
            }
        }
        
        return -1; //unconnected vertices
    }
    
    /**
     * @param   fromLabel   label of from vertex
     * @param   toLabel     label of to vertex
     * @return  list of vertex labels for path from fromLabel to toLabel, null if from vertex 
     * and to vertex are not connected
     * @throws  NullPointerException    when either label is null
     * @throws  NoSuchElementException  when either vertex does not exist
     */
    public ArrayList<String> shortestPath(String fromLabel, String toLabel) {
        if (fromLabel == null || toLabel == null) {
            throw new NullPointerException();
        }
        
        Vertex fromVertex = vertices.get(fromLabel);
        Vertex toVertex = vertices.get(toLabel);
        
        if (fromVertex == null || toVertex == null) {
            throw new NoSuchElementException();
        }
                
        TraversalNode node = new TraversalNode(fromLabel, null);
        
        MyQueueLL<TraversalNode> operations = new MyQueueLL<TraversalNode>();
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> shortestPath = new ArrayList<String>();
        
        operations.enqueue(node); //processing from label vertex first
        
        while (operations.size() > 0) { 
            TraversalNode currNode = operations.dequeue(); 
            if (currNode.getLabel().equals(toLabel)) {
                TraversalNode target = currNode;
                while (target.getPrev() != null) { //found fromLabel, exit
                    shortestPath.add(0, target.getLabel());
                    target = target.getPrev();
                }
                shortestPath.add(0, target.getLabel());
                return shortestPath; //from and to label vertices are connected
            } else if (!visited.contains(currNode.getLabel())) { 
                visited.add(currNode.getLabel()); //add vertex(label) to visited list path
                ArrayList<Vertex> adjacent = vertices.get(currNode.getLabel()).getAdjacent(); 
                for (Vertex v : adjacent) { 
                    TraversalNode newNode = new TraversalNode(v.getLabel(), currNode);
                    operations.enqueue(newNode); //add the adjacent label onto the operations queue
                }
            }
        }
        
        if (shortestPath.size() == 0) {
            return null; //from and to label vertices are disconnected
        } else {
            return shortestPath; //from and to label vertices are connected
        }
    }
    
    /**
     * Returns hash table of vertices as a string for testing purposes.
     * @Override    toString in class Object
     * @return  vertices table as a string
     */
    public String toString() {
        return vertices.toString();
    }
    
    private class Vertex {
        private String label;
        private String value;
        private ArrayList<Vertex> adjacent;
        
        public Vertex(String label) {
            this.label = label;
            adjacent = new ArrayList<Vertex>();
        }
        
        public Vertex(String label, String value) {
            this.label = label;
            this.value = value;
            adjacent = new ArrayList<Vertex>();
        }
        
        public String getLabel() {
            return label;
        }
        
        public String getValue() {
            return value;
        }
        
        public void setValue(String value) {
            this.value = value;
        }
        
        public ArrayList<Vertex> getAdjacent() {
            return adjacent;
        }
        
        public void addConnection(Vertex vertex) {
            adjacent.add(vertex);
        }
        
        public String toString() { // just print out the vertex label to represent the vertex
            String format = label + ":";
            for (Vertex v : adjacent) {
                format += "[" + v.getLabel() + "]";
            }
            return format;
        }
    }
    
    private class TraversalNode {
        private String label;
        private String value;
        private int distance;
        private TraversalNode prev;
        
        public TraversalNode(String label, int distance) {
            this.label = label;
            this.distance = distance;
        }
        
        public TraversalNode(String label, TraversalNode prev) {
            this.label = label;
            this.prev = prev;
        }
        
        public String getLabel() {
            return label;
        }
        
        public String getValue() {
            return value;
        }
        
        public int getDistance() {
            return distance;
        }
        
        public TraversalNode getPrev() {
            return prev;
        }
        
        public String toString() {
            return label.toString();
        }
    }
}