package system;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import wypozyczalnia.ProfilKlienta;

public class Operacja {

    public Date data;
    private Egzemplarz egzemplarz;

    public Operacja() {
        this.data = new Date();
    }

    public Operacja(Date data, Egzemplarz egzemplarz) {
        this.data = data;
        this.egzemplarz = egzemplarz;
    }

    public boolean porownajDaty(Date dataRezerwacji) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        Calendar c4 = Calendar.getInstance();

        c1.setTime(data);
        c2.setTime(data);
        c3.setTime(dataRezerwacji);
        c4.setTime(dataRezerwacji);

        c2.add(Calendar.DATE, 7);
        c3.add(Calendar.DATE, 7);

        if ((c3.compareTo(c2) <= 0 && c3.compareTo(c1) >= 0) || (c4.compareTo(c2) <= 0 && c4.compareTo(c1) >= 0)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String dane = data.toString() + " | " + egzemplarz.toString();
        return dane;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Operacja other = (Operacja) obj;
        if (!Objects.equals(this.data, other.data) || !Objects.equals(this.egzemplarz, other.egzemplarz)) {
            return false;
        }
        return true;
    }
}
