package br.com.davi.model;

import java.util.List;

public class Course {

    private Long id;
    private String title;
    private List<Lesson> lessons;

    public Course() {
    }

    public Course(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public List<Lesson> getLessons() {
        return lessons;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + ", lessons=" + lessons + "]";
    }
}