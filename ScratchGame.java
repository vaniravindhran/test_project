import java.util.*;

public class ScratchGame {
    private static final Random random = new Random();

    public static void main(String[] args) {
        // Sample configuration
        int rows = 3;
        int columns = 3;
        Map<String, Double> symbolProbabilities = new HashMap<>();
        symbolProbabilities.put("A", 50);
        symbolProbabilities.put("B", 25);
        symbolProbabilities.put("C", 10);
        symbolProbabilities.put("D", 5);
        symbolProbabilities.put("E", 3); 
        symbolProbabilities.put("F", 1.5); 

        Map<String, Double> bonusSymbolProbabilities = new HashMap<>();
        bonusSymbolProbabilities.put("10x", 10);
        bonusSymbolProbabilities.put("5x", 5);
        bonusSymbolProbabilities.put("+1000", 1000);
        bonusSymbolProbabilities.put("+500", 100);
        bonusSymbolProbabilities.put("MISS", 0); 

        // User's betting amount
        double betAmount = 10.0;

        // Generate matrix
        String[][] matrix = generateMatrix(rows, columns, symbolProbabilities);

        // Display generated matrix
        System.out.println("Generated Matrix:");
        for (String[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        // Calculate winnings
        double totalWinnings = calculateWinnings(matrix, symbolProbabilities, betAmount);
        System.out.println("Total Winnings: " + totalWinnings);
    }

    private static String[][] generateMatrix(int rows, int columns, Map<String, Double> symbolProbabilities) {
        String[][] matrix = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String symbol = getRandomSymbol(symbolProbabilities);
                matrix[i][j] = symbol;
            }
        }
        return matrix;
    }

    private static String getRandomSymbol(Map<String, Double> symbolProbabilities) {
        double randomNumber = random.nextDouble();
        double cumulativeProbability = 0.0;
        for (Map.Entry<String, Double> entry : symbolProbabilities.entrySet()) {
            cumulativeProbability += entry.getValue();
            if (randomNumber <= cumulativeProbability) {
                return entry.getKey();
            }
        }
        // Default to the last symbol
        return symbolProbabilities.keySet().iterator().next();
    }

    private static double calculateWinnings(String[][] matrix, Map<String, Double> symbolProbabilities, double betAmount) {
        // For simplicity, assume winnings are based on the number of occurrences of a specific symbol
        double totalWinnings = 0.0;
        for (Map.Entry<String, Double> entry : symbolProbabilities.entrySet()) {
            String symbol = entry.getKey();
            double probability = entry.getValue();
            int symbolCount = countSymbolOccurrences(matrix, symbol);
            // Example: If symbol A appears 2 times, the user wins 2 times their bet amount
            totalWinnings += symbolCount * betAmount * getSymbolReward(symbol);
        }
        return totalWinnings;
    }

    private static int countSymbolOccurrences(String[][] matrix, String symbol) {
        int count = 0;
        for (String[] row : matrix) {
            for (String cell : row) {
                if (cell.equals(symbol)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static double getSymbolReward(String symbol) {
        switch (symbol) {
            case "A":
                return 50;
            case "B":
                return 25;
            case "C":
                return 10;
            case "D":
                return 5;
            case "E":
                return 3;
            case "F":
                return 1.5;
            default:
                return 0;
        }
    }
}
