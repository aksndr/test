package ru.aksndr.model;

import org.junit.Test;
import retrofit.RestAdapter;
import ru.aksndr.servicelayer.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;

/**
 * User: a.arzamastsev Date: 22.08.14 Time: 14:28
 */
public class BaseTest {
    private static final String SERVER = "http://localhost:8080";

    public static ServiceApi api = new RestAdapter.Builder()
            .setEndpoint(SERVER).build()
            .create(ServiceApi.class);

    @Test
    public void addFlat() {
        Flat f = addFlatTest("16", "Жданова 7");
        assertNotNull(f);
    }

    @Test
    public void getHouse() {
        List<House> h = getHouseTest("Жданова");
        assertNotNull(h);
    }

    @Test
    public void getFlatsListOfHouse() {
        House h = getHouseTest("Жданова 7").get(0);
        ArrayList<Flat> flatList = api.getHouseFlatsList(h.getId());
        assertNotNull(flatList);
    }

    public static Flat addFlatTest(String flatNum, String houseAddress) {
        List<House> houses = getHouseTest(houseAddress);
        House h = houses.get(0);
        assertNotNull(h);
        return api.addFlat(h.getId(), flatNum);
    }

    public static List<House> getHouseTest(String houseAddress) {
        return api.getHouseByAddress(houseAddress);
    }

    public static User createUser(String login, String firstname, String lastname, String flatnum, String address) throws Exception {
        User user = User.create().withLogin(login).withFirstname(firstname).withLastname(lastname).build();
        user.setFlat(addFlatTest(flatnum, address));
        api.addUser(user);
        return user;
    }
}
