package com.ftao.paths.service;

import com.ftao.paths.domain.Driver;
import com.ftao.paths.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    @Autowired
    private DriverRepository dirverRepository;

    /***
     * 增
     * @param driver
     * @return
     */
    public Driver driverAdd(Driver driver)
    {
        return dirverRepository.save(driver);
    }

    /***
     * 删
     * @param id
     */
    public void driverDel(Integer id)
    {
        dirverRepository.delete(id);
    }

    /***
     * 查询所有
     * @return
     */
    public List<Driver> driverFindAll()
    {
        return dirverRepository.findAll();
    }

    /***
     * 查询一个
     * @param id
     * @return
     */
    public Driver driverFindOne(Integer id)
    {
        return dirverRepository.findOne(id);
    }

    /***
     * 改
     * @param driver
     * @return
     */
    public Driver dirverUpdate(Driver driver)
    {
        return dirverRepository.save(driver);
    }


}
