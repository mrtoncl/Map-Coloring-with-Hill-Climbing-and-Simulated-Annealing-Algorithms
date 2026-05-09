import java.util.Random;

public class hw3 {

    // Generates a random graph with sparse edges (probability 4/N)
    private static int[][] graphGenerator(int N) {
        int[][] graph = new int[N][N];
        double prob_of_ones = 4.0 / N;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (Math.random() < prob_of_ones) {
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                } else {
                    graph[i][j] = 0;
                    graph[j][i] = 0;
                }
            }
        }
        return graph;
    }

    // Assigns a random color (1 to 4) to each node
    private static int[] randomColorAssignment(int N) {
        int[] colors = new int[N];
        for (int i = 0; i < N; i++) {
            colors[i] = (int) (1 + Math.random() * 4);
        }
        return colors;
    }

    // Computes the total number of conflicting edges (same color on both ends)
    private static int computeConflictCount(int[][] graph, int[] graphColors) {
        int conflictCount = 0;
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (graph[i][j] == 1 && graphColors[i] == graphColors[j]) {
                    conflictCount++;
                }
            }
        }
        return conflictCount;
    }

    // Local search using Hill Climbing: Greedily picks the best color for conflicted nodes
    private static int hillClimbing(int[][] graph, int[] graphColors, int conflictCount) {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 1 && graphColors[i] == graphColors[j]) {
                    int originalColor = graphColors[i];
                    int bestColor = originalColor;
                    int minConflict = conflictCount;

                    // Try all other 3 colors (colors are 1, 2, 3, 4)
                    for (int c = 1; c <= 4; c++) {
                        if (c != originalColor) {
                            graphColors[i] = c;
                            int currentConflict = computeConflictCount(graph, graphColors);
                            if (currentConflict < minConflict) {
                                minConflict = currentConflict;
                                bestColor = c;
                            }
                        }
                    }
                    // Apply the best color found
                    graphColors[i] = bestColor;
                    conflictCount = minConflict;

                }
            }
        }
        return conflictCount;
    }

    // Local search using Simulated Annealing: Accepts worse states with some probability to escape local optima
    private static int simulatedAnnealing(int[][] graph, int[] graphColors, int conflictCount) {
        double T = 100;
        double alpha = 0.99;
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int minConflict = conflictCount;
                if (graph[i][j] == 1 && graphColors[i] == graphColors[j]) {
                    graphColors[i] = (int) (1 + Math.random() * 4);

                }
                int currentConflict = computeConflictCount(graph, graphColors);
                int delta_E = currentConflict - minConflict;
                
                // If it's a better state, accept it
                if (delta_E < 0) {
                    minConflict = currentConflict;
                } 
                // If it's a worse state, accept with probability P = e^(-delta_E / T)
                else if (delta_E > 0) {
                    double P = Math.exp(-delta_E / T);
                    if (Math.random() < P) {
                        minConflict = currentConflict;
                    }
                }
                T = T * alpha; // Decrease temperature
                conflictCount = minConflict;
            }
        }

        return conflictCount;
    }

    public static void main(String[] args) {
        int[] N_Values = { 10, 20, 50, 100 };
        for (int n : N_Values) {
            int hcSuccess = 0;
            int hcFailedConflicts = 0;
            int hcFailedCount = 0;

            int saSuccess = 0;
            int saFailedConflicts = 0;
            int saFailedCount = 0;

            for (int run = 0; run < 10; run++) {
                int[][] graph = graphGenerator(n);

                // --- Hill Climbing ---
                int[] hcColors = randomColorAssignment(n);
                int hcInitialConflicts = computeConflictCount(graph, hcColors);
                int hcFinalConflicts = hillClimbing(graph, hcColors, hcInitialConflicts);

                // Success is defined as reaching exactly 0 conflicts
                if (hcFinalConflicts == 0) {
                    hcSuccess++;
                } else {
                    hcFailedConflicts += hcFinalConflicts;
                    hcFailedCount++;
                }

                // --- Simulated Annealing ---
                int[] saColors = randomColorAssignment(n);
                int saInitialConflicts = computeConflictCount(graph, saColors);
                int saFinalConflicts = simulatedAnnealing(graph, saColors, saInitialConflicts);

                // Success is defined as reaching exactly 0 conflicts
                if (saFinalConflicts == 0) {
                    saSuccess++;
                } else {
                    saFailedConflicts += saFinalConflicts;
                    saFailedCount++;
                }
            }

            // Averages over 10 runs
            double hcSuccessRate = (hcSuccess / 10.0) * 100.0;
            double hcAvgFailedConflicts = hcFailedCount > 0 ? (double) hcFailedConflicts / hcFailedCount : 0.0;
            int avgIterations = n * n; // Both algorithms run N*N loops

            double saSuccessRate = (saSuccess / 10.0) * 100.0;
            double saAvgFailedConflicts = saFailedCount > 0 ? (double) saFailedConflicts / saFailedCount : 0.0;

            // Output Results
            System.out.println("---- Results for N = " + n + " ----");
            System.out.println("Hill Climbing:");
            System.out.println("  Success Rate: " + String.format("%.2f", hcSuccessRate) + "%");
            System.out.println("  Average Iterations: " + avgIterations);
            System.out.println("  Final Conflicts (Avg for failed runs): " + String.format("%.2f", hcAvgFailedConflicts));

            System.out.println("Simulated Annealing:");
            System.out.println("  Success Rate: " + String.format("%.2f", saSuccessRate) + "%");
            System.out.println("  Average Iterations: " + avgIterations);
            System.out.println("  Final Conflicts (Avg for failed runs): " + String.format("%.2f", saAvgFailedConflicts));
            System.out.println();
        }
    }
}