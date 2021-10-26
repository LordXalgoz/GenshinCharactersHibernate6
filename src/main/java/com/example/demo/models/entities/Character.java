package com.example.demo.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column
    public String name;
    @Column
    public String sex;
    @Column
    public String element;
    @Column
    public String weapon;

    public Character() {

    }
}
