package com.ftao.paths.controller;

import com.ftao.paths.domain.CarDriver;
import com.ftao.paths.domain.Driver;
import com.ftao.paths.domain.Result;
import com.ftao.paths.service.CarDriverService;
import com.ftao.paths.service.CarService;
import com.ftao.paths.service.DriverService;
import com.ftao.paths.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarDriverController {
    @Autowired
    private CarDriverService carDriverService;
    @Autowired
    private CarService carService;
    @Autowired
    private DriverService driverService;
    @GetMapping(value="/cardriver/car/{carId}")
    public Result findByCarId(@PathVariable("carId") Integer carId)
    {
        return ResultUtil.success(carDriverService.carDriverFindByCarId(carId));
    }
    @GetMapping(value="/cardriver/driver/{driverId}")
    public Result findByDriverId(@PathVariable("driverId") Integer driverId)
    {
        return ResultUtil.success(carDriverService.carDriverFindByDriverId(driverId));
    }



}
