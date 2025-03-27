package com.turan.service.impl;

import com.turan.dto.DtoTask;
import com.turan.dto.DtoUser;
import com.turan.dto.DtoUserIU;
import com.turan.entity.Task;
import com.turan.entity.User;
import com.turan.exception.BaseException;
import com.turan.exception.ErrorMessage;
import com.turan.exception.MessageType;
import com.turan.repository.UserRepository;
import com.turan.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public DtoUser updateUserTask(Long id, DtoUserIU dtoUserIU) {

        DtoUser dtoUser = new DtoUser();
        User user = new User();
        BeanUtils.copyProperties(dtoUserIU, user);


        Optional<User> optional = userRepository.findById(id);


        if (optional.isEmpty()) {
            return null;
        }

        User dbUser = optional.get();
        BeanUtils.copyProperties(dtoUserIU, dbUser,"id" , "tasks");


        List<Task> dbTasks = dbUser.getTasks();

        for (DtoTask dtoTask : dtoUserIU.getTasks()) {
            for (Task task1 : dbTasks) {
                if (task1.getId().equals(dtoTask.getId())) {
                    BeanUtils.copyProperties(dtoTask, task1, "id");
                }
            }
        }

        dbUser = userRepository.save(dbUser);


        BeanUtils.copyProperties(dbUser,dtoUser);

         for (Task task1 : dbUser.getTasks()){
             DtoTask dtoTask = new DtoTask();
             BeanUtils.copyProperties(task1,dtoTask);
             dtoUser.getTasks().add(dtoTask);
        }


        return dtoUser;

    }





    @Override
    public DtoUser saveUserTask(DtoUserIU dtoUserIU){

               DtoUser dtoUser = new DtoUser();
               User user = new User();
               BeanUtils.copyProperties(dtoUserIU,user);

                List<Task> taskList = new ArrayList<>();
               if (dtoUserIU.getTasks()!=null){
                   for (DtoTask dtoTask : dtoUserIU.getTasks()){
                       Task task = new Task();
                       BeanUtils.copyProperties(dtoTask,task);
                       taskList.add(task);
                   }
               }
               user.setTasks(taskList);

                  User dbUser  = userRepository.save(user);
                  BeanUtils.copyProperties(dbUser,dtoUser);

                  List<DtoTask> dtoTasks = new ArrayList<>();

                  for (Task task : dbUser.getTasks()){
                      DtoTask dtoTask1 = new DtoTask();
                      BeanUtils.copyProperties(task,dtoTask1);
                      dtoTasks.add(dtoTask1);
                  }
                  dtoUser.setTasks(dtoTasks);


        return dtoUser;


    }





    @Override
    public DtoUser getUserTaskByID(Long id) {

        DtoUser dtoUser = new DtoUser();
        Optional<User> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST , id.toString()));
        }


        User user = optional.get();
        List<Task> tasks = optional.get().getTasks();
        BeanUtils.copyProperties(user, dtoUser);

        if (tasks != null && !tasks.isEmpty()) {
            for (Task task : tasks) {
                DtoTask dtoTask = new DtoTask();
                BeanUtils.copyProperties(task, dtoTask);
                dtoUser.getTasks().add(dtoTask);
            }
        }

        return dtoUser;
    }

    }

