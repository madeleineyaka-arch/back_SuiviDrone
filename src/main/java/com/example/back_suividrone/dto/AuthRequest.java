package com.example.back_suividrone.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
