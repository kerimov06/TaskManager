package com.turan.exception;

import lombok.Getter;

@Getter
public enum MessageType {


     NO_RECORD_EXIST("505" , "Bu Userin melumatlari tapilmadi"),
     GENERAL_EXCEPTION("102" , "Umumi bir xeta olusdu yeniden nezer yetirin");


      private String code;
      private String message;

       MessageType(String code , String message){
          this.code = code;
          this.message = message;
      }



}
