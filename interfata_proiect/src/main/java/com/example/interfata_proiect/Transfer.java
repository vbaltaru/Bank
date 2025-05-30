package com.example.interfata_proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transfer {

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

    @FXML
    private Text soldcurent;
    @FXML
    private TextField Nume;
    @FXML
    private TextField Prenume;
    @FXML
    private TextField Email;
    @FXML
    private TextField Suma;
    @FXML
    private Button Trimite;
    @FXML
    private Button acasa;
    @FXML
    private Text Eroare;

    public void initialize() {
        soldcurent.setText(String.format("%.2f", DateUtilizator.sold));
    }

    public void home(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    public void Send(ActionEvent event) throws IOException {
        String nume = Nume.getText();
        String prenume = Prenume.getText();
        String email = Email.getText();
        double suma;
        try {
            suma = Double.parseDouble(Suma.getText());
            if (suma <= 0) {
                Eroare.setText("Suma trebuie sa fie mai mare decat 0");
                return;
            }
        } catch (NumberFormatException e) {
            Eroare.setText("Suma invalida");
            return;
        }

        if (DateUtilizator.sold < suma) {
            Eroare.setText("Fonduri insuficiente");
            return;
        }

        try (Connection connection = Bazadedate.getConnection()) {
            String query = "SELECT sold, valuta FROM persoane WHERE nume = ? AND prenume = ? AND email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nume);
                preparedStatement.setString(2, prenume);
                preparedStatement.setString(3, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        Eroare.setText("Destinatar inexistent");
                        return;
                    }
                    double soldDestinatar = resultSet.getDouble("sold");
                    String valutaDestinatar = resultSet.getString("valuta");

                    // Amount to subtract from sender (in sender's currency)
                    double soldNou = DateUtilizator.sold - suma;
                    // Amount to add to recipient (converted to recipient's currency)
                    double sumaConvertita = conversie(suma, DateUtilizator.valuta, valutaDestinatar);
                    double soldNouDestinatar = soldDestinatar + sumaConvertita;

                    // Update sender
                    String updateSender = "UPDATE persoane SET sold = ? WHERE email = ?";
                    try (PreparedStatement psSender = connection.prepareStatement(updateSender)) {
                        psSender.setDouble(1, soldNou);
                        psSender.setString(2, DateUtilizator.email);
                        psSender.executeUpdate();
                    }

                    // Update recipient
                    String updateRecipient = "UPDATE persoane SET sold = ? WHERE email = ?";
                    try (PreparedStatement psRecipient = connection.prepareStatement(updateRecipient)) {
                        psRecipient.setDouble(1, soldNouDestinatar);
                        psRecipient.setString(2, email);
                        psRecipient.executeUpdate();
                    }

                    DateUtilizator.sold = soldNou;

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    stage.getScene().setRoot(root);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Eroare.setText("Eroare la transfer");
        }
    }

}