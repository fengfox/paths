package com.ftao.paths.repository;

import com.ftao.paths.domain.CarDriver;
import com.ftao.paths.utils.CarDriverUitl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarDriverRepository extends JpaRepository<CarDriver,Integer> {
    //通过carid查找
    List<CarDriver> findByCarId(Integer carId);
    List<CarDriver> findByDriverId(Integer driverId);

}
