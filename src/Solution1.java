import static java.lang.Math.min;

public class Solution1 {

    private static final int DIGITS = 5;
    private static final int START = 0;
    private static final int NONE = 0;
    private static final int INIT = 1;
    private static final int NEXT = 1;
    private static final int EXTRA = 1;
    private static final int MAX_DIGIT = 9;

    public static int solution(int S) {
        int[][] sum = new int[S + EXTRA][DIGITS];

        for (int i = 0; i < S + EXTRA; i++) {
            sum[i][START] = NONE;

            if (i > MAX_DIGIT) {
                sum[i][NEXT] = NONE;
            } else {
                sum[i][NEXT] = EXTRA;
            }
        }



        for (int i = 0; i < DIGITS; i++) {
            sum[START][i] = INIT;
        }

        for (int i = NEXT; i < S + EXTRA; i++) {
            for (int j = NEXT + NEXT; j < DIGITS; j++) {
                int limit = min(min(MAX_DIGIT, S), i);
                for (int k = 0; k <= limit; k++) {
                    sum[i][j] += sum[i - k][j - 1];
                }
            }
        }

        return sum[S][DIGITS - EXTRA];
    }

    public static void main(String[] args) {
        System.out.println(solution(35));
    }
}
