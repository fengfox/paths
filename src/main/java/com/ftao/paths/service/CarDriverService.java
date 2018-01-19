package com.ftao.paths.service;

import com.ftao.paths.domain.CarDriver;
import com.ftao.paths.repository.CarDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarDriverService {
    @Autowired
    private CarDriverRepository carDriverRepository;
    //增,改
    public CarDriver carDriverSave(CarDriver carDriver)
    {
        return carDriverRepository.save(carDriver);
    }
    //删
    public void carDriverDelete(Integer id)
    {
        carDriverRepository.delete(id);
    }
    //查
    public List<CarDriver> carDriverFindAll()
    {
        return carDriverRepository.findAll();
    }
    public CarDriver carDriverFindOne(Integer id)
    {
        return carDriverRepository.findOne(id);
    }
    public List<CarDriver> carDriverFindByCarId(Integer carId)
    {
        return carDriverRepository.findCarDriverByCarId(carId);
    }
    public List<CarDriver> carDriversFindByDriverId(Integer driverId)
    {
        return carDriverRepository.findCarDriverByDriverId(driverId);
    }

}
