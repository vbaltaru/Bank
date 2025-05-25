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
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button ContNou;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField Pin;

    @FXML
    private Button loginButton;

    String email;
    int PIN;

    public void login(ActionEvent event) {
        email = emailField.getText();
        PIN = Integer.parseInt(Pin.getText());
        if (!(email.equals("Admin")) && PIN != 123456) {
            ContNou.setText("Nu ai cont? Înregistrează-te");
        } else{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
                Parent root2 = fxmlLoader.load();
                Stage Meniu=(Stage) ((Button) event.getSource()).getScene().getWindow();
                Meniu.getScene().setRoot(root2);
                System.out.println(DateUtilizator.adresa);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void Register(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contnou.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage ContNou = (Stage) ((Button) event.getSource()).getScene().getWindow();
            ContNou.getScene().setRoot(root1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


