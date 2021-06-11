package com.example.ems.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    public long id;
    public String name;
    public String author;
    public String publish;
    public String time;
    public String ISBN;
    public String price;
    public String state;
    public String keywords;
    public String theme;
    public String type;
    public String pages;

}
