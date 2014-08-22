package ru.aksndr.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aksndr on 11.08.14.
 */
public class UserTest extends BaseTest {

    public final static String FIRSTNAME = "Ivan";
    public final static String LASTNAME = "Ivanov";
    public final static String ADDR = "Жданова 9";
    public final static Long FLATID = 32L;

    public List<String> testData;

    @Before
    public void initTestData() {
        testData = new ArrayList<>();
        testData.add("i.ivanov,Ivan,Ivanov,1,Жданова 9");
        testData.add("a.ivanova,Anna,Ivanova,1,Жданова 9");
        testData.add("a.arzamastsev,Александр,Арзамасцев,176,Жданова 9");
        testData.add("p.petrov,Пётр,Петров,25,Жданова 9");
    }

    @Test
    public void createUserTest() throws Exception {
        for (String record : testData) {
            String[] r = record.split(",");

            User u = createUser(r[0], r[1], r[2], r[3], r[4]);

            assertEquals(u.getLogin(), r[0]);
            assertEquals(u.getFirstname(), r[1]);
            assertEquals(u.getLastname(), r[2]);
            assertEquals(u.getFlat().getFlatnum(), r[3]);
            assertEquals(u.getFlat().getHouse().getAddress(), r[4]);
            assertTrue(u.getId() == 0);

        }


    }
}

