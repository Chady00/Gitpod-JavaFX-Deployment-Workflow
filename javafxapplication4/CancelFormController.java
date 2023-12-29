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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class CancelFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private static FXMLDocumentController fxmlDocumentController;

    public void setFXMLDocumentController(FXMLDocumentController fxmlDocumentController) {
        this.fxmlDocumentController = fxmlDocumentController;
    }
    
    @FXML
    private TextField cInput;

    public void openCancelStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CancelForm.fxml"));
        Parent patientForm = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Please fill the form");
        stage.setScene(new Scene(patientForm));
        stage.show();
    }

    @FXML
    void cancel(ActionEvent event) {
        String input = cInput.getText();
        if (!input.isEmpty()) {
            int id = Integer.parseInt(cInput.getText());
            Hospital hospital = Hospital.getInstance();
            hospital.cancelPatient(id);
             // Close the current window
            Stage stage = (Stage) cInput.getScene().getWindow();
            stage.close();
            fxmlDocumentController.updateMainTable();
            
        }
        else {
           // Display an error message
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Please enter an ID");
            a.setContentText("Make sure you enter a valid ID.");
            Optional<ButtonType> result = a.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
