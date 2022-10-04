import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class VODService extends UnicastRemoteObject implements IVODService {

    public static final List<MovieDesc> movies = List.of(new MovieDesc("Charlie & la chocolatrie","2070628523","Friand de sucreries, il décide de participer à un concours organisé par l'intimidant Willy Wonka, à la tête de la chocolaterie de la ville.",33),
            new MovieDesc("Harry Potter 1","0747532699","Harry Potter, un jeune orphelin, est élevé par son oncle et sa tante qui le détestent. ", 10),
            new MovieDesc("Kaamelott","9781234567897","Le tyrannique Lancelot du Lac et ses mercenaires saxons font régner la terreur sur le royaume de Logres.",20));

    protected VODService() throws RemoteException {
    }

    public List<MovieDesc> viewCatalog() {
        return movies;
    }

    public Bill playmovie(String isbn, IClientBox box) throws IOException, NotBoundException {
        MovieDesc movieDescription = movies.stream().filter(movieDesc -> Objects.equals(movieDesc.getIsbn(), isbn)).findAny().get();
        MovieDataManager manager = new MovieDataManager();

        String movie = manager.getMovie(isbn);
        //byte[] allContent = Files.readAllBytes(Paths.get(movie));
        byte[] allContent = movie.getBytes();
        byte[] content = Arrays.copyOfRange(allContent,0,5);
        box.stream(content);

        new Thread("Stream Thread") {
            public void run(){
                for(int i = 5; i < allContent.length; i+=5){

                   byte[] content = i+5 >= allContent.length
                           ? Arrays.copyOfRange(allContent,i,allContent.length)
                           : Arrays.copyOfRange(allContent,i,i+5) ;
                    try {
                        box.stream(content);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
        return new Bill(movieDescription.getIsbn(),movieDescription.getPrice());
    }
}
