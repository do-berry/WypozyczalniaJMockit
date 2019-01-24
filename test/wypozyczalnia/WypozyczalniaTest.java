/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalnia;

import mockit.FullVerificationsInOrder;
import mockit.Injectable;
import mockit.integration.junit4.JMockit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author USER
 */
@RunWith(JMockit.class)
public class WypozyczalniaTest {
    
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
}
