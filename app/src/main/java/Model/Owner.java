/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Eric Gatpandan
 */
public class Owner implements Serializable {
    private String id;
    private String password;
    private String companyName;
    private String SecurityQuiz;
    private String SecurityAnswer;
    private double credit;
    private final ArrayList<Customer> customers; //customer list from
    private ArrayList<Message> msgBox;
    
    private  ArrayList<Calendar> cal;
    private List<Customer> appCust;     //For appointment

    private String announcement;

    //Display values
    private boolean dName;
    private boolean dCredit;

    public Owner(String myid, String mypass, String mycomp,double mycredit, String SecuQuiz, String SecuAns)
    {
        id = myid;
        password = mypass;
        companyName = mycomp;
        credit = mycredit;
        SecurityQuiz= SecuQuiz;
        SecurityAnswer= SecuAns;
        customers = new ArrayList<>();
        msgBox = new ArrayList<>();
        
        cal = new ArrayList<>();
        appCust = new ArrayList<>();
        dName = true;
        dCredit = true;

        announcement = "None";
    }

    //Getter and Setter for announcement
    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
        //set announcement for all customers
        for (Customer cust : customers) {
            cust.setAnnouncement(announcement);
        }
    }

    //Getter setter for display toggle values
    public boolean getdName() {return dName;}
    public void setdName(boolean _val) {dName = _val;}
    public boolean getdCredit() {return dCredit;}
    public void setdCredit(boolean _val) {dCredit = _val;}
    public String getSecurityQuiz(){return SecurityQuiz;}
    public String getSecurityAnswer() {return SecurityAnswer;}

    public void viewCustomers()
    {
        System.out.println("Customers: ");
        for(int c = 0; c < customers.size(); c++)
        {
            System.out.println(c + ": " + customers.get(c).getCustomerName());
        }
    }
    /*public Customer selectCustomer()
    {
        viewCustomers();
        System.out.println("Select a customer: ");
        int input = STDIN.nextInt();
        return customers.get(input);
    }*/
    public void addAppointment(Calendar _cal,Customer _cust){
        cal.add(_cal);
        appCust.add(_cust);
    }
    public void removeAppointment(Calendar _cal,Customer _cust){
        cal.remove(_cal);
        appCust.remove(_cust);
    }
    public int getaptssize () {
        return appCust.size();
    }
    public ArrayList<Calendar> getAppointmentDateList()
    {
        return cal;
    }
    public List<Customer> getAppointmentCustList()
    {
        return appCust;
    }
    public void addCustomer(Customer cust)
    {
        customers.add(cust);
    }
    public void setID(String myid)
    {
        id = myid;
    }
    public void setPassword(String mypassword)
    {
        password = mypassword;
    }
    public void setCompanyName(String myCompany)
    {
        companyName = myCompany;
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
    public void sendCreditTo(Customer cust, double num)
    {
        cust.addCredit(num);
        this.subCredit(num);
    }
    public String getID()
    {
        return id;
    }
    public String getPassword()
    {
        return password;
    }
    public String getCompanyName()
    {
        return companyName;
    }
    
        public ArrayList<Customer> getCustomerList() {
        return customers;
    }
    
    public void sendMessage(Customer cust, String subject, String content) {
        cust.receiveMessage(new Message(companyName, subject, content));
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
        System.out.println("You have " + count + " unread messages!");
    }
    
}
