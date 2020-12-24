package com.teksoi.restapi.service;

import com.teksoi.restapi.dto.Response;
import com.teksoi.restapi.dto.ToDoDto;

public interface ToDoService {
    Response create(ToDoDto toDoDto);

    Response getAll();

    Response update(Long id, ToDoDto toDoDto);

    Response delete(Long id);

    Response deleteAll();
}
