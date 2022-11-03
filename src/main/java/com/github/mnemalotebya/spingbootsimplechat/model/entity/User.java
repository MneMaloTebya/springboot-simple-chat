package com.github.mnemalotebya.spingbootsimplechat.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "message")
    private List<Message> messages;
}
