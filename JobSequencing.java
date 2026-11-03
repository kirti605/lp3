//Implement job sequencing with deadlines using a greedy method.


import java.util.*;

class Job {
    char id;
    int deadline, profit;

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        Job jobs[] = new Job[n];

        System.out.println("Enter job id, deadline and profit:");
        for (int i = 0; i < n; i++) {
            char id = sc.next().charAt(0);
            int deadline = sc.nextInt();
            int profit = sc.nextInt();

            jobs[i] = new Job(id, deadline, profit);
        }

        // Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int maxDeadline = 0;
        for (Job j : jobs) {
            maxDeadline = Math.max(maxDeadline, j.deadline);
        }

        char slot[] = new char[maxDeadline + 1];
        boolean filled[] = new boolean[maxDeadline + 1];

        int totalProfit = 0, count = 0;

        // Schedule jobs
        for (int i = 0; i < n; i++) {
            for (int t = jobs[i].deadline; t >= 1; t--) {

                if (!filled[t]) {
                    filled[t] = true;
                    slot[t] = jobs[i].id;

                    totalProfit += jobs[i].profit;
                    count++;
                    break;
                }
            }
        }

        System.out.print("\nJob sequence: ");
        for (int t = 1; t <= maxDeadline; t++) {
            if (filled[t]) {
                System.out.print(slot[t] + " ");
            }
        }

        System.out.println("\nJobs scheduled: " + count);
        System.out.println("Total profit: " + totalProfit);

        sc.close();
    }
}
