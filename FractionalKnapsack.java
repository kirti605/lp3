//Write a program to solve a fractional Knapsack problem using a greedy method.


import java.util.*;

class Item {
    double value, weight;

    Item(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item items[] = new Item[n];

        System.out.println("Enter value and weight of each item:");
        for (int i = 0; i < n; i++) {
            double value = sc.nextDouble();
            double weight = sc.nextDouble();

            items[i] = new Item(value, weight);
        }

        System.out.print("Enter knapsack capacity: ");
        double W = sc.nextDouble();

        // Sort by value/weight ratio in descending order
        Arrays.sort(items, (a, b) ->
            Double.compare(b.value / b.weight, a.value / a.weight)
        );

        double totalValue = 0;
        double remaining = W;

        System.out.println("\nItems taken:");

        for (int i = 0; i < n && remaining > 0; i++) {

            if (items[i].weight <= remaining) {

                // Take the whole item
                totalValue += items[i].value;
                remaining -= items[i].weight;

                System.out.println("Item (v=" + items[i].value
                    + ", w=" + items[i].weight + ") -> full");

            } else {

                // Take a fraction of the item
                double fraction = remaining / items[i].weight;

                totalValue += items[i].value * fraction;

                System.out.printf("Item (v=%.2f, w=%.2f) -> fraction %.2f%n",
                    items[i].value, items[i].weight, fraction);

                remaining = 0;
            }
        }

        System.out.printf("%nMaximum value = %.2f%n", totalValue);

        sc.close();
    }
}

/*
The Knapsack Problem is an optimization problem in which we select items with given values and weights to maximize 
the total value without exceeding the capacity of the knapsack.

Fractional Knapsack is a problem in which we can take either the complete item or a fraction of an item to maximize the total value.

Types:
1. 0/1 Knapsack Problem: Each item can either be taken completely (1) or left behind (0). You cannot break an item into pieces.
2. Fractional Knapsack Problem: You can take a fraction or part of an item (like gold dust or liquids). This is easily solved using a greedy algorithm.

Algorithm (Greedy approach)
1. Accept the number of items.
2. Accept the value and weight of each item.
3. Calculate the value/weight ratio for every item.
4. Sort items in descending order of their ratio.
5. Select the item with the highest ratio first.
6. If the complete item fits, take the whole item.
7. Otherwise, take only the required fraction of that item.
8. Calculate the total value.
9. Display the maximum value obtained.

Time and space complexity
Operation	Complexity
Calculating ratios	\(O(n)\)
Sorting items	\(O(n log n)\)
Selecting items	\(O(n)\)
Overall time	\(O(n log n)\)
Space for storing items	\(O(n)\)


Enter number of items: 3

Enter value and weight of each item:
60 10
100 20
120 30

Enter knapsack capacity: 50
*/
