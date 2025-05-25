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

public class Transfer {
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

    public void Send(ActionEvent event) throws IOException{
        String nume = Nume.getText();
        String prenume = Prenume.getText();
        String email = Email.getText();
        double suma = Double.parseDouble(Suma.getText());
        String ValDestinatar=DateUtilizator.valuta2;
        if(!nume.equals(DateUtilizator.nume2) || !prenume.equals(DateUtilizator.prenume2) || !email.equals(DateUtilizator.email2)){
            Eroare.setText("Datele nu corespund!");
        } else if (suma > DateUtilizator.sold) {
            Eroare.setText("Fonduri insuficiente!");
        }

        if(DateUtilizator.valuta.equals(ValDestinatar)){
            DateUtilizator.sold = DateUtilizator.sold - suma;
            DateUtilizator.sold2 = DateUtilizator.sold2 + suma;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        }
        if(DateUtilizator.valuta.equals("RON") && ValDestinatar.equals("EUR")){
            if(DateUtilizator.sold<suma){
                Eroare.setText("Fonduri insuficiente");
            }else{
                DateUtilizator.sold=DateUtilizator.sold-suma;
                DateUtilizator.sold2=DateUtilizator.sold2+suma/5;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
            }
        }
        if(DateUtilizator.valuta.equals("RON") && ValDestinatar.equals("USD")){
            if(DateUtilizator.sold<suma){
                Eroare.setText("Fonduri insuficiente");
            }else{
                DateUtilizator.sold=DateUtilizator.sold-suma;
                DateUtilizator.sold2=DateUtilizator.sold2+suma/4;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
            }
        }
        if(DateUtilizator.valuta.equals("EUR") && ValDestinatar.equals("RON")){
            if(DateUtilizator.sold<suma){
                Eroare.setText("Fonduri insuficiente");
            }else{
                DateUtilizator.sold=DateUtilizator.sold-suma;
                DateUtilizator.sold2=DateUtilizator.sold2+suma*5;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
            }
        }
        if(DateUtilizator.valuta.equals("EUR") && ValDestinatar.equals("USD")){
            if(DateUtilizator.sold<suma){
                Eroare.setText("Fonduri insuficiente");
            }else{
                DateUtilizator.sold=DateUtilizator.sold-suma;
                DateUtilizator.sold2=DateUtilizator.sold2+suma*1.2;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
            }
        }
        if(DateUtilizator.valuta.equals("USD") && ValDestinatar.equals("RON")){
            if(DateUtilizator.sold<suma){
                Eroare.setText("Fonduri insuficiente");
            }else{
                DateUtilizator.sold=DateUtilizator.sold-suma;
                DateUtilizator.sold2=DateUtilizator.sold2+suma*4;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
            }
        }
        if(DateUtilizator.valuta.equals("USD") && ValDestinatar.equals("EUR")){
            if(DateUtilizator.sold<suma){
                Eroare.setText("Fonduri insuficiente");
            }else{
                DateUtilizator.sold=DateUtilizator.sold-suma;
                DateUtilizator.sold2=DateUtilizator.sold2+suma/1.2;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
            }
        }
        System.out.println(DateUtilizator.sold2);
    }
}