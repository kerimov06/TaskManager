package com.turan.service.impl;

import com.turan.dto.DtoTask;
import com.turan.entity.Task;
import com.turan.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/*
@Service*/
/*ublic class TaskService implements ITaskService {*/

  /*  @Autowired
    private TaskRepository taskRepository;
*/



/*

    @Override
    public DtoTask findByTitle(String title){
         DtoTask dtoTask = new DtoTask();
          Task dbTask = taskRepository.findByTitle(title);
           if(dbTask!=null){
               BeanUtils.copyProperties(dbTask,dtoTask);
               return dtoTask;
           }

          return null;
    }

    @Override
    public DtoTask updateAllTask(Integer id , DtoTask updateTask){
        DtoTask dto = new DtoTask();
        Optional<Task>  task = taskRepository.findById(id);

        if(task.isPresent()){
            Task dtoTask = task.get();
            dtoTask.setCompleted(updateTask.isCompleted());
            dtoTask.setDescription(updateTask.getDescription());
            dtoTask.setTitle(updateTask.getTitle());

            Task dbTask = taskRepository.save(dtoTask);
            BeanUtils.copyProperties(dbTask,dto);

            return dto;
        }
        return null;
    }



    @Override
    public void deleteTask(Integer id){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            Task dbTask = task.get();
            taskRepository.delete(dbTask);


        }

    }


    @Override
    public DtoTask getAllTaskById(Integer id){
        DtoTask dto = new DtoTask();
        Optional<Task> optional = taskRepository.findById(id);
        if(optional.isPresent()){
             Task dbTask = optional.get();
            BeanUtils.copyProperties(dbTask,dto);
            return dto;
        }
        return null;
    }




    @Override
    public DtoTask createTask(DtoTask dtoTask){
        DtoTask  dto =  new DtoTask();
        Task task = new Task();

        BeanUtils.copyProperties(dtoTask,task);

        Task dtoT = taskRepository.save(task);

        BeanUtils.copyProperties(dtoT,dto);

        return dto;



        }

    @Override
    public List<DtoTask> getAllTask() {
        List<DtoTask> dtoTasks = new ArrayList<>();
        List<Task> taskList =  taskRepository.getAllTasks();

         for (Task task : taskList){
             DtoTask dto = new DtoTask();
              BeanUtils.copyProperties(task,dto);
              dtoTasks.add(dto);
         }
         return dtoTasks;
    }

}

*/

