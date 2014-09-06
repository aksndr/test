package ru.aksndr.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.aksndr.model.*;
import ru.aksndr.servicelayer.ServiceApi;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by aksndr on 06.09.14.
 */
@Component
public class QueryController extends BaseController {

    //получить список всех пользователей
    @RequestMapping(value = ServiceApi.USERS_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> getUsersList(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return usersRepository.findAll();
    }

    //получить список домов по адресу
    @RequestMapping(value = ServiceApi.GET_HOUSE_PATH, method = RequestMethod.GET)
    @ResponseBody
    public List<House> getHouseByAddress(@RequestParam("address") String address) {
        return houseRepository.findByAddress(address);
    }

    //получить список адресов по маске
    @RequestMapping(value = ServiceApi.GET_ADDRESSES, method = RequestMethod.GET)
    @ResponseBody
    public List<String> getHousesAddresses(@RequestParam("address") String address) {
        return houseRepository.findLikeAddress(address);
    }

    //получить список квартир дома
    @RequestMapping(value = ServiceApi.GET_FLATS_PATH, method = RequestMethod.GET)
    @ResponseBody
    public List<Flat> getHouseFlatsList(@PathVariable("houseId") Long houseId) {
        House h = houseRepository.findOne(houseId);
        return flatsRepository.findByHouseid(h.getId());
    }

    //получить id квартиры по логину
    @RequestMapping(value = ServiceApi.GET_FLAT_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Long findFlatIdByLogin(@RequestParam("login") String login) {
        return usersRepository.findFlatidByLogin(login);
    }

    //получить счётчк по его серийнику
    @RequestMapping(value = ServiceApi.GET_COUNTER_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Counter getCounter(@PathVariable("sn") long sn) {
        return countersRepository.getCounterBySn(sn);
    }

    //получить пользователя по его id
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
    @RequestMapping(value = ServiceApi.USERS_COUNTERS_PATH, method = RequestMethod.GET)
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

    //получить список счётчиков квартиры по id квартиры
    @RequestMapping(value = ServiceApi.GET_COUNTERS_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Set<Counter> getCountersByFlatId(@PathVariable("flatId") Long flatid) {
        return countersRepository.getCountersByFlatId(flatid);
    }

    //получить объект типа счётчика по его названию (метод для тестирования)
    @RequestMapping(value = ServiceApi.GET_COUNTERTYPE_PATH, method = RequestMethod.GET)
    @ResponseBody
    CounterType getCounterType(@RequestParam("typename") String typename) {
        return counterTypesRepository.findByTypeName(typename);
    }

    //получить список типов счётчиков
    @RequestMapping(value = ServiceApi.GET_COUNTERTYPES_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Set<String> getAllCounterTypes() {
        return counterTypesRepository.getAllCounterTypes();
    }

    @RequestMapping(value = ServiceApi.GET_FLAT_COUNTER_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Counter getUserCounterByType(@PathVariable("flatId") Long flatid, @RequestParam("type") String type) {
        return countersRepository.getCountersByFlatIdAndType(flatid, type);
    }

    //получить список всех счётчиков
    @RequestMapping(value = ServiceApi.GET_ALL_COUNTERS_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Counter> getAllCounters() {
        return countersRepository.findAll();
    }

    @RequestMapping(value = ServiceApi.GET_COUNTER_RECORDS_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Record> getCounterRecords(@PathVariable("sn") Long sn) {
        Counter counter = countersRepository.getCounterBySn(sn);
        return recordsRepository.findByCounter(counter);
    }

    //вернуть показания определённого счётчика данной квартиры за указанный период снб тип, описание, значение, дата замера
    @RequestMapping(value = ServiceApi.GET_FLATS_COUNTERS_RECORDS_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Record> getFlatsCounterRecords(@PathVariable("flatId") Long flatId,
                                                   @RequestParam("dateStart") String dateStart,
                                                   @RequestParam(value = "dateEnd", required = false) String dateEnd) {
        Set<Counter> counters = countersRepository.getCountersByFlatId(flatId);
        if (dateEnd == null) {
            Date dt = new java.util.Date();
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            dateEnd = sdf.format(dt);
        }

        return recordsRepository.findByCountersSns(counters, dateStart, dateEnd);
    }

    //вернуть показания всех счётчиков данной квартиры за указанный период то же самое что и выше, только для всех счётчиков
    //вернуть список квартир данного дома с показателями за период, с фильтром по счётчикам
}
