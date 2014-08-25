package ru.aksndr.model;

import org.junit.Before;
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
    public List<String> testData;

    @Before
    public void initTestData() {
        testData = new ArrayList<>();
        testData.add("i.ivanov,Ivan,Ivanov,1,Жданова 9");
        testData.add("a.ivanova,Anna,Ivanova,1,Жданова 9");
        testData.add("a.arzamastsev,Александр,Арзамасцев,176,Жданова 9");
        testData.add("p.petrov,Пётр,Петров,25,Жданова 9");
    }

    public static ServiceApi api = new RestAdapter.Builder()
            .setEndpoint(SERVER).build()
            .create(ServiceApi.class);

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
        User user = new User(login, firstname, lastname);
        Flat f = addFlatTest(flatnum, address);
        user.setFlat(f);
        user = api.addUser(user);
        return user;
    }
}
