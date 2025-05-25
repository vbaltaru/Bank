package com.example.interfata_proiect;
import javafx.fxml.FXML;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.IOException;

public class AdaugaBani {

    @FXML
    private TextField suma;

    @FXML
    private Text Valuta;

    @FXML
    private MenuItem MRON;

    @FXML
    private MenuItem MEUR;

    @FXML
    private  MenuItem MUSD;

    @FXML
    private Button add;

    @FXML
    private Text soldcurent;

    @FXML
    private Button acasa;

    private double Suma;

    public void initialize(){
        soldcurent.setText(String.format("%.2f", DateUtilizator.sold));
    }

    public void schimbRON(ActionEvent event){
        Valuta.setText("RON");
    }

    public void schimbEUR(ActionEvent event){
        Valuta.setText("EUR");
    }

    public void schimbUSD(ActionEvent event){
        Valuta.setText("USD");
    }

    public void add_money(ActionEvent event) throws IOException{
        Suma=Double.parseDouble(suma.getText());
        DateUtilizator.sold=DateUtilizator.sold+Suma;
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Meniu.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage=(Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    public void home(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Meniu.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage=(Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }



}
