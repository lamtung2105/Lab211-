package View;

import Controller.StudentController;
import java.io.IOException;
import java.util.Scanner;

/**
 * The StudentView class provides a command-line interface for managing student
 * data.
 */
public class StudentView {

    /**
     * The main method for running the student management program.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws IOException If an error occurs during file loading or saving.
     */
    public static void main(String[] args) throws IOException {
        StudentController controller = new StudentController();
        StudentView view = new StudentView();

        // Load data from the file when the program starts
        controller.loadDataFromFile("D://student_data.txt");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Student");
            System.out.println("2. Find and Sort Student");
            System.out.println("3. Update/Delete Student");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.println("Your choice:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    controller.createStudent();
                    break;
                case 2:
                    controller.findAndSortStudent();
                    break;
                case 3:
                    controller.updateDeleteStudent();
                    break;
                case 4:
                    controller.generateReport();
                    break;
                case 5:
                    // Save data to the file before exiting
                    controller.saveDataToFile("D://student_data.txt");
                    System.exit(0);
            }
        }
    }
}
