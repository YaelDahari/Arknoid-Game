public class Main {
    public static void main(String[] args) {
        String word = "??z???";
        String substr = "zz";

        String output = findLowest(word, substr);
        System.out.println(output);
    }

    public static String findLowest(String word, String substr) {
        int len_word = word.length();
        int len_substr = substr.length();

        for (int i = len_word - len_substr; i >= 0; i--) {
            if (canInsertSub(word, substr, i)) {
                StringBuilder output = new StringBuilder();
                output.append(word.substring(0, i));
                output.append(substr);
                output.append(word.substring(i + len_substr));
                return changeRemainingChars(output.toString());
            }
        }

        return "-1";
    }

    public static boolean canInsertSub(String word, String substr, int i) {
        for (int j = 0; j < substr.length(); j++) {
            if (word.charAt(i) == '?' || word.charAt(i) == substr.charAt(j)) {
                i++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static String changeRemainingChars(String word) {
        int len_word = word.length();
        char[] arr = word.toCharArray();
        for (int i = 0; i < len_word; i++) {
            if (arr[i] == '?') {
                arr[i] = 'a';
            }
        }
        return new String(arr);
    }
}