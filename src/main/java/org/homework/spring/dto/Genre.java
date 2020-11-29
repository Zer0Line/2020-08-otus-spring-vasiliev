package org.homework.spring.dto;

public class Genre {

    private Long id;

    private String name;

    public Genre(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public Genre(Long id) {
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
