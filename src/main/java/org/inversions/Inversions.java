package org.inversions;

import java.util.Arrays;

public class Inversions {
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

  // Merges two arrays AND counts the number of inversions between them
  public static InversionResult mergeAndCount(int[] A, int[] B) {
    // mergeandcount
    int[] output = new int[A.length + B.length];
    int i = 0;
    int j = 0;
    int k = 0;
    int count = 0;

    while (i < A.length && j < B.length) {
      if (A[i] < B[j]) {
        output[k] = A[i];
        i++;
      } else {
        output[k] = B[j];
        j++;
        count += A.length - i;
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
    return new InversionResult(output, count);
  }

  // Sorts two arrays AND sums the number of inversions between them
  public static InversionResult sortAndCount(int[] arr) {
    if (arr.length == 1) {
      return new InversionResult(arr, 0);
    }
    int mid = arr.length / 2;
    int[] left = subArray(arr, 0, mid);
    int[] right = subArray(arr, mid, arr.length);
    InversionResult leftResult = sortAndCount(left);
    InversionResult rightResult = sortAndCount(right);
    InversionResult mergeResult = mergeAndCount(leftResult.arr, rightResult.arr);
    return new InversionResult(
        mergeResult.arr, leftResult.count + rightResult.count + mergeResult.count);
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Merge and Count Method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Merge and Count Method");
    System.out.println("Expected:");
    System.out.println("[1, 2, 3, 4, 5, 6, 7, 8]\n" + "8");

    System.out.println("\nGot:");

    int[] arr1 = {1, 3, 6, 8};
    int[] arr2 = {2, 4, 5, 7};
    InversionResult m1 = mergeAndCount(arr1, arr2);

    printArray(m1.arr);
    System.out.println(m1.count);

    // --------------------------
    // Test 2: Sort and Count
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Sort and Count");
    System.out.println("Expected:");
    System.out.println(
        """
                    [1, 2, 3, 4]
                    5
                    [1, 2, 3, 4, 5, 6, 7, 8]
                    7
                    [1, 2, 3, 4, 5, 6, 7, 8]
                    8""");

    System.out.println("\nGot:");

    InversionResult A = sortAndCount(new int[] {4, 2, 3, 1});
    printArray(A.arr);
    // [1, 2, 3, 4]
    System.out.println(A.count);
    // 5

    InversionResult B = sortAndCount(new int[] {2, 3, 4, 5, 6, 7, 8, 1});
    printArray(B.arr);
    // [1, 2, 3, 4, 5, 6, 7, 8]
    System.out.println(B.count);
    // 7

    InversionResult C = sortAndCount(new int[] {2, 3, 7, 4, 1, 5, 8, 6});
    printArray(C.arr);
    // [1, 2, 3, 4, 5, 6, 7, 8]
    System.out.println(C.count);
    // 8
  }

  static class InversionResult {
    int[] arr;
    int count;

    public InversionResult(int[] arr, int count) {
      this.arr = arr;
      this.count = count;
    }
  }
}
