package com.turan.controller;

import com.turan.dto.DtoUser;

public interface IUserController {

    public DtoUser getUserTaskByID(Long id);
}
