package com.example.interfata_proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Profil {
    @FXML
    private Text username,Nume,Prenume,CNP,DataNasterii,Telefon,Email,Cetatenie,Adresa;

    @FXML
    private Button acasa,transferbani;

    public void initialize(){
        Nume.setText(DateUtilizator.nume);
        Prenume.setText(DateUtilizator.prenume);
        CNP.setText(String.valueOf(DateUtilizator.cnp));
        DataNasterii.setText(DateUtilizator.data != null ? DateUtilizator.data.toString() : "");
        Telefon.setText(String.valueOf(DateUtilizator.nrTelefon));
        Email.setText(DateUtilizator.email);
        Cetatenie.setText(DateUtilizator.cetatenie);
        Adresa.setText(DateUtilizator.adresa+ ", " + DateUtilizator.tara);
        String nume,prenume;
        nume= DateUtilizator.nume;
        prenume= DateUtilizator.prenume;
        String usernameValue = "";
        if (nume != null && nume.length() >= 2) {
            usernameValue = "" + nume.charAt(0) + nume.charAt(nume.length() - 1);
        }
        username.setText(usernameValue.toUpperCase());
    }
    public void home(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Meniu.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage=(Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    public void Transfer(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Transfer.fxml"));
        Parent root=fxmlLoader.load();
        Stage login=(Stage) ((Button) event.getSource()).getScene().getWindow();
        login.getScene().setRoot(root);
    }

}
