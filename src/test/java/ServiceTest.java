import org.junit.Assert;
import org.junit.Test;
import retrofit.RestAdapter;
import ru.aksndr.model.Flat;
import ru.aksndr.model.House;
import ru.aksndr.model.User;
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

    User user = User.create()
            .withFirstname(UserTest.FIRSTNAME)
            .withLastname(UserTest.LASTNAME)
            .build();

    private ServiceApi api = new RestAdapter.Builder()
            .setEndpoint(SERVER).build()
            .create(ServiceApi.class);

    @Test
    public void testAddUser() throws Exception {
        User received = api.addUser(user);
        assertEquals(user.getFirstname(), UserTest.FIRSTNAME);
        assertEquals(user.getLastname(), UserTest.LASTNAME);
        assertEquals(user.getFlatid(), UserTest.FLATID);
        assertTrue(received.getId() > 0);
    }

    @Test
    public void addFlat() throws Exception {
        Flat flat = new Flat();
        flat.setFlatnum("176");
        flat.setHouseid(9L);
        Flat received = api.addFlat(flat);
        assertTrue(received.getId() > 0);
    }


    @Test
    public void addNewUser() throws Exception {
        Flat flat = new Flat();
        flat.setFlatnum("176");

        House house = new House();
        house.setAddress("Жданова 9");

        User u = User.create()
                .withFirstname("Александр")
                .withLastname("Арзамасцев").build();

        User received = api.addNewUser(u, flat, house);

        Assert.assertNotNull(received.getFlatid());

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
