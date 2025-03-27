package com.turan.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoTask {

     private Long id;

    @NotEmpty(message = "Title never been empty or null")
    private String title;
    @Size(min = 10 , max = 100 , message = "Please write between area of min and max")
    private String description;
    private boolean completed;

}
