import cz.tul.client.ServerApi;
import cz.tul.data.User;
import org.junit.Test;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

import java.util.List;

public class DataTest {

    private final String TEST_URL = "http://localhost:8080";

    private ServerApi insecureService = new RestAdapter.Builder()
            .setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build().create(ServerApi.class);

    @Test
    public void getUsers() throws Exception {

        List<User> users = insecureService.showUsers();

        System.out.println(users);

    }
}


