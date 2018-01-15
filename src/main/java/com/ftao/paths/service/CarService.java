package com.ftao.paths.service;


import com.ftao.paths.domain.Car;
import com.ftao.paths.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    public Car getCar(Integer id)
    {
        return carRepository.findOne(id);
    }
    public Car carAdd(Car car)
    {
        return carRepository.save(car);
    }
    public void carDel(Integer id)
    {
         carRepository.delete(id);
    }
    public Car carUpdate(Car car)
    {
        return carRepository.save(car);
    }
    public List<Car> carsFindAll()
    {
        return carRepository.findAll();
    }
}
