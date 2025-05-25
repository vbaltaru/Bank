module com.example.interfata_proiect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.interfata_proiect to javafx.fxml;
    exports com.example.interfata_proiect;
}