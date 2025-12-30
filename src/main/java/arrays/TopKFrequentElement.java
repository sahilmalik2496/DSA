package arrays;

import java.util.*;

/*
https://leetcode.com/problems/top-k-frequent-elements/description/
 */

public class TopKFrequentElement {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        // Min-heap for frequency, Max-heap for string lexicography
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int freqA = counts.get(a);
            int freqB = counts.get(b);
            if (freqA != freqB) {
                return Integer.compare(freqA, freqB); // Ascending frequency
            } else {
                return b.compareTo(a); // Descending alphabetical (Max-heap for strings)
            }
        });

        for (String word : counts.keySet()) {
            pq.add(word);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Build the result list
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }

        // Since it was a min-heap, we have the top K,
        // but they are in increasing order. We need decreasing.
        Collections.reverse(res);
        return res;
    }
}
