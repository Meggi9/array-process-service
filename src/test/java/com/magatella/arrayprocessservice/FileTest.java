package com.magatella.arrayprocessservice;

import com.magatella.arrayprocessservice.services.ArrayService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileTest {
    ArrayService arrayService = new ArrayService();
    String filePath = "D:/Projects/JavaDev/test_data/10m.txt";

    @Test
    public void fileTest() throws IOException {
        arrayService.readFromFile(filePath);
    }

    @Test
    public void findMaxValueTest() throws IOException{
        System.out.println(arrayService.findMaxValue(filePath));
    }

    @Test
    public void findMinValueTest() throws IOException{
        System.out.println(arrayService.findMinValue(filePath));
    }

    @Test
    public void findMedianValueTest() throws IOException{
        System.out.println(arrayService.findMedianValue(filePath));
    }

    @Test
    public void calcAvgValueTest() throws IOException{
        System.out.println(arrayService.calcAvgValue(filePath));
    }

    @Test
    public void findAscendingSubsequenceTest() throws IOException{
        System.out.println(arrayService.findAscendingSubsequence(filePath));
    }

    @Test
    public void findDescendingSubsequence() throws IOException{
        System.out.println(arrayService.findDescendingSubsequence(filePath));
    }
}
