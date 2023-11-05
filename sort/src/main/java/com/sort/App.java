package com.sort;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        int[] sizes = { 50, 500, 1000, 5000, 10000 };
        for (int size : sizes) {
            System.out.println("\n\nRunning for size: " + size);
            long timeBubble = 0;
            long timeInsert = 0;
            long timeSelection = 0;
            long timeHeap = 0;
            long timeMerge = 0;
            long timeQuick = 0;
            long timeShell = 0;
            for (int timer = 0; timer < 5; timer++) {
                // System.out.println("Sample number: " + timer);
                int[][] arrays = new int[7][size];
                Random random = new Random();

                int[] randomArray = new int[size];
                for (int i = 0; i < size; i++) {
                    randomArray[i] = random.nextInt(1001);
                }

                for (int i = 0; i < 7; i++) {
                    System.arraycopy(randomArray, 0, arrays[i], 0, size);
                }

                long startBubble = System.nanoTime();
                bubbleSort(arrays[0]);
                timeBubble += (System.nanoTime() - startBubble);

                long startInsert = System.nanoTime();
                insertSort(arrays[1]);
                timeInsert += (System.nanoTime() - startInsert);

                long startSelection = System.nanoTime();
                selectionSort(arrays[2]);
                timeSelection += (System.nanoTime() - startSelection);

                long startHeap = System.nanoTime();
                heapSort(arrays[3]);
                timeHeap += (System.nanoTime() - startHeap);

                long startMerge = System.nanoTime();
                mergeSort(arrays[4]);
                timeMerge += (System.nanoTime() - startMerge);

                long startQuick = System.nanoTime();
                quickSort(arrays[5]);
                timeQuick += (System.nanoTime() - startQuick);

                long startShell = System.nanoTime();
                shellSort(arrays[6]);
                timeShell += (System.nanoTime() - startShell);
            }
            timeBubble = ceilDiv((int) timeBubble, 5);
            timeInsert = ceilDiv((int) timeInsert, 5);
            timeSelection = ceilDiv((int) timeSelection, 5);
            timeHeap = ceilDiv((int) timeHeap, 5);
            timeMerge = ceilDiv((int) timeMerge, 5);
            timeQuick = ceilDiv((int) timeQuick, 5);
            timeShell = ceilDiv((int) timeShell, 5);

            System.out.println("Time for Bubble: " + timeBubble);
            System.out.println("Time for Insert: " + timeInsert);
            System.out.println("Time for Selection: " + timeSelection);
            System.out.println("Time for Heap: " + timeHeap);
            System.out.println("Time for Merge: " + timeMerge);
            System.out.println("Time for Quick: " + timeQuick);
            System.out.println("Time for Shell: " + timeShell);
        }
    }

    public static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] insertSort(int[] arr) {
        int n = arr.length;
        int iterations = 0;
        int moves = 0;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                iterations++;
                moves++;
            }
            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort - Iterations: " + iterations + ", Moves: " + moves);
        return arr;
    }


    private static int[] bubbleSort(int[] vetor) {
        int len = vetor.length;
        int iterations = 0;
        int swaps = 0;
        for (int i = 0; i < len; i++) {
            for (int u = 0; u < (len - i) - 1; u++) {
                iterations++;
                if (vetor[u] > vetor[u + 1]) {
                    int temp = vetor[u];
                    vetor[u] = vetor[u + 1];
                    vetor[u + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.println("Bubble Sort - Iterations: " + iterations + ", Swaps: " + swaps);
        return vetor;
    }


    public static void mergeSort(int[] arr) {
        int[] counters = new int[1]; // counters[0] for copies
        if (arr.length > 1) {
            int[] temp = new int[arr.length];
            mergeSort(arr, temp, 0, arr.length - 1, counters);
            System.out.println("Merge Sort - Copies: " + counters[0]);
        }
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right, int[] counters) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, temp, left, mid, counters);
            mergeSort(arr, temp, mid + 1, right, counters);
            merge(arr, temp, left, mid, right, counters);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right, int[] counters) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
            counters[0]++; // Counting copies
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = temp[i++];
        }
    }


    // Quick Sort
    public static void quickSort(int[] arr) {
        int[] counters = new int[2]; // counters[0] for iterations, counters[1] for swaps
        quickSort(arr, 0, arr.length - 1, counters);
        System.out.println("Quick Sort - Iterations: " + counters[0] + ", Swaps: " + counters[1]);
    }

    private static void quickSort(int[] arr, int low, int high, int[] counters) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, counters);
            quickSort(arr, low, pivotIndex - 1, counters);
            quickSort(arr, pivotIndex + 1, high, counters);
        }
    }

    private static int partition(int[] arr, int low, int high, int[] counters) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            counters[0]++; // Counting iterations
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                counters[1]++; // Counting swaps
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        counters[1]++; // Counting swaps
        return i + 1;
    }


    // Shell Sort
    public static void shellSort(int[] arr) {
        int iterations = 0;
        int moves = 0;
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                    iterations++;
                    moves++;
                }
                arr[j] = temp;
            }
        }
        System.out.println("Shell Sort - Iterations: " + iterations + ", Moves: " + moves);
    }


    // Heap Sort
    public static void heapSort(int[] arr) {
        int swaps = 0;
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            swaps += heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            swaps++;
            swaps += heapify(arr, i, 0);
        }
        System.out.println("Heap Sort - Swaps: " + swaps);
    }

    private static int heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int swaps = 0;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            swaps++;
            swaps += heapify(arr, n, largest);
        }

        return swaps;
    }


    // Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int iterations = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                iterations++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            if (i != minIndex) swaps++;
        }

        System.out.println("Selection Sort - Iterations: " + iterations + ", Swaps: " + swaps);
    }

    public static int ceilDiv(int x, int y) {
        // Use the ceiling of x / y by adding y - 1 to x if y is not zero
        return (y == 0) ? 0 : (x + y - 1) / y;
    }

}