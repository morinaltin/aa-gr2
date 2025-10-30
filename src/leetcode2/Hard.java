package leetcode2;

import java.util.Arrays;

public class Hard {
    public int uniqueLetterString(String s) {
        int totalSum = 0;
        int uniqueCharsEndingHere = 0;

        int[] contributionAtLastPosition = new int[26];
        int[] lastSeenPosition = new int[26];

        Arrays.fill(lastSeenPosition, -1);

        for (int currentIndex = 0; currentIndex < s.length(); ++currentIndex) {
            final int charIndex = s.charAt(currentIndex) - 'A';
            final int newContribution = currentIndex - lastSeenPosition[charIndex];

            uniqueCharsEndingHere -= contributionAtLastPosition[charIndex];
            uniqueCharsEndingHere += newContribution;

            contributionAtLastPosition[charIndex] = newContribution;
            lastSeenPosition[charIndex] = currentIndex;

            totalSum += uniqueCharsEndingHere;
        }

        return totalSum;
    }
}