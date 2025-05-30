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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    @FXML
    private Button transfer;

    @FXML
    private Button card;

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

    public void add_money(ActionEvent event) throws IOException {
        try {
            Suma = Double.parseDouble(suma.getText());
            if (Suma <= 0) {
                suma.setText("Suma invalida");
                return;
            }
        } catch (NumberFormatException e) {
            suma.setText("Suma invalida");
            return;
        }

        double converted = conversie(Suma, Valuta.getText(), DateUtilizator.valuta);
        DateUtilizator.sold += converted;
        soldcurent.setText(String.format("%.2f", DateUtilizator.sold));

        try (Connection connection = Bazadedate.getConnection()) {
            String update = "UPDATE persoane SET sold = ? WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
                preparedStatement.setDouble(1, DateUtilizator.sold);
                preparedStatement.setString(2, DateUtilizator.email);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    private double conversie(double amount, String from, String to) {
        if (from.equals(to)) return amount;
        if (from.equals("RON") && to.equals("EUR")) return amount / 5.06;
        if (from.equals("RON") && to.equals("USD")) return amount / 4.47;
        if (from.equals("EUR") && to.equals("RON")) return amount * 5.06;
        if (from.equals("EUR") && to.equals("USD")) return amount * 1.13;
        if (from.equals("USD") && to.equals("RON")) return amount * 4.47;
        if (from.equals("USD") && to.equals("EUR")) return amount / 1.13;
        return amount;
    }
    public void home(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Meniu.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage=(Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    public void transfer(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Transfer.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage=(Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    public void carduri(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Carduri.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage=(Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
