package arrays;

/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;

        int min = Integer.MAX_VALUE;

        for(String s: strs){
            if (s.length() < min){
                min = s.length();
            }
        }


        String res ="";

        for(int i =0; i< min; i++) {
            char c = strs[0].charAt(i);
            for(int j=1; j< n; j++) {
                if (strs[j].charAt(i) != c){
                    return res;
                }
            }
            res+= c;
        }

        return res;
    }
}
