package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// sources: https://stackoverflow.com/questions/41275278/java-paths-get-readallbytespath-not-working-with-relative-path
// https://stackoverflow.com/questions/11955728/how-to-calculate-the-median-of-an-array
// You can change txt file to one you want in line 23

public class Main {

    public static void main(String[] args) throws IOException {
        var startTime = System.currentTimeMillis();
        Path currentRelativePath = Paths.get("");
        String currentPath = currentRelativePath.toAbsolutePath().toString();

        String relativePath = "src/com/company/bigData.txt";
        Path path = Paths.get(currentPath, relativePath);

        List<String> l = Files.readAllLines(path, StandardCharsets.UTF_8);
        List<Integer> listOfNumbers = new ArrayList<>(Integer.MAX_VALUE / 10);
        for (String s : l) {
            listOfNumbers.add(Integer.parseInt(s));
        }
        assert listOfNumbers.size() > 0;
        System.out.println(listOfNumbers.get(listOfNumbers.size() / 2));
        System.out.println("Maximum value: " + findMaxValueInList(listOfNumbers));
        System.out.println("Minimum value: " + findMinValueInList(listOfNumbers));
        System.out.printf("Arithmetic mean: %.2f%n", calculateArithmeticMean(listOfNumbers));
        System.out.println("Largest increasing sequence: " + findLargestIncreasingSequence(listOfNumbers));
        System.out.println("Largest decreasing sequence: " + findLargestDecreasingSequence(listOfNumbers));
        System.out.printf("Median: %.2f%n", calculateMedian(listOfNumbers));
        System.out.println((System.currentTimeMillis() - startTime) / 1000 + " seconds");
    }

    public static Integer findMaxValueInList(List<Integer> listOfNumbers) {
        return listOfNumbers.stream().max(Integer::compareTo).orElse(0);
    }

    public static Integer findMinValueInList(List<Integer> listOfNumbers) {
        return listOfNumbers.stream().min(Integer::compareTo).orElse(0);
    }

    public static double calculateMedian(List<Integer> listOfNumbers) {
        int n = listOfNumbers.size();
        Collections.sort(listOfNumbers);
        return n % 2 == 0 ? ((double) (listOfNumbers.get(n / 2 - 1) + listOfNumbers.get(n / 2)) / 2.0) :
                (double) listOfNumbers.get(listOfNumbers.size()/2);
    }

    public static double calculateArithmeticMean(List<Integer> listOfNumbers) {
        Integer sum = 0;
        int i;
        for (i = 0; i < listOfNumbers.size(); i++) {
            sum += listOfNumbers.get(i);
        }
        return (double) sum / i;
    }

    public static List<Integer> findLargestIncreasingSequence(List<Integer> listOfNumbers) {
        int start = 0;
        int maxLength = 1;
        int currentStart = 0;
        int currentLength = 1;

        for (int i = 1; i < listOfNumbers.size(); i++) {
            if (listOfNumbers.get(i) > listOfNumbers.get(i - 1)) {
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    start = currentStart;
                }
                currentStart = i;
                currentLength = 1;
            }
        }

        if (currentLength > maxLength) {
            maxLength = currentLength;
            start = currentStart;
        }

        List<Integer> largestSequence = new ArrayList<>();
        for (int i = start; i < start + maxLength; i++) {
            largestSequence.add(listOfNumbers.get(i));
        }

        return largestSequence;
    }

    public static List<Integer> findLargestDecreasingSequence(List<Integer> listOfNumbers) {
        int start = 0;
        int maxLength = 1;
        int currentStart = 0;
        int currentLength = 1;

        for (int i = 1; i < listOfNumbers.size(); i++) {
            if (listOfNumbers.get(i) < listOfNumbers.get(i - 1)) {
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    start = currentStart;
                }
                currentStart = i;
                currentLength = 1;
            }
        }

        if (currentLength > maxLength) {
            maxLength = currentLength;
            start = currentStart;
        }

        List<Integer> largestSequence = new ArrayList<>();
        for (int i = start; i < start + maxLength; i++) {
            largestSequence.add(listOfNumbers.get(i));
        }

        return largestSequence;
    }
}
