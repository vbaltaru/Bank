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
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Contnou {

    public static boolean adult(Date Date){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-18);
        Date plus18 = calendar.getTime();
        return Date.before(plus18);
    }

    @FXML
    private TextField NumeField;

    @FXML
    private  TextField PrenumeField;

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

    public void confirm1(ActionEvent event){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateUtilizator.nume = NumeField.getText();
            DateUtilizator.prenume = PrenumeField.getText();
            DateUtilizator.cnp = Long.parseLong(CNPField.getText());
            DateUtilizator.data = dateFormat.parse(DataField.getText());
            DateUtilizator.tara = TaraField.getText();
            DateUtilizator.cetatenie= CetatenieField.getText();
            DateUtilizator.adresa = AdresaField.getText();
            if(String.valueOf(DateUtilizator.cnp).length()!=13){
                CNPField.setText("CNP-ul trebuie sa aiba 13 cifre");
                CNPField.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
                return;
            }
            if (adult(DateUtilizator.data)) {
                try {
                    FXMLLoader fxmlLoader1=new FXMLLoader(getClass().getResource("ContNou2.fxml"));
                    Parent root1=fxmlLoader1.load();
                    Stage ContNou2=(Stage) ((Button) event.getSource()).getScene().getWindow();
                    ContNou2.getScene().setRoot(root1);
                }catch (IOException e){
                    e.printStackTrace();
                }
            } else  {
                DataField.setText("Nu ai 18 ani!");
                DataField.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
            }
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

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

    public void confirm2(ActionEvent event) {
        DateUtilizator.email = Email.getText();
        confirmemail = ConfirmEmail.getText();
        DateUtilizator.pin = Integer.parseInt(PIN.getText());
        confirmpin = Integer.parseInt(ConfirmPIN.getText());
        DateUtilizator.nrTelefon = Integer.parseInt(Telefon.getText());
        DateUtilizator.nrTelefon= Integer.parseInt(String.valueOf(Telefon.getText()));
        if (!DateUtilizator.email.equals(confirmemail)) {
            Email.setText("Emailurile nu sunt identice");
            Email.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
        } else if (DateUtilizator.pin != confirmpin ) {
            PIN.setText("PIN-urile nu coincid.");
            PIN.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
        } else if(String.valueOf(DateUtilizator.pin).length()!=6){
            PIN.setText("PIN-ul trebuie sa fie de 6 cifre");
            PIN.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
        } else if(String.valueOf(DateUtilizator.nrTelefon).length()!=10){
            Telefon.setText("Numarul de telefon trebuie sa aiba 10 cifre");
            Telefon.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
        } else if(!robot.isSelected()){
            if (this.Robot == null) {
                Robot.setText("Acceptati termenii si conditiile");
                Robot.setStyle("-fx-border-radius: 20; -fx-background-color: #005B00; -fx-border-color: #ffed00; -fx-text-fill: red; -fx-font-weight: bold;");
            }
        } else {
            try {
                FXMLLoader fxmlLoader1=new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root1=fxmlLoader1.load();
                Stage ContNou2=(Stage) ((Button) event.getSource()).getScene().getWindow();
                ContNou2.getScene().setRoot(root1);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
