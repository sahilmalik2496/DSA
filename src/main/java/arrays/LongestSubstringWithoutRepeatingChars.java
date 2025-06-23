package arrays;

/*
 Given a string s, find the length of the longest
substring
 without repeating characters.

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */


public class LongestSubstringWithoutRepeatingChars {
    public int lengthOfLongestSubstring(String s) {
        if(s==null)
            return 0;
        int arr[] = new int[256];
        int start=0;
        for(int i=0;i< 256; i++)
            arr[i]=-1;
        int res=0;
        for(int i=0;i<s.length();i++) {
            System.out.println(start);
            int c= s.charAt(i);
            if(arr[c] != -1) {
                res = Math.max(res, i-start);
                start = Math.max(start,arr[c]+1);
            }
            arr[c]=i;
        }
        res=Math.max(res,s.length()-start);
        return res;
    }
}
