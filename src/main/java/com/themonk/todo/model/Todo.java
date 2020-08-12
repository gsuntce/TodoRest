package com.themonk.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Todo {

    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String description;
    private Date targetDate;
    private Boolean isDone;
}
