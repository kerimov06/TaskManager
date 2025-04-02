package com.turan.controller.impl;

import com.turan.entity.ResponsEntity;

public class RestBaseController {

    public <T> ResponsEntity<T>  ok(T data){
         return  ResponsEntity.ok(data);
    }

    public <T> ResponsEntity<T> error(String errorMessage){
         return ResponsEntity.error(errorMessage);
    }
}
