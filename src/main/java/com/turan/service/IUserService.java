package com.turan.service;

import com.turan.dto.DtoUser;
import com.turan.entity.Task;

import java.util.List;

public interface IUserService {

    public DtoUser getUserTaskByID(Long id);


}
