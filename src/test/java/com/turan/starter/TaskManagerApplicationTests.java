package com.turan.starter;

import com.turan.dto.DtoUser;
import com.turan.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {TaskManagerApplication.class})
class TaskManagerApplicationTests {

	 @Autowired
	 private IUserService userService;

	 @Test
	 public void getUserTaskByID(){

		   DtoUser dtoUser =  userService.getUserTaskByID(2L);

		    if (dtoUser!=null){
				System.out.println("User : " + dtoUser.getSurname());
			}
	 }



}
