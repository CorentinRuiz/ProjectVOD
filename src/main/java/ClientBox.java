import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientBox extends UnicastRemoteObject implements Serializable,IClientBox {
    protected ClientBox() throws RemoteException {
    }

    @Override
    public void stream(byte[] chunck) throws RemoteException {
        for (byte b : chunck) {
            System.out.println(b);
        }
    }
}
