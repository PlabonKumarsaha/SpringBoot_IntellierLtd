package com.teksoi.restapi.service.impl;

import com.teksoi.restapi.dto.Response;
import com.teksoi.restapi.dto.ToDoDto;
import com.teksoi.restapi.exception.ResourceNotFoundException;
import com.teksoi.restapi.model.ToDo;
import com.teksoi.restapi.repository.ToDoRepository;
import com.teksoi.restapi.service.ToDoService;
import com.teksoi.restapi.utils.ResponseBuilder;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("toDoService")
public class ToDoServiceImpl implements ToDoService {

    private final String root = "ToDo";

    private final ToDoRepository toDoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ToDoServiceImpl(ToDoRepository toDoRepository, ModelMapper modelMapper) {
        this.toDoRepository = toDoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response create(ToDoDto toDoDto) {
        ToDo toDo = modelMapper.map(toDoDto, ToDo.class);
        toDo.setActive(true);
        toDo = toDoRepository.save(toDo);
        if (toDo != null) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, null, String.format("Created successfully", root));
        }
        return ResponseBuilder.getFailResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error occurred");
    }

    @Override
    public Response getAll() {
        List<ToDo> toDoList = toDoRepository.findAllByActiveTrue();
        List<ToDoDto> responseDtos = new ArrayList<>();
        toDoList.forEach(course -> {
            ToDoDto toDoDto = modelMapper.map(course, ToDoDto.class);
            responseDtos.add(toDoDto);
        });

        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, responseDtos, responseDtos.size(), String.format("%s list", root));
    }

    @Override
    public Response update(Long id, ToDoDto toDoDto) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (!optionalToDo.isPresent()) {
            return ResponseBuilder.getFailResponse(HttpStatus.NOT_FOUND, String.format("Requested %s could not be found", root));
        }

        try {
            ToDo toDo = optionalToDo.get();
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(toDoDto, toDo);
            toDo = toDoRepository.save(toDo);

            if (toDo != null) {
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, null, String.format("%s updated successfully", root));
            }
            return ResponseBuilder.getFailResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error occurred");

        } catch (NullPointerException e) {
            return ResponseBuilder.getFailResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseBuilder.getFailResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Response delete(Long id) {
        Optional<ToDo> optionalCourse = toDoRepository.findById(id);

        if (!optionalCourse.isPresent()) {
            return ResponseBuilder.getFailResponse(HttpStatus.NOT_FOUND, String.format("Requested %s could not be found", root));
        }

        try {
            ToDo toDo = optionalCourse.get();
            toDo.setActive(false); //Soft delete by setting active to false
            toDo = toDoRepository.save(toDo);

            if (toDo != null) {
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, null, String.format("%s deleted successfully", root));
            }
            return ResponseBuilder.getFailResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error occurred");
        } catch (ResourceNotFoundException e) {
            return ResponseBuilder.getFailResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (NullPointerException e) {
            return ResponseBuilder.getFailResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseBuilder.getFailResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Response deleteAll() {
        toDoRepository.deleteAll(false);
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, null, String.format("%s deleted successfully", root));
    }
}
