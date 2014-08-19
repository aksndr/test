import org.junit.Test;
import retrofit.RestAdapter;
import ru.aksndr.model.User;
import ru.aksndr.servicelayer.UserServiceApi;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by aksndr on 11.08.14.
 */
public class ServiceTest {

    private static final String SERVER = "http://localhost:8080";

    User user = User.create()
            .withFirstname(UserTest.FIRSTNAME)
            .withLastname(UserTest.LASTNAME)
            .withAge(UserTest.AGE).build();

    private UserServiceApi userSvc = new RestAdapter.Builder()
            .setEndpoint(SERVER).build()
            .create(UserServiceApi.class);

    @Test
    public void testAddUser() throws Exception {
        User received = userSvc.addUser(user);
        assertEquals(user.getFirstname(), UserTest.FIRSTNAME);
        assertEquals(user.getLastname(), UserTest.LASTNAME);
        assertEquals(user.getAge(), UserTest.AGE);
        assertTrue(received.getId() > 0);
    }

    @Test
    public void getUsersList() throws Exception {
        ArrayList<User> users = userSvc.getUsersList();
        assertNotNull(users);
    }

    @Test
    public void getUserById() throws Exception {
        User received = userSvc.addUser(user);
        User user = userSvc.getUser(received.getId());
        assertEquals(received, user);
    }

}
