package com.turan.repository;

import com.turan.dto.DtoTask;
import com.turan.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query(value = "SELECT*FROM task.task WHERE title = :title" , nativeQuery = true)
    Task findByTitle(@Param("title")String title);
    @Query(value = "SELECT*FROM task.task " , nativeQuery = true)
    List<Task> getAllTasks();

}
