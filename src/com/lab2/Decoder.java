/*
Raul Fosselman
cs 310
Lab 2 Software decode
10/22/2019
 */

package com.lab2;

import java.util.TreeMap;

public class Decoder {
    private static MyQueue queue = new MyQueue();

    public static MyQueue decode(MyQueue incomingVals, TreeMap<Integer, int[]> knownPatterns) {

        while (incomingVals.size() >= 6) {
            incomingVals.normalize();
            int[] currentWord = incomingVals.peek(7);
            int howFarToMove = checkForWordMatch((int[]) currentWord,
                    (TreeMap<Integer, int[]>) knownPatterns);
            for (int i = 0; i < howFarToMove; i++) {
                incomingVals.nextValue();
            }
        }
        return queue;
    }

    //returns an int specifying how many values in the queue should be dequeued
    // loads code # into a queue if a word is found
    private static int checkForWordMatch(int[] cw, TreeMap<Integer, int[]> knownPatterns){
        for (Object key : knownPatterns.keySet()) {
            int[] pat = knownPatterns.get(key);
            int noiseCount = 0;
            int pulseCount = 0;

            int j = 0;
            int i = 0;
            while (i < 6) {
                if (cw[j] == pat[i]) {
                    pulseCount++;
                } else if (j+1 == cw.length || cw[j + 1] != pat[i]) {
                    break;//current pattern doesn't match, try next pattern
                } else {
                    noiseCount++;
                    if(noiseCount > 1) break;
                    pulseCount++;
                    j++;
                }
                if (pulseCount == 6) {
                    queue.loadValue((Integer) key);
                    return j;//return the length that the word spanned, 6 or 7
                }
                i++;
                j++;
            }
        }
        return 1; //if nothing matched move 1 value
    }
}
