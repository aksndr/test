package ru.aksndr.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.aksndr.model.*;
import ru.aksndr.servicelayer.ServiceApi;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by aksndr on 06.09.14.
 */
@Component
public class CommandController extends BaseController {

    @RequestMapping(value = ServiceApi.ADD_USER_PATH, method = RequestMethod.POST)
    @ResponseBody
    public User addUser(@RequestBody User u) {
        return usersRepository.save(u);
    }

    @RequestMapping(value = ServiceApi.ADD_HOUSE_PATH, method = RequestMethod.POST)
    @ResponseBody
    public House addHouse(@RequestParam("address") String address, HttpServletResponse response) {
        House house = findHouseByAddress(address);
        if (house != null) return house;
        return houseRepository.save(new House(address));
    }

    @RequestMapping(value = ServiceApi.ADD_NEW_USER_PATH, method = RequestMethod.POST)
    @ResponseBody
    public User addNewUser(@RequestPart User u, @RequestPart Flat f, @RequestPart House h, HttpServletResponse response) {
        House house = findHouseByAddress(h.getAddress());
        f.setHouse(house);
        flatsRepository.save(f);
        u.setFlat(f);
        return usersRepository.save(u);
    }

    @RequestMapping(value = ServiceApi.ADD_FLAT_TO_HOUSE_PATH, method = RequestMethod.POST)
    @ResponseBody
    public Flat addFlat(@RequestParam("houseId") Long houseId, @RequestParam String flatnum, HttpServletResponse response) {
        House h = houseRepository.findOne(houseId);
        List<Flat> flatList = flatsRepository.findByFlatNumAndAddress(flatnum, h.getAddress());
        if (flatList.size() == 0) {
            Flat f = new Flat();
            f.setHouse(h);
            f.setFlatnum(flatnum);
            return flatsRepository.save(f);
        } else if (flatList.size() == 1) {
            return flatList.get(0);
        }
        response.setHeader("Error", "Found " + flatList.size() + " for the flat number: " + flatnum + " at address: " +
                h.getAddress());
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }

    @RequestMapping(value = ServiceApi.ADD_COUNTERTYPE_PATH, method = RequestMethod.POST)
    @ResponseBody
    public CounterType addCounterType(@RequestBody CounterType ct) {
        if (ct.getId() == null) {
            CounterType counterType = counterTypesRepository.findByTypeName(ct.getTypename());
            if (counterType != null) return counterType;
        }
        return counterTypesRepository.save(ct);
    }

    @RequestMapping(value = ServiceApi.ADD_COUNTER_PATH, method = RequestMethod.POST)
    @ResponseBody
    public Counter addCounter(@PathVariable("flatId") long flatId, @RequestBody Counter c, HttpServletResponse response) {
        Flat f = flatsRepository.findOne(flatId);
        if (f == null) {
            response.setHeader("Error", "Not found flat with this id: " + flatId);
            return null;
        }
        c.setFlat(f);
        return countersRepository.save(c);
    }

    @RequestMapping(value = ServiceApi.GET_COUNTERTYPES_PATH, method = RequestMethod.POST)
    @ResponseBody
    CounterType getCounterType(@RequestParam("typename") String typename) {
        return counterTypesRepository.findByTypeName(typename);
    }

    @RequestMapping(value = ServiceApi.ADD_RECORD_PATH, method = RequestMethod.POST)
    @ResponseBody
    public Record addRecord(@RequestBody Record record) {
//        Record r = recordsRepository.findByCounterAndDate(record.getCounter(),record.getRecdate());
//        if (r==null)
        return recordsRepository.save(record);
//        return r;
    }

    //вернуть показания всех счётчиков данной квартиры за указанный период то же самое что и выше, только для всех счётчиков
    //вернуть список квартир данного дома с показателями за период, с фильтром по счётчикам
}
