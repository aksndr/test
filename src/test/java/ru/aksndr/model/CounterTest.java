package ru.aksndr.model;

import org.junit.Assert;
import org.junit.Test;
import retrofit.RestAdapter;
import ru.aksndr.servicelayer.ServiceApi;

import java.util.Set;

/**
 * User: a.arzamastsev Date: 22.08.14 Time: 10:15
 */
public class CounterTest {

    private static final String SERVER = "http://localhost:8080";

    private ServiceApi api = new RestAdapter.Builder()
            .setEndpoint(SERVER).build()
            .create(ServiceApi.class);

    @Test
    public void getCounterTest() {
        Counter counter = api.getCounter(446L);
        Assert.assertNotNull(counter.getFlat());
    }


    @Test
    public void getUserCountersListTest() {
        Set<Counter> counterList = api.getUserCountersList("");   //TODO
        Assert.assertNotNull(counterList);
    }

    @Test
    public void createCounterTest() {

        Counter counter = new Counter();
        counter.setSn(446L);
        counter.setDescr("Горячая в ванной");
        counter.setType(getDummyCounterType());

        Counter received = api.addCounter(3L, counter);
        Assert.assertNotNull(received.getFlat());

    }

    @Test
    public void createCounterNullFlatTest() {
        Counter counter = new Counter();
        counter.setSn(123L);
        counter.setDescr("В ванной");
        counter.setType(getDummyCounterType());

        Counter received = api.addCounter(12341234L, counter);
        Assert.assertNull(received);

    }

    public CounterType getDummyCounterType() {
        CounterType counterType = new CounterType();
        counterType.setTypeName("Счётчик воды");
        return api.addCounterType(counterType);
    }
}
