package com.example.ems.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private String username;
    private String password;



    private long id;
    private long quantity;
    private String email;
    private String address;

}
