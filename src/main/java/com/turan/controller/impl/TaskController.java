package com.turan.controller.impl;

import com.turan.controller.ITaskController;
import com.turan.dto.DtoTask;
import com.turan.entity.Task;
import com.turan.service.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/task")
public class TaskController implements ITaskController {

    @Autowired
    private ITaskService taskService;

    @GetMapping("/taskFindByTitle/{title}")
    @Override
    public DtoTask findByTitle(@PathVariable(name = "title")String title){
        return taskService.findByTitle(title);
    }
    @PutMapping("/taskUpdate/{id}")
    @Override
    public DtoTask updateAllTask(@PathVariable (name = "id") Integer id , @RequestBody DtoTask updateTask){
        return taskService.updateAllTask(id,updateTask);
    }
    @DeleteMapping("/taskDeletById/{id}")
    @Override
    public void deleteTask(@PathVariable (name = "id") Integer id){
        taskService.deleteTask(id);
    }
    @GetMapping("/taskById/{id}")
    @Override
    public DtoTask getAllTaskById(@PathVariable(name = "id") Integer id){
        return taskService.getAllTaskById(id);
    }

    @PostMapping("/new-task")
    @Override
    public DtoTask createTask(@RequestBody @Valid DtoTask dtoTask){
        return taskService.createTask(dtoTask);
    }

    @GetMapping("/list-task")
    @Override
    public List<DtoTask> getAllTask(){
        return taskService.getAllTask();
    }
}
