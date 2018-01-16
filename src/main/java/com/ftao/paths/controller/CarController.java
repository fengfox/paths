package com.ftao.paths.controller;

import com.ftao.paths.domain.Car;
import com.ftao.paths.domain.Result;
import com.ftao.paths.service.CarService;
import com.ftao.paths.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping(value="/cars/{id}")
    public Result getCar(@PathVariable("id") Integer id)
    {
        return ResultUtil.success(carService.getCar(id));
    }
    @PostMapping(value="/cars/add")
    public Result carAdd(@Valid Car car,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(carService.carAdd(car));
    }
    @DeleteMapping(value="/cars/delete/{id}")
    public Result carDel(@PathVariable("id") Integer id)
    {

        carService.carDel(id);
        return ResultUtil.success();

    }
    @GetMapping(value="/cars/all")
    public Result carsFindAll()
    {
        return ResultUtil.success(carService.carsFindAll());
    }
    @PutMapping(value="/cars/update")
    public Result carUpdate(@Valid Car car,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(carService.carUpdate(car));
    }
}
