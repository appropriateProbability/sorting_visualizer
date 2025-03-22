package com.sorting.visualizer.algorithms;

public class MergeSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; ++i)
            leftArr[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            rightArr[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                array[k] = leftArr[i];
                i++;
            } else {
                array[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArr[j];
            j++;
            k++;
        }
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }
}
