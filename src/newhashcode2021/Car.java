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
class Car {

    public int id;
    public Queue<Street> route;
    public int totalTime;
    public int currentStreetTime;

    public Car(int id) {
        this.id = id;
        route = new LinkedBlockingQueue<>();
        totalTime = 0;
        currentStreetTime = -1;
    }

    public void addStreetToRoute(Street s) {
        route.add(s);
        totalTime += s.travelTime;
    }

    public boolean move() {
        
        if (currentStreetTime == -1) {
            currentStreetTime = route.peek().travelTime;
        } else if (totalTime == 1) { // End of route
            return false;
        } else if (currentStreetTime == 1) { // End of current street
            route.poll(); // Remove current street from route
            route.peek().addCar(this); // Add to next street
            return false;
        }

        currentStreetTime--;
        totalTime--;

        return true;
    }
    
    public Street getNextStreet(){
        return route.peek();
    }

}
