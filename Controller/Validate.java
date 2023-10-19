package Controller;

import java.util.Scanner;

public class Validate {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to input an integer within a specified range and
     * validates it.
     *
     * @param message The message to display to the user.
     * @param min The minimum valid value.
     * @param max The maximum valid value.
     * @return The validated integer input.
     */
    public int inputInt(String message, int min, int max) {
        System.out.print(message);
        while (true) {
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                //check range of number
                if (number < min || number > max) {
                    System.out.print("Please input between " + min + ", " + max + ": ");
                    continue;
                }
                return number;
            } catch (Exception e) {
                System.out.print("Please input an integer number: ");
            }
        }
    }

    /**
     * Prompts the user to input a double within a specified range and validates
     * it.
     *
     * @param message The message to display to the user.
     * @param min The minimum valid value.
     * @param max The maximum valid value.
     * @return The validated double input.
     */
    public double inputDouble(String message, double min, double max) {
        System.out.print(message);
        while (true) {
            String input = scanner.nextLine();
            try {
                double number = Double.parseDouble(input);
                //check range of number
                if (number < min || number > max) {
                    System.out.print("Please input between " + min + ", " + max + ": ");
                    continue;
                }
                return number;
            } catch (Exception e) {
                System.out.print("Please input an double number: ");
            }
        }
    }

    /**
     * Prompts the user to input a string that matches a specified regular
     * expression.
     *
     * @param message The message to display to the user.
     * @param regex The regular expression pattern to match.
     * @return The validated string input.
     */
    public String inputString(String message, String regex) {
        System.out.print(message);
        while (true) {
            String input = scanner.nextLine();
            if (!input.matches(regex)) {
                System.out.print("Please input match regex(" + regex + ").");
                continue;
            }
            return input;
        }
    }

    /**
     * Prompts the user to input a "Y" or "N" and validates the response.
     *
     * @param message The message to display to the user.
     * @return True if the user inputs "Y" (case-insensitive), false if "N".
     */
    public boolean checkInputYN(String message) {
        System.out.println(message);    
        while (true) {
            String result = scanner.nextLine();

            if (result.equalsIgnoreCase("Y")) {
                return true;
            }

            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }
}
