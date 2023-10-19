package View;

import Controller.TaskManager;
import Model.Task;
import java.util.List;
import java.util.Scanner;

/**
 * The TaskView class is responsible for the user interface of the task
 * management program.
 */
public class TaskView {

    private static TaskManager manager = new TaskManager();

    public static void main(String[] args) {
        showMenu();
    }

    /**
     * Displays the main menu for the task management program and handles user
     * input.
     */
    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Menu");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Show Tasks");
            System.out.println("4. Exit");

            System.out.print("\nSelect an option: ");
            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1":
                        addTask();
                        break;
                    case "2":
                        deleteTask();
                        break;
                    case "3":
                        showTasks();
                        break;
                    case "4":
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Prompts the user for input and adds a new task to the system.
     *
     * @throws Exception If input validation fails.
     */
    private static void addTask() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter requirement name: ");
        String requirementName = scanner.nextLine();

        System.out.print("Enter assignee: ");
        String assignee = scanner.nextLine();

        System.out.print("Enter reviewer: ");
        String reviewer = scanner.nextLine();

        System.out.print("Enter task type ID (1-4): ");
        String taskTypeId = scanner.nextLine();

        System.out.print("Enter date (dd-MM-yyyy): ");
        String date = scanner.nextLine();

        System.out.print("Enter plan from (8.0-17.5): ");
        String planFrom = scanner.nextLine();

        System.out.print("Enter plan to (8.0-17.5): ");
        String planTo = scanner.nextLine();

        int id = manager.addTask(requirementName, assignee, reviewer, taskTypeId, date, planFrom, planTo);
        System.out.println("Added task " + id);
    }

    /**
     * Prompts the user for the ID of a task and deletes it from the system.
     *
     * @throws Exception If the task with the given ID is not found.
     */
    private static void deleteTask() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter task ID to delete: ");
        String id = scanner.nextLine();

        manager.deleteTask(id);
        System.out.println("Deleted task " + id);
    }

    /**
     * Displays the list of tasks.
     */
    private static void showTasks() {
        List<Task> tasks = manager.getDataTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (Task task : tasks) {
                System.out.println(task.toString());
            }
        }
    }
}
