package ru.aksndr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.aksndr.datalayer.*;
import ru.aksndr.model.House;

import java.util.List;

/**
 * Created by aksndr on 06.09.14.
 */
@Controller
public class BaseController {
    @Autowired
    protected UsersRepository usersRepository;
    @Autowired
    protected FlatsRepository flatsRepository;
    @Autowired
    protected HouseRepository houseRepository;
    @Autowired
    protected CountersRepository countersRepository;
    @Autowired
    protected CounterTypesRepository counterTypesRepository;
    @Autowired
    protected RecordsRepository recordsRepository;

    public BaseController() {
    }

//    @Autowired
//    public BaseController(UsersRepository usersRepository, FlatsRepository flatsRepository, HouseRepository houseRepository,
//                   CountersRepository countersRepository, CounterTypesRepository counterTypesRepository, RecordsRepository recordsRepository) {
//
//        this.usersRepository = usersRepository;
//        this.flatsRepository = flatsRepository;
//        this.houseRepository = houseRepository;
//        this.countersRepository = countersRepository;
//        this.counterTypesRepository = counterTypesRepository;
//        this.recordsRepository = recordsRepository;
//    }

    public House findHouseByAddress(String address) {
        List<House> houseList = houseRepository.findByAddress(address);
        if (houseList.size() == 0) {
            return houseRepository.save(new House(address));
        } else if (houseList.size() == 1) {
            return houseList.get(0);
        }
        return null;
    }
}
