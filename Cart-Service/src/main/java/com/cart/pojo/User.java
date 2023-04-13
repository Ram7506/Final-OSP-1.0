package com.cart.pojo;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private long phoneNo;
    private String address;
}
