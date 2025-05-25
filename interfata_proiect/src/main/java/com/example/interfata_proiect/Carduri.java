package com.example.interfata_proiect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class Carduri {
    @FXML
    private Button acsa, transferbani, addCardBtn;
    @FXML
    private TableView<Card> cardTable;
    @FXML
    private TableColumn<Card, String> numberCol;
    @FXML
    private TableColumn<Card, String> cvvCol;
    @FXML
    private TableColumn<Card, String> expCol;

    private ObservableList<Card> cardList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        numberCol.setCellValueFactory(cellData -> cellData.getValue().cardNumberProperty());
        cvvCol.setCellValueFactory(cellData -> cellData.getValue().cvvProperty());
        expCol.setCellValueFactory(cellData -> cellData.getValue().expirationDateProperty());
        cardTable.setItems(cardList);

        // Format card number in groups of 4
        numberCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.replaceAll("(.{4})", "$1 ").trim());
                }
            }
        });
    }

    public void addCard(ActionEvent event) {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            number.append((int) (Math.random() * 10));
        }
        StringBuilder cvv = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            cvv.append((int) (Math.random() * 10));
        }
        int month = 1 + (int) (Math.random() * 12);
        int year = java.time.Year.now().getValue() % 100 + 1 + (int) (Math.random() * 5);
        String expiration = String.format("%02d/%02d", month, year);

        cardList.add(new Card(number.toString(), cvv.toString(), expiration));
    }

    public void Transfer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Transfer.fxml"));
        Parent root = fxmlLoader.load();
        Stage login = (Stage) ((Button) event.getSource()).getScene().getWindow();
        login.getScene().setRoot(root);
    }

    public void home(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}