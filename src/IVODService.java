import java.util.List;

public interface IVODService {
    List<MovieDesc> viewCatalog();

    Bill playmovie(String isbn, IClientBox box);
}
