/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.random;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author lenovo
 */
public class FXMLDocumentController implements Initializable {

    // when method is called, it will change the scene to another one :
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent dayAtHospitalParent = FXMLLoader.load(getClass().getResource("StartDayMenu.fxml"));
        Scene dayAtHospitalScene = new Scene(dayAtHospitalParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dayAtHospitalScene);
        window.show();
    }

    @FXML
    // Creating instance of patient form controller
    // sending the current running instance FXML controller to patientForm controller
    // opening form with that instance
    public void openPatientForm() throws IOException {
        PatientFormController patientFormController = new PatientFormController(); // Handle the exception appropriately
        patientFormController.setFXMLDocumentController(this);
        patientFormController.openPatientForm();
    }

    public void Remove() {
        Hospital hospital = Hospital.getInstance();
        hospital.RemovePatient();
        updateMainTable();
    }

    public void openCancelStage() throws IOException {

        CancelFormController cancelForm = new CancelFormController(); // Handle the exception appropriately
        cancelForm.setFXMLDocumentController(this);
        cancelForm.openCancelStage();
    }

    @FXML
    void OpenCheckStat(ActionEvent event) throws IOException {

        StatusFormController statusContInstance = new StatusFormController(); // Handle the exception appropriately
        statusContInstance.setFXMLDocumentController(this);
        statusContInstance.openStatusForm();
    }

    @FXML
    private Label statusCount;

    void updateStatusCount(String statusInput) {
        
        if (!statusInput.isEmpty()) {
            Hospital hospital = Hospital.getInstance();
            int i = Integer.parseInt(statusInput);
            
            int j = hospital.getPatientQueue().getWaiting(hospital.getOrder(i)); 
            if (j != -1) {
                statusCount.setText(Integer.toString(j));
                //change color to green:
                statusCount.setStyle("-fx-text-fill: red;");
            } else {
                // display an error alert 
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("The queue is empty");
                a.setContentText("Invalid ID, patient either doesn't exist or is dismissed/cancelled.");
                Optional<ButtonType> result = a.showAndWait();
            }
        }
        
        
    }

    @FXML
    private TableView<Patient> QueueTable;

    @FXML
    private TableColumn<Patient, String> condCol;

    @FXML
    private TableColumn<Patient, String> dptCol;

    @FXML
    private TableColumn<Patient, String> nameCol;

    @FXML
    private TableColumn<Patient, String> numCol;

    @FXML
    private TableColumn<Patient, String> pIDCol;

    @FXML
    private TableColumn<Patient, String> urgentCol;

    public void displayLinkedListInTable(DoubleLinkedList patientDeptList) {
        ObservableList observableList = FXCollections.observableArrayList(patientDeptList.toObservableList());
         //   ObservableList<PatientDepartmentPair> observableList = FXCollections.observableArrayList(patientDeptList.toList());

        QueueTable.setItems(observableList);
        dptCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        urgentCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().isIsEmergency())));
        pIDCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPatientId())));
        // numCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getOrder())));
        condCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCondition()));
    }

    // handler function after patient submits form : updates the main table 
    public void updateMainTable() {
        //reset status count
        statusCount.setText("_");
        Hospital h = Hospital.getInstance();
        
        DoubleLinkedList p = h.getPatientQueue();
      //  DoubleLinkedList<Patient> p = h.getPatientQueue();
      
        DoubleLinkedList patientDeptList = new DoubleLinkedList();
       // DoubleLinkedList<PatientDepartmentPair> patientDeptList = new DoubleLinkedList<>();
       
       
        int index =1;
        // creating list with the patient-department blueprint 
        
        displayLinkedListInTable(p);
    }

    @FXML
    void EndDay(ActionEvent event) {
        Hospital h = Hospital.getInstance();
        boolean isEmpty = h.isListEmpty();
        if (!isEmpty) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("End Day Alert");
            a.setContentText("Please ensure all patients are dismissed before closing.");
            Optional<ButtonType> result = a.showAndWait();
            return;
        }

        // Create a file and write the list for each department
        try (FileWriter fileWriter = new FileWriter("patient_records.txt"); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            ArrayList<ArrayList<Patient>> patientRecord = h.getPatientRecord();

            String[] departmentNames = {"Child", "Male", "Female", "Cancelled"};
            System.out.println(patientRecord.size());

            for (int i = 0; i < departmentNames.length; i++) {
                bufferedWriter.write("+----------------------------------+\n");
                bufferedWriter.write(String.format("| %-30s |\n", departmentNames[i] + " Category"));
                bufferedWriter.write("+----------------------------------+\n");

                ArrayList<Patient> patients = patientRecord.get(i);
                for (Patient patient : patients) {
                    bufferedWriter.write(String.format("| %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                            "Patient ID:", "Name:", "Condition:", "Emergency:", "Dismissed:"));
                    bufferedWriter.write(String.format("| %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                            patient.getPatientId(), patient.getName(), patient.getCondition(),
                            patient.isIsEmergency(), patient.isIsDismissed()));
                    bufferedWriter.write("+----------------------------------+\n");
                }
                bufferedWriter.write("\n\n");
            }

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("End Day Information");
            successAlert.setContentText("Patient records saved successfully.");
            Optional<ButtonType> result = successAlert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your requirements
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (statusCount != null) {
            statusCount.setText("_");
        }
    }

}
