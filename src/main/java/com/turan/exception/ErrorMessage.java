package com.turan.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private MessageType messageType ;


    private String ofStatic;

    public String prepareException(){

         StringBuilder builder = new StringBuilder();

         builder.append(messageType.getMessage());

          if (ofStatic!=null){
              builder.append(":" + ofStatic);
          }

        return builder.toString();

    }


}
