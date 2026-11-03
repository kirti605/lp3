//Write a program to solve a 0-1 Knapsack problem using dynamic programming or branch and bound strategy.


import java.util.*;

public class Knapsack01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int wt[] = new int[n + 1];
        int val[] = new int[n + 1];

        System.out.println("Enter value and weight of each item:");
        for (int i = 1; i <= n; i++) {
            val[i] = sc.nextInt();
            wt[i] = sc.nextInt();
        }

        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        int dp[][] = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (wt[i] <= w) {
                    dp[i][w] = Math.max(
                        val[i] + dp[i - 1][w - wt[i]],
                        dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("\nItems taken:");

        int w = W;
        boolean selected[] = new boolean[n + 1];

        for (int i = n; i >= 1; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selected[i] = true;
                w = w - wt[i];
            }
        }

        for (int i = 1; i <= n; i++) {
            if (selected[i]) {
                System.out.printf(
                    "Item (v=%.2f, w=%.2f) -> full%n",
                    (double) val[i], (double) wt[i]
                );
            } else {
                System.out.printf(
                    "Item (v=%.2f, w=%.2f) -> not taken%n",
                    (double) val[i], (double) wt[i]
                );
            }
        }

        System.out.printf(
            "%nMaximum value = %.2f%n", (double) dp[n][W]
        );

        sc.close();
    }
}

/*
DP is a method where we solve a big problem by solving smaller problems and storing their answers so that we don't have to calculate them again.

Algorithm (Dynamic Programming)
1. Accept the number of items.
2. Accept the weight and value of each item.
3. Accept the knapsack capacity.
4. Create a DP table of size (n+1) × (W+1).
5. Initialize the first row and column with zero.
6. For each item, check whether its weight fits into the current capacity.
7. If it fits, calculate both inclusion and exclusion values.
8. Store the maximum of these two values.
9. If it does not fit, copy the previous value.
10. Display the maximum value.
11. Trace backward through the DP table to find selected items.
5. Time and space complexity
Complexity	Value
Time complexity	\(O(n W)\)
Space complexity	\(O(n W)\)
Where:
- n = Number of items.
- W = Knapsack capacity.
Why O(n × W)?

Because the program uses two nested loops:
- Outer loop runs n times.
- Inner loop runs W times approximately.
Therefore, time complexity is \(O(nW)\).

Note: This is called pseudo-polynomial time because complexity depends on the numerical value of capacity W, not just the number of input items.

*/
