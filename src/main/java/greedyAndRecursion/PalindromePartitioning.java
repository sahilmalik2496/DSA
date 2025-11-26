package greedyAndRecursion;

import java.util.*;

/*
https://leetcode.com/problems/palindrome-partitioning/description/
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome
 partitioning of s.

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]


*/

class PalindromePartitioning {
    public boolean isPalindrome(String s, int start, int end) {
        while(start< end) {
            if(s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    public void recur(String s, List<List<String>> res, int idx, List<String> curr) {
        if (idx == s.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for(int i=idx; i< s.length(); i++) {
            if(isPalindrome(s, idx, i)) {
                curr.add(s.substring(idx, i+1));
                recur(s, res, i+1, curr);
                curr.remove(curr.size() -1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        recur(s, res, 0, new ArrayList<>());
        return res;
    }
}
