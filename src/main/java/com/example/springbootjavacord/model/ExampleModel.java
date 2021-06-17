package com.example.springbootjavacord.model;

import javax.persistence.*;

@Entity
@Table
public class ExampleModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String text;

    public ExampleModel() {
    }

    public ExampleModel(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ExampleModel{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
