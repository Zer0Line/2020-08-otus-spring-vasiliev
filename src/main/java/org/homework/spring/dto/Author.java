package org.homework.spring.dto;

public class Author {

    private Long id;

    private String name;

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
