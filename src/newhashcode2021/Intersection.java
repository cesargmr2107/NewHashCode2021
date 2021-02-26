/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newhashcode2021;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 *
 * @author cgmr2
 */
public class Intersection {

    public int id;
    public HashMap<String, Street> enteringStreets;
    public LinkedHashMap<Street, Integer> schedule;

    public Intersection(int id) {
        this.id = id;
        enteringStreets = new HashMap<>();
        schedule = new LinkedHashMap<>();
    }

    public void addEnteringStreet(Street s) {
        enteringStreets.put(s.name, s);
    }

    public Street chooseStreet() {
        Street minStreet = null;
        int minTime = Integer.MAX_VALUE;
        for (Street street : enteringStreets.values()) {
            Car firstCar = street.checkFirstCar();
            if (firstCar != null && firstCar.totalTime < minTime) {
                minStreet = street;
                minTime = firstCar.totalTime;
            }
        }
        if (minStreet != null) {
            updateSchedule(minStreet);
        }
        return minStreet;
    }

    public boolean hasSchedule() {
        return !schedule.isEmpty();
    }

    public int nStreetsInSchedule() {
        return schedule.size();
    }

    public void updateSchedule(Street s) {
        Integer scheduledTime = schedule.get(s);
        if (scheduledTime == null) { // It hasn't been added yet
            scheduledTime = 0;
        }
        schedule.put(s, scheduledTime + 1);
    }

    public String scheduleToString() {
        StringBuilder toret = new StringBuilder();
        toret.append(id).append("\n");
        toret.append(schedule.size()).append("\n");
        for (Entry<Street, Integer> entry : schedule.entrySet()) {
            toret.append(entry.getKey().name).append(" ").append(entry.getValue()).append("\n");
        }
        return toret.toString();
    }

}
