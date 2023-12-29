/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class PatientFormController implements Initializable {

    private static FXMLDocumentController fxmlDocumentController;

    public void setFXMLDocumentController(FXMLDocumentController fxmlDocumentController) {
        this.fxmlDocumentController = fxmlDocumentController;
    }

    @FXML
    private Button cancelButton;

    @FXML
    private TextField conditionInput;

    @FXML
    private CheckBox eCheckbox;

    @FXML
    private TextField nameInput;

    @FXML
    private ComboBox<String> cBox;
    
    @FXML
    public void submitForm(ActionEvent e) throws IOException {
        //get instance of hospital 
        Hospital hospital = Hospital.getInstance();

        //extract the attriubte values
        String name = nameInput.getText();
        String condition = conditionInput.getText();
        boolean isEmerg = eCheckbox.isSelected();
        String cat = cBox.getValue();
        // if missing input
        if (name.isEmpty() || condition.isEmpty() || (cat == null)) {
            // Display an alert message
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Missing Information");
            a.setContentText("Make sure to fill all fields.");
            Optional<ButtonType> result = a.showAndWait();
            return;
        }
        if (name.matches(".*\\d.*") || condition.matches(".*\\d.*")) {
            // Display an alert message
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Invalid data entered ");
            a.setContentText("Make sure your name and condition do not include numbers or special characters.");
            Optional<ButtonType> result = a.showAndWait();
            return;
        }
//        // if the field is ticked (even if department is chosen) , update the dept to Emergency
//        if (isEmerg) {
//            dept = "Covid";
//        }
        //instanciate patient and add it to list
        Patient p = new Patient(name, condition, cat, isEmerg);
        hospital.addPatientQueue(p);

        hospital.updateDoctorQueue(cat, p);
        //close that window
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.close();
        
        // using instance to update the maintable 
        fxmlDocumentController.updateMainTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    // Other @FXML-annotated fields
    // Setting stage, title and scene
    public void openPatientForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientForm.fxml"));
        Parent patientForm = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Please fill the form");
        stage.setScene(new Scene(patientForm));

        stage.show();
        setupComboBox(loader);
    }
    
    // Defining options for the department checkbox
    private void setupComboBox(FXMLLoader loader) {
        PatientFormController c = loader.getController();
        c.cBox.getItems().add("Child (less than 12)");
        c.cBox.getItems().add("Male");
        c.cBox.getItems().add("Female");
    }

}
