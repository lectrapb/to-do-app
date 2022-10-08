package com.app.src.todo.domain.todo;

import lombok.Data;

@Data
public class ToDo {

    private String  uid;
    private String  description;
    private boolean status;
    private boolean complete;
    private String  createDate;
    private String  updateDate;
    private String  uidUser;
}
