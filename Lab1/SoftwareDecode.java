//
//    Rfossel
//    CS 310
//    Lab1: Software Decode
//    09/24/19

package com.company;

public class SoftwareDecode {

    //Problem #3 - 111 detection
    public static int patternDetector3(int [] array) {
        int startOfIndex = -1; //remains -1 if pattern isn't present
        int count;
        for (int i = 0; i < array.length; i++) {
            count = 0;
            int unitsPastStartValue = 20;
            for (int j = 0; j < array.length; j++) {
                if ((array[j] - array[i]) == (unitsPastStartValue)) {
                    count++;
                    unitsPastStartValue+=20;
                }
                if (count == 2) {
                    startOfIndex = i;
                    return startOfIndex;
                }
            }
        }
        return startOfIndex;
    }

    //Problem #4 - 110 detection
    public static int patternDetector4(int [] array){
        int foundStart = -1; //remains -1 if pattern isn't present
        int unitsPastStartValue;

        for (int i = 0; i < array.length; i++) {
            unitsPastStartValue = 20;
            for (int j = 0; j < array.length; j++) {
                if ((array[j] - array[i]) == (unitsPastStartValue)) {
                    unitsPastStartValue+=20;
                }
                if((unitsPastStartValue == 40) && (array[j] - array[i] > 40)){
                    foundStart = i;
                    return foundStart;
                }
            }
        }
        return foundStart;
    }

    //Problem #5 - 111 detection with user input for gap distance
    public static int patternDetector5(int [] array, int unitsPastStartValue){
        int foundStart = -1; //remains -1 if pattern isn't present
        int resetValue = unitsPastStartValue;
        int count;

        for (int i = 0; i < array.length; i++) {
            unitsPastStartValue = resetValue;
            count= 0;
            for (int j = 0; j < array.length; j++) {
                if ((array[j] - array[i]) == (unitsPastStartValue)) {
                    unitsPastStartValue *= 2;
                    count++;
                }
                if (count == 2) {
                    foundStart = i;
                    return foundStart;
                }
            }
        }
        return foundStart;
    }

    //Problem #6 - 111 detection with wraparound handling
    public static int patternDetector6(int [] array){
        int foundStart = -1; //remains -1 if pattern isn't present
        int unitsPastStartValue;
        int count;
        int wrapAroundHolder;

        for (int i = 0; i < array.length; i++) {
            unitsPastStartValue = 20;
            count = 0;
            wrapAroundHolder = 0;

            for (int j = 0; j < array.length; j++) {
                //When value overflows at 65535, wrap back around to 0
                if (unitsPastStartValue + array[i] > 65535){
                    wrapAroundHolder = (unitsPastStartValue + array[i]) % 65536;
                }
                if (((array[j] - array[i]) == (unitsPastStartValue))
                        || (wrapAroundHolder == array[j])) {
                    unitsPastStartValue *= 2;
                    count++;
                }
                if (count == 2) {
                    foundStart = i;
                    return foundStart;
                }
            }
        }
        return foundStart;
    }

    public static void main(String[] args) {
        int data[] = {63505,65535,8,10,19,40,39,65496 ,65515};
        System.out.println("Results: " + patternDetector6(data));

        int data111[] = {0, 20, 40};
        System.out.println("Results: should be 0 = " + patternDetector5(data111, 20));
        int fail[] = {40, 20, 0};
        System.out.println("Results: should be -1 = " + patternDetector5(fail, 20));
        int fail1[] = {0,20};
        System.out.println("Results: should be -1 = " + patternDetector5(fail1, 20));
        int fail2[] = {1,2,5};
        System.out.println("Results: should be -1 = " + patternDetector5(fail2, 20));
        int a111and110[] = {0, 20, 30, 40, 50, 60, 101};
        System.out.println("Results: should be 0 = " + patternDetector5(a111and110, 20));
        int b111[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,39,40,50,59};
        System.out.println("Results: should be 19 = " + patternDetector5(b111, 20));
        int c110[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,39,40,50,62};
        System.out.println("Results: should be -1 = " + patternDetector5(c110, 20));
    }
}
