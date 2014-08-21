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
import ru.aksndr.model.Flat;
import ru.aksndr.model.House;
import ru.aksndr.model.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @RequestMapping(value = ServiceApi.ADD_NEW_USER_PATH, method = RequestMethod.POST)
    @ResponseBody
    public User addNewUser(@RequestPart User u, @RequestPart Flat f, @RequestPart House h, HttpServletResponse response) {
        List<House> houseList = houseRepository.findByAddress(h.getAddress());
        if (houseList.size() > 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setHeader("Error", "Found more than one house on these address.");
            return null;
        } else if (houseList.size() == 0) {
            h = houseRepository.save(h);
        } else if (houseList.size() == 1) {
            h = houseList.get(0);
        }
        f.setHouseid(h.getId());
        flatsRepository.save(f);
        u.setFlatid(f.getId());
        return usersRepository.save(u);
    }

    @RequestMapping(value = ServiceApi.ADD_FLAT_PATH, method = RequestMethod.POST)
    @ResponseBody
    public Flat addFlat(@RequestBody Flat f) {
        return flatsRepository.save(f);
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


    //вернуть показания определённого счётчика данной квартиры за указанный период снб тип, описание, значение, дата замера
    //вернуть показания всех счётчиков данной квартиры за указанный период то же самое что и выше, только для всех счётчиков
    //вернуть список квартир данного дома с показателями за период, с фильтром по счётчикам

}
