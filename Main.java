import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Meniu Principal ===");
            System.out.println("1. Inregistreaza un cont nou");
            System.out.println("2. Conecteaza-te la un cont");
            System.out.println("3. Iesi din program");
            System.out.print("Alege o optiune: ");

            int optiune = scanner.nextInt();
            scanner.nextLine(); // Consumă newline-ul rămas

            switch (optiune) {
                case 1:
                    System.out.println("=== Inregistrare cont nou ===");
                    Inregistrare aplicatie = new Inregistrare(); // Creează un cont nou
                    break;

                case 2:
                    Log_in aplicatie2 = new Log_in(); // Creează un obiect pentru autentificare
                    System.out.println("=== Conectare la cont ===");
                    System.out.print("Introdu email-ul: ");
                    String email = scanner.nextLine();
                    System.out.print("Introdu PIN-ul: ");
                    int pin = scanner.nextInt();
                    scanner.nextLine(); // Consumă newline-ul rămas

                    boolean autentificat = aplicatie2.Log_in(email, pin); // Apelează metoda de autentificare
                    if (autentificat) {
                        System.out.println("Autentificare reușită!");
                        while (autentificat) {
                            System.out.println("1. Vizualizează sold");
                            System.out.println("2. Depune bani");
                            System.out.println("3. Retrage bani");
                            System.out.println("4. Transfer bani");
                            System.out.println("5. Iesi din cont");
                            System.out.print("Alege o optiune: ");
                            int optiuneCont = scanner.nextInt();
                            scanner.nextLine(); // Consumă newline-ul rămas

                            switch (optineCont) {
                                case 1:
                                System.out.println("Soldul tau este: " + sold);
                                    break;
                                case 2:
                                System.out.print("Introdu suma pe care vrei sa o depui: ");
                                    double suma = scanner.nextDouble();
                                    scanner.nextLine(); // Consumă newline-ul rămas

                                    double soldNou = aplicatie2.getSold() + suma;
                                    aplicatie2.setSold(soldNou);

                                    try (Connection connection = Bazadedate.getConnection()){
                                        String updateQuery = "UPDATE persoane SET Sold = ? WHERE Email = ?";
                                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
                                            preparedStatement.setDouble(1, soldNou);
                                            preparedStatement.setString(2, email);
                                            preparedStatement.executeUpdate();
                                            System.out.println("Depunere reușită! Soldul actualizat este: " + soldNou);
                                        }
                                    }catch (Exception e){
                                        System.err.println("Eroare la actualizarea soldului: " + e.getMessage());
                                    }
                                    break;




                                default:
                                    break;
                            }


                            } else {
                                System.out.println("Optiune invalida. Te rog sa alegi din nou.");
                            }
                        }
                    } else {
                        System.out.println("Autentificare eșuată. Încearcă din nou.");
                    }
                    break;

                case 3:
                    System.out.println("Iesire din program...");
                    running = false;
                    break;

                default:
                    System.out.println("Optiune invalida. Te rog sa alegi din nou.");
            }
        }

        scanner.close();
    }
}