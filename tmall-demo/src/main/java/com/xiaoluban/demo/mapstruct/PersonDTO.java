package com.xiaoluban.demo.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonDTO {

//    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Date birth;
    private String birthDateFormat;

}