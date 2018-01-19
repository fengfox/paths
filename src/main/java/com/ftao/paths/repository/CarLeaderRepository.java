package com.ftao.paths.repository;

import com.ftao.paths.domain.CarLeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarLeaderRepository extends JpaRepository<CarLeader,Integer> {

    List<CarLeader> findByCarId(Integer carId);
    List<CarLeader> findByLeaderId(Integer leaderId);
}
