package org.randomizedAlgorithims;

public class QuickSort {

  // Simple insertion sort to use in the base case of quick sort
  // This method uses in place sorting and sorts everything between start and end, inclusive
  public static void insertionSort(int[] arr, int start, int end) {
    for (int i = start + 1; i <= end; i++) {
      int key = arr[i];
      int j = i - 1;

      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }
  }

  // Wrapper method for quickSort
  public static void quickSort(int[] arr) {
    qSort(arr, 0, arr.length - 1);
  }

  // Recursive method for quickSort
  public static void qSort(int[] arr, int start, int end) {
    if (start < end) {
      int pivot = partition(arr, start, end);
      qSort(arr, start, pivot - 1);
      qSort(arr, pivot + 1, end);
    }
  }

  private static int partition(int[] arr, int start, int end) {
    int pivot = arr[end];
    int i = start - 1;

    for (int j = start; j < end; j++) {
      if (arr[j] <= pivot) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }

    int temp = arr[i + 1];
    arr[i + 1] = arr[end];
    arr[end] = temp;

    return i + 1;
  }

  // Array printer
  public static void printArray(int[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length - 1; i++) {
      System.out.print(arr[i] + ", ");
    }
    System.out.println(arr[arr.length - 1] + "]");
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: QuickSort
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: QuickSort");
    System.out.println("Expected:");
    System.out.println(
        "[1, 2, 3, 4, 5, 6, 7, 8, 9]\n"
            + "[12, 21, 23, 34, 37, 41, 43, 71, 73, 74, 134, 234, 356]");

    System.out.println("\nGot:");

    int[] arr1 = {3, 1, 6, 7, 5, 8, 4, 9, 2};
    quickSort(arr1);
    printArray(arr1);

    int[] arr2 = {12, 37, 74, 356, 23, 41, 134, 71, 234, 73, 21, 34, 43};
    quickSort(arr2);
    printArray(arr2);

    // --------------------------
    // Test 2: Big QuickSort
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Big QuickSort");
    System.out.println("Expected:");
    System.out.println(
        "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,"
            + " 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45,"
            + " 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66,"
            + " 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87,"
            + " 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99]\n");

    System.out.println("\nGot:");

    int[] arr3 = {
      61, 8, 62, 13, 19, 21, 17, 38, 46, 81, 4, 69, 37, 7, 67, 73, 71, 45, 60, 40, 35, 68, 64, 6,
      53, 77, 47, 75, 50, 78, 83, 9, 54, 49, 79, 89, 88, 18, 2, 93, 80, 28, 0, 98, 84, 97, 86, 14,
      82, 3, 57, 44, 22, 20, 76, 23, 70, 66, 32, 59, 26, 95, 41, 25, 52, 27, 85, 15, 16, 51, 5, 58,
      74, 11, 63, 29, 56, 91, 39, 31, 24, 12, 96, 30, 43, 99, 94, 72, 65, 42, 48, 36, 87, 55, 33, 1,
      10, 92, 90, 34
    };
    quickSort(arr3);
    printArray(arr3);
  }
}
