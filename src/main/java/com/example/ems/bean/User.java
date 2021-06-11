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

    //以下为多的数据库字段

    private long id;
    private String quantity;
    private String email;
    private String address;

}
