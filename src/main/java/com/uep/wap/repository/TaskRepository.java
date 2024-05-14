package com.uep.wap.repository;

import com.uep.wap.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Find tasks by sprint
    List<Task> findBySprintSprintId(Long sprintId);

    // Find tasks by board column
    List<Task> findByBoardColumnColumnId(Long columnId);

    // Find tasks assigned to a specific user
    List<Task> findByAssigneeUserId(Long userId);

    // Find tasks by status
    List<Task> findByStatus(String status);

    // Find tasks by priority
    List<Task> findByPriority(String priority);

    // Find tasks due on a specific date
    List<Task> findByDueDate(Date dueDate);

    // Find tasks that are overdue
    List<Task> findByDueDateBefore(Date currentDate);

    // Find tasks that are due within a certain range
    List<Task> findByDueDateBetween(Date startDate, Date endDate);
}
