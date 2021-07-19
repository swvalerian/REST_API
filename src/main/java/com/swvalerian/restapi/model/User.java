package com.swvalerian.restapi.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    // подсказано
    @OneToMany(mappedBy = "user")
    List<Event> events; // OneToMany

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
//        this.events = events;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Event> getEvents() {
//        return events;
//    }

//    public void setEvents(List<Event> events) {
//        this.events = events;
//    }

    @Override
    public String toString() {
        return "\nUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", events=" + events +
                '}' + "\n";
    }
}