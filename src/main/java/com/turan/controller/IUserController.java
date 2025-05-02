package com.turan.controller;

import com.turan.dto.DtoUser;
import com.turan.dto.DtoUserIU;
import com.turan.entity.ResponsEntity;

public interface IUserController {

    public ResponsEntity<DtoUser> getUserTaskByID(Long id);
    public DtoUser saveUserTask(DtoUserIU dtoUserIU);
    public ResponsEntity<DtoUser> updateUserTask(Long id, DtoUserIU dtoUserIU);
    public void deleteUserById(Long id);
}
