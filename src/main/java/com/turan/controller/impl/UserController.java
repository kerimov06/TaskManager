package com.turan.controller.impl;

import com.turan.controller.IUserController;
import com.turan.dto.DtoUser;
import com.turan.dto.DtoUserIU;
import com.turan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/task-manager")
public class UserController implements IUserController {

    @Autowired
    private IUserService userService;


    @PutMapping("/updateTask/{id}")
     public  DtoUser updateUserTask(@PathVariable(name = "id") Long id , @RequestBody DtoUserIU dtoUserIU){
        return userService.updateUserTask(id,dtoUserIU);
    }


    @PostMapping("/saveTasks")
    @Override
    public DtoUser saveUserTask(@RequestBody DtoUserIU dtoUserIU){
         return userService.saveUserTask(dtoUserIU);

    }


    @GetMapping("/findById/{id}")
    @Override
    public DtoUser getUserTaskByID(@PathVariable(name = "id") Long id){
        return userService.getUserTaskByID(id);
    }
}
