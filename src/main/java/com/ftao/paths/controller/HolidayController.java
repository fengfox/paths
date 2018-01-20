package com.ftao.paths.controller;


import com.ftao.paths.domain.Holiday;
import com.ftao.paths.domain.Result;
import com.ftao.paths.repository.HolidayRepository;
import com.ftao.paths.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

public class HolidayController {
    @Autowired
    private HolidayRepository holidayRepository;
    @GetMapping(value="/holiday/all")
    public Result holidayFindAll()
    {
        return ResultUtil.success(holidayRepository.findAll());
    }
    @GetMapping(value="/holiday/{id}")
    public Result holidayFindOne(@PathVariable("id") Integer id)
    {
        return ResultUtil.success(holidayRepository.findOne(id));
    }
    @PostMapping(value="/holiday/save")
    public Result holidaySave(Holiday holiday, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(holidayRepository.save(holiday));
    }
    @DeleteMapping(value="/holiday/delete/{id}")
    public Result holidayDelete(@PathVariable("id") Integer id)
    {
        holidayRepository.delete(id);
        return ResultUtil.success();
    }
}
