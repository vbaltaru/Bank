import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Log_in {
    private double sold; // Stocam soldul utilizatorului

    public boolean login(String email, int pin) {
        try (Connection connection = Bazadedate.getConnection()) {
            String query = "SELECT * FROM persoane WHERE Email = ? AND PIN = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setInt(2, pin);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Autentificare reușită! Bun venit, " + resultSet.getString("Nume") + " " + resultSet.getString("Prenume"));
                        sold = resultSet.getDouble("Sold");
                        System.out.println("Soldul tău curent este: " + sold);
                        return true;
                    } else {
                        System.out.println("Autentificare eșuată. Email sau PIN incorect.");
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Eroare la conectarea cu baza de date: " + e.getMessage());
            return false;
        }
    }

    // Getter pentru sold
    public double getSold() {
        return sold;
    }

    // Setter pentru sold
    public void setSold(double sold) {
        this.sold = sold;
    }
}