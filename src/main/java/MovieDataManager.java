import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieDataManager {
    public static final String FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/data/movies.json";

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
