package com.labs.lab2_realm.model;

import java.util.Date;

import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskEntity extends RealmObject {

    private String name;
    private int priority;
    private Date date;
}
