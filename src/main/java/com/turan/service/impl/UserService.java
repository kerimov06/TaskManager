package com.turan.service.impl;

import com.turan.dto.DtoTask;
import com.turan.dto.DtoUser;
import com.turan.entity.Task;
import com.turan.entity.User;
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
    public DtoUser getUserTaskByID(Long id) {

        DtoUser dtoUser = new DtoUser();
        Optional<User> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
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

