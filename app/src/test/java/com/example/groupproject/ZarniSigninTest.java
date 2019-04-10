package com.example.groupproject;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import Model.AllUsers;
import Model.Customer;
import Model.Owner;

import static Controller.SignIn.Signin;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ZarniSigninTest {
    Owner owner;
    Customer customer,customer2;
    AllUsers allUsers;

    public ZarniSigninTest(){
        owner = new Owner("zarni123","1234","zarni",0);
        customer = new Customer("pyae123","1234","pyae",owner);
        customer2 = new Customer("zone123","1234","zone",owner);

        allUsers = new AllUsers();
        allUsers.addOwner(owner);
        allUsers.addCustomer(customer);
    }
    @Test
    public void signinTest(){
        // signin with correct owner info
        assertTrue(Signin("zarni123","1234",allUsers));

        // signin with correct customer info
        assertTrue(Signin("pyae123","1234",allUsers));

        // owner signin with wrong password
        assertFalse(Signin("zarni123","1233",allUsers));

        // customer signin with wrong password
        assertFalse(Signin("pyae123","1233",allUsers));

        // invalid user sigin
        assertFalse(Signin("zarni","1234",allUsers));

        // appointment size
        Calendar cal = new GregorianCalendar(2019,5,15);
        owner.addAppointment(cal,customer);
        owner.addAppointment(cal,customer2);
        int size = owner.getAppointmentCustList().size();
        assertEquals(2,size);

    }
    /*
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    */
}