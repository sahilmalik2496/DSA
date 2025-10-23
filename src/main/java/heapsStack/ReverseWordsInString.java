package heapsStack;

/*
https://leetcode.com/problems/reverse-words-in-a-string/
Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should
only have a single space separating the words. Do not include any extra spaces.
 */


import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseWordsInString {
    public String reverseWords(String s) {
        Deque<String> deque = new ArrayDeque<>();
        int left = 0, right = s.length() - 1;

        // Trim spaces
        while (left <= right && s.charAt(left) == ' ') left++;
        while (right >= left && s.charAt(right) == ' ') right--;

        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                word.append(c);
            } else if (word.length() > 0) {
                deque.addFirst(word.toString());
                word.setLength(0);
            }
            left++;
        }
        deque.addFirst(word.toString());
        return String.join(" ", deque);
    }
}
