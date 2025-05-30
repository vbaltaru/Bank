package com.example.interfata_proiect;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;

public class HelloController {

    @FXML
    private Button contNou;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField pinField;

    @FXML
    private Button loginButton;

    private String email;
    private int pin;

    public void login(ActionEvent event) throws IOException {
        email = emailField.getText();
        try {
            pin = Integer.parseInt(pinField.getText());
        } catch (NumberFormatException e) {
            contNou.setText("PIN invalid!");
            return;
        }
        try (Connection connection = Bazadedate.getConnection()) {
            String query = "SELECT * FROM persoane WHERE email = ? AND codpin = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setInt(2, pin);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        DateUtilizator.email= resultSet.getString("email");
                        DateUtilizator.pin = resultSet.getInt("codpin");
                        DateUtilizator.nume = resultSet.getString("nume");
                        DateUtilizator.prenume = resultSet.getString("prenume");
                        DateUtilizator.tara = resultSet.getString("tara");
                        DateUtilizator.cetatenie = resultSet.getString("cetatenie");
                        DateUtilizator.adresa = resultSet.getString("adresa");
                        DateUtilizator.cnp = resultSet.getLong("cnp");
                        DateUtilizator.data = resultSet.getDate("datanasterii");
                        DateUtilizator.nrTelefon = resultSet.getInt("nrtelefon");
                        DateUtilizator.sold = resultSet.getDouble("sold");
                        DateUtilizator.valuta = resultSet.getString("valuta");
                        DateUtilizator.cardNumber = resultSet.getString("cardnumber");
                        DateUtilizator.cvv = resultSet.getInt("cvv");
                        DateUtilizator.dataCard = resultSet.getString("datacard");
                        String culoareStr = resultSet.getString("culoarecard");
                        if (culoareStr != null && !culoareStr.isEmpty()) {
                            DateUtilizator.culoarecard = Color.valueOf(culoareStr);
                        } else {
                            DateUtilizator.culoarecard = null;
                        }
                        DateUtilizator.visa = resultSet.getDouble("visa");
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
                        Parent root = fxmlLoader.load();
                        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        stage.getScene().setRoot(root);
                    } else {
                        contNou.setText("Nu ai cont!Inregistreaza-te!");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Eroare la conectarea cu baza de date: " + e.getMessage());
        }
    }

    public void register(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contnou.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


