package wypozyczalnia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import system.Egzemplarz;
import system.Operacja;

public class Wypozyczalnia {

    List<ProfilKlienta> klienci;
    List<Film> filmy;

    public Wypozyczalnia() {
        klienci = new ArrayList();
        filmy = new ArrayList();
    }

    public String dodajKlienta(String daneklienta[]) {
        ProfilKlienta nowyklient = new ProfilKlienta(daneklienta);
        if (this.szukajKlienta(nowyklient) == null) {
            klienci.add(nowyklient);
            return "Profil dodany";
        }
        return "Profil juz istnieje";
    }

    public String dodajFilm(String[] daneFilmu) {
        Film nowyFilm = new Film(daneFilmu);
        if (this.szukajFilmu(nowyFilm) == null) {
            filmy.add(nowyFilm);
            return "Film dodany";
        }
        return "Film juz istnieje";
    }

    public String dodajEgzemplarz(String[] daneFilmu, int ID) {
        Film film = new Film(daneFilmu);
        if ((film = this.szukajFilmu(film)) != null) {
            film.dodajEgzemplarz(ID);
            return "Egzemplarz dodany";
        }
        return "Egzemplarz juz istnieje";
    }

    public ProfilKlienta szukajKlienta(ProfilKlienta klient) {
        if (!klienci.isEmpty()) {
            int index;
            if ((index = klienci.indexOf(klient)) != -1) {
                return klienci.get(index);
            }
        }
        return null;
    }

    public Film szukajFilmu(Film film) {
        if (!filmy.isEmpty()) {
            int index;
            if ((index = filmy.indexOf(film)) != -1) {
                return filmy.get(index);
            }
        }
        return null;
    }

    public String zlozRezerwacje(String pesel, String[] daneFilmu, Date data) {
        ProfilKlienta klient = new ProfilKlienta(pesel);

        if ((klient = szukajKlienta(klient)) != null) {
            Film film = new Film(daneFilmu);
            if ((film = szukajFilmu(film)) != null) {
                Egzemplarz egzemplarz = film.sprawdzDate(data);

                if (egzemplarz != null) {
                    klient.dodajRezerwacje(data, egzemplarz); 
                    return "Rezerwacja dodana";
                }
                return "Brak dostepnych egzemplarzy";
            }
            return "Nie znaleziono filmu";
        }
        return "Nie znaleziono profilu";
    }

    public String przedluzOkresWypozyczenia(String pesel, Date data, int ID, String[] daneFilmu) {
        ProfilKlienta klient = new ProfilKlienta(pesel);
        if ((klient = szukajKlienta(klient)) != null) {
            Film film = new Film(daneFilmu);
            if ((film = szukajFilmu(film)) != null) {
                Egzemplarz egzemplarz = new Egzemplarz(ID, film);
                if (film.sprawdzPrzedluzenie(data, egzemplarz)) {
                    klient.przedluz(data, egzemplarz);
                    return "Przedluzono okres wypozyczenia";
                }
                return "Egzemplarz jest zarezerwowany na dana date";
            }
            return "Nie znaleziono filmu";
        }
        return "Nie znaleziono profilu";
    }

    public String wypozycz(String pesel, String[] daneFilmu, Date data) {
        ProfilKlienta klient = new ProfilKlienta(pesel);

        if ((klient = szukajKlienta(klient)) != null) {
            Film film = new Film(daneFilmu);
            if ((film = szukajFilmu(film)) != null) {
                Egzemplarz egzemplarz = film.sprawdzDate(data);

                if (egzemplarz != null) {
                    klient.dodajWypozyczenie(egzemplarz, data);
                    return "Wypozyczono";
                }
                return "Brak dostepnych egzemplarzy";
            }
            return "Nie znaleziono filmu";
        }
        return "Nie znaleziono profilu";
    }

    /**
     *
     * @param t
     */
    public static void main(String[] args) {
        String info;
        Wypozyczalnia wypozyczalnia = new Wypozyczalnia();

        String daneFilmu[] = {"1", "Kler", "Fonograf", "Wojciech Smarzowski", "Obyczajowy", "DVD"};
        info = wypozyczalnia.dodajFilm(daneFilmu);
        System.out.println(info);

        String daneFilmu1[] = {"2", "Jumanji: Welcome to the Jungle", "Columbia Pictures", "Jake Kasdan", "Przygodowy", "Blu-ray"};
        info = wypozyczalnia.dodajFilm(daneFilmu1);
        System.out.println(info);

        System.out.println("Wypisz filmy");
        for (Film film : wypozyczalnia.filmy) {
            System.out.println(film.toString());
        }

        String daneKlienta[] = {"11111111111", "Zdzislaw", "Programista", "zprog@gmail.com", "Wroclaw Krzywa 3/1"};
        info = wypozyczalnia.dodajKlienta(daneKlienta);
        System.out.println(info);

        String daneKlienta1[] = {"12121212121", "Aleksander", "Wielki", "aw@mail.com", "Wroclaw Prosta 1/2"};
        info = wypozyczalnia.dodajKlienta(daneKlienta1);
        System.out.println(info);

        System.out.println("\n" + "Wypisz klientow");
        for (ProfilKlienta klient : wypozyczalnia.klienci) {
            System.out.println(klient.toString());
        }

        info = wypozyczalnia.dodajEgzemplarz(daneFilmu, 1);
        System.out.println(info);
        info = wypozyczalnia.dodajEgzemplarz(daneFilmu, 2);
        System.out.println(info);

        info = wypozyczalnia.dodajEgzemplarz(daneFilmu1, 3);
        System.out.println(info);

        System.out.println("\n" + "Wypisz egzemplarze");
        for (Film film : wypozyczalnia.filmy) {
            for (Egzemplarz egzemplarz : film.egzemplarze) {
                System.out.println(egzemplarz.toString());
            }
            System.out.println(" ");
        }

        Date data = new Date(12, 10, 11);
        info = wypozyczalnia.zlozRezerwacje("12121212121", daneFilmu, data);
        System.out.println(info);

        info = wypozyczalnia.zlozRezerwacje("11111111111", daneFilmu, data);
        System.out.println(info);

        Date data1 = new Date(12, 10, 19);
        info = wypozyczalnia.zlozRezerwacje("12121212121", daneFilmu, data1);
        System.out.println(info);

        System.out.println("\n" + "Wypisz rezerwacje");
        for (ProfilKlienta klient : wypozyczalnia.klienci) {
            for (Operacja operacja : klient.rezerwacje) {
                System.out.println(klient.toString() + " | " + operacja.toString());
            }
            System.out.println(" ");
        }

        info = wypozyczalnia.wypozycz("12121212121", daneFilmu, data1);
        System.out.println(info);

        System.out.println("\n" + "Wypisz wypozyczenia");
        for (ProfilKlienta klient : wypozyczalnia.klienci) {
            for (Operacja operacja : klient.wypozyczenia) {
                System.out.println(klient.toString() + " | " + operacja.toString());
            }
            System.out.println(" ");
        }
    }
}
