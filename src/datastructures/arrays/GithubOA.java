package datastructures.arrays;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class GithubOA {
    public static int solution(int U, int[] weight) {
        Arrays.sort(weight);
        return minTurnBacks(U, weight, 0, weight.length - 1) - 1;
    }

    private static int minTurnBacks(int U, int[] weight, int left, int right) {
        if (left >= right) {
            return 0; // Base case: no cars to process
        }

        int minTurns = Integer.MAX_VALUE;

        // Consider the case where no cars cross the bridge
        int noCross = right - left + 1;
        minTurns = Math.min(minTurns, noCross);

        // Try all combinations of two cars to cross the bridge
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                if (weight[i] + weight[j] <= U) {
                    // Recursively process the remaining cars after these two cross
                    int turns = minTurnBacks(U, weight, i + 1, j - 1);
                    minTurns = Math.min(minTurns, turns + (right - j + 1));
                }
            }
        }

        return minTurns;
    }

    public static void main(String[] args){
        int[] weight1 = {5, 3, 8, 1, 8, 7, 7, 6};
        int[] weight2 = {7, 6, 5, 2, 7, 4, 5, 4};
        int[] weight3 = {3, 4, 3, 1};

        System.out.println(solution(9, weight1)); // Output: 4
        System.out.println(solution(7, weight2)); // Output: 5
        System.out.println(solution(7, weight3)); // Output: 0
    }
}
