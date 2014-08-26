package ru.aksndr.model;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import retrofit.RestAdapter;
import ru.aksndr.servicelayer.ServiceApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * User: a.arzamastsev Date: 22.08.14 Time: 10:15
 */
public class CounterTest extends BaseTest {

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
        for (String record : testData) {
            String login = record.split(",")[0];
            Set<Counter> counterList = api.getUserCountersList(login);
            for (Counter counter : counterList) {
                Assert.assertNotNull(counter);
            }
        }
    }

    @Test
    public void getAllCounterTypes() {
        Set<String> types = api.getAllCounterTypes();
        Assert.assertTrue(types.size() != 0);
    }

    @Test
    public void addCounterTypes() {
        CounterType counterType = new CounterType();
        counterType.setTypename("Счётчик холодной воды в ванной");
        counterType = api.addCounterType(counterType);
        Assert.assertNotNull(counterType);
    }

    @Test
    public void getFlatsCounters() {
        Set<Counter> counterList = api.getCountersByFlatId(1L);
        Assert.assertNotNull(counterList);
    }


    @Test
    public void createCounterTest() {
        Set<String> types = api.getAllCounterTypes();
        for (String record : testData) {
            String login = record.split(",")[0];
            Long flatid = api.findFlatIdByLogin(login);
            for (String type : types) {
                Counter counter = api.getUserCounterByType(flatid, type);

                if (counter == null) {
                    Counter newCounter = new Counter();
                    newCounter.setSn(getRandomSn());
                    newCounter.setType(getCounterTypeByName(type));

                    Counter received = api.addCounter(flatid, newCounter);
                    Assert.assertNotNull(received.getFlat());
                }

            }
        }
    }

    private Long getRandomSn() {
        long LOWER_RANGE = 0; //assign lower range value
        long UPPER_RANGE = 1000000; //assign upper range value
        Random random = new Random();


        long randomValue = LOWER_RANGE +
                (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE));
        return randomValue;
    }

    @Test
    public void getRnd() {
        Integer value = getRandomValue();
        Assert.assertNotNull(value);
    }

    private Integer getRandomValue() {
        Integer LOWER_RANGE = 0;
        Integer UPPER_RANGE = 100;
        Random random = new Random();


//        Integer randomValue = LOWER_RANGE +
//                (Integer) (random.nextInt()- UPPER_RANGE);
        return (Integer) random.nextInt((UPPER_RANGE - LOWER_RANGE) + 1) + LOWER_RANGE;
    }

    private CounterType getCounterTypeByName(String typename) {
        return api.getCounterTypeByTypeName(typename);
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

    @Test
    public void createCounterRecordsTest() {
        Set<Counter> counters = api.getAllCounters();
        List<DateTime> dates = getPeriodCollection();
        Assert.assertNotNull(counters);

        for (Counter counter : counters) {
            for (DateTime dt : dates) {
                Record record = new Record();
                record.setCounter(counter);
                record.setDatetime(dt.toString("dd.MM.yyyy"));
                record.setValue(getRandomValue());
                Record retval = api.addRecord(record);
                Assert.assertNotNull(retval);

            }
        }


    }

    public CounterType getDummyCounterType() {
        CounterType counterType = new CounterType();
        counterType.setTypename("Счётчик воды");
        return api.addCounterType(counterType);
    }

    public List<DateTime> getPeriodCollection() {
        List<DateTime> monthList = new ArrayList<DateTime>(12);
        DateTime start = new DateTime(2014, 1, 25, 0, 0, 0, 0);
        DateTime end = new DateTime(2014, 12, 30, 0, 0, 0, 0);
        while (start.isBefore(end)) {
            monthList.add(start);
            start = start.plusMonths(1);
        }
        return monthList;
    }
}
