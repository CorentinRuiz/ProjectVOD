import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;

import java.util.Objects;
import java.util.Optional;

public class Connection implements IConnection {
    UserManager userManager;

    public Connection(UserManager userManager) {
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

    public IVODService login(String mail,String pwd) throws InvalidCredentialsException {
        Optional<User> user = userManager.getUsers().stream().filter(client -> Objects.equals(client.getEmail(), mail)).findAny();
        if(user.isEmpty()){
            throw new InvalidCredentialsException("The user doesn't exist");
        }
        else if(user.get().getPassword() != pwd){
            throw new InvalidCredentialsException("Wrong password");
        }
        else{
            return new VODService();
        }

    }

}
