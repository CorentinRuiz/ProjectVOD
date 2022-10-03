import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MovieDataManager {
    public static final String FILE_PATH = "/Users/corentinruiz/ProjectVOD/src/main/resources/data/movies.json";

    private List<Movie> movies;

    public MovieDataManager() {
        this.movies = new ArrayList<>();
    }

    public String getMovie(String isbn){
        try {
            Movie[] usersData = new ObjectMapper().readValue(new File(FILE_PATH), Movie[].class);
            this.movies = new ArrayList<>(Arrays.asList(usersData));
            return this.movies.stream().filter(movie -> Objects.equals(movie.getIsbn(), isbn)).findAny().get().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
