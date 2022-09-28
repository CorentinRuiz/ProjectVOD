package server;

import java.util.ArrayList;

public class Connection {
    ArrayList<Client> clientList;

    boolean signIn(String mail,String pwd){
        return true;
    }

    IVODService login(String mail,String pwd){
        return new VODService();
    }

}
