import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.Math.abs;
import static java.lang.Math.min;

class Processes {

    static int solution(Integer[] loads) {
        // let's implement dynamic programming (like nap-sack)
        Integer sum = sumArray(loads);
        Integer half = sum / 2;
        Integer[][] m = new Integer[loads.length][half + 1];
        for (int i = 0; i < loads.length; i++) {
            m[i][0] = 0;
        }
        for (int i = 1; i < half + 1; i++) {
            m[0][i] = min(abs(i - loads[0]), i);
        }
        for (int i = 1; i < loads.length; i++) {
            int val = loads[i];
            for (int j = 1; j < half; j++) {
                m[i][j] = min(abs(j - loads[i]), val);
//                    m[i][j] = min(m[i - 1][j], m[i - 1][abs(j - val)]);

            }
        }

        return m[loads.length - 1][half - 1];

//        System.err.println("Tip: Use System.err.println() to write debug messages on the output tab.");
//        return 0;
    }

    static int sumArray(Integer[] loads) {
        int sum = 0;
        for (int i = 0; i < loads.length; i++) {
            sum += loads[i];
        }
        return sum;
    }

    public static void main(String[] args) {

        Integer[] loads1 = {1,1,9,8,9,3};
        Integer[] loads2 = {1,2,3,4,5};
        Integer[] loads3 = {5,6,1};
        Integer[] loads = {3,2,20,5,4,1,5};

        System.out.print(solution(loads));
    }

    private static Integer[] getIntegerArray(String str) {
        return Stream.of(str.split("\\,"))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }
}