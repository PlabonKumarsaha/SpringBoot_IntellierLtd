package com.teksoi.restapi.repository;

import com.teksoi.restapi.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Modifying
    @Query("update ToDo t set t.active = ?1")
    int deleteAll(Boolean active);

    List<ToDo> findAllByActiveTrue();
}