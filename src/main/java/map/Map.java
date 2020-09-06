package map;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * This is an implementation of a dictionary
 * that stores its elements in a linked list array
 * and uses a hashing function to calculate an
 * integer, that is the index in the
 * array where the particular element is stored.
 *
 * @author Ekaterina Marinova
 */
public class Map implements Serializable {

    private static final long serialVersionUID = 42L;

    private static final int INITIAL_CAPACITY = 16;

    private static int size = 0;

    /**
     * Linked list array, where data is stored.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Node[] nodes;

    /**
     * If not explicitly set from user, capacity is the default value
     */
    private int capacity = INITIAL_CAPACITY;

    /**
     * The Node is the object storing the data and also behaves as a linked list.
     */
    protected static class Node {
        //the key's hash code
        private Integer hash;
        //the key itself
        private String key;
        //the value object
        private Object value;
        //the reference to the next node
        private Node next;

        Node(Integer hash, String key, Object value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        Node() {}

        public Integer getHash() {
            return hash;
        }

        public Object getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "hash=" + hash +
                    ", key='" + key + '\'' +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public Map(int capacity) {
        this.capacity = capacity;
        nodes = new Node[capacity];
    }

    public Map() {
        nodes = new Node[INITIAL_CAPACITY];
    }

    /**
     * This hashing function is used to calculate an index
     * where the new node will be stored, based on the key's hashCode.
     *
     * @param key - the given key
     * @return - an integer index that fits the bounds of the array
     */
    private int hash(String key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % capacity);
    }

    /**
     * PUT function used to add elements to the data structure.
     * <p>
     * First we check whether the actual current size of the array
     * is equal to the capacity. If so => resize by creating
     * a new array with double the initial size.
     * <p>
     * Afterwards, if on the given index an object does not yet exist, create one.
     * If it does => fetch the last node and add a new node to the tail.
     *
     * @param key   - given key
     * @param value - given value, associated with the key
     */
    public void put(String key, Object value) {
        if (size == capacity) {
            capacity *= 2;
            nodes = Arrays.copyOf(nodes, capacity);
        }

        var currentNode = nodes[hash(key)];
        if (currentNode == null)
            //create new if empty
            nodes[hash(key)] = new Node(key.hashCode(), key, value, null);
        else {
            while (currentNode.hasNext())
                currentNode = currentNode.getNext();

            currentNode.setNext(new Node(key.hashCode(), key, value, null));
        }

        ++size;
    }

    /**
     * GET function used to retrieve a value based on a given key.
     * <p>
     * It works by checking the hashes of the keys of the nodes
     * and returns the one that matches. If no hash matches to the
     * hash of the given key returns null.
     *
     * @param key - key object based on which searching is performed
     * @return - the value of the object if found, if not - {@code null}
     */
    public Object get(String key) {
        var currentNode = nodes[hash(key)];
        Object value = null;

        while (currentNode != null) {
            if (currentNode.getHash() == key.hashCode()) {
                value = currentNode.getValue();
                break;
            }
            currentNode = currentNode.getNext();
        }

        return value;
    }

    /**
     * CONTAINS function that checks whether the given key is present in the data structure.
     *
     * @param key - the key based on which searching is performed
     * @return - {@code true} if found, {@code false} if not
     */
    public boolean contains(String key) {
        var currentNode = nodes[hash(key)];
        while (currentNode != null) {
            if (currentNode.getHash() == key.hashCode()) return true;
            currentNode = currentNode.getNext();
        }
        return false;
    }

    /**
     * REMOVE function that removes an element based on the given key, if present.
     * <p>
     * If the current node's hash matches the key's => return true
     * Otherwise keep looping while keeping track of the previous node;
     * If a node with such key is not found = return false;
     * Else swap references and return true.
     *
     * @param key - key object based on which searching is performed
     * @return - {@code true} if removed, {@code false} if not
     */
    public boolean remove(String key) {
        // Store head node
        Node temp = nodes[hash(key)], prev = null;

        if (temp != null && temp.hash == key.hashCode()) {
            nodes[hash(key)] = temp.next;
            return true;
        }

        while (temp != null && temp.hash != key.hashCode()) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) return false;

        prev.next = temp.next;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return capacity == map.capacity &&
                Arrays.equals(nodes, map.nodes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity);
        result = 31 * result + Arrays.hashCode(nodes);
        return result;
    }

    @Override
    public String toString() {
        return "Map{" +
                "nodes=" + Arrays.toString(nodes) +
                ", capacity=" + capacity +
                '}';
    }

}
