package com.turan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsEntity<T> {

     private boolean result;

     private String errorMessage;

     private T data;



     public static  <T> ResponsEntity<T>  ok(T data){
            ResponsEntity<T> responsEntity = new ResponsEntity<>();
              responsEntity.setResult(true);
              responsEntity.setErrorMessage(null);
              responsEntity.setData(data);

              return responsEntity;
         }

         public static  <T> ResponsEntity<T> error(String errorMessage){
             ResponsEntity<T> responsEntity = new ResponsEntity<>();
               responsEntity.setErrorMessage(errorMessage);
               responsEntity.setResult(false);
               responsEntity.setData(null);

               return  responsEntity;
         }

    }
