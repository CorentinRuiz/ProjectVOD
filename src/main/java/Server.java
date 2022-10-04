
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            UserManager userManager = new UserManager();
            userManager.retrieveUsers();
            IConnection connection = new Connection(10001,userManager);
            Registry reg= LocateRegistry.createRegistry(2001);
            reg.rebind("connectionVOD",connection);
        	System.out.println("Server launched successfully");
            while(true) {}
        }catch(RemoteException e){
            System.err.println(e);
        }
    }
}
