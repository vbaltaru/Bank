import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Card{
    Random random = new Random();
    private long nrCard;
    private int cvv;
    private String dataexpirare;

    public Card() {
        SetNrCard();
        setCvv();
        setDataexpirare();
    }

    public void SetNrCard() {
        long n;
        do {
            n = random.nextInt(10);
            for (int i = 0; i < 15; i++) {
                n = n * 10 + random.nextInt(10);
            }
        } while (n == 0 || String.valueOf(n).length() != 16);
        this.nrCard = n;
    }

    public void setCvv() {
        int n;
        do {
            n = random.nextInt(10);
            for (int i = 0; i < 2; i++) {
                n = n * 10 + random.nextInt(10);
            }
        } while (n == 0 || String.valueOf(n).length() != 3);
        this.cvv = n;
    }

    public void setDataexpirare() {
        Calendar calendar = Calendar.getInstance();
        int year = random.nextInt(10) + 2023; // Random year between 2023 and 2032
        int month = random.nextInt(12); // Random month (0-11)
        calendar.set(year, month, 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
        this.dataexpirare = dateFormat.format(calendar.getTime());
        
    }
}
