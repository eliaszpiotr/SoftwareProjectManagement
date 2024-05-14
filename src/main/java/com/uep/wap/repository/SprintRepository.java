package com.uep.wap.repository;

import com.uep.wap.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    // Find all sprints for a given project
    List<Sprint> findByProjectProjectId(Long projectId);

    // Find sprints by their name within a project
    List<Sprint> findByProjectProjectIdAndName(Long projectId, String name);

    // Find sprints that are currently active
    List<Sprint> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date startDate, Date endDate);

    // Find sprints that have not started yet
    List<Sprint> findByStartDateGreaterThan(Date date);

    // Find sprints that have already finished
    List<Sprint> findByEndDateLessThan(Date currentDate);

    // Find sprints within a specific date range
    List<Sprint> findByStartDateBetweenOrEndDateBetween(Date startDate, Date endDate, Date startDate2, Date endDate2);
}
