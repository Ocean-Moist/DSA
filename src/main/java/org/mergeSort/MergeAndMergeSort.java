package org.mergeSort;

import java.util.Arrays;

public class MergeAndMergeSort {
  // Helper method to create subArrays from arrays
  // The indexes work just like substring: begin is included
  // but end is excluded
  public static int[] subArray(int[] arr, int begin, int end) {
    int[] output = new int[end - begin];
    for (int i = 0; i < output.length; i++) {
      output[i] = arr[begin + i];
    }
    return output;
  }

  // Helper method to print array to aid debugging
  public static void printArray(int[] arr) {
    System.out.println(Arrays.toString(arr));
  }

  // Give two sorted arrays A and B return one
  // large combined sorted array
  public static int[] merge(int[] A, int[] B) {
    // merge
    int[] output = new int[A.length + B.length];
    int i = 0;
    int j = 0;
    int k = 0;

    while (i < A.length && j < B.length) {
      if (A[i] < B[j]) {
        output[k] = A[i];
        i++;
      } else {
        output[k] = B[j];
        j++;
      }
      k++;
    }
    while (i < A.length) {
      output[k] = A[i];
      i++;
      k++;
    }
    while (j < B.length) {
      output[k] = B[j];
      j++;
      k++;
    }
    return output;
  }

  // Recursively sorts the array using Merge Sort
  public static int[] mergeSort(int[] arr) {
    if (arr.length == 1) {
      return arr;
    }

    int mid = arr.length / 2;
    int[] left = subArray(arr, 0, mid);
    int[] right = subArray(arr, mid, arr.length);
    return merge(mergeSort(left), mergeSort(right));
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Merge Method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Merge Method");
    System.out.println("Expected:");
    System.out.println("[1, 2, 3, 4, 5, 7, 8, 10]\n" + "[1, 2, 3, 4, 5, 7, 8, 10]");

    System.out.println("\nGot:");

    int[] l = {1, 3, 4, 7};
    int[] r = {2, 5, 8, 10};
    printArray(merge(l, r));
    printArray(merge(r, l));

    // --------------------------
    // Test 2: Merge Sort
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Merge Sort");
    System.out.println("Expected:");
    System.out.println("[1, 2, 4, 12, 15, 23, 24, 28, 32, 43, 51, 56, 63, 67, 87, 94]");

    System.out.println("\nGot:");

    int[] arr = {4, 1, 23, 51, 2, 67, 12, 32, 87, 43, 56, 63, 28, 94, 15, 24};
    printArray(mergeSort(arr));
    // [1, 2, 4, 12, 15, 23, 24, 28, 32, 43, 51, 56, 63, 67, 87, 94]
  }
}
