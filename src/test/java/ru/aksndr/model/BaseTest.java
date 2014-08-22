package ru.aksndr.model;

import org.junit.Test;
import retrofit.RestAdapter;
import ru.aksndr.servicelayer.ServiceApi;

import java.util.ArrayList;

import static junit.framework.Assert.assertNotNull;

/**
 * User: a.arzamastsev Date: 22.08.14 Time: 14:28
 */
public class BaseTest {
    private static final String SERVER = "http://localhost:8080";

    public static ServiceApi api = new RestAdapter.Builder()
            .setEndpoint(SERVER).build()
            .create(ServiceApi.class);

    public static Flat getFlatTest(String flatNum, String houseAddress) {
        House h = getHouseTest(houseAddress);
        assertNotNull(h);
        Flat f = new Flat();
        f.setFlatnum(flatNum);
        f.setHouse(h);
        return api.addFlat(f);
    }

    @Test
    public void getFlat() {
        Flat f = getFlatTest("1", "Жданова 7");
        assertNotNull(f);
    }

    @Test
    public void getHouse() {
        House h = getHouseTest("Жданова 7");
        assertNotNull(h);
    }

    @Test
    public void getFlatsListOfHouse() {
        House h = getHouseTest("Жданова 7");
        ArrayList<Flat> flatList = api.getHouseFlatsList(h.getId());
        assertNotNull(flatList);
    }


    public static House getHouseTest(String houseAddress) {
        House h = new House();
        h.setAddress(houseAddress);
        return api.addHouse(h);
    }

    public static User createUser(String login, String firstname, String lastname, String flatnum, String address) throws Exception {
        User user = User.create().withLogin(login).withFirstname(firstname).withLastname(lastname).build();
        user.setFlat(getFlatTest(flatnum, address));
        api.addUser(user);
        return user;
    }
}
