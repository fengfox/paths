package com.ftao.paths.controller;

import com.ftao.paths.domain.Path;
import com.ftao.paths.domain.Result;
import com.ftao.paths.repository.PathRepository;
import com.ftao.paths.service.PathsService;
import com.ftao.paths.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PathController {
    @Autowired
    private PathsService pathsService;

    //全查
    @GetMapping(value="/paths/all")
    public Result pathsFindAll()
    {
        return ResultUtil.success(pathsService.pathsFindAll());
    }
    @GetMapping(value="/paths/{id}")
    public Result pathsFindOne(@PathVariable("id") Integer id)
    {
        return ResultUtil.success(pathsService.pathsFindOne(id));
    }
    //增加和修改
     @PostMapping(value="/paths/save")
     public Result pathsSave(@Valid Path path, BindingResult bindingResult)
     {
         if(bindingResult.hasErrors())
         {
             return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
         }
         return ResultUtil.success(pathsService.pathsSave(path));
     }
     //删除
    @DeleteMapping(value="/paths/delete/{id}")
    public Result pathsDelete(@PathVariable("id") Integer id)
    {
        pathsService.pathsDelete(id);
        return ResultUtil.success();
    }
}
