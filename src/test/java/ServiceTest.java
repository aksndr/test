import org.junit.Assert;
import org.junit.Test;
import retrofit.RestAdapter;
import ru.aksndr.model.Flat;
import ru.aksndr.model.House;
import ru.aksndr.model.User;
import ru.aksndr.model.UserTest;
import ru.aksndr.servicelayer.ServiceApi;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by aksndr on 11.08.14.
 */
public class ServiceTest {

    private static final String SERVER = "http://localhost:8080";

    User user = new User(UserTest.FIRSTNAME, UserTest.FIRSTNAME, UserTest.LASTNAME);

    private ServiceApi api = new RestAdapter.Builder()
            .setEndpoint(SERVER).build()
            .create(ServiceApi.class);

    @Test
    public void testAddUser() throws Exception {
        User received = api.addUser(user);
        assertEquals(user.getFirstname(), UserTest.FIRSTNAME);
        assertEquals(user.getLastname(), UserTest.LASTNAME);
        assertTrue(received.getId() > 0);
    }

    @Test
    public void addNewUser() throws Exception {
        Flat flat = new Flat();
        flat.setFlatnum("176");

        House house = new House();
        house.setAddress("Жданова 9");

        User u = new User("Наталья", "Наталья", "Арзамасцева");

        User received = api.addNewUser(u, flat, house);

        Assert.assertNotNull(received.getFlat());

    }

    @Test
    public void getUsersList() throws Exception {
        ArrayList<User> users = api.getUsersList();
        assertNotNull(users);
    }

    @Test
    public void getUserById() throws Exception {
        User received = api.addUser(user);
        User user = api.getUser(received.getId());
        assertEquals(received, user);
    }

}
