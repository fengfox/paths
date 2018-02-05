package com.ftao.paths.service;

import com.ftao.paths.domain.Path;
import com.ftao.paths.domain.Result;
import com.ftao.paths.repository.PathRepository;
import com.ftao.paths.utils.HuffmanTree;
import com.ftao.paths.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<List<Path>> pathsCalculate(List<HuffmanTree.Node> nodes,List<Integer> total)
    {
        List<List<HuffmanTree.Node>> nodesList=new ArrayList<List<HuffmanTree.Node>>();
        nodesList=HuffmanTree.nodesSplit(nodes,total);
        List<List<Path>> pathsList=new ArrayList<List<Path>>();
        for(int i=0;i<nodesList.size();i++)
        {
            List<Path> paths=new ArrayList<Path>();

            for(int j=0;j<nodesList.get(i).size();j++)
            {
                Path path=new Path();
                path=(Path)nodesList.get(i).get(j).getData();
                paths.add(path);
            }
            pathsList.add(paths);
        }
        return pathsList;
    }

}
