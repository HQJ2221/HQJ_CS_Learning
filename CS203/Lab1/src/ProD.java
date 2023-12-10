import java.util.Scanner;

public class ProD {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), S = in.nextInt();
        if (n >= 1 && n <= 3000 && S >= 1 && S <= 1000000000) {
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                int num = in.nextInt();
                if (num >= 1 && num <= 1000000000) {
                    nums[i] = num;
                }
            }
            long count = findThreeSum(nums, S);
            System.out.println(count);
        }
    }

    public static long findThreeSum(int[] nums, int S) {
        long total = nums[0] + nums[1] + nums[2] == S ? 1 : 0;
        int i = 0, j = 1, k = 2;
        while (sum(nums, i, j, k) <= S && k < nums.length - 1) {
            k++;
            if (sum(nums, i, j, k) == S) {
                total++;
            }
        }
        if (sum(nums, i, j, k) > S) {
            k--;
        }

        while (i + 2 < k) {
            // first turn, going up
            while (j + 1 < k && sum(nums, i, j, k) <= S) {
                j++;
                if (sum(nums, i, j, k) == S) {
                    total++;
                }
            }

            // second turn, going down
            do {
                if (j + 1 == k) {
                    j--;
                }
                k--;
            } while (i + 2 < k && sum(nums, i, i + 1, k) > S);
            if (sum(nums, i, j, k) == S) {
                total++;
            }

            while (i + 1 < j && sum(nums, i, j, k) >= S) {
                j--;
                if (sum(nums, i, j, k) == S) {
                    total++;
                }
            }

            do {
                if (i + 1 == j) {
                    j++;
                }
                i++;
            } while (i + 2 < k && sum(nums, i, k - 1, k) < S);
            if (sum(nums, i, j, k) == S) {
                total++;
            }
        }
        return total;
    }

    static int sum(int[] nums, int i, int j, int k) {
        return nums[i] + nums[j] + nums[k];
    }
}
