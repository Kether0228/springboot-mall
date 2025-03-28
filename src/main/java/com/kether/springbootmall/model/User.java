package com.kether.springbootmall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private Integer user_id;
    private String email;
    @JsonIgnore
    private String password;
    private Date create_date;
    private Date last_modify_time;
}
