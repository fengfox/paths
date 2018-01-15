package com.ftao.paths.controller;

import com.ftao.paths.domain.Car;
import com.ftao.paths.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping(value="/cars/{id}")
    public Car getCar(@PathVariable("id") Integer id)
    {

        return carService.getCar(id);
    }
    @PostMapping(value="/cars/add")
    public Car carAdd(@Valid Car car)
    {
        return carService.carAdd(car);
    }
    @PostMapping(value="/cars/del")
    public void carDel(@RequestParam("id") Integer id)
    {
        carService.carDel(id);
    }
    @GetMapping(value="/cars/all")
    public List<Car> carsFindAll()
    {
        return carService.carsFindAll();
    }

}
