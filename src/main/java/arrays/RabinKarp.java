package arrays;

/*
Rabin Karp Algorithm - Pattern Searching


10

0
Problem: Given a text and a pattern, write a function that prints all the occurrences of the pattern in the text. Assume the length of text  > pattern and both are given in the form of strings.

Examples:

Example 1:
Input: text = “abccddaefg” , pattern=”cdd”
Output: 2
Explanation: “cdd” pattern is found at index 2.

Example 2:
Input: text="this is a test text" , pattern=”test”
Output: 10
Explanation: “test” pattern is found at index 10.


Example Input
vbnet
Copy
Edit
Text:    "ABABABABC"
Pattern: "ABABC"
PRIME: 101
Step 1: Compute Initial Hashes
Using the hash function:

hash
=
∑
(
character
×
PRIME
position
)
hash=∑(character×PRIME
position
 )
Pattern Hash Calculation (ABABC)
makefile
Copy
Edit
A = 65 * 101^0 = 65
B = 66 * 101^1 = 6666
A = 65 * 101^2 = 656565
B = 66 * 101^3 = 67363266
C = 67 * 101^4 = 686952967
-----------------------------------
Pattern Hash = 754048529
First Window Hash (ABABA)
makefile
Copy
Edit
A = 65 * 101^0 = 65
B = 66 * 101^1 = 6666
A = 65 * 101^2 = 656565
B = 66 * 101^3 = 67363266
A = 65 * 101^4 = 659746565
-----------------------------------
First Text Window Hash = 726809127
Step 2: Compare Hashes & Slide the Window
Now, we slide the window and recalculate hashes.

Index	Text Window	Text Hash	Compare with Pattern Hash?
0	"ABABA"	726809127	❌ No Match
1	"BABAB"	754416195	❌ No Match
2	"ABABA"	726809127	❌ No Match
3	"BABAB"	754416195	❌ No Match
4	"ABABC"	754048529	✅ Match! (Verify substring)
Step 3: Verify Matching Substring
Since hash(text[4:9]) == hash(pattern), we compare substrings:

sql
Copy
Edit
Text: "ABABC"
Pattern: "ABABC"
✅ Exact Match! Pattern found at index 4.


Time Complexity of Rabin-Karp Algorithm
The Rabin-Karp algorithm consists of two main operations:

Hash Calculation (for pattern and first window of text)
Sliding Window Hash Recalculation & Matching
1. Preprocessing (Hash Calculation)
Computing the initial hash for the pattern takes O(m) time, where m is the pattern length.
2. Sliding Window Comparisons
We iterate through the text O(n - m + 1) times (where n is the text length).
Each iteration performs:
Hash comparison (O(1))
Rolling hash update (O(1))
Substring comparison if hashes match (O(m) worst case)
Best Case Time Complexity: O(n)
If hash collisions are rare, only O(1) work is done per window, leading to O(n) time.
Worst Case Time Complexity: O(n * m)
If many hash collisions occur, every window requires an O(m) substring comparison, leading to O(n * m) complexity.
This happens when all substrings have the same hash but are different (e.g., worst-case hashing scenarios).
Average Case Time Complexity: O(n)
With a good hash function (e.g., a prime modulus to minimize collisions), comparisons remain O(1) for most cases, leading to O(n) time.
 */
public class RabinKarp {
    private static final int PRIME = 101; // A prime number for hashing

    public static void searchPattern(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        int patternHash = createHash(pattern, patternLength);
        int textHash = createHash(text, patternLength);

        for (int i = 0; i <= textLength - patternLength; i++) {
            if (patternHash == textHash && text.substring(i, i + patternLength).equals(pattern)) {
                System.out.println("Pattern found at index: " + i);
            }
            if (i < textLength - patternLength) {
                textHash = recalculateHash(text, textHash, i, patternLength);
            }
        }
    }

    private static int createHash(String str, int length) {
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash += (int) (str.charAt(i) * Math.pow(PRIME, i));
        }
        return hash;
    }

    private static int recalculateHash(String text, int oldHash, int oldIndex, int patternLength) {
        int newHash = oldHash - text.charAt(oldIndex);
        newHash /= PRIME;
        newHash += (int) (text.charAt(oldIndex + patternLength) * Math.pow(PRIME, patternLength - 1));
        return newHash;
    }

    public static void main(String[] args) {
        String text = "ABABABABC";
        String pattern = "ABABC";
        searchPattern(text, pattern);
    }
}

