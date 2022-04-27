package com.example.myspringbootapp.controller.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class UserForm {
    private Long id;

    @NotBlank(message = "이름은 필수입력 항목입니다.")
    private String name;

    @NotBlank(message = "Email은 필수입력 항목입니다.")
    @Email(message = "올바른 Email 형식이 아닙니다.")
    private String email;


}
