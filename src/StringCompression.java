public class StringCompression {
    public int compress(char[] chars) {
        int len = chars.length, sum = 1, startIndex = 1;
        char currentChar = chars[0], nextChar;
        for (int i = 1; i < len; i++) {
            nextChar = chars[i];
            if (nextChar == currentChar)  {
                sum++;
                chars[i] = '-';
            } else {
                // Insert the sum unless it's 1
                if (sum != 1) {
                    String str = String.valueOf(sum);
                    char[] arr = str.toCharArray();
                    int length = arr.length;
                    for (int j = 0; j < length; j++) {
                        chars[startIndex] = arr[j];
                        startIndex++;
                    }
                }

                // Reset the variables
                sum = 1;
                startIndex = i + 1;
                currentChar = nextChar;
            }
        }

        moveMinusesToRight(chars);

        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (chars[i] == '-') {
                count++;
            } else {
                break;
            }
        }

        return len - count;

    }

    public void moveMinusesToRight(char[] chars) {
        int left = 0;
        int len = chars.length;
        for (int right = 0; right < len; right++) {
            if (chars[right] != '-') {
                char temp = chars[right];
                chars[right] = chars[left];
                chars[left] = temp;
                left++;
            }
        }
    }

    public static void main(String[] args) {
        StringCompression s = new StringCompression();
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(s.compress(chars));
    }
}

