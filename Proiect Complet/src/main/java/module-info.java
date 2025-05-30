module com.example.interfata_proiect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires org.kordamp.ikonli.javafx;
    requires annotations;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.interfata_proiect to javafx.fxml;
    exports com.example.interfata_proiect;
}