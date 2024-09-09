package com.tablemasterbackend.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetHighestFrequency<T> {
    public static <T> T findMostFrequentElement(ArrayList<T> list) {
        if (list == null || list.isEmpty()) {
            return null; // handle edge case where the list is null or empty
        }

        // Create a HashMap to store the frequency of each element
        HashMap<T, Integer> frequencyMap = new HashMap<>();

        // Iterate through the ArrayList and update the frequency of each element
        for (T element : list) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }

        // Find the element with the highest frequency
        T mostFrequentElement = null;
        int maxFrequency = 0;
        for (Map.Entry<T, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostFrequentElement = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        return mostFrequentElement;
    }
}
