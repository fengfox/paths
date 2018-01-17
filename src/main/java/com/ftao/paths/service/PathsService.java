package com.ftao.paths.service;

import com.ftao.paths.domain.Path;
import com.ftao.paths.domain.Result;
import com.ftao.paths.repository.PathRepository;
import com.ftao.paths.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathsService {
    @Autowired
    private PathRepository pathsRepository;


    //全查
    public List<Path> pathsFindAll()
    {
        return pathsRepository.findAll();
    }
    //查一个
    public Path pathsFindOne(Integer id)
    {
        return pathsRepository.findOne(id);
    }
    //更新/新增
    public Path pathsSave(Path path)
    {
        return pathsRepository.save(path);
    }
    //删除
    public void pathsDelete(Integer id)
    {
        pathsRepository.delete(id);
    }



}
