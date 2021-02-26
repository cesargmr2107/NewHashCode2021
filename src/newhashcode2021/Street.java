/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newhashcode2021;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author cgmr2
 */
class Street {

    public int originIntersection;
    public int endIntersection;
    public String name;
    public int travelTime;
    public Queue<Car> waitingCars;
            
    public Street(String[] streetInfo) {
        originIntersection = Integer.parseInt(streetInfo[0]);
        endIntersection = Integer.parseInt(streetInfo[1]);
        name = streetInfo[2];
        travelTime = Integer.parseInt(streetInfo[3]);
        waitingCars = new LinkedBlockingQueue<>();
    }
    
    public void addCar(Car c){
        waitingCars.add(c);
    }
    
    public Car checkFirstCar(){
        return waitingCars.peek();
    }
    
    public Car removeCar(){
        return waitingCars.poll();
    }

    @Override
    public String toString() {
        return "Street{" + "originIntersection=" + originIntersection + ", endIntersection=" + endIntersection + ", name=" + name + ", travelTime=" + travelTime + ", waitingCars=" + waitingCars + '}';
    }
    
}
