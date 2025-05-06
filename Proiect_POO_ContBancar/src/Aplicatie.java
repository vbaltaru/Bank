import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Aplicatie {

    public Aplicatie() {
        try {
            ContNou contNou = new ContNou();
            contNou.initializetastatura();

            try (Connection connection = Bazadedate.getConnection()) {
                String insert = "INSERT INTO cont (Nume, Prenume, DataNasterii, Telefon, Email, Domiciliu, Cetatenie,Cod_PIN) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(contNou.getDataNasterii());

                    preparedStatement.setString(1, contNou.getNume());
                    preparedStatement.setString(2, contNou.getPrenume());
                    preparedStatement.setString(3, formattedDate);
                    preparedStatement.setString(4, String.valueOf(contNou.getTelefon()));
                    preparedStatement.setString(5, contNou.getEmail());
                    preparedStatement.setString(6, contNou.getDomiciliu());
                    preparedStatement.setString(7, contNou.getCetatenie());
                    preparedStatement.setString(8, String.valueOf(contNou.getPin()));

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " Inserat cu succes");
                }
            }
        } catch (Exception e) {
            System.err.println("Conexiunea cu baza de date nu a reusit " + e.getMessage());
        }
    }
}