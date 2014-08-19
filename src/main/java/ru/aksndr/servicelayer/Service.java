package ru.aksndr.servicelayer;

/**
 * Created by aksndr on 11.08.14.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.aksndr.datalayer.FlatsRepository;
import ru.aksndr.datalayer.HouseRepository;
import ru.aksndr.datalayer.UsersRepository;
import ru.aksndr.model.Flat;
import ru.aksndr.model.House;
import ru.aksndr.model.User;

import javax.servlet.http.HttpServletResponse;

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
    public User addNewUser(@RequestBody User u, @RequestBody Flat f, @RequestBody House h, HttpServletResponse response) {
        if (houseRepository.findByAddress(h.getAddress()).size() > 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setHeader("Error", "Found more than one house on these address.");
            return null;
        } else if (houseRepository.findByAddress(h.getAddress()).size() == 0) {
            h = houseRepository.save(h);
        }
        f.setHouseId(h.getId());
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
}
