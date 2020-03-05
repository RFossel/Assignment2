/*
Rfossel
CS 310
Lab 2 Software decode
10/22/2019
 */

package com.lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws IOException {
        MyQueue queue = new MyQueue();
        MyQueue queueOutgoing;
        TreeMap<Integer, int[]> milesCodes = new TreeMap<>();
        try{
            File knownPatterns = new File(args[0]);
            File incomingValues = new File(args[1]);

            loadKnownPatterns(knownPatterns, milesCodes);
            loadIncomingVals(incomingValues, queue);
        }
        catch (java.io.FileNotFoundException ex){
            System.out.println("Make sure you provide a known Miles pattern file and then an input file");
            System.exit(0);
        }
        queueOutgoing = Decoder.decode(queue, milesCodes);

        List<String> csv = new ArrayList<String>();
        csv.add(queueOutgoing.toString());

        Path file = Paths.get("outgoing-codes.txt");
        Files.write(file, csv, StandardCharsets.UTF_8);

    }

    private static void loadKnownPatterns(File knownPatterns,
                                          TreeMap<Integer, int[]> milesCodes) throws FileNotFoundException {
        Scanner scanner = new Scanner(knownPatterns);
        scanner.useDelimiter(",");

        int i = 0;
        int[] milesWord = new int[6];
        while (scanner.hasNext()){
            if(i == 6){ //put a new array w/ a separate address into the map
                milesCodes.put(parseInt(scanner.next()),//pos 6 = code #
                        new int[] {milesWord[0],milesWord[1],milesWord[2]
                                ,milesWord[3],milesWord[4],milesWord[5]});
                i = 0;
            }
            else{
                milesWord[i] = parseInt(scanner.next());
                i++;
            }
        }
        scanner.close();
    }

    private static void loadIncomingVals(File incomingValues,
                                         MyQueue queue) throws FileNotFoundException {
        Scanner scanner = new Scanner(incomingValues);
        scanner.useDelimiter(",");

        while (scanner.hasNext())
        {
            queue.loadValue(parseInt(scanner.next()));
        }
        scanner.close();
    }

}
