package com.magatella.arrayprocessservice;

import com.magatella.arrayprocessservice.services.ArrayService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileTest {
    ArrayService arrayService = new ArrayService();

    @Test
    public void fileTest() throws IOException {


        arrayService.readFromFile("D:/Projects/JavaDev/test_data/10m.txt");
    }

    @Test
    public void findMaxValueTest() throws IOException{
        System.out.println(arrayService.findMaxValue("D:/Projects/JavaDev/test_data/10m.txt"));
    }

    @Test
    public void findMinValueTest() throws IOException{
        System.out.println(arrayService.findMinValue("D:/Projects/JavaDev/test_data/10m.txt"));
    }

    @Test
    public void findMedianValueTest() throws IOException{
        System.out.println(arrayService.findMedianValue("D:/Projects/JavaDev/test_data/10m.txt"));
    }

    @Test
    public void calcAvgValueTest() throws IOException{
        System.out.println(arrayService.calcAvgValue("D:/Projects/JavaDev/test_data/10m.txt"));
    }

    @Test
    public void findAscendingSubsequenceTest() throws IOException{
        System.out.println(arrayService.findAscendingSubsequence("D:/Projects/JavaDev/test_data/50.txt"));
    }

    @Test
    public void findDescendingSubsequence() throws IOException{
        System.out.println(arrayService.findDescendingSubsequence("D:/Projects/JavaDev/test_data/50.txt"));
    }
}
