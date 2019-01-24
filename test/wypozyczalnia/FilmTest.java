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
import system.Egzemplarz;

/**
 *
 * @author USER
 */
@RunWith(JMockit.class)
public class FilmTest {
    
    @Injectable
    Egzemplarz egzemplarz;
    
    public FilmTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of szukajEgzemplarz method, of class Film.
     */
    @Test
    public void testSzukajEgzemplarz() {
        System.out.println("szukajEgzemplarz");
        Film instance = new Film(0, "film0", "studio0", "rezyser0", "gatunek0", "nosnik0");
        
        Egzemplarz daneEgzemplarze[] = {egzemplarz, new Egzemplarz(0, instance),new Egzemplarz(1, instance), new Egzemplarz(2, instance)};
        for(int i =0; i<3; i++)
        {
            instance.egzemplarze.add(daneEgzemplarze[i]);
        }
        for(int i =0; i < 3; i++)
        {
            assertEquals(daneEgzemplarze[i], instance.szukajEgzemplarz(daneEgzemplarze[i]));
        }

        assertEquals(null, instance.szukajEgzemplarz(daneEgzemplarze[3]));
        
        new FullVerificationsInOrder() {
        {
            egzemplarz.equals(any);  maxTimes = 2;
        }
        };
    }
}
