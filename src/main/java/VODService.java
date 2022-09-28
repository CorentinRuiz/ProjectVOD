import java.util.List;

public class VODService implements IVODService {

    public static final List<MovieDesc> movies = List.of(new MovieDesc("Charlie & la chocolatrie","2070628523","Friand de sucreries, il décide de participer à un concours organisé par l'intimidant Willy Wonka, à la tête de la chocolaterie de la ville.",33),
            new MovieDesc("Harry Potter 1","0747532699","Harry Potter, un jeune orphelin, est élevé par son oncle et sa tante qui le détestent. ", 10),
            new MovieDesc("Kaamelott","9781234567897","Le tyrannique Lancelot du Lac et ses mercenaires saxons font régner la terreur sur le royaume de Logres.",20));

    public List<MovieDesc> viewCatalog() {
        return movies;
    }

    public Bill playmovie(String isbn, IClientBox box) {
        MovieDesc movie = movies.stream().filter(movieDesc -> movieDesc.getIsbn() == isbn).findAny().get();
        return new Bill(movie.getIsbn(),movie.getPrice());
    }
}
