package com.ftao.paths.controller;

import com.ftao.paths.domain.Driver;
import com.ftao.paths.domain.Result;
import com.ftao.paths.service.DriverService;
import com.ftao.paths.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DriverController {
    @Autowired
    private DriverService driverService;

    /***
     * 增
     * @param driver
     * @param bindingResult
     * @return
     */
    @PostMapping("/drivers/add")
    public Result driverAdd(@Valid Driver driver, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(driverService.driverAdd(driver));
    }

    /***
     * 改
     * @param driver
     * @param bindingResult
     * @return
     */
    @PutMapping("/drivers/update")
    public Result driverUpdate(@Valid Driver driver, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(driverService.driverAdd(driver));
    }

    /***
     * 查全部
     * @return
     */
    @GetMapping("/drivers/all")
    public Result driverFindAll()
    {
        return ResultUtil.success(driverService.driverFindAll());
    }

    /***
     * 查一个
     * @param id
     * @return
     */
    @GetMapping("/drivers/{id}")
    public Result driverFindOne(@PathVariable("id") Integer id)
    {
        return ResultUtil.success(driverService.driverFindOne(id));
    }

    /***
     * 删
     * @param id
     * @return
     */
    @DeleteMapping("/drivers/delete/{id}")
    public Result driverDel(@PathVariable("id") Integer id)
    {
        driverService.driverDel(id);
        return ResultUtil.success();
    }

}
