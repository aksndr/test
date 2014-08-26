package ru.aksndr.servicelayer;

/**
 * Created by aksndr on 11.08.14.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.aksndr.datalayer.*;
import ru.aksndr.model.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Controller
public class Service {

    @Qualifier("usersRepository")
    @Autowired
    UsersRepository usersRepository;

    @Qualifier("flatsRepository")
    @Autowired
    FlatsRepository flatsRepository;

    @Qualifier("houseRepository")
    @Autowired
    HouseRepository houseRepository;

    @Qualifier("countersRepository")
    @Autowired
    CountersRepository countersRepository;

    @Qualifier("counterTypesRepository")
    @Autowired
    CounterTypesRepository counterTypesRepository;

    @Qualifier("recordsRepository")
    @Autowired
    RecordsRepository recordsRepository;

    @RequestMapping(value = ServiceApi.USERS_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> getUsersList(HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        return usersRepository.findAll();
    }

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

    @RequestMapping(value = ServiceApi.GET_HOUSE_PATH, method = RequestMethod.POST)
    @ResponseBody
    public List<House> getHouseByAddress(@RequestParam("address") String address) {
        return houseRepository.findByAddress(address);
    }

    @RequestMapping(value = ServiceApi.GET_ADDRESSES, method = RequestMethod.POST)
    @ResponseBody
    public List<String> getHousesAddresses(@RequestParam("address") String address) {
        return houseRepository.findLikeAddress(address);
    }

    public House findHouseByAddress(String address) {
        List<House> houseList = houseRepository.findByAddress(address);
        if (houseList.size() == 0) {
            return houseRepository.save(new House(address));
        } else if (houseList.size() == 1) {
            return houseList.get(0);
        }
//        response.setHeader("Error", "Found more than one house on these address.");
        return null;
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

    @RequestMapping(value = ServiceApi.GET_FLAT_PATH, method = RequestMethod.POST)
    @ResponseBody
    public Long findFlatIdByLogin(@RequestParam("login") String login) {
        return usersRepository.findFlatidByLogin(login);
    }

    @RequestMapping(value = ServiceApi.GET_FLATS_PATH, method = RequestMethod.GET)
    @ResponseBody
    public List<Flat> getHouseFlatsList(@PathVariable("houseId") Long houseId, HttpServletResponse response) {
        House h = houseRepository.findOne(houseId);
        return flatsRepository.findByHouseid(h.getId());
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

    @RequestMapping(value = ServiceApi.GET_COUNTERTYPES_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Set<String> getAllCounterTypes() {
        return counterTypesRepository.getAllCounterTypes();
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

    @RequestMapping(value = ServiceApi.GET_COUNTER_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Counter getCounter(@PathVariable("sn") long sn) {
        return countersRepository.getCounterBySn(sn);
    }

    @RequestMapping(value = ServiceApi.USER_PATH, method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("id") long id, HttpServletResponse response) {
        User user = usersRepository.findOne(id);
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return user;
    }

    /*вернуть список счётчиков для данного пользователя.
    Возвращает sn, тип, описание
    берём id квартиры по логину, по id квартиры получаем список счётчиков,
     сразу с типом (заджойнить в репозитории)
     */
    @RequestMapping(value = ServiceApi.USERS_COUNTERS_PATH, method = RequestMethod.POST)
    @ResponseBody
    public Set<Counter> getUserCountersList(@RequestParam("login") String login, HttpServletResponse response) {
        User user = usersRepository.findByLogin(login);
        if (user == null) {
            response.setHeader("Error", "Not found user with this login: " + login);
            return null;
        }
        Flat flat = user.getFlat();
        if (flat == null) {
            response.setHeader("Error", "Not found flat for this user: " + login);
            return null;
        }
        return countersRepository.getCountersByFlatId(flat.getId());
    }

    @RequestMapping(value = ServiceApi.GET_COUNTERS_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Set<Counter> getCountersByFlatId(@PathVariable("flatId") Long flatid) {
        return countersRepository.getCountersByFlatId(flatid);
    }

    @RequestMapping(value = ServiceApi.GET_COUNTERS_PATH, method = RequestMethod.POST)
    @ResponseBody
    public Counter getUserCounterByType(@PathVariable("flatId") Long flatid, @RequestParam("type") String type) {
        return countersRepository.getCountersByFlatIdAndType(flatid, type);
    }

    @RequestMapping(value = ServiceApi.GET_COUNTERTYPES_PATH, method = RequestMethod.POST)
    @ResponseBody
    CounterType getCounterType(@RequestParam("typename") String typename) {
        return counterTypesRepository.findByTypeName(typename);
    }

    @RequestMapping(value = ServiceApi.GET_ALL_COUNTERS_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Counter> getAllCounters() {
        return countersRepository.findAll();
    }

    @RequestMapping(value = ServiceApi.ADD_RECORD_PATH, method = RequestMethod.POST)
    @ResponseBody
    public Record addRecord(@RequestBody Record record) {
//        Record r = recordsRepository.findByCounterAndDate(record.getCounter(),record.getDatetime());
//        if (r==null)
        return recordsRepository.save(record);
//        return r;
    }


    //вернуть показания определённого счётчика данной квартиры за указанный период снб тип, описание, значение, дата замера

    //вернуть показания всех счётчиков данной квартиры за указанный период то же самое что и выше, только для всех счётчиков
    //вернуть список квартир данного дома с показателями за период, с фильтром по счётчикам

}
