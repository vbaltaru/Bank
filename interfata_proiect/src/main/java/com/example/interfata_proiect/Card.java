package com.example.interfata_proiect;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Card {
    private final StringProperty cardNumber;
    private final StringProperty cvv;
    private final StringProperty expirationDate;

    public Card(String cardNumber, String cvv, String expirationDate) {
        this.cardNumber = new SimpleStringProperty(cardNumber);
        this.cvv = new SimpleStringProperty(cvv);
        this.expirationDate = new SimpleStringProperty(expirationDate);
    }

    public StringProperty cardNumberProperty() { return cardNumber; }
    public StringProperty cvvProperty() { return cvv; }
    public StringProperty expirationDateProperty() { return expirationDate; }

    public String getCardNumber() { return cardNumber.get(); }
    public String getCvv() { return cvv.get(); }
    public String getExpirationDate() { return expirationDate.get(); }
}
