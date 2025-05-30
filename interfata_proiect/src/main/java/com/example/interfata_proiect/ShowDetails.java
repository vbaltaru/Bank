package com.example.interfata_proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ShowDetails {
    @FXML
    private Text eroare;

    @FXML
    private TextField pinfield;

    @FXML
    private Button submit;

    private Carduri carduriController;

    public void setCarduriController(Carduri controller) {
        this.carduriController = controller;
    }

    public void submit_pin(ActionEvent event){
        int pin = Integer.parseInt(pinfield.getText());
        if(pin != DateUtilizator.pin){
            eroare.setOpacity(1.0);
        } else {
            Carduri.valid = true;
            if (carduriController != null) {
                carduriController.refresh();
            }
            javafx.stage.Stage stage = (javafx.stage.Stage) submit.getScene().getWindow();
            stage.close();
        }
    }
}
