package com.example.interfata_proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Carduri {

    public static boolean valid = false;

    public static String numarCard() {
        StringBuilder numar = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            if (i > 0 && i % 4 == 0) {
                numar.append(" ");
            }
            numar.append((int) (Math.random() * 10));
        }
        return numar.toString();
    }

    public static int cvvCard() {
        int cvv = 0;
        for (int i = 0; i < 3; i++) {
            cvv = cvv * 10 + (int) (Math.random() * 10);
        }
        return cvv;
    }

    public static String dataCard() {
        int month = 1 + (int) (Math.random() * 12);
        int year = java.time.Year.now().getValue() + 1 + (int) (Math.random() * 5);
        return String.format("%04d-%02d-01", year, month);
    }

    private String formatExpiry(String date) {
        if (date == null || date.length() < 7) return "";
        String[] parts = date.split("-");
        return parts[1] + "/" + parts[0].substring(2);
    }

    @FXML
    private Button acsa, transferbani, addCard;

    @FXML
    private Text nr_card;

    @FXML
    private Text cvv_card;

    @FXML
    private Text data_card;

    @FXML
    private Rectangle card;

    @FXML
    private FontIcon visa;

    public void initialize() {
        card.setFill(DateUtilizator.culoarecard);
        visa.setOpacity(DateUtilizator.visa);
        refresh();
    }

    public void refresh() {
        if (valid) {
            nr_card.setText(DateUtilizator.cardNumber != null ? DateUtilizator.cardNumber : "");
            cvv_card.setText(DateUtilizator.cvv > 0 ? String.valueOf(DateUtilizator.cvv) : "");
            data_card.setText(formatExpiry(DateUtilizator.dataCard));
        } else {
            nr_card.setText(DateUtilizator.cardNumber != null ? DateUtilizator.cardNumber.replaceAll("\\d", "*") : "");
            cvv_card.setText(DateUtilizator.cvv > 0 ? "***" : "");
            data_card.setText(formatExpiry(DateUtilizator.dataCard));
        }
    }

    public void addCard(ActionEvent event) {
        javafx.scene.paint.Color randomColor = javafx.scene.paint.Color.color(Math.random(), Math.random(), Math.random(), 1.0);
        DateUtilizator.culoarecard = randomColor;
        card.setFill(DateUtilizator.culoarecard);

        DateUtilizator.visa = 1.0;
        visa.setOpacity(DateUtilizator.visa);

        DateUtilizator.cardNumber = numarCard();
        DateUtilizator.cvv = cvvCard();
        DateUtilizator.dataCard = dataCard();

        valid = true;
        refresh();

        try (Connection connection = Bazadedate.getConnection()) {
            String query = "UPDATE persoane SET cardnumber = ?, cvv = ?, datacard = ?, culoarecard = ? WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, DateUtilizator.cardNumber);
                preparedStatement.setInt(2, DateUtilizator.cvv);
                preparedStatement.setString(3, DateUtilizator.dataCard);
                preparedStatement.setString(4, DateUtilizator.culoarecard.toString());
                preparedStatement.setString(5, DateUtilizator.email);
                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Cardul a fost adăugat cu succes!");
                } else {
                    System.out.println("Nu s-a putut adăuga cardul.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Afiseaza(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/interfata_proiect/ShowDetails.fxml"));
        Parent root = fxmlLoader.load();
        ShowDetails controller = fxmlLoader.getController();
        controller.setCarduriController(this);
        Stage newStage = new Stage();
        newStage.setTitle("Show Details");
        newStage.setScene(new javafx.scene.Scene(root));
        newStage.show();
    }

    public void Transfer(ActionEvent event) throws IOException {

        valid = false;
        refresh();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Transfer.fxml"));
        Parent root = fxmlLoader.load();
        Stage login = (Stage) ((Button) event.getSource()).getScene().getWindow();
        login.getScene().setRoot(root);
    }

    public void home(ActionEvent event) throws IOException {

        valid = false;
        refresh();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}