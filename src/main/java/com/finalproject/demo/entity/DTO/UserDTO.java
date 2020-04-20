package com.finalproject.demo.entity.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter

public class UserDTO {


    @NotNull
    private String username;

    @NotNull
    @Min(8)
    private String password;

    @NotNull
    private String passwordConfirm;

    @NotNull
    @Email
    private String email;

}
