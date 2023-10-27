package com.plannerapp.repo;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignee(User user);


    @Query("SELECT t FROM Task t WHERE t.assignee IS NULL")
    List<Task> findByAssigneeIsNull();
}
