package com.example.ems.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Administrator {

    private long id;
    private String username;
    private String password;


}
