/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author lenovo
 */
public class Hospital {

    private static Hospital desk; // for singleton
    private DoubleLinkedList patientQueue;
     //private DoubleLinkedList<Patient> patientQueue;
    
    
    private ArrayList<ArrayList<Patient>> patientRecord;

    public Hospital() {
         this.patientQueue = new DoubleLinkedList();
        //patientQueue = new DoubleLinkedList<>();
        this.patientRecord = new ArrayList<>(List.of( new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    //method to add the patient to the queue
    public void addPatientQueue(Patient p) {
        if (patientQueue.isEmpty()){
            patientQueue.add(p);
            
        }
        else if (p.isIsEmergency()) {
            Node n = findLastEmergencyNode();
            // 1, 2, 3, 4, 6
            // inserts between last covid patient and first regular patient
            

                
            
            patientQueue.insert(n, p);
            
        } else {
            patientQueue.add(p);
        }

    }

    // Helper method to find the index of the last emergency patient in patientQueue
    public void updateDoctorQueue(String d, Patient p) {
        if ("Child (less than 12)".equals(d)) {
            patientRecord.get(0).add(p);
        } else if (d == "Male") {
            patientRecord.get(1).add(p);
        } else if (d == "Female") {
            patientRecord.get(2).add(p);
        } else { 
            patientRecord.get(3).add(p);
        }
    }

    public void RemovePatient() {
        if (!patientQueue.isEmpty()) {
            Patient p = patientQueue.getFirst();
            patientQueue.getFirst().setIsDismissed(true);
            patientQueue.removeFirst();

            String patientInfo = "Patient ID: " + p.getPatientId()
                    + "\nName: " + p.getName()
                    + "\nCondition: " + p.getCondition()
                    + "\nEmergency: " + p.isIsEmergency()
                    + "\nDismissed: " + p.isIsDismissed();

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Removed a patient");
            a.setContentText(patientInfo);
            Optional<ButtonType> result = a.showAndWait();
            return;
        } else {
            // display an error 
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("The queue is empty");
            a.setContentText("There are 0 patients in the hospital.");
            Optional<ButtonType> result = a.showAndWait();
        }
    }

    public void cancelPatient(int id) {
        Patient p = null;
        Node n = patientQueue.traverseId(id); // in DLL class
        // 1, 2, 3, 4, 5, 6
        if (n == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("This ID doesn't exist.");
            a.setContentText("Please Enter an existing patient ID.");
            Optional<ButtonType> result = a.showAndWait();
            return ;
        }
        Patient c = n.data;
        patientQueue.remove(n);
                p = c;

                String patientInfo = "Patient ID: " + (p.getPatientId())
                        + "\nName: " + p.getName()
                        + "\nCondition: " + p.getCondition()
                        + "\nEmergency: " + p.isIsEmergency()
                        + "\nDismissed: " + p.isIsDismissed();

                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Cancelled a patient");
                a.setContentText(patientInfo);
                Optional<ButtonType> result = a.showAndWait();
       
        
        
        // recording patient
        for (ArrayList<Patient> patientList : patientRecord) {
            for (Patient p2: patientList){
                Patient recordPatient = p2;
                if (recordPatient.getPatientId() == id) {
                    patientList.remove(p2);
                    patientRecord.get(3).add(recordPatient);
                    return;
                }
            }
        }

        
    }
    
    public Node getOrder(int id){
        return getPatientQueue().traverseId(id);
    }

    /*public ArrayList<Patient> getActivePatientsFromId(int id) {
        int i = 0;
        ArrayList<Patient> activePatients = new ArrayList<>();
      
        for (ArrayList<Patient> patientList : patientRecord) {
            if (i==3){
                return activePatients;
            }
            for (Patient recordPatient : patientList) {
                if (recordPatient.getPatientId() == id) {
                    for (Patient x : patientList) {
                        activePatients.add(x);
                        if (x.getPatientId() == id) {
                            return activePatients;
                        }
                    }
                }
            }
            i++;
        }
        return activePatients;
    }*/
    
    
    

    public DoubleLinkedList getPatientQueue() {
        return patientQueue;
    }
//public DoubleLinkedList<Patient> getPatientQueue() {
//        return patientQueue;
//    }



    // RECURSION
    private Node findLastEmergencyNodeRecursive(Node n) {
        if (n == null){
            return null;
        }
        
    
        if (n.data.isIsEmergency()){
            Node save = findLastEmergencyNodeRecursive(n.next);
            if (save != null){
                return save;
            }
            else {
                return n;
            }
        }
        else {
            return null;
        }
    }
   
    // Wrapper method to start the recursion from the beginning of the list
    private Node findLastEmergencyNode() {
        return findLastEmergencyNodeRecursive(patientQueue.getHead());
    }
    //method to assign patient to a doctor

    // singleton approach 
    public static Hospital getInstance() {
        if (desk == null) {
            desk = new Hospital();
        }
        return desk;
    }

    boolean isListEmpty() {
        return patientQueue.isEmpty();
    }

    public ArrayList<ArrayList<Patient>> getPatientRecord() {
        return patientRecord;
    }

    public String getDeptFromId(int id) {
        // Check the patientRecord for the patient with the given ID
        int listIndex = 0;
        for (ArrayList<Patient> patientList : patientRecord) {
            for (Patient recordPatient : patientList) {
                if (recordPatient.getPatientId() == id) {
                    // If found, return the department based on the patient's department
                    return getDeptString(listIndex);
                }
            }
            listIndex++;
        }

        // If the patient with the given ID is not found, return null or an appropriate default value
        return null;
    }

    private String getDeptString(int listIndex) {
        // Convert department abbreviation to the corresponding full department name
        switch (listIndex) {
            case 0:
                return "Minor";
            case 1:
                return "Male";
            case 2:
                return "Female";
            default:
                return "Unknown Department";
        }
    }
}
