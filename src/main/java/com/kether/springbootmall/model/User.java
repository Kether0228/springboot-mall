package com.kether.springbootmall.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private Integer user_Id;
    private String email;
    private String password;
    private Date create_date;
    private Date last_modify_time;
}
