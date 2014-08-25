package ru.aksndr.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aksndr on 11.08.14.
 */
public class UserTest extends BaseTest {

    public final static String FIRSTNAME = "Ivan";
    public final static String LASTNAME = "Ivanov";
    public final static String ADDR = "Жданова 9";
    public final static Long FLATID = 32L;

    @Test
    public void findFlatIdByLogin() {
        Long flatid = api.findFlatIdByLogin("a.arzamastsev");
        assertNotNull(flatid);
    }

    @Test
    public void createUserTest() throws Exception {
        for (String record : testData) {
            String[] r = record.split(",");

            User u = createUser(r[0], r[1], r[2], r[3], r[4]);

            assertEquals(u.getLogin(), r[0]);
            assertEquals(u.getFirstname(), r[1]);
            assertEquals(u.getLastname(), r[2]);
            assertEquals(u.getFlat().getHouse().getAddress(), r[4]);
            assertTrue(u.getId() != 0);

        }
    }

    @Test
    public void getUserTest() {
        User u = api.getUser(2L);
        assertNotNull(u);
    }


}

