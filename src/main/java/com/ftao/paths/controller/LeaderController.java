package com.ftao.paths.controller;

import com.ftao.paths.domain.Leader;
import com.ftao.paths.domain.Result;
import com.ftao.paths.service.LeaderService;
import com.ftao.paths.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LeaderController {
    @Autowired
    private LeaderService leaderService;
    @GetMapping(value="/leader/all")
    public Result leaderFindAll()
    {
        try {
            return ResultUtil.success(leaderService.leaderFindAll());
        }
        catch (Exception e)
        {
            return ResultUtil.error(e.hashCode(),e.getMessage());
        }
    }
    @GetMapping(value="/leader/{id}")
    public Result leaderFindOne(@PathVariable("id") Integer id)
    {
        try{
            return ResultUtil.success(leaderService.leaderFindOne(id));
        }

        catch (Exception e)
        {
            return ResultUtil.error(e.hashCode(),e.getMessage());
        }


    }
    @PostMapping(value="/leader/save")
    public Result leaderSave(@Valid Leader leader, BindingResult bindingResult)
    {
        try {
            if (bindingResult.hasErrors()) {
                return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
            }
            return ResultUtil.success(leaderService.leaderSave(leader));
        }
        catch (Exception e)
        {
            return ResultUtil.error(e.hashCode(),e.getMessage());
        }


    }
    @DeleteMapping(value="/leader/delete/{id}")
    public Result leaderDelete(@PathVariable("id") Integer id)
    {
        try {
            leaderService.leaderDelete(id);
            return ResultUtil.success();
        }
        catch (Exception e)
        {
            return ResultUtil.error(e.hashCode(),e.getMessage());
        }
    }

}
