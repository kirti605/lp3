//Write a program to implement Huffman Encoding using a greedy strategy.


import java.util.*;

class Node {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        left = right = null;
    }
}

public class HuffmanEncoding {

    // Print Huffman codes
    static int printCodes(Node root, String code) {
        if (root == null)
            return 0;

        // Leaf node
        if (root.left == null && root.right == null) {
            if (code.isEmpty())
                code = "0";

            System.out.println(root.ch + "\t" + root.freq + "\t" + code);

            return root.freq * code.length();
        }

        int leftBits = printCodes(root.left, code + "0");
        int rightBits = printCodes(root.right, code + "1");

        return leftBits + rightBits;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of characters: ");
        int n = sc.nextInt();

        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.freq, b.freq)
        );

        System.out.println("Enter character and frequency:");

        for (int i = 0; i < n; i++) {
            char ch = sc.next().charAt(0);
            int freq = sc.nextInt();

            pq.add(new Node(ch, freq));
        }

        // Build Huffman tree
        while (pq.size() > 1) {

            Node left = pq.poll();
            Node right = pq.poll();

            Node parent = new Node('\0', left.freq + right.freq);

            parent.left = left;
            parent.right = right;

            pq.add(parent);
        }

        Node root = pq.poll();

        System.out.println("\nHuffman Codes:");
        System.out.println("Char\tFreq\tCode");

        int totalBits = printCodes(root, "");

        System.out.println("\nTotal bits after encoding: " + totalBits);

        sc.close();
    }
}

/*
Huffman Encoding is a lossless data compression technique that assigns shorter binary codes to frequently 
occurring characters and longer codes to less frequent characters.

Time and space complexity
Operation	Complexity
Creating priority queue	\(O(n)\)
Building Huffman tree	\(O(n\log n)\)
Generating codes	\(O(nh)\)
Overall time	\(O(n\log n)\)
Space complexity	\(O(n)\)

Where:
- n = Number of characters.
- h = Height of the Huffman tree.
The tree-building process takes \(O(n\log n)\) time because we repeatedly remove and insert nodes into the priority queue.

The tree requires \(O(n)\) space.

Greedy Algorithm is used in Huffman Encoding.

main objective of Huffman Encoding is To reduce the total number of bits required to store or transmit data.

Why is Huffman Encoding called a greedy algorithm?
Because it repeatedly selects the two nodes with the smallest frequencies and combines them to build the Huffman tree.

Total bits = Frequency × Code length for every character.

Algorithm (Greedy approach)
1. Accept the number of characters and their frequencies.
2. Create a leaf node for each character.
3. Insert all nodes into a min-priority queue.
4. Remove the two nodes having the smallest frequencies.
5. Create a new parent node with their combined frequency.
6. Make the two removed nodes its left and right children.
7. Insert the new parent node back into the priority queue.
8. Repeat until only one node remains.
9. Traverse the Huffman tree to generate binary codes.
10. Calculate and display the total encoded bits.

*/