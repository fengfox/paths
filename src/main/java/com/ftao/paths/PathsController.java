package com.ftao.paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PathsController {
    @Autowired
    private PathRepository pathRepository;

    /***
     * 获取所有路程
     * @return
     */
    @GetMapping("/paths")
    public List<Path> findAllPath()
    {
        return pathRepository.findAll();
    }

    @PostMapping(value="/paths/add")
    public Path pathAdd(@RequestParam("length") Integer length,@RequestParam("type") Integer type,@RequestParam("ran") Integer ran,@RequestParam("route") String route,
                        @RequestParam("remark") String remark)
    {
        Path path=new Path();
        path.setLength(length);
        path.setRan(ran);
        path.setRemark(remark);
        path.setRoute(route);
        path.setType(type);
        return pathRepository.save(path);
    }


}
