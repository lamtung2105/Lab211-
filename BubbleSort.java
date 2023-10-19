package bubblesort.bubblesort;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * This program allows users to input the number of elements in an array,
 * generates random integers within a specified range (less than the array size)
 * for each element, and then displays the unsorted and sorted arrays using the
 * bubble sort algorithm.
 */
public class BubbleSort {

    /**
     * The main method of the program.
     *
     * @param args command line arguments
     * @return void
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array;

        int size = getArraySize(scanner); // Prompt user for the number of elements in the array

        // Generate a random array based on user input with a range less than the array size
        array = generateRandomArray(size);

        // Display the unsorted array 
        System.out.println("Unsorted Array: " + Arrays.toString(array));

        // Sort the array using bubble sort
        bubbleSort(array);

        // Display the sorted array
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    /**
     * Prompt user to enter the number of elements in the array and returns the
     * valid input size.
     *
     * @param scanner Scanner object to read user input
     * @return size as the array size entered by the user as a positive integer
     */
    public static int getArraySize(Scanner scanner) {
        int size = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter the number of elements in the array: ");
                size = scanner.nextInt();
                if (size <= 0) {
                    System.out.println("Array size must be a positive integer.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        return size;
    }

    /**
     * Generate a random array based on user input with a range less than the
     * array size
     *
     * @param size the size of the array
     * @return array containing random integers
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
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
