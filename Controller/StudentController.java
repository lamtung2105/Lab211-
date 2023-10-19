package Controller;

import Model.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The StudentController class manages student data and operations.
 */
public class StudentController {

    private List<Student> students = new ArrayList<>();
    private int idCounter = 1;

    /**
     * Create a new student and add them to the list.
     */
    public void createStudent() {
        Scanner scanner = new Scanner(System.in);
        int limit = 10;

        for (int i = 0; i < limit; i++) {
            int id = idCounter++;
            String studentName;
            int semester;
            String courseName;

            System.out.print("Enter Student Name: ");
            studentName = scanner.nextLine();

            do {
                System.out.print("Enter Semester (1-9): ");
                semester = scanner.nextInt();
            } while (semester < 1 || semester > 9);

            do {
                System.out.println("Choose Course (1: C/C++, 2: Java, 3: .Net): ");
                int courseChoice = scanner.nextInt();
                switch (courseChoice) {
                    case 2:
                        courseName = "Java";
                        break;
                    case 3:
                        courseName = ".Net";
                        break;
                    case 1:
                        courseName = "C/C++";
                        break;
                    default:
                        System.out.println("Invalid course choice.");
                        continue;
                }
                break;
            } while (true);

            Student student = new Student(id, studentName, semester, courseName);
            students.add(student);
        }

        // After creating the fixed number of students, ask if you want to continue.
        System.out.print("Do you want to continue (Y/N)? ");
        char choice = scanner.next().charAt(0);
        if (Character.toString(choice).equalsIgnoreCase("N")) {
            return;
        }

    }   

    /**
     * Find and sort students by name.
     */
    public void findAndSortStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter part of Student Name: ");
        String searchName = scanner.nextLine();

        List<Student> foundStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getStudentName().contains(searchName)) {
                foundStudents.add(student);
            }
        }

        // Sort found students by name
        Collections.sort(foundStudents, Comparator.comparing(Student::getStudentName));

        // Print the sorted student information
        for (Student student : foundStudents) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getStudentName() + ", Semester: " + student.getSemester() + ", Course: " + student.getCourseName());
        }
    }

    /**
     * Update or delete a student by ID.
     */
    public void updateDeleteStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Student ID to update or delete: ");
        int id = scanner.nextInt();
        
        boolean found = false;
        for (Student student : students) {
            if (student.getId() == id) {
                found = true;
                System.out.print("Do you want to update (U) or delete (D) the student? ");
                char choice = scanner.next().charAt(0);
                scanner.nextLine(); // Consume the newline character

                if (choice == 'U' || choice == 'u') {
                    System.out.print("Enter new Student Name: ");
                    String name = scanner.nextLine();

                    do {
                        System.out.print("Enter new Semester (1-9): ");
                        int semester = scanner.nextInt();
                        if (semester >= 1 && semester <= 9) {
                            student.setStudentName(name);
                            student.setSemester(semester);
                            break;
                        } else {
                            System.out.println("Invalid semester input.");
                        }
                    } while (true);

                    do {
                        System.out.println("Choose Course (1: Java, 2: .Net, 3: C/C++): ");
                        int courseChoice = scanner.nextInt();
                        switch (courseChoice) {
                            case 1:
                                student.setCourseName("Java");
                                break;
                            case 2:
                                student.setCourseName(".Net");
                                break;
                            case 3:
                                student.setCourseName("C/C++");
                                break;
                            default:
                                System.out.println("Invalid course choice.");
                                continue;
                        }
                        break;
                    } while (true);
                } else if (choice == 'D' || choice == 'd') {
                    students.remove(student);
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found with ID: " + id);
        }
    }

    /**
     * Generate a report of students and their course counts.
     */
    public void generateReport() {
        // Create a map to store the count of courses for each student
        Map<String, Map<String, Integer>> reportData = new HashMap<>();

        for (Student student : students) {
            String studentName = student.getStudentName();
            String courseName = student.getCourseName();

            // Check if the student's name is already in the report data
            if (reportData.containsKey(studentName)) {
                Map<String, Integer> courseCounts = reportData.get(studentName);

                // Check if the course is already in the student's entry
                if (courseCounts.containsKey(courseName)) {
                    // Increment the course count
                    courseCounts.put(courseName, courseCounts.get(courseName) + 1);
                } else {
                    // Initialize the course count
                    courseCounts.put(courseName, 1);
                }
            } else {
                // Initialize a new entry for the student
                Map<String, Integer> courseCounts = new HashMap<>();
                courseCounts.put(courseName, 1);
                reportData.put(studentName, courseCounts);
            }
        }

        // Display the report
        for (Map.Entry<String, Map<String, Integer>> entry : reportData.entrySet()) {
            String studentName = entry.getKey();
            Map<String, Integer> courseCounts = entry.getValue();
            for (Map.Entry<String, Integer> courseEntry : courseCounts.entrySet()) {
                String courseName = courseEntry.getKey();
                int totalCourses = courseEntry.getValue();
                System.out.println(studentName + " | " + courseName + " | " + totalCourses);
            }
        }
    }

    /**
     * Load student data from a file.
     *
     * @param filename The name of the file to load data from.
     */
    public void loadDataFromFile(String filename) {

        try (Scanner fileScanner = new Scanner(new File("D://student_data.txt"))) {

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();

                // Extract student details from line
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int semester = Integer.parseInt(parts[2]);
                String course = parts[3];

                Student student = new Student(id, name, semester, course);
                students.add(student);

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }

    }

    /**
     * Save student data to a file.
     *
     * @param filename The name of the file to save data to.
     * @throws IOException If an error occurs during file saving.
     */
    public void saveDataToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("D://student_data.txt"))) {
            for (Student student : students) {
                // Write student information to the file
                writer.println(student.getId() + "|" + student.getStudentName() + "|" + student.getSemester() + "|" + student.getCourseName());
            }
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + filename);
        }
    }

}
