package com.ftao.paths.repository;

import com.ftao.paths.domain.CarDriver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarDriverRepository extends JpaRepository<CarDriver,Integer> {
    //通过carid查找
    public List<CarDriver> findCarDriverByCarId(Integer carId);
    public List<CarDriver> findCarDriverByDriverId(Integer driverId);
}
