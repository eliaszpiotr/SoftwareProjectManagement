package com.uep.wap.service;

import com.uep.wap.model.BoardColumn;
import com.uep.wap.model.Sprint;
import com.uep.wap.model.Task;

import com.uep.wap.model.User;
import com.uep.wap.repository.BoardColumnRepository;
import com.uep.wap.repository.SprintRepository;
import com.uep.wap.repository.TaskRepository;
import com.uep.wap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private BoardColumnRepository boardColumnRepository;

    @Autowired
    private UserRepository userRepository;

    // Tworzenie nowego zadania
    @Transactional
    public Task createTask(Long sprintId, Long boardColumnId, Long assigneeId, String title, String description, Task.TaskStatus status, Task.TaskPriority priority, Date dueDate) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new IllegalArgumentException("Sprint with ID " + sprintId + " not found"));
        BoardColumn boardColumn = boardColumnRepository.findById(boardColumnId)
                .orElseThrow(() -> new IllegalArgumentException("BoardColumn with ID " + boardColumnId + " not found"));
        User assignee = userRepository.findById(assigneeId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + assigneeId + " not found"));

        Task task = new Task(sprint, boardColumn, assignee, title, description, status, priority, dueDate);
        return taskRepository.save(task);
    }

    // Pobieranie zadania po ID
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    // Aktualizacja zadania
    @Transactional
    public Task updateTask(Long taskId, String title, String description, Task.TaskStatus status, Task.TaskPriority priority, Date dueDate) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + taskId + " not found"));
        existingTask.setTitle(title);
        existingTask.setDescription(description);
        existingTask.setStatus(status);
        existingTask.setPriority(priority);
        existingTask.setDueDate(dueDate);
        return taskRepository.save(existingTask);
    }

    // Usunięcie zadania
    @Transactional
    public void deleteTask(Long taskId) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + taskId + " not found"));
        taskRepository.delete(existingTask);
    }

    // Pobieranie wszystkich zadań dla danego sprintu
    public List<Task> getTasksBySprintId(Long sprintId) {
        return taskRepository.findBySprintSprintId(sprintId);
    }

    // Pobieranie wszystkich zadań przypisanych do danego użytkownika
    public List<Task> getTasksByAssigneeId(Long assigneeId) {
        return taskRepository.findByAssigneeUserId(assigneeId);
    }
}
