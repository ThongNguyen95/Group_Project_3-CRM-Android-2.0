package com.example.groupproject;

import org.junit.Test;

import Model.AllUsers;
import Model.Customer;
import Model.Owner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MyoZawTest {

    AllUsers allUsers;
    Owner owner,owner2;
    Customer customer,customer1;

    @Test
    public void ChangePasswordtest(){
        allUsers = new AllUsers();
        owner = new Owner("1","1","1",1000,"Ques", "Ans");
        customer = new Customer("1","1","1",owner,"Ques", "Ans");
        String npassword = "hello";
        allUsers.addCustomer(customer);
        allUsers.addOwner(owner);
        boolean val = allUsers.ChangeOpass("1", "1",npassword);
        assertTrue(val);
    }

    @Test
    public void ChangeCPasswordTest(){
        allUsers = new AllUsers();
        owner = new Owner("1","1","1",1000,"Ques", "Ans");
        customer = new Customer("1","1","1",owner,"Ques", "Ans");
        String npassword = "hello";
        allUsers.addCustomer(customer);
        allUsers.addOwner(owner);
        boolean val = allUsers.ChangeCpass("1", "1",npassword);
        assertTrue(val);
    }

    @Test
    public void DeleteOAccountTest(){
        allUsers = new AllUsers();
        owner = new Owner("1","1","1",1000,"Ques", "Ans");
        owner2 = new Owner("2","2","2",1000,"Ques", "Ans");
        allUsers.addOwner(owner);
        allUsers.addOwner(owner2);
        allUsers.DeleteOaccount("1", "1");
        assertEquals(1,allUsers.getOwnerSize());
    }

    @Test
    public void DeleteCAccountTest(){
        allUsers = new AllUsers();
        owner = new Owner("1","1","1",1000,"Ques", "Ans");
        allUsers.addOwner(owner);
        customer = new Customer("1","1","1",owner,"Ques", "Ans");
        customer = new Customer("2","2","2",owner,"Ques", "Ans");
        allUsers.addCustomer(customer);
        allUsers.addCustomer(customer1);
        allUsers.DeleteCaccount("2", "2");
        assertEquals(1,allUsers.getCustomerSize());
    }

    @Test
    public void ForgotOwnerPasswordTest(){
        allUsers = new AllUsers();
        owner = new Owner("1","1","1",1000,"Ques", "Ans");
        customer = new Customer("1","1","1",owner,"Ques", "Ans");
        allUsers.addCustomer(customer);
        allUsers.addOwner(owner);
        boolean val = allUsers.GetOwnerPassword("1","Ans");
        assertTrue(val);
        if(val==true)
        {
            boolean val2 = allUsers.SetOwnerNewPassword("new","new","1");
            assertTrue(val2);
        }
    }

    @Test
    public void ForgotCustomerPasswordTest(){
        allUsers = new AllUsers();
        owner = new Owner("1","1","1",1000,"Ques", "Ans");
        customer = new Customer("1","1","1",owner,"Ques", "Ans");
        allUsers.addCustomer(customer);
        allUsers.addOwner(owner);
        boolean val = allUsers.GetCustomerPassword("1","Ans");
        assertTrue(val);
        if(val==true)
        {
            boolean val2 = allUsers.SetCustomerNewPassword("new","new","1");
            assertTrue(val2);
        }
    }

    @Test
    public void WrongQuizTest(){
        allUsers = new AllUsers();
        owner = new Owner("1","1","1",1000,"Ques", "Ans");
        customer = new Customer("1","1","1",owner,"Ques", "Ans");
        allUsers.addCustomer(customer);
        allUsers.addOwner(owner);
        boolean val = allUsers.GetCustomerPassword("1","Anss");
        assertFalse(val);
    }


}

