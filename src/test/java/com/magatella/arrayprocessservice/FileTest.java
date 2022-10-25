package com.magatella.arrayprocessservice;

import com.magatella.arrayprocessservice.services.ArrayService;
import org.junit.jupiter.api.Test;

public class FileTest {
    ArrayService arrayService;
    String filePath = "D:/Projects/JavaDev/test_data/10m.txt";

    @Test
    public void fileTest() {
        arrayService.readFromFile(filePath);
    }

    @Test
    public void findMaxValueTest() {
        System.out.println(arrayService.findMaxValue(filePath));
    }

    @Test
    public void findMinValueTest() {
        System.out.println(arrayService.findMinValue(filePath));
    }

    @Test
    public void findMedianValueTest() {
        System.out.println(arrayService.findMedianValue(filePath));
    }

    @Test
    public void calcAvgValueTest() {
        System.out.println(arrayService.calcAvgValue(filePath));
    }

    @Test
    public void findAscendingSubsequenceTest() {
        System.out.println(arrayService.findAscendingSubsequence(filePath));
    }

    @Test
    public void findDescendingSubsequence() {
        System.out.println(arrayService.findDescendingSubsequence(filePath));
    }

    @Test
    public void calcCheckSumFile() {
        System.out.println(arrayService.CheckSumFile(filePath));
    }
}
