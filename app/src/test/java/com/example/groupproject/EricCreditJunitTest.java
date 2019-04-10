package com.example.groupproject;

import org.junit.Test;

import Model.AllUsers;
import Model.Customer;
import Model.Owner;

import static org.junit.Assert.assertEquals;

public class EricCreditJunitTest {

    CreditActivity creditActivity;


    public EricCreditJunitTest() {
        creditActivity = new CreditActivity();
    }

    //Test Owner SignUp method
    @Test
    public void OwnerCredits() {
        Owner owner = new Owner("yes","yes","yes",0);
        owner.addCredit(8540);
        assertEquals((double) 8540, owner.getCredit(),0);
        owner.subCredit(540);
        assertEquals((double) 8000, owner.getCredit(),0);
    }

    //Test Customer SignUp method
    @Test
    public void CustomerCredits() {
        Owner owner = new Owner("yes","yes","yes",0);
        Customer customer = new Customer("yes","yes","yes",owner);
        customer.addCredit(8888);
        assertEquals((double) 8888, customer.getCredit(),0);
        customer.sendCreditTo(owner,888);
        assertEquals((double) 888, owner.getCredit(),0);
    }
}
