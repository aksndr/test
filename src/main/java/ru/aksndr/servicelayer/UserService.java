package ru.aksndr.servicelayer;

/**
 * Created by aksndr on 11.08.14.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.aksndr.datalayer.DataLayer;
import ru.aksndr.datalayer.UsersRepository;
import ru.aksndr.model.User;

import javax.servlet.http.HttpServletResponse;

@Controller
//@EnableAutoConfiguration
public class UserService {
    @Autowired
    DataLayer dataLayer;

    @Autowired
    UsersRepository usersRepository;

    @RequestMapping(value = UserServiceApi.USERS_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> getUsersList(HttpServletResponse response) {
        response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        return usersRepository.findAll();
    }

    @RequestMapping(value = UserServiceApi.ADD_USER_PATH, method = RequestMethod.POST)
    @ResponseBody
    public User addUser(@RequestBody User u) {
        return usersRepository.save(u);
    }

    @RequestMapping(value = UserServiceApi.USER_PATH, method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("id") long id, HttpServletResponse response) {
        User user = usersRepository.findOne(id);
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return user;
    }
}
