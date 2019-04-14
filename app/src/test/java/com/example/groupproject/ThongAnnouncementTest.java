package com.example.groupproject;

import org.junit.Test;

import Model.Customer;
import Model.Owner;

import static junit.framework.TestCase.assertEquals;

public class ThongAnnouncementTest {
    Owner owner;
    Customer customer_1;
    Customer customer_2;
    public ThongAnnouncementTest() {
        owner = new Owner("ownerID", "ownerPW", "Owner", 0);
        customer_1 = new Customer("custID_1", "custPW_1",
                "Customer_1", owner);
        customer_2 = new Customer("custID_2", "custPW_2",
                "Customer_2", owner);
    }

    // This function test the owner.setAnnouncement(), which is actually not a getter function
    // because it modifies the announcement variables of all the customers belong to the
    // same business. It helps to ensure the announcement is successfully sent to all customers.
    @Test
    public void ownerSetAnnouncementTest() {
        //Test all announcement status of both owner and customers before setting new announcement
        assertEquals("None", owner.getAnnouncement());
        assertEquals("None", customer_1.getAnnouncement());
        assertEquals("None", customer_2.getAnnouncement());
        //Now call the function to set the announcement and send them to all customers
        owner.setAnnouncement("This is Announcement #1");
        //Test if all the business customers have received the announcement
        assertEquals("This is Announcement #1", owner.getAnnouncement());
        assertEquals("This is Announcement #1", customer_1.getAnnouncement());
        assertEquals("This is Announcement #1", customer_2.getAnnouncement());
    }
}
