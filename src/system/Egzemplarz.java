package system;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import wypozyczalnia.Film;

public class Egzemplarz {

    int IDEgzemplarza;
    Film film;
    List<Operacja> rezerwacje;
    String status;

    public Egzemplarz() {
        rezerwacje = new ArrayList();
    }

    public Egzemplarz(int ID, Film film) {
        this.IDEgzemplarza = ID;
        this.film = film;
        status = "magazyn";
        rezerwacje = new ArrayList();
    }

    public void dodajRezerwacje(Operacja operacja) {
        rezerwacje.add(operacja);
    }

    public boolean sprawdzRezerwacje(Date data) {
        if (!rezerwacje.isEmpty()) {
            for (Operacja rezerwacja : rezerwacje) {
                if (rezerwacja.porownajDaty(data)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void zmienStatus() {
        if (status.equals("magazyn")) {
            status = "wypożyczono";
        } else {
            status = "magazyn";
        }
    }

    @Override
    public String toString() {
        String dane = String.valueOf(IDEgzemplarza) + " | " + film.toString() + " | " + status;
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
        final Egzemplarz other = (Egzemplarz) obj;
        if (!Objects.equals(this.IDEgzemplarza, other.IDEgzemplarza)) {
            return false;
        }
        return true;
    }
}
