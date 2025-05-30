package com.example.interfata_proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Contnou {

    public static boolean adult(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        Date plus18 = calendar.getTime();
        return date.before(plus18);
    }

    @FXML
    private TextField NumeField;
    @FXML
    private TextField PrenumeField;
    @FXML
    private TextField CNPField;
    @FXML
    private TextField DataField;
    @FXML
    private TextField TaraField;
    @FXML
    private TextField CetatenieField;
    @FXML
    private TextField AdresaField;
    @FXML
    private Button Confirm1;
    @FXML
    private TextField Email;
    @FXML
    private TextField ConfirmEmail;
    @FXML
    private TextField PIN;
    @FXML
    private TextField ConfirmPIN;
    @FXML
    private Button Sign_Up;
    @FXML
    private TextField Telefon;
    @FXML
    private Label Robot;
    @FXML
    private CheckBox robot;

    private String confirmemail;
    private int confirmpin;

    public void setEmail(String email) {
        if (Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
            DateUtilizator.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    public void confirm1(ActionEvent event) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateUtilizator.nume = NumeField.getText();
            DateUtilizator.prenume = PrenumeField.getText();
            DateUtilizator.cnp = Long.parseLong(CNPField.getText());
            DateUtilizator.data = dateFormat.parse(DataField.getText());
            DateUtilizator.tara = TaraField.getText();
            DateUtilizator.cetatenie = CetatenieField.getText();
            DateUtilizator.adresa = AdresaField.getText();

            if (String.valueOf(DateUtilizator.cnp).length() != 13) {
                CNPField.setText("CNP-ul trebuie sa aiba 13 cifre");
                CNPField.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
                return;
            }
            if (!adult(DateUtilizator.data)) {
                DataField.setText("Nu ai 18 ani!");
                DataField.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
                return;
            }
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("ContNou2.fxml"));
            Parent root1 = fxmlLoader1.load();
            Stage ContNou2 = (Stage) ((Button) event.getSource()).getScene().getWindow();
            ContNou2.getScene().setRoot(root1);
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    public void confirm2(ActionEvent event) {
        try {
            setEmail(Email.getText());
            confirmemail = ConfirmEmail.getText();
            DateUtilizator.pin = Integer.parseInt(PIN.getText());
            confirmpin = Integer.parseInt(ConfirmPIN.getText());
            DateUtilizator.nrTelefon = Integer.parseInt(Telefon.getText());

            if (!DateUtilizator.email.equals(confirmemail)) {
                Email.setText("Emailurile nu sunt identice");
                Email.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
                return;
            }
            if (DateUtilizator.pin != confirmpin) {
                PIN.setText("PIN-urile nu coincid.");
                PIN.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
                return;
            }
            if (String.valueOf(DateUtilizator.pin).length() != 6) {
                PIN.setText("PIN-ul trebuie sa fie de 6 cifre");
                PIN.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
                return;
            }
            if (String.valueOf(DateUtilizator.nrTelefon).length() != 10) {
                Telefon.setText("Numarul de telefon trebuie sa aiba 10 cifre");
                Telefon.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
                return;
            }
            if (!robot.isSelected()) {
                Robot.setText("Acceptati termenii si conditiile");
                Robot.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
                return;
            }

            if (DateUtilizator.tara.equalsIgnoreCase("Romania") || DateUtilizator.tara.equalsIgnoreCase("România")) {
                DateUtilizator.valuta = "RON";
            } else if (DateUtilizator.tara.equalsIgnoreCase("America") || DateUtilizator.tara.equalsIgnoreCase("United States")) {
                DateUtilizator.valuta = "USD";
            } else {
                DateUtilizator.valuta = "EUR";
            }
            DateUtilizator.sold = 0.0;

            try (Connection connection = Bazadedate.getConnection()) {
                String insert = "INSERT INTO persoane (email, codpin, nume, prenume, tara, cetatenie, adresa, cnp, datanasterii, sold, valuta, nrtelefon) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
                    preparedStatement.setString(1, DateUtilizator.email);
                    preparedStatement.setInt(2, DateUtilizator.pin);
                    preparedStatement.setString(3, DateUtilizator.nume);
                    preparedStatement.setString(4, DateUtilizator.prenume);
                    preparedStatement.setString(5, DateUtilizator.tara);
                    preparedStatement.setString(6, DateUtilizator.cetatenie);
                    preparedStatement.setString(7, DateUtilizator.adresa);
                    preparedStatement.setLong(8, DateUtilizator.cnp);
                    preparedStatement.setDate(9, new java.sql.Date(DateUtilizator.data.getTime()));
                    preparedStatement.setDouble(10, DateUtilizator.sold);
                    preparedStatement.setString(11, DateUtilizator.valuta);
                    preparedStatement.setInt(12, DateUtilizator.nrTelefon);

                    preparedStatement.executeUpdate();
                }
            }
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root1 = fxmlLoader1.load();
            Stage ContNou2 = (Stage) ((Button) event.getSource()).getScene().getWindow();
            ContNou2.getScene().setRoot(root1);
        } catch (IllegalArgumentException e) {
            Email.setText("Adresa de email nu este valida");
            Email.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}