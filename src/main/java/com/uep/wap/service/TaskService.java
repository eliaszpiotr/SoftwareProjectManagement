package com.uep.wap.service;

import com.uep.wap.dto.TaskDTO;
import com.uep.wap.model.Task;
import com.uep.wap.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Convert Task to TaskDTO
    private TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getTaskId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        // Add other fields as necessary
        return taskDTO;
    }

    // Convert TaskDTO to Task
    private Task convertToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTaskId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        // Add other fields as necessary
        return task;
    }

    // Creating a new task
    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task newTask = convertToEntity(taskDTO);
        return convertToDTO(taskRepository.save(newTask));
    }

    // Getting a task by ID
    public TaskDTO getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + taskId + " not found"));
        return convertToDTO(task);
    }

    // Updating task data
    @Transactional
    public TaskDTO updateTask(Long taskId, TaskDTO taskDTO) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + taskId + " not found"));
        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        // Update other fields as necessary
        return convertToDTO(taskRepository.save(existingTask));
    }

    // Deleting a task
    @Transactional
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new IllegalArgumentException("Task with ID " + taskId + " not found");
        }
        taskRepository.deleteById(taskId);
    }

    // Getting all tasks
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}