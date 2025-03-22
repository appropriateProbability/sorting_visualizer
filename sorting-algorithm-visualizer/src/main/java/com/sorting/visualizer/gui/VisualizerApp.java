package com.sorting.visualizer.gui;

import com.sorting.visualizer.algorithms.SortingAlgorithm;
import com.sorting.visualizer.algorithms.SortingFactory;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.Random;

public class VisualizerApp extends Application {

    private TextArea outputArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sorting Algorithm Visualizer");

        // Dropdown for selecting sorting algorithm
        ComboBox<String> algorithmChoice = new ComboBox<>();
        algorithmChoice.getItems().addAll("Bubble", "Quick", "Insertion", "Selection", "Merge", "Heap", "Radix");
        algorithmChoice.setValue("Bubble");

        // Input field for array size
        TextField arraySizeInput = new TextField();
        arraySizeInput.setPromptText("Enter array size");

        // Start button
        Button startButton = new Button("Sort");

        // Output area
        outputArea = new TextArea();
        outputArea.setEditable(false);

        // Action for the Start button
        startButton.setOnAction(e -> {
            String selectedAlgorithm = algorithmChoice.getValue();
            int arraySize = Integer.parseInt(arraySizeInput.getText());
            int[] array = generateRandomArray(arraySize);
            visualizeSorting(selectedAlgorithm, array);
        });

        // Layout
        HBox controls = new HBox(10, algorithmChoice, arraySizeInput, startButton);
        controls.setAlignment(Pos.CENTER);
        VBox layout = new VBox(10, controls, outputArea);
        layout.setAlignment(Pos.CENTER);

        // Scene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(100);  // Random numbers between 0 and 99
        }
        return array;
    }

    private void visualizeSorting(String algorithmName, int[] array) {
        try {
            SortingAlgorithm algorithm = SortingFactory.getAlgorithm(algorithmName.toLowerCase());
            long startTime = System.nanoTime();
            outputArea.appendText("Unsorted Array: " + Arrays.toString(array) + "\n");
            algorithm.sort(array);
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1_000_000.0;

            outputArea.appendText("Algorithm: " + algorithm.getName() + "\n");
            outputArea.appendText("Array Size: " + array.length + "\n");
            outputArea.appendText("Sorted Array: " + Arrays.toString(array) + "\n");
            outputArea.appendText("Time Taken: " + duration + " ms\n");
            outputArea.appendText("--------------------------------------------\n");

        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }
}
