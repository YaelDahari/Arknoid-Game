import java.util.regex.*;

class Solution {
    static final int EXTRA = 1;
    static final int START = 0;
    /** This method finds the words in s, reverse each word and append it to result and then reverses ersult and returns it. This way the approach is O(n), because I'm using the append and reverse methods of the class StringBuilder. **/
    public String reverseWords(String s) {
        // Pattern that matches any non-space characters at least once and then a single space
        Pattern space = Pattern.compile("[^ ]+ ", Pattern.CASE_INSENSITIVE);
        Matcher matcher = space.matcher(s);
        int start, end = START;
        StringBuilder result = new StringBuilder(), str;
        boolean isOneWord = false, isFirst = true;

        // Seeking words in s
        while (matcher.find()) {

            // Initializing the start and end indexes and creating the word sub-string
            start = matcher.start();
            end = matcher.end() - EXTRA;
            str = new StringBuilder(s.substring(start, end));

            // If it's not the first word, add a space
            if (!isFirst) {
                str.append(" ");
            } else {
                // The first word occurs only once
                isFirst = false;
            }

            // Reversing the string and appending it to the result
            str.reverse();
            result.append(str);

        }

        // In the case that there's only a single word
        if (end == START) {
            isOneWord = true;
        }

        // Add the last word to result, if it exists
        String rest = s.substring(end);
        char[] arr = rest.toCharArray();
        start = START;
        end = rest.length() - EXTRA;

        // Ignoring whitespace
        while (start != end && arr[start] == ' ') {
            start++;
        }
        while (start != end && arr[end] == ' ') {
            end--;
        }

        // If there's a last word
        if (arr[start] != ' ') {
            // Create the new sub-string according to the indexes
            str = new StringBuilder(rest.substring(start, end + EXTRA));
            // We need to add space iff there are other words
            if (!isOneWord) {
                str.append(" ");
            }
            // Reverse the string and append to result
            str.reverse();
            result.append(str);
        }

        // Reverse the reult and return it as a string
        result.reverse();
        return result.toString();
    }

    public static void main(String[] args) {
        String s1 = " the sky is blue";
        Solution solution = new Solution();
        System.out.println(solution.reverseWords(s1));

        String s2 = "  hello world  ";
        System.out.println(solution.reverseWords(s2));

        String s3 = "what";
        System.out.println(solution.reverseWords(s3));

        String s4 = "     i   ";
        System.out.println(solution.reverseWords(s4));

        String s5 = " asdasd df f";
        System.out.println(solution.reverseWords(s5));
    }
}