/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lenovo
 */
public class PatientDepartmentPair { 
// utility class, serves as blueprint for patient and department at the same time
// so I can get the department based on a patient ID
    private Patient patient;
    private String department;
    private int order=0;
    
    public PatientDepartmentPair(Patient patient, String department) {
        this.patient = patient;
        this.department = department;
    }
    public void setOrder(int x){
        order = x;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDepartment() {
        return department;
    }
    public int getOrder(){
        return order;
    }
}

