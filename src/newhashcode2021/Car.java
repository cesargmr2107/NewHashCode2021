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
        totalTime--;

        if (currentStreetTime == -1) { // Start travelling next street
            if (route.isEmpty()) { // Not end of route
                return false;
            }
            currentStreetTime = route.peek().travelTime;
        } else if (currentStreetTime == 1) { // End of current street
            currentStreetTime = -1;
            route.poll();
            if (!route.isEmpty()) { // Not end of route
                route.peek().addCar(this); // Add to next street
            }
            return false;
        } else {
            currentStreetTime--;
        }

        return true;
    }

    public Street getNextStreet() {
        return route.peek();
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", route=" + route + ", totalTime=" + totalTime + ", currentStreetTime=" + currentStreetTime + '}';
    }

}
