package wypozyczalnia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import system.Egzemplarz;
import system.Operacja;

public class Film {

    int IDFilmu;
    String tytul;
    String studio;
    String rezyser;
    String gatunek;
    String nosnik;
    List<Egzemplarz> egzemplarze;

    public Film() {
    }

    public Film(String[] daneFilmu) {
        this.IDFilmu = Integer.parseInt(daneFilmu[0]);			
        this.tytul = daneFilmu[1];
        this.studio = daneFilmu[2];
        this.rezyser = daneFilmu[3];
        this.gatunek = daneFilmu[4];
        this.nosnik = daneFilmu[5];
        egzemplarze = new ArrayList();
    }

    public Film(int ID, String tytul, String studio, String rezyser, String gatunek, String nosnik) {
        this.IDFilmu = ID;
        this.tytul = tytul;
        this.studio = studio;
        this.rezyser = rezyser;
        this.gatunek = gatunek;
        this.nosnik = nosnik;
        egzemplarze = new ArrayList();
    }

    public void dodajEgzemplarz(int ID) {
        Egzemplarz nowyEgzemplarz = new Egzemplarz(ID, this);

        if (egzemplarze.isEmpty() || szukajEgzemplarz(nowyEgzemplarz) == null) {
            egzemplarze.add(nowyEgzemplarz);
        }
    }

    public Egzemplarz sprawdzDate(Date data) {

        if (!egzemplarze.isEmpty()) {
            for (Egzemplarz egzemplarz : egzemplarze) {
                if (!egzemplarz.sprawdzRezerwacje(data)) {
                    return egzemplarz;
                }
            }
        }
        return null;
    }

    boolean sprawdzPrzedluzenie(Date data, Egzemplarz egzemplarz) {
        if (egzemplarz.sprawdzRezerwacje(data)) {
            return false;
        }
        return true;
    }

    public Egzemplarz szukajEgzemplarz(Egzemplarz egzemplarz) {
        if (!egzemplarze.isEmpty()) {
            int index;
            if ((index = egzemplarze.indexOf(egzemplarz)) != -1) {
                return egzemplarze.get(index);
            }
        }
        return null;
    }

        @Override
    public String toString()
    {
        String dane = IDFilmu + " | " + tytul + " | " + studio + " | " + rezyser + " | " +  gatunek + " | " + nosnik;
        return dane;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.IDFilmu);
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
        final Film other = (Film) obj;
        if (!Objects.equals(this.tytul, other.tytul) || !Objects.equals(this.studio, other.studio) || !Objects.equals(this.rezyser, other.rezyser) || !Objects.equals(this.nosnik, other.nosnik)) {
            return false;
        }
        return true;
    }
}
