package Controller;

import Model.Task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The TaskManager class is responsible for managing tasks.
 */
public class TaskManager {

    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    /**
     * Adds a new task to the list of tasks.
     *
     * @param requirementName The name of the requirement.
     * @param assignee The person assigned to the task.
     * @param reviewer The task reviewer.
     * @param taskTypeId The ID of the task type (1-4).
     * @param date The date in the format "dd-MM-yyyy" when the task is
     * performed.
     * @param planFrom The start time of the task (8.0-17.5).
     * @param planTo The end time of the task (8.0-17.5).
     * @return The ID of the newly added task.
     * @throws Exception If input validation fails.
     */
    public int addTask(String requirementName, String assignee, String reviewer, String taskTypeId, String date, String planFrom, String planTo) throws Exception {
        // Input validation
        int typeId = Integer.parseInt(taskTypeId);
        if (typeId < 1 || typeId > 4) {
            throw new Exception("Invalid task type ID");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date taskDate = dateFormat.parse(date);
        
        double from = Double.parseDouble(planFrom);
        double to = Double.parseDouble(planTo);
        if (from >= to || from < 8.0 || to > 17.5) {
            throw new Exception("Invalid plan time");
        }

        // Create a new task
        Task task = new Task(nextId++, typeId, requirementName, taskDate, from, to, assignee, reviewer);
        tasks.add(task);

        return task.getId();
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to delete.
     * @throws Exception If the task with the given ID is not found.
     */
    public void deleteTask(String id) throws Exception {
        int taskId = Integer.parseInt(id);

        // Search for the task
        Task found = null;
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                found = task;
                break;
            }
        }

        if (found == null) {
            throw new Exception("Task not found");
        }

        // Remove the task
        tasks.remove(found);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getDataTasks() {
        return tasks;
    }
}
