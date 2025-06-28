import java.util.Scanner;
import java.util.stream.Stream;

class Heights {
    static int START = 0;
    static int EXTRA = 1;

    static int solution(Integer[] A) {
        Integer[] rows = new Integer[A.length];
        rows[START] = A[START];
        int row = START;
        boolean isInserted;
        for (int i = EXTRA; i < A.length; i++) {
            int val = A[i];
            isInserted = false;
            for (int j = 0; j <= row; j++) {
                if (val < rows[j]) {
                    rows[j] = val;
                    isInserted = true;
                    break;
                }
            }
            if (!isInserted) {
                row++;
                rows[row] = val;
            }
        }
        return row + EXTRA;
    }

    public static void main(String[] args) {
        // Read from stdin, solve the problem, write answer to stdout.
        Scanner in = new Scanner(System.in);
        Integer[] A = getIntegerArray(in.next());

        System.out.print(solution(A));
    }

    private static Integer[] getIntegerArray(String str) {
        return Stream.of(str.split("\\,"))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }
}