import java.io.*;
import java.util.*;
import java.util.Random;

public class MatrixOperations {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Case 1: Command-line arguments or user input for filenames
        if (args.length == 2) {
            
            String file1 = args[0];
            String file2 = args[1];
            int[][] matrix1 = readMatrixFromFile(file1);
            int[][] matrix2 = readMatrixFromFile(file2);

           
            if (matrix1[0].length != matrix2.length) {
                System.out.println("Matrix multiplication not possible with the given matrices.");
                return;
            }

           
            int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);

            
            writeMatrixToFile("matrix3.txt", resultMatrix);
            System.out.println("Matrix multiplication result saved in matrix3.txt");

        } else {
            // Case 2: Command-line arguments or user input for an integer
            System.out.print("Enter an integer to generate random square matrices: ");
            int n = scanner.nextInt();

            
            int[][] matrix1 = generateRandomMatrix(n);
            int[][] matrix2 = generateRandomMatrix(n);

            
            writeMatrixToFile("matrix1.txt", matrix1);
            writeMatrixToFile("matrix2.txt", matrix2);

            
            int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);

            
            writeMatrixToFile("matrix3.txt", resultMatrix);

            System.out.println("Random matrices saved in matrix1.txt and matrix2.txt. Multiplication result saved in matrix3.txt.");
        }
    }

    
    public static int[][] readMatrixFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<int[]> matrixList = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("\\s+");
            int[] row = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                row[i] = Integer.parseInt(tokens[i]);
            }
            matrixList.add(row);
        }
        reader.close();

        
        int[][] matrix = new int[matrixList.size()][];
        for (int i = 0; i < matrixList.size(); i++) {
            matrix[i] = matrixList.get(i);
        }

        return matrix;
    }

    
    public static void writeMatrixToFile(String filename, int[][] matrix) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (int[] row : matrix) {
            for (int val : row) {
                writer.write(val + " ");
            }
            writer.newLine();
        }
        writer.close();
    }

    
    public static int[][] generateRandomMatrix(int n) {
        Random rand = new Random();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(10); // Random integers from 0 to 9
            }
        }
        return matrix;
    }

    
    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;

        int[][] resultMatrix = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return resultMatrix;
    }
}
