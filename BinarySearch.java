package lab.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * This program allows users to input the number of array elements, generates
 * random integers within a specified range for each element, and then performs
 * binary search to display the sorted array and the index of a user-input
 * search number.
 */
public class BinarySearch {

    /**
     * The main method of the program.
     *
     * @param args command line arguments
     * @return void
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array;

        try {
            int size;
            while (true) {
                size = getArraySize(scanner);
                if (size > 0) {
                    break;
                } else {
                    System.out.println("Array size must be a positive integer.");
                }
            }
            array = generateRandomArray(size);

            // Prompt the user for the search number
            System.out.print("Enter the search value: ");
            int target;
            try {
                target = scanner.nextInt();
            } catch (InputMismatchException e) {
                throw new InputMismatchException("Invalid input for the search value.");
            }

            // Sort the array using bubble sort
            bubbleSort(array);

            // Display the sorted array
            System.out.println("Sorted array: " + Arrays.toString(array));

            ArrayList<Integer> positions = binarySearch(array, target);
            if (positions == null) {
                System.out.println("Found " + target + " at index: Not found");
            } else {
                System.out.print("Found " + target + " at index: ");
                for (int i = 0; i < positions.size(); i++) {
                    System.out.print(positions.get(i));
                    if (i < positions.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }

            // Display the unsorted array
            System.out.println("Unsorted Array: " + Arrays.toString(array));

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Array size must be a positive integer.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

 
    /**
     * Prompt user to enter the number of elements in the array
     * and returns the valid input size.
     * @param scanner Scanner object to read user input
     * @return size as the array size entered by the user as a positive integer
     */
    public static int getArraySize(Scanner scanner) {
        int size = 0;
        while (size <= 0) {
            System.out.print("Enter the number of elements in the array: ");
            try {
                size = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear the invalid input
            }
            if (size <= 0) {
                System.out.println("Array size must be a positive integer.");
            }
        }
        return size;
    }

    /**
     * Generate a random array based on user input with a range less than the
     * array size
     *
     * @param size the size of the array
     * @return array
     */
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }

    /**
     * Sorts the given array in ascending order using the bubble sort algorithm.
     *
     * @param array the array to be sorted
     * @return void
     */
    public static void bubbleSort(int[] array) {
        int size = array.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Performs binary search to find the index/indices of a target number in a
     * sorted array. Returns a list of indices where the target is found, or
     * null if not found.
     *
     * @param array the sorted array
     * @param target the number to search for
     * @return a list of indices where the target is found, or null if not found
     */
    public static ArrayList<Integer> binarySearch(int[] array, int target) {
        ArrayList<Integer> positions = new ArrayList<>();
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                // Target found, add index to the list
                positions.add(mid);

                // Continue searching in the left and right subarrays for more occurrences
                int leftValue = mid - 1;
                int rightValue = mid + 1;

                while (leftValue >= 0 && array[leftValue] == target) {
                    positions.add(leftValue);
                    leftValue--;
                }

                while (rightValue < array.length && array[rightValue] == target) {
                    positions.add(rightValue);
                    rightValue++;
                }
                return positions;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Target not found
        return null;
    }

}
