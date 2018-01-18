package com.ftao.paths.service;

import com.ftao.paths.domain.Leader;
import com.ftao.paths.repository.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderService {

    @Autowired
    private LeaderRepository leaderRepository;
    //增,改
    public Leader leaderSave(Leader leader)
    {
        return leaderRepository.save(leader);
    }

    //删除
    public void leaderDelete(Integer id)
    {
        leaderRepository.delete(id);
    }
    //查
    public List<Leader> leaderFindAll()
    {
        return leaderRepository.findAll();
    }
    public Leader leaderFindOne(Integer id)
    {
        return leaderRepository.findOne(id);
    }

}
