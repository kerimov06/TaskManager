package com.turan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserIU {

    private Long id;

    private String name;

    private String surname;

    private List<DtoTask> tasks;
}
