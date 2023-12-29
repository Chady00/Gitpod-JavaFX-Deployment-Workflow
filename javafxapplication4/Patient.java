/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lenovo
 */
public class Patient {
    
    private static int numPatients = 0;
    private int patientId;
    private String name;
    private String condition;
    private boolean isEmergency;
    private boolean isDismissed;
    private String category;
    
    // constructor 
    public Patient(String name, String condition, String category, boolean emergency){
        this.patientId=++numPatients;
        this.name = name;
        this.condition = condition;
        this.isEmergency= emergency;
        this.category = category;
        this.isDismissed = false; // not dismissed by default
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static int getNumPatients() {
        return numPatients;
    }

    public static void setNumPatients(int numPatients) {
        Patient.numPatients = numPatients;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(boolean isEmergency) {
        this.isEmergency = isEmergency;
    }

    public boolean isIsDismissed() {
        return isDismissed;
    }

    public void setIsDismissed(boolean isDismissed) {
        this.isDismissed = isDismissed;
    }
    
}
   