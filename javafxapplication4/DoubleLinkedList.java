/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication4;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lenovo
 */
// DoubleLinkedList class to implement a doubly linked list
public class DoubleLinkedList {

    private Node head;
    private Node tail;
    private int size;

    // Constructor

    /**
     *
     */
    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Method to add a patient to the end of the doubly linked list

    /**
     *
     * @param p
     */
    public Node getHead(){
        return head;
    }
    
  
    
    public void insert(Node n, Patient p){
        // 1, 2, 3, 4, 5, 6
        // 7
        Node i = new Node(p);
        if (n == null){
            if (head == null){
                head = tail = i;
            }
            else {
                i.next = head;
                head.prev = i;
                head = i;
            
                size ++;
                
            }
            return ;
        }
        
    
        i.next = n.next;
        i.prev = n;
        if (n.next != null){
            n.next.prev = i;
        }
        n.next = i;
        if (n == tail){
            tail = i;
        }
        size ++;
    }
    
    public void add(Patient p) {
        Node newNode = new Node(p);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
            newNode.next = null;
        }
        size++;
    }
   // 1, 2, 3, 4, 5, 6
        public void remove(Node n) {
        // Check if the node to be removed is null
        if (n == null) {
            return;
        }
        if (n == head) {
            head = n.next;
            if (head != null) {
                head.prev = null;
            }
        } else {
            if (n.prev != null) {
                n.prev.next = n.next;
            }

            if (n.next != null) {
                n.next.prev = n.prev;
            }
        }

        if (n == tail) {
            tail = n.prev;
            if (tail != null) {
                tail.next = null;
            }
        }
        size--;
    }
    
    public int getWaiting(Node n){
        if (n == null){
            return -1;
        }
        
        int count = 0;
        Node current = n;
        while (current != null){
            if (current == head){
                return count;
            }
            current = current.prev;
            count++;
        }
        
        return -1;
    }
    
    // RECURSION
    public Node traverseId(int id){
        return traverseIdR(id, head);
    }
    
    private Node traverseIdR(int id, Node current) {
        if (current == null || current.data.getPatientId() == id) {
            return current;
        }

        return traverseIdR(id, current.next);
    }
    
    
    // Method to add a patient to a specific index in the doubly linked list

    /**
     *
     * @param index
     * @param p
     */
    public void add(int index, Patient p) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node newNode = new Node(p);

        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;

            if (tail == null) {
                tail = newNode;
            }
        } else if (index == size) {
            newNode.prev = tail;
            if (tail != null) {
                tail.next = newNode;
            }
            tail = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }

        size++;
    }

    // Method to get the first element in the list

    /**
     *
     * @return
     */
    public Patient getFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    // Method to remove the first element from the linked list

    /**
     *
     */
    public void removeFirst() {
        if (isEmpty()) {
            return;
        }

        head = head.next;
        if (head != null) {
            head.prev = null;
        }

        size--;
    }

    // Method to check if the doubly linked list is empty

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    public ObservableList toObservableList() {
        ObservableList observableList = FXCollections.observableArrayList();
        Node current = head;  // Assuming DoubleNode is my node class
        while (current != null) {
            observableList.add(current.data);
            current = current.next;
        }

        return observableList;
    }

    // Override iterator to iterate over the linked list

    /**
     *
     * @return
     */
    

    // Method to get the size of the doubly linked list

    /**
     *
     * @return
     */
    public int size() {
        return size;
    }

    // Method to get a specific node in the doubly linked list

    /**
     *
     * @param index
     * @return
     */
    public Patient get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }
}
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class DoubleLinkedList<T> implements Iterable<T> {
//    private Node<T> head;
//    private Node<T> tail;
//    private int size;
//
//    public DoubleLinkedList() {
//        this.head = null;
//        this.tail = null;
//        this.size = 0;
//    }
//
//    public void add(T data) {
//        Node<T> newNode = new Node<>(data);
//        if (isEmpty()) {
//            head = tail = newNode;
//        } else {
//            newNode.prev = tail;
//            tail.next = newNode;
//            tail = newNode;
//        }
//        size++;
//    }
//
//    public void add(int index, T data) {
//        if (index < 0 || index > size) {
//            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
//        }
//
//        Node<T> newNode = new Node<>(data);
//
//        if (index == 0) {
//            newNode.next = head;
//            if (head != null) {
//                head.prev = newNode;
//            }
//            head = newNode;
//
//            if (tail == null) {
//                tail = newNode;
//            }
//        } else if (index == size) {
//            newNode.prev = tail;
//            if (tail != null) {
//                tail.next = newNode;
//            }
//            tail = newNode;
//        } else {
//            Node<T> current = head;
//            for (int i = 0; i < index; i++) {
//                current = current.next;
//            }
//
//            newNode.prev = current.prev;
//            newNode.next = current;
//            current.prev.next = newNode;
//            current.prev = newNode;
//        }
//
//        size++;
//    }
//
//    public T getFirst() {
//        if (isEmpty()) {
//            return null;
//        }
//        return head.data;
//    }
//
//    public void removeFirst() {
//        if (isEmpty()) {
//            return;
//        }
//
//        head = head.next;
//        if (head != null) {
//            head.prev = null;
//        }
//
//        size--;
//    }
//
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    @Override
//    public Iterator<T> iterator() {
//        return new Iterator<T>() {
//            private Node<T> current = head;
//
//            @Override
//            public boolean hasNext() {
//                return current != null;
//            }
//
//            @Override
//            public T next() {
//                if (!hasNext()) {
//                    return null;
//                }
//                T data = current.data;
//                current = current.next;
//                return data;
//            }
//        };
//    }
//
//    public int size() {
//        return size;
//    }
//
//    public T get(int index) {
//        if (index < 0 || index >= size) {
//            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
//        }
//
//        Node<T> current = head;
//        for (int i = 0; i < index; i++) {
//            current = current.next;
//        }
//
//        return current.data;
//    }
// public List<T> toList() {
//        List<T> list = new ArrayList<>();
//        for (T element : this) {
//            list.add(element);
//        }
//        return list;
//    }
//    private static class Node<T> {
//        T data;
//        Node<T> prev;
//        Node<T> next;
//
//        public Node(T data) {
//            this.data = data;
//            this.prev = null;
//            this.next = null;
//        }
//    }
//}

