package com.example.interfata_proiect;
import javafx.fxml.FXML;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.IOException;

public class Meniu {

    @FXML
    private Button adauga;

    @FXML
    private Button card;

    @FXML
    private Button acasa;

    @FXML
    private Button transferbani;

    @FXML
    private Button log_out;

    @FXML
    private Button account;

    @FXML
    private Text sold;

    @FXML
    private Text valuta;

    @FXML
    private Text soldcurent;

    public void initialize(){
        sold.setText(String.format("%.2f", DateUtilizator.sold));
        valuta.setText(DateUtilizator.valuta);
    }


    public void log_out(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxmlLoader.load();
        Stage login=(Stage) ((Button) event.getSource()).getScene().getWindow();
        login.getScene().setRoot(root);
    }

    public void Addmoney(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AdaugaBani.fxml"));
        Parent root=fxmlLoader.load();
        Stage login=(Stage) ((Button) event.getSource()).getScene().getWindow();
        login.getScene().setRoot(root);
    }

    public void transfer(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Transfer.fxml"));
        Parent root=fxmlLoader.load();
        Stage login=(Stage) ((Button) event.getSource()).getScene().getWindow();
        login.getScene().setRoot(root);
    }

    public void profil(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root=fxmlLoader.load();
        Stage login=(Stage) ((Button) event.getSource()).getScene().getWindow();
        login.getScene().setRoot(root);
    }

    public void Card(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Carduri.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
