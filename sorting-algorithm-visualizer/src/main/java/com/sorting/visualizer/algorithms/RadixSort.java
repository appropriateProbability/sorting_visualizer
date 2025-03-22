package com.sorting.visualizer.algorithms;

public class RadixSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        int max = getMax(array);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(array, exp);
        }
    }

    private int getMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private void countSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int num : array) {
            count[(num / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int num = array[i];
            output[count[(num / exp) % 10] - 1] = num;
            count[(num / exp) % 10]--;
        }

        System.arraycopy(output, 0, array, 0, n);
    }

    @Override
    public String getName() {
        return "Radix Sort";
    }
}
