package com.example.testrequestmapping;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Todo {
    @NotEmpty(message = "title not empty")
    @Size(min = 5,max = 100)
    public String title;
    @NotEmpty(message = "detail not null")
    @NotBlank(message = "detail not blank")
    public String detail;
}
