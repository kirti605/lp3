//Write a non-recursive and a recursive program to calculate Fibonacci numbers and analyze their time and space complexity.

//Write a non-recursive and a recursive program to calculate Fibonacci numbers and analyze their time and space complexity.


import java.util.Scanner;

public class daa1 {

    // Non-recursive method
    static int fibIter(int n) {
        if (n <= 1)
            return n;

        int a = 0, b = 1, c;

        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return b;
    }

    // Recursive method
    static int fibRec(int n) {
        if (n <= 1)
            return n;

        return fibRec(n - 1) + fibRec(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        System.out.print("Iterative Fibonacci: ");
        for (int i = 0; i < n; i++)
            System.out.print(fibIter(i) + " ");

        System.out.print("\nRecursive Fibonacci: ");
        for (int i = 0; i < n; i++)
            System.out.print(fibRec(i) + " ");

        sc.close();
    }
}
/* 
Iterative: Uses a loop and only three variables to calculate each next number. Therefore, time is \(O(n)\) and auxiliary space is \(O(1)\).
Recursive: Calls itself twice for most values of \(n\), causing repeated calculations. Therefore, time is \(O(2^n)\) and maximum call-stack space is \(O(n)\).
*/