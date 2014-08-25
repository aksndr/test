package ru.aksndr.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by aksndr on 25.08.14.
 */
public class HouseTest extends BaseTest {
    @Test
    public void getFlatsListOfHouse() {
        House h = getHouseTest("Жданова 9").get(0);
        ArrayList<Flat> flatList = api.getHouseFlatsList(h.getId());
        assertNotNull(flatList);
    }

    @Test
    public void addFlat() {
        Flat f = addFlatTest("16", "Жданова 7");
        assertNotNull(f);
    }

    @Test
    public void getAddresses() {
        List<String> addresses = api.getHousesAddresses("Жданова");
        assertNotNull(addresses);
    }

    @Test
    public void addHouse() {
        House h = new House();
        h.setAddress("Жданова 9");
        House retVal = api.addHouse(h);
        assertNotNull(retVal);
    }
}
