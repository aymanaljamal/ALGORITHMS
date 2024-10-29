package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MatrixChainMultiplicationFX extends Application {

    // Function to perform Matrix Chain Multiplication
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the UI components
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        Button calculateButton = new Button("Calculate Matrix Chain Multiplication");
        TextField dimensionsInput = new TextField();
        dimensionsInput.setPromptText("Enter dimensions (e.g., 4,2,3,1,2,2,3)");

        // Action when the button is clicked
        calculateButton.setOnAction(e -> {
            // Get dimensions from user input
            String input = dimensionsInput.getText().trim();
            if (input.isEmpty()) {
                outputArea.setText("Please enter dimensions.");
                return;
            }
            
            String[] dimensionStrings = input.split(",");
            int n = dimensionStrings.length;

            // Convert input to integer array
            int[] dimensions = new int[n];
            try {
                for (int i = 0; i < n; i++) {
                    dimensions[i] = Integer.parseInt(dimensionStrings[i].trim());
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter valid integers.");
                return;
            }

            // Initialize the cost and split matrices
            int[][] cost = new int[n - 1][n - 1];
            int[][] best = new int[n - 1][n - 1];

            // Calculate the minimum cost of multiplication
            for (int length = 2; length <= n; length++) { // length of the chain
                for (int i = 0; i <= n - length; i++) {
                    int j = i + length - 1; // end index of the chain
                    cost[i][j - 1] = Integer.MAX_VALUE; // Initialize to max value
                    // Try all possible split points
                    for (int k = i; k < j; k++) {
                        int t = (i == k ? 0 : cost[i][k - 1]) + (k + 1 == j ? 0 : cost[k][j - 1]) +
                                dimensions[i] * dimensions[k] * dimensions[j];
                        if (t < cost[i][j - 1]) {
                            cost[i][j - 1] = t; // Update minimum cost
                            best[i][j - 1] = k; // Update best split point
                        }
                    }
                }
            }

            // Prepare output
            StringBuilder output = new StringBuilder();
            output.append("Minimum cost of multiplication: ").append(cost[0][n - 2]).append("\n");
            output.append("Optimal parenthesization: ");
            output.append(printOptimalParenthesization(best, 0, n - 2)).append("\n\n");
            output.append("Cost Array:\n").append(arrayToString(cost)).append("\n");
            output.append("Best Array:\n").append(arrayToString(best));

            // Display results in the output area
            outputArea.setText(output.toString());
        });

        // Set up the layout
        VBox vbox = new VBox(10, new Label("Enter Matrix Dimensions:"), dimensionsInput, calculateButton, outputArea);
        Scene scene = new Scene(vbox, 600, 400);

        // Configure the stage
        primaryStage.setTitle("Matrix Chain Multiplication");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Function to print the optimal parenthesization
    private String printOptimalParenthesization(int[][] best, int i, int j) {
        if (i == j) {
            return "A" + (i + 1);
        } else {
            return "(" + printOptimalParenthesization(best, i, best[i][j]) +
                   printOptimalParenthesization(best, best[i][j] + 1, j) + ")";
        }
    }

    // Function to convert a 2D array to a string
    private String arrayToString(int[][] array) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : array) {
            for (int val : row) {
                sb.append(val).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
