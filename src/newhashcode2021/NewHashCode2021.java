/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newhashcode2021;

import java.util.ArrayList;

/**
 *
 * @author cgmr2
 */
public class NewHashCode2021 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        if (args.length != 1) {

            System.out.println("Uso: java HashCode2021 entrada.txt");

        } else {

            System.out.println("Reading file... " + args[0]);

            // Read file into input
            ArrayList<String[]> input = Util.readFile(".\\input\\" + args[0] + ".txt");

            // Pass input to problem
            Problem problem = new Problem(input);

            // Solve problem and get result
            String result = problem.solve();

            // Write result to file
            System.out.println("Writing file... " + args[0] + "_out.txt");
            Util.writeFile(".\\output\\" + args[0] + "_out.txt", result);

        }

    }

}
