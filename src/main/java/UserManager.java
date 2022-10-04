import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


public class UserManager {
    public static final String FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/data/users.json";

    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void saveUsers() {
        try {
            new ObjectMapper().writeValue(new File(FILE_PATH),users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void retrieveUsers(){
        try {
            User[] usersData = new ObjectMapper().readValue(new File(FILE_PATH), User[].class);
            this.users = new ArrayList<>(Arrays.asList(usersData));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
