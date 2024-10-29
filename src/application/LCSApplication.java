package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LCSApplication extends Application {

    private TextArea costArea;
    private TextArea directionArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Longest Common Subsequence (LCS)");

        // Create GUI components
        Label label1 = new Label("Enter First String:");
        TextField textField1 = new TextField();
        
        Label label2 = new Label("Enter Second String:");
        TextField textField2 = new TextField();
        
        Button computeButton = new Button("Compute LCS");
        computeButton.getStyleClass().add("button"); // Add button style

        // Text areas for displaying results
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.getStyleClass().add("text-area"); // Add text area style
        
        costArea = new TextArea(); // Initialize cost area
        costArea.setEditable(false);
        costArea.getStyleClass().add("text-area"); // Add text area style
        
        directionArea = new TextArea(); // Initialize direction area
        directionArea.setEditable(false);
        directionArea.getStyleClass().add("text-area"); // Add text area style

        // Event handler for the button
        computeButton.setOnAction(e -> {
            String x = textField1.getText().trim();
            String y = textField2.getText().trim();
            if (!x.isEmpty() && !y.isEmpty()) {
                String lcsResult = computeLCS(x, y);
                resultArea.setText("LCS of \"" + x + "\" and \"" + y + "\" is: " + lcsResult);
            } else {
                resultArea.setText("Please enter both strings.");
            }
        });

        // Layout setup using HBox for input fields
        HBox inputLayout = new HBox(10);
        inputLayout.getChildren().addAll(label1, textField1, label2, textField2, computeButton);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(inputLayout, resultArea, costArea, directionArea);

        // Create and set the scene with CSS
        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add(getClass().getResource("ayman.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String computeLCS(String x, String y) {
        char[] X = x.toCharArray();
        char[] Y = y.toCharArray();
        int m = X.length;
        int n = Y.length;
        int[][] b = new int[m + 1][n + 1];
        int[][] c = computeLCSLength(X, Y, b);

        StringBuilder lcs = new StringBuilder();
        printLCS(b, X, m, n, lcs);
        displayCostAndDirectionTables(c, b);

        return lcs.toString();
    }

    private static int[][] computeLCSLength(char[] x, char[] y, int[][] b) {
        int m = x.length;
        int n = y.length;
        int[][] c = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            c[i][0] = 0; 
        }
        for (int j = 0; j <= n; j++) {
            c[0][j] = 0; 
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i - 1] == y[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1; 
                    b[i][j] = 1; 
                } else if (c[i - 1][j] >= c[i][j - 1]) { 
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 2; 
                } else { 
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 3; 
                }
            }
        }
        return c; 
    }

    private void displayCostAndDirectionTables(int[][] c, int[][] b) {
        StringBuilder costTable = new StringBuilder("Cost Table:\n");
        StringBuilder directionTable = new StringBuilder("Direction Table:\n");

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                costTable.append(c[i][j]).append("\t");
                directionTable.append(getDirectionSymbol(b[i][j])).append("\t");
            }
            costTable.append("\n");
            directionTable.append("\n");
        }

        costArea.setText(costTable.toString());
        directionArea.setText(directionTable.toString());
    }

    private String getDirectionSymbol(int direction) {
        return switch (direction) {
            case 1 -> "↖"; 
            case 2 -> "↑"; 
            case 3 -> "←"; 
            default -> "0"; 
        };
    }

    private static void printLCS(int[][] b, char[] x, int i, int j, StringBuilder lcs) {
        if (i == 0 || j == 0) {
            return; 
        }
        if (b[i][j] == 1) { 
            printLCS(b, x, i - 1, j - 1, lcs);
            lcs.append(x[i - 1]); 
        } else if (b[i][j] == 2) { 
            printLCS(b, x, i - 1, j, lcs);
        } else { 
            printLCS(b, x, i, j - 1, lcs);
        }
    }

    public static void main(String[] args) {
        launch(args); 
    }
}
