package com.turan.controller;

import com.turan.dto.DtoTask;
import com.turan.entity.Task;

import java.util.List;

public interface ITaskController {

    public DtoTask createTask(DtoTask dtoTask);
    public List<DtoTask> getAllTask();
    public DtoTask getAllTaskById(Integer id);
    public void deleteTask(Integer id);
    public DtoTask updateAllTask(Integer id , DtoTask updateTask);
}
