package com.turan.controller;

import com.turan.dto.DtoUser;
import com.turan.dto.DtoUserIU;

public interface IUserController {

    public DtoUser getUserTaskByID(Long id);
    public DtoUser saveUserTask(DtoUserIU dtoUserIU);
    public DtoUser updateUserTask(Long id, DtoUserIU dtoUserIU);
}
