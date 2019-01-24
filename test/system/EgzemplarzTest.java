/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

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

/**
 *
 * @author USER
 */
@RunWith(JMockit.class)
public class EgzemplarzTest {
    
    @Mocked
        Operacja operacja;
    @Injectable
        Operacja operacja2;
    public EgzemplarzTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of dodajRezerwacje method, of class Egzemplarz.
     */

    @Test
    public void testDodajRezerwacje() {
        System.out.println("dodajRezerwacje");
        Egzemplarz instance = new Egzemplarz();
        Date daneDaty[] = {new Date(12, 10, 11), new Date(19, 10, 11), new Date(12, 12, 11)};
        Operacja daneOperacje[] = {operacja2, new Operacja(daneDaty[0], instance), new Operacja(daneDaty[1], instance), new Operacja(daneDaty[2], instance)};
  
         for(int i=0; i< 4; i++)
        {
            instance.dodajRezerwacje(daneOperacje[i]);   
        }
        for(int i=0; i< 4; i++)
        {
         assertEquals(daneOperacje[i], instance.rezerwacje.get(i));
        }
        new FullVerificationsInOrder() {
        {
            operacja2.equals(any); ; maxTimes = 3;
        }
        };
    }

    /**
     * Test of sprawdzRezerwacje method, of class Egzemplarz.
     */
    @Test
    public void testSprawdzRezerwacje() {
        System.out.println("sprawdzRezerwacje");
        
        Date daneDaty[] = {new Date(12, 10, 11), new Date(19, 10, 11), new Date(12, 12, 11), new Date(13, 1, 11), new Date(13, 2, 11), new Date(13, 3, 11), new Date(13, 4, 11),new Date(13, 14, 11)};

        Egzemplarz instance = new Egzemplarz();
        boolean expResult[] = {true, false};
        boolean result;  
        
        new Expectations() {
        {
            operacja.porownajDaty(daneDaty[0]);     result = true;
            operacja.porownajDaty(daneDaty[1]);     result = false;
        }
        };
        for(int i =0;i <2; i++)
        {
            instance.dodajRezerwacje(operacja);
            result = instance.sprawdzRezerwacje(daneDaty[i]);
            assertEquals(expResult[i], result);
        }
        new FullVerificationsInOrder() {
        {
            operacja.porownajDaty(daneDaty[0]); maxTimes = 1;
        }
        };
    }
}
