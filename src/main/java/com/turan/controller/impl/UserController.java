package com.turan.controller.impl;

import com.turan.controller.IUserController;
import com.turan.dto.DtoUser;
import com.turan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/task-manager")
public class UserController implements IUserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/findById/{id}")
    @Override
    public DtoUser getUserTaskByID(@PathVariable(name = "id") Long id){
        return userService.getUserTaskByID(id);
    }
}
