package com.sorting.visualizer.algorithms;

public class SortingFactory {
    public static SortingAlgorithm getAlgorithm(String name) {
        switch (name.toLowerCase()) {
            case "bubble":
                return new BubbleSort();
            case "quick":
                return new QuickSort();
            case "insertion":
                return new InsertionSort();
            case "selection":
                return new SelectionSort();
            case "merge":
                return new MergeSort();
            case "heap":
                return new HeapSort();
            case "radix":
                return new RadixSort();
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
    }
}
