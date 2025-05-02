package com.turan.controller.impl;

import com.turan.controller.IUserController;
import com.turan.dto.DtoUser;
import com.turan.dto.DtoUserIU;
import com.turan.entity.ResponsEntity;
import com.turan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/task-manager")
public class UserController extends RestBaseController implements IUserController {

    @Autowired
    private IUserService userService;


    @PutMapping("/updateTask/{id}")
     public  ResponsEntity<DtoUser> updateUserTask(@PathVariable(name = "id") Long id , @RequestBody DtoUserIU dtoUserIU){
        return ok(userService.updateUserTask(id,dtoUserIU));
    }

    @DeleteMapping("/deleteTask/{id}")
    @Override
    public void deleteUserById(@PathVariable(name = "id") Long id) {
         userService.deleteUserById(id);
    }


    @PostMapping("/saveTasks")
    @Override
    public DtoUser saveUserTask(@RequestBody DtoUserIU dtoUserIU){
         return userService.saveUserTask(dtoUserIU);

    }


    @GetMapping("/findById/{id}")
    @Override
    public ResponsEntity<DtoUser> getUserTaskByID(@PathVariable(name = "id") Long id){
        return ok(userService.getUserTaskByID(id));
    }
}
