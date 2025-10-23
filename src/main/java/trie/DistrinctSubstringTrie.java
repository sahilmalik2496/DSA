package trie;

/*

https://takeuforward.org/plus/dsa/problems/number-of-distinct-substrings-in-a-string

Problem Statement: Implement a program that takes a string 'S' as input and returns the number of distinct substrings of
the given string, including the empty substring. Use a trie data structure to accomplish this.

Note:

A string ‘B’ is considered a substring of a string ‘A’ if ‘B’ can be obtained by deleting zero or more characters from the
 start and end of ‘A’.
Two strings ‘X’ and ‘Y’ are considered different if there is at least one index ‘i’ such that the character of ‘X’ at
index ‘i’ is different from the character of ‘Y’ at index ‘i’ (i.e., X[i] != Y[i]).
*/


public class DistrinctSubstringTrie {
    public class Node {
        private final Node[] links = new Node[26];
        private boolean isEndOfWord;

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public Node get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        public void setEnd() {
            isEndOfWord = true;
        }

        public boolean isEnd() {
            return isEndOfWord;
        }
    }

    public int countDistinctSubstrings(String s) {
        final Node root = new Node();
        int count = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            Node node = root;
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                if (!node.containsKey(ch)) {
                    node.put(ch, new Node());
                    count++;
                }
                node = node.get(ch);
            }
        }

        return count + 1; // +1 to include the empty substring
    }

    public static void main(String[] args) {
        String s = "striver";
        DistrinctSubstringTrie trie = new DistrinctSubstringTrie();
        System.out.println("Current String: " + s);
        System.out.println("Number of distinct substrings: " + trie.countDistinctSubstrings(s));
    }
}
