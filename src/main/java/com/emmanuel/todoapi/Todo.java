package com.emmanuel.todoapi;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;

    @Size(min = 10, message = "Mindestens 10 Zeichen eingeben...")
    private String description;
    private boolean done;

    @Future(message = "Datum muss in der Zukunft liegen")
    private LocalDate dateline;

    public Todo() {
    }

    public Todo(int id, String username, String description, boolean done, LocalDate dateline) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.done = done;
        this.dateline = dateline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDate getDateline() {
        return dateline;
    }

    public void setDateline(LocalDate dateline) {
        this.dateline = dateline;
    }

    @Override
    public String toString() {
        return String.format("Todo [id=%s, username=%s, description=%s, done=%s, dateline=%s]", id, username, description, done, dateline);
    }
}
