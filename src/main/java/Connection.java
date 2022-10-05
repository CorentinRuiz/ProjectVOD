import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.Optional;

import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;

public class Connection extends UnicastRemoteObject implements IConnection {
    UserManager userManager;

    private static final String mpCryptoPassword = "PasswordSalt";

    public Connection(int port,UserManager userManager) throws RemoteException {
        super(port);
        this.userManager = userManager;
    }

    public synchronized boolean signIn(String mail, String pwd) throws SignInFailed {
        if(userManager.getUsers().stream().anyMatch(m -> Objects.equals(m.getEmail(), mail))){
         throw new SignInFailed("This email is already used");
        }else{
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(mpCryptoPassword);
            String encryptedPassword = encryptor.encrypt(pwd);
            userManager.getUsers().add(new User(mail,encryptedPassword));
            userManager.saveUsers();
            return true;
        }
    }

    public IVODService login(String mail,String pwd) throws InvalidCredentialsException, RemoteException {
        Optional<User> user = userManager.getUsers().stream().filter(client -> Objects.equals(client.getEmail(), mail)).findAny();
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(mpCryptoPassword);
        if(user.isEmpty()){
            throw new InvalidCredentialsException("The user doesn't exist");
        }
        else if(!Objects.equals(decryptor.decrypt(user.get().getPassword()), pwd)){
            throw new InvalidCredentialsException("Wrong password");
        }
        else{
            return new VODService();
        }

    }

}
