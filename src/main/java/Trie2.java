package java;

/*
Problem Statement: Implement a Trie data structure that supports the following methods:

Insert (word): To insert a string `word` in the Trie.
Count Words Equal To (word): Return the count of occurrences of the string word in the Trie.
Count Words Starting With (prefix): Return the count of words in the Trie that have the string “prefix” as a prefix.
Erase (word): Delete one occurrence of the string word from the Trie.
Note:

The Erase(word) function is guaranteed to be called only when a word is present in the Trie.
Release the memory associated with variables using dynamic memory allocation at the end of your solution.

Example 1:
Input:
Insert: ‘apple’, ‘apps’, ‘apxl’
Count Number of Words Equal to: ‘apple’
Count Number of Words Starting with: ‘app’, ‘ap’
Erase word: ‘apxl’
Output: 
Inserted ‘apple’, Inserted ‘apps’,Inserted, ‘apxl’.
Number of Words Equal to ‘apple’: 1
Number of Words Starting with ‘app’: 2 and ‘ap’: 3
Erased ‘apxl’
Explanation: Insert Operations: “apple”, “apps” and “apxl” are inserted. 
*/


class Node {
    private final Node[] links = new Node[26];
    private int cntEndWith = 0;
    private int cntPrefix = 0;

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public Node get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, Node node) {
        links[ch - 'a'] = node;
    }

    public void increaseEnd() {
        cntEndWith++;
    }

    public void increasePrefix() {
        cntPrefix++;
    }

    public void deleteEnd() {
        cntEndWith--;
    }

    public void reducePrefix() {
        cntPrefix--;
    }

    public int getEnd() {
        return cntEndWith;
    }

    public int getPrefix() {
        return cntPrefix;
    }
}

public class Trie2 {
    private final Node root;

    public Trie2() {
        root = new Node();
    }

    // Inserts a word into the trie
    public void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
            node.increasePrefix();
        }
        node.increaseEnd();
    }

    // Counts how many times a word is present in the trie
    public int countWordsEqualTo(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return 0;
            }
        }
        return node.getEnd();
    }

    // Counts how many words in the trie start with the given prefix
    public int countWordsStartingWith(String prefix) {
        Node node = root;
        for (char ch : prefix.toCharArray()) {
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return 0;
            }
        }
        return node.getPrefix();
    }

    // Erases one occurrence of the given word from the trie
    public void erase(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (node.containsKey(ch)) {
                node = node.get(ch);
                node.reducePrefix();
            } else {
                return; // Word doesn't exist
            }
        }
        node.deleteEnd();
    }
}
