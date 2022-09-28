import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserManager {
    public static final String FILE_PATH = "/Users/corentinruiz/ProjectVOD/src/main/resources/data/users.json";

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