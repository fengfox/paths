package com.ftao.paths.utils;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public class CarDriverUitl {


   public static class CarDriver{
       Integer carId;
       Integer carName;
       Integer driverId;
       Integer driverName;

       public CarDriver(Integer carId, Integer carName, Integer driverId, Integer driverName) {
           this.carId = carId;
           this.carName = carName;
           this.driverId = driverId;
           this.driverName = driverName;
       }

       public CarDriver() {
       }

       public Integer getCarId() {
           return carId;
       }

       public void setCarId(Integer carId) {
           this.carId = carId;
       }

       public Integer getCarName() {
           return carName;
       }

       public void setCarName(Integer carName) {
           this.carName = carName;
       }

       public Integer getDriverId() {
           return driverId;
       }

       public void setDriverId(Integer driverId) {
           this.driverId = driverId;
       }

       public Integer getDriverName() {
           return driverName;
       }

       public void setDriverName(Integer driverName) {
           this.driverName = driverName;
       }
   }
}
