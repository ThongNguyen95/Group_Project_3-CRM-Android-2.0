/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Eric Gatpandan
 */
public class Customer implements Serializable {
    private String id;
    private String password;
    private String customerName;
    private String SecurityQuiz;
    private String SecurityAnswer;
    private Owner business;
    private double credit;
    private ArrayList<Message> msgBox;
    private String announcement;

    public Customer(String myid, String mypassword, String mycustomerName, Owner _bus, String secuQuiz, String secuAns)
    {
        id = myid;
        password = mypassword;
        customerName = mycustomerName;
        business = _bus;
        SecurityQuiz = secuQuiz;
        SecurityAnswer = secuAns;
        credit = 0;
        msgBox = new ArrayList<>();
        announcement = "None";
        business.addCustomer(this);
    }

    public String getAnnouncement() {
        return announcement;
    }
    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public void setID(String myid)
    {
    id = myid;
    }
    public void setPassword(String mypassword)
    {
    password = mypassword;
    }

     public String getID()
    {
        return id;
    }
    public String getPassword()
    {
        return password;
    }

    public String getCustomerName()
    {
        return customerName;
    }
    public String getSecurityQuiz(){return SecurityQuiz;}
    public String getSecurityAnswer() {return SecurityAnswer;}
    public void setBusiness(Owner _bus)
    {
        business = _bus;
    }
    public Owner getBus()
    {
        return business;
    }

    public void setCredit(double num)
    {
        credit = num;
    }
    public void addCredit(double num)
    {
        credit += num;
    }
    public void subCredit(double num)
    {
        credit -= num;
    }
    public double getCredit()
    {
        return credit;
    }
        public void sendMessage(String subject, String content) {
        business.receiveMessage(new Message(customerName, subject, content));
    }

    public void sendCreditTo(Owner own,double num)
    {
        own.addCredit(num);
        this.subCredit(num);
    }
    public void receiveMessage(Message msg) {
        msgBox.add(msg);
    }
    
    public ArrayList<Message> getMsgBox() {
        return msgBox;
    }
    
    public void messageAlert() {
        int count = 0;
        for (Message msg : msgBox) {
            if (!msg.isViewed()) count++;
        }
        System.out.println("You have " + count + "unread messages!");
    }
}

 
