package com.ftao.paths.service;

import com.ftao.paths.domain.Holiday;
import com.ftao.paths.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;
    @GetMapping(value="/holiday/all")
    public List<Holiday> holidayFindAll()
    {
        return holidayRepository.findAll();
    }
    @GetMapping(value="/holiday/{id}")
    public Holiday holidayFindOne(Integer id)
    {
        return holidayRepository.findOne(id);
    }

    @PostMapping(value="/holiday/save")
    public Holiday holidaySave(Holiday holiday)
    {
        return holidayRepository.save(holiday);
    }
    @DeleteMapping(value="/holiday/delete")
    public void holidayDelete(Integer id)
    {
        holidayRepository.delete(id);
    }



}
