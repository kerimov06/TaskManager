package com.turan.service;

import com.turan.dto.DtoUser;
import com.turan.dto.DtoUserIU;
import com.turan.entity.Task;

import java.util.List;

public interface IUserService {

    public DtoUser getUserTaskByID(Long id);

    public DtoUser saveUserTask(DtoUserIU dtoUserIU);

    public DtoUser updateUserTask(Long id , DtoUserIU dtoUserIU);


}
