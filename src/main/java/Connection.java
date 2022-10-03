import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.Optional;

public class Connection extends UnicastRemoteObject implements IConnection {
    UserManager userManager;

    public Connection(int port,UserManager userManager) throws RemoteException {
        super(port);
        this.userManager = userManager;
    }

    public boolean signIn(String mail, String pwd) throws SignInFailed {
        if(userManager.getUsers().stream().anyMatch(m -> Objects.equals(m.getEmail(), mail))){
         throw new SignInFailed("This email is already used");
        }else{
            userManager.getUsers().add(new User(mail,pwd));
            userManager.saveUsers();
            return true;
        }
    }

    public IVODService login(String mail,String pwd) throws InvalidCredentialsException, RemoteException {
        Optional<User> user = userManager.getUsers().stream().filter(client -> Objects.equals(client.getEmail(), mail)).findAny();
        if(user.isEmpty()){
            throw new InvalidCredentialsException("The user doesn't exist");
        }
        else if(!Objects.equals(user.get().getPassword(), pwd)){
            System.out.println(user.get().getPassword());
            System.out.println(pwd);
            throw new InvalidCredentialsException("Wrong password");
        }
        else{
            return new VODService();
        }

    }

}
