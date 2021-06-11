package com.example.ems.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("borrowedBook")
public class BorrowedBook {
    public Long bookid;
    public Long userid;
    public String ISBN;
    public String borrowedTime;
    public String name;

}
