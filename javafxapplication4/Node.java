/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lenovo
 */
public class Node {
    Patient data;
    Node prev;
    Node next;

    // Constructor
    public Node(Patient data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
