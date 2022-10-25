package com.magatella.arrayprocessservice.services;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;
import java.util.stream.*;

@Service
public class ArrayService {

    public ArrayList<Integer> readFromFile(String filePath) {
        ArrayList<Integer> integerArrayList;
        Stream<String> readStream = null;
        try {
            readStream = Files.lines(Paths.get(filePath));
        }catch (IOException e){
            e.getMessage();
        }
        integerArrayList = readStream.map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
        return integerArrayList;
    }

    public String findMaxValue(String filePath) {
        ArrayList<Integer> integerArrayList = readFromFile(filePath);
        return Collections.max(integerArrayList).toString();
    }

    public String findMinValue(String filePath) {
        ArrayList<Integer> integerArrayList = readFromFile(filePath);
        return Collections.min(integerArrayList).toString();
    }

    public String findMedianValue(String filePath) {
        ArrayList<Integer> integerArrayList = readFromFile(filePath);
        integerArrayList.sort(Comparator.naturalOrder());

        if (integerArrayList.size() % 2 == 1) {
            return integerArrayList.get(integerArrayList.size() / 2).toString();
        } else {
            double result = 0.5 * (integerArrayList.get(integerArrayList.size() / 2) + integerArrayList.get(integerArrayList.size() / 2 + 1));
            return Double.toString(result);
        }
    }

    public String calcAvgValue(String filePath) {
        ArrayList<Integer> integerArrayList = readFromFile(filePath);
        double result = integerArrayList.stream().mapToDouble(value -> value).average().orElse(0.0);
        return Double.toString(result);
    }


    public String findAscendingSubsequence(String filePath) {
        ArrayList<Integer> integerArrayList = readFromFile(filePath);
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;

        for (int i = count; i < integerArrayList.size() - 1; i++) {
            if (integerArrayList.get(i) < integerArrayList.get(i + 1)) {
                list = new ArrayList<>(list);
                if (!list.contains(integerArrayList.get(i))) {
                    list.add(integerArrayList.get(i));
                }
                list.add(integerArrayList.get(i + 1));
            } else {
                list = new ArrayList<>();
                count++;
            }
            if (!list.isEmpty()) {
                lists.add(list);
            }
        }
        int maxSizeList = lists.stream().mapToInt(ArrayList::size).max().getAsInt();
        return lists.stream().filter(j -> (j.size() == (maxSizeList)))
                .collect(Collectors.toCollection(ArrayList::new)).toString();
    }

    public String findDescendingSubsequence(String filePath) {
        ArrayList<Integer> integerArrayList = readFromFile(filePath);
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;

        for (int i = count; i < integerArrayList.size() - 1; i++) {
            if (integerArrayList.get(i) > integerArrayList.get(i + 1)) {
                list = new ArrayList<>(list);
                if (!list.contains(integerArrayList.get(i))) {
                    list.add(integerArrayList.get(i));
                }
                list.add(integerArrayList.get(i + 1));
            } else {
                list = new ArrayList<>();
                count++;
            }
            if (!list.isEmpty()) {
                lists.add(list);
            }
        }
        int maxSizeList = lists.stream().mapToInt(ArrayList::size).max().getAsInt();
        return lists.stream().filter(j -> (j.size() == (maxSizeList)))
                .collect(Collectors.toCollection(ArrayList::new)).toString();
    }

    public String CheckSumFile(String filePath) {
        try {
            File file = new File(filePath);
            MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
            return getFileChecksum(shaDigest, file);
        }catch (NoSuchAlgorithmException e){
            return "Exceprion: "+e.getMessage();
        }
    }

    public String getFileChecksum(MessageDigest digest, File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] byteArray = new byte[1024];
            int bytesCount = 0;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
            ;
            fis.close();
            byte[] bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }catch (IOException e){
            return "Exceprion: "+e.getMessage();
        }
    }
}
