package com.teksoi.restapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ToDoDto {
    private Long id;
    private String title;
    private String description;
    private Date eventDate;
}
