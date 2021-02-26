/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newhashcode2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

/**
 *
 * @author cgmr2
 */
public class Problem {

    private int simulationTime;
    private int nIntersections;
    private int nStreets;
    private int nCars;
    private int carScore;

    private HashMap<String, Street> allStreets;
    private ArrayList<Intersection> intersections;

    public Problem(ArrayList<String[]> input) {

        // First line
        simulationTime = Integer.parseInt(input.get(0)[0]);
        nIntersections = Integer.parseInt(input.get(0)[1]);
        nStreets = Integer.parseInt(input.get(0)[2]);
        nCars = Integer.parseInt(input.get(0)[3]);
        carScore = Integer.parseInt(input.get(0)[4]);

        // Intersections
        intersections = new ArrayList<>(nIntersections);
        for (int i = 0; i < nIntersections; i++) {
            intersections.add(new Intersection(i));
        }

        // Streets
        allStreets = new HashMap<>(nStreets);
        for (int i = 1; i <= nStreets; i++) {
            Street s = new Street(input.get(i));
            allStreets.put(s.name, s);
            Intersection endInters = intersections.get(s.endIntersection);
            endInters.addEnteringStreet(s);
        }

        // Cars
        for (int i = nStreets + 1; i < input.size(); i++) {
            Car c = new Car(i);
            int nStreetsInRoute = Integer.parseInt(input.get(i)[0]);
            for (int j = 1; j <= nStreetsInRoute; j++) {
                String sName = input.get(i)[j];
                Street s = allStreets.get(sName);
                c.addStreetToRoute(s);
            }
            // Add to waiting list in first street
            c.getNextStreet().addCar(c);
        }

    }

    public String solve() {

        ArrayList<Car> carsInTransit = new ArrayList<>();

        // Each second of the simulation time
        for (int sec = 0; sec < simulationTime; sec++) {

            // Update cars in transit
            ListIterator<Car> iter = carsInTransit.listIterator();
            while (iter.hasNext()) {
                if (!iter.next().move()) {
                    iter.remove();
                }
            }

            for (Intersection inter : intersections) {
                Street s = inter.chooseStreet();
                if (s != null) {
                    Car firstCar = s.checkFirstCar();
                    if (firstCar != null) {
                        firstCar.move();
                        carsInTransit.add(firstCar);
                    }
                }
            }

        }

        return outputParser();
    }

    public String outputParser() {

        StringBuilder parsed = new StringBuilder();
        int nSchedules = 0;

        for (Intersection inter : intersections) {
            if (inter.hasSchedule()) {
                nSchedules++;
                parsed.append(inter.scheduleToString());
            }
        }

        StringBuilder toret = new StringBuilder();
        toret.append(nSchedules);
        toret.append("\n");
        toret.append(parsed);
        
        //System.out.println(toret.toString());

        return toret.toString();
    }
}
