/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class StatusFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    private static FXMLDocumentController fxmlDocumentController;
    public void setFXMLDocumentController(FXMLDocumentController fxmlDocumentController) {
        this.fxmlDocumentController = fxmlDocumentController;
    }
    
    @FXML
    private TextField statusInput;
    
    
    public void openStatusForm() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatusForm.fxml"));
        Parent patientForm = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Please fill the form");
        stage.setScene(new Scene(patientForm));
    
        stage.show();
    }
    
    @FXML
    public void RequestCount(ActionEvent e){
        // on button press return the number of patients waiting
        if(statusInput!=null){
            fxmlDocumentController.updateStatusCount(statusInput.getText());
            // Close the current window
            Stage stage = (Stage) statusInput.getScene().getWindow();
            stage.close();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
