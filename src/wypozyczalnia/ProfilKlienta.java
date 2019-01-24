package wypozyczalnia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import system.Egzemplarz;
import system.Operacja;

public class ProfilKlienta {

    String pesel;
    String imie;
    String nazwisko;
    String email;
    String adres;
    List<Operacja> wypozyczenia;
    List<Operacja> rezerwacje;

    public ProfilKlienta() {
        wypozyczenia = new ArrayList();
        rezerwacje = new ArrayList();
    }

    public ProfilKlienta(String pesel) {
        this.pesel = pesel;
    }

    public ProfilKlienta(String[] daneKlienta) {
        this.pesel = daneKlienta[0];
        this.imie = daneKlienta[1];
        this.nazwisko = daneKlienta[2];
        this.email = daneKlienta[3];
        this.adres = daneKlienta[4];
        wypozyczenia = new ArrayList();
        rezerwacje = new ArrayList();
    }

    public ProfilKlienta(String pesel, String imie, String nazwisko, String email, String adres) {
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.adres = adres;
    }

    public void dodajRezerwacje(Date data, Egzemplarz egzemplarz) {
        Operacja operacja = new Operacja(data, egzemplarz);
        egzemplarz.dodajRezerwacje(operacja);
        rezerwacje.add(operacja);
    }

    public void dodajWypozyczenie(Egzemplarz egzemplarz, Date data) {
        Operacja operacja = new Operacja(data, egzemplarz);
        wypozyczenia.add(operacja);
    }

    public void przedluz(Date data, Egzemplarz egzemplarz) {
        Operacja operacja = new Operacja(data, egzemplarz);
        int index = wypozyczenia.indexOf(egzemplarz);
        operacja = wypozyczenia.get(index);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        operacja.data = calendar.getTime();
    }

    @Override
    public String toString()
    {
        String dane = pesel + " | " + imie + " | " + nazwisko + " | " + email + " | " +  adres;
        return dane;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.pesel);
        return hash;
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
        final ProfilKlienta other = (ProfilKlienta) obj;
        if (!Objects.equals(this.pesel, other.pesel)) {
            return false;
        }
        return true;
    }

}
