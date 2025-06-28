class Solution2 {

    private static final int INIT = 0;
    private static final int EXTRA = 1;

    public static int solution(int[] points, String tokens) {

        // Counting the number of adjacent T's
        int counter = INIT;

        // The sum
        int sum = INIT;

        int index = INIT;

        for (char c : tokens.toCharArray()) {
            if (c == 'T') {
                counter++;
                sum += points[index];
            } else {
                if (counter != INIT) {
                    sum += (counter - EXTRA);
                    counter = INIT;
                }
            }
            index++;
        }

        if (counter != INIT) {
            sum += (counter - EXTRA);
        }


        return sum;
    }

    public static void main(String[] args) {
        int[] points = {3,4,5,2,3};
        String str = "TEETT";
        System.out.println(solution(points, str));

    }
}