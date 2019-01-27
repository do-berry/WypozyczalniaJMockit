/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalnia;

import java.util.Date;
import mockit.Expectations;
import mockit.FullVerificationsInOrder;
import mockit.Injectable;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import system.Egzemplarz;
import system.Operacja;
/**
 *
 * @author USER
 */
@RunWith(JMockit.class)
public class WypozyczalniaTest {
    
    @Mocked
        Film film;
    @Injectable
        ProfilKlienta klient;
    public WypozyczalniaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of szukajKlienta method, of class Wypozyczalnia.
     */
    @Test
    public void testSzukajKlienta() {
        System.out.println("szukajKlienta");
        Wypozyczalnia instance = new Wypozyczalnia();
        String daneDoKlientow[][]={{"000","imie0", "nazwisko0", "email0", "adres0"}, {"111","imie1", "nazwisko1", "email1", "adres1"}, {"222","imie2", "nazwisko2", "email2", "adres2"}};
        ProfilKlienta daneKlienci[] = {klient, new ProfilKlienta(daneDoKlientow[0]),new ProfilKlienta(daneDoKlientow[1]), new ProfilKlienta(daneDoKlientow[2])};
    
        instance.klienci.add(klient);
        for(int i =0; i< 3; i++)
        {
            instance.dodajKlienta(daneDoKlientow[i]);
        }

        for(int i=0; i< 4; i++)
        {
            assertEquals(daneKlienci[i], instance.szukajKlienta(daneKlienci[i]));
        }
        
        new FullVerificationsInOrder() {
        {
            klient.equals(any); maxTimes = 2;
        }
        };
    }
    /**
     * Test of zlozRezerwacje method, of class Wypozyczalnia.
     */
    @Test
    public void testZlozRezerwacje() {
        System.out.println("zlozRezerwacje");
        Wypozyczalnia instance = new Wypozyczalnia();
        String[] daneFilmu = null;
        String[] expResult = {"Brak dostepnych egzemplarzy", "Rezerwacja dodana", "Nie znaleziono profilu"};      
        String result;
        String[] danePesele = {"000", "111"};
        String daneDoKlientow[][]={{"000","imie0", "nazwisko0", "email0", "adres0"}};
        String[] daneDoFilmu = {"1", "tytul1", "studio1", "rezyse1", "gatunek1", "nosnik1"};
        
        Egzemplarz egzemplarz = new Egzemplarz(1, film);
        
        Date daneDaty[] = {new Date(12, 10, 11), new Date(19, 10, 11)};
        
        instance.dodajKlienta(daneDoKlientow[0]);
        instance.filmy.add(film);
        instance.filmy.add(new Film(daneDoFilmu));
        new Expectations() {
        {
            film.sprawdzDate(daneDaty[0]);     result = null;
            film.sprawdzDate(daneDaty[1]);     result = egzemplarz;
            instance.szukajFilmu(new Film(daneDoFilmu)); result = film;
        }
        };
        for(int i =0; i<2; i++)
        {
            result = instance.zlozRezerwacje(danePesele[0],daneDoFilmu , daneDaty[i]);
            assertEquals(expResult[i], result);
        }
        
        result = instance.zlozRezerwacje(danePesele[1], daneFilmu, daneDaty[0]);
        assertEquals(expResult[2], result);
    }

    /**
     * Test of wypozycz method, of class Wypozyczalnia.
     */
    @Test
    public void testWypozycz() {
        System.out.println("wypozycz");
        Wypozyczalnia instance = new Wypozyczalnia();
        String[] daneFilmu = null;
        String[] expResult = {"Brak dostepnych egzemplarzy", "Wypozyczono", "Nie znaleziono profilu"};      
        String result;
        String[] danePesele = {"000", "111"};
        String daneDoKlientow[][]={{"000","imie0", "nazwisko0", "email0", "adres0"}};
        String[] daneDoFilmu = {"1", "tytul1", "studio1", "rezyse1", "gatunek1", "nosnik1"};
        
        Egzemplarz egzemplarz = new Egzemplarz(1, film);
        
        Date daneDaty[] = {new Date(12, 10, 11), new Date(19, 10, 11)};
        
        instance.dodajKlienta(daneDoKlientow[0]);
        instance.filmy.add(film);
        instance.filmy.add(new Film(daneDoFilmu));
        new Expectations() {
        {
            film.sprawdzDate(daneDaty[0]);     result = null;
            film.sprawdzDate(daneDaty[1]);     result = egzemplarz;
            instance.szukajFilmu(new Film(daneDoFilmu)); result = film;
        }
        };
        for(int i =0; i<2; i++)
        {
            result = instance.wypozycz(danePesele[0],daneDoFilmu , daneDaty[i]);
            assertEquals(expResult[i], result);
        }
        result = instance.zlozRezerwacje(danePesele[1], daneFilmu, daneDaty[0]);
        assertEquals(expResult[2], result);
    }

}
