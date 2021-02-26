/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newhashcode2021;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cgmr2
 */
public class Util {

    public static ArrayList<String[]> readFile(String route) {

        ArrayList<String[]> toret = new ArrayList<>();

        try {

            File text = new File(route);
            Scanner scnr = new Scanner(text);
            while (scnr.hasNextLine()) {
                toret.add(scnr.nextLine().split(" "));
            }

        } catch (Exception ex) {
            System.err.println("Ha ocurrido un problema al leer el archivo");
        }

        return toret;
    }

    public static void writeFile(String fileName, String fileContent) {
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter out = new PrintWriter(fw);
            out.print(fileContent);
            out.close();
            fw.close();
        } catch (Exception ex) {
            System.err.println("Ha ocurrido un problema al escribir el archivo");
        }
    }

}
