package com.example.assurance;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.ReponseDevis;
import services.ServiceReponseDevis;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class updateReponseDevis implements Initializable {

    @FXML
    private Button boutonAjouter;

    @FXML
    private ImageView brandingImageView;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField tfAdresse;

    @FXML
    private DatePicker tfDate;

    @FXML
    private TextField tfModele;
    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPrenom;

    @FXML
    private TextField tfPuissance;

    @FXML
    private TextField tfid;

    @FXML
        void cancelButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
        Parent root = loader.load();
        GestionDevis controller = loader.getController();


        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        }

    private ReponseDevis selecteddevis;
    public void initData(ReponseDevis devis) throws SQLException {
        selecteddevis = devis;
        // Populate the fields in the UI with the data from selectedUser
        tfNom.setText(selecteddevis.getEtat());
        tfPrenom.setText(selecteddevis.getDecision());
        tfAdresse.setText(selecteddevis.getDocuments());

        tfEmail.setText(String.valueOf(selecteddevis.getEmail_id()));

        tfDate.setValue(selecteddevis.getDate_reglement()); // Assuming tfDate is a DatePicker
        tfModele.setText(selecteddevis.getDelai_reparation());
        tfPuissance.setText(String.valueOf(selecteddevis.getDuree_validite())); // Converting double to String
        tfid.setText(String.valueOf(selecteddevis.getId())); // Converting int to String
    }
@FXML
    void updateOne1(ActionEvent Event)throws SQLException{
    String selectedEtat = tfNom.getText();
    String selectedDecision = tfPrenom.getText();
    String selectedDocuments = tfAdresse.getText();

    int selectedEmail =Integer.parseInt( tfEmail.getText());


    LocalDate selectedDate = tfDate.getValue(); // Assuming tfDate is a DatePicker
    String selectedDelaiR = tfModele.getText();
    String selectedDuree = tfPuissance.getText();

    int id = Integer.parseInt(tfid.getText());
    ReponseDevis d = new ReponseDevis(id,selectedEmail, selectedEtat, selectedDecision, selectedDelaiR,selectedDuree, selectedDocuments, selectedDate);
    ServiceReponseDevis st = new ServiceReponseDevis();

    try {
        st.updateOne(d);
        System.out.println("Reponse devis updated successfully.");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reponse Devis changé!");
        alert.setContentText("Reponse Devis changé avec succes");
        alert.show();
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Failed to update Reponse Reponse devis: " + e.getMessage());
    }
}
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Image brandingImage = new Image(getClass().getResource("/images/django-removebg-preview.png").toString());
            brandingImageView.setImage(brandingImage);
        } catch (Exception e) {
            e.printStackTrace();
        }}


    }


