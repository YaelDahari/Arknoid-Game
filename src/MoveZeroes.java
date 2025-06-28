public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        // Simplest solution: bubble sort
        int temp;
        int maxIndex = nums.length;
        for (int i = 0; i < maxIndex; i++) {
            if (nums[i] == 0) {
                temp = i;
                for (int j = i + 1; j < maxIndex; j++) {
                    nums[i] = nums[j];
                    i++;
                }
                i = temp - 1;
                nums[maxIndex - 1] = 0;
                maxIndex--;
            }
        }

    }

    public static void main(String[] args) {
        MoveZeroes m = new MoveZeroes();
        int[] nums = {0, 0, 1};
        m.moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
