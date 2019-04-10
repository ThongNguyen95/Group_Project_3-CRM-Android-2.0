package Controller;

import Model.AllUsers;
import Model.Customer;
import Model.Owner;



public class DeleteAccount {
    private AllUsers allUser;
    private String userName,passWord;
    private Owner owners = null;
    private Customer customer = null;

    public boolean DeleteAccount(String _userName,String _passWord, AllUsers _allUser){
    userName = _userName;
    passWord = _passWord;
    allUser = _allUser;

    owners = allUser.getOwnerBasedOnID(_userName);
    customer = allUser.getCustomerBasedOnID(_userName);

        if(owners != null && customer == null){
        if (owners.getID().equals(_userName) && owners.getPassword().equals(_passWord)){
            return true;
        }
        else {
            // wrong password;
            return false;
        }
    }
    // then check the customer list
        if(owners == null && customer != null){
        if (customer.getID().equals(_userName) && customer.getPassword().equals(_passWord)){

            return true;
        }
        else {
            // wrong password;
            return false;
        }
    }

    // return false if both of them are null
        return false;
}
}
