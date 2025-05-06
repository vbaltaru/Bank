import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ContNou {
    private String Nume,Prenume,domiciliu,cetatenie,email;
    private Date dataNasterii;
    private int telefon,pin;



    public void setEmail(String email){
        if(Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",email)){
            this.email=email;
        }
        else{
            throw new IllegalArgumentException("Adresa nu este valida");
        }
    }
    public void setDataNasterii(Date dataNasterii){
        if(peste18(dataNasterii)){
            this.dataNasterii=dataNasterii;
        }
        else {
            throw new IllegalArgumentException("Varsta minima este de 18 ani");
        }
    }
    public void setPin(int pin){
        if(valid(pin)){
            this.pin=pin;
        }else{
            throw new IllegalArgumentException("Pin-ul trebuie sa fie de 6 cifre");
        }

    }
    private boolean valid(int pin){
        return pin>=0 && pin <=999999;
    }
    private boolean peste18(Date dataNasterii){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,-18);
        Date plus18 =calendar.getTime();
        return dataNasterii.before(plus18);

    }
    public void initializetastatura() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            System.out.print("Nume:");
            this.Nume = scanner.nextLine();

            System.out.print("Prenume:");
            this.Prenume = scanner.nextLine();

            System.out.print("Domiciliu:");
            this.domiciliu = scanner.nextLine();

            System.out.print("Cetatenie:");
            this.cetatenie = scanner.nextLine();

            System.out.print("Email:");
            setEmail(scanner.nextLine());

            System.out.print("Data Nasterii (yyyy-MM-dd):");
            this.dataNasterii = dateFormat.parse(scanner.nextLine());
            setDataNasterii(this.dataNasterii);

            System.out.print("Telefon:");
            this.telefon = scanner.nextInt();

            System.out.print("PIN (6 numere): ");
            setPin(scanner.nextInt());

        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid. Foloseste yyyy-MM-dd.");
        } catch (Exception e) {
            throw new IllegalArgumentException("Eroare: " + e.getMessage());
        }
    }


    public String getNume() {
        return  this.Nume;
    }

    public String getPrenume() {
        return this.Prenume;
    }

    public Date getDataNasterii() {
        return this.dataNasterii;
    }

    public int getTelefon() {
        return this.telefon;
    }

    public String getEmail() {
        return this.email;
    }

    public int getPin() {
        return this.pin;
    }

    public String getCetatenie() {
        return cetatenie;
    }

    public String getDomiciliu() {
        return domiciliu;
    }
}
