package com.ny.listener.basic.entity;

import javax.persistence.*;


@Entity
@Table
public class UserEntity {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column
    private String name;


    @Column
    private int isActv;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsActv() {
        return isActv;
    }

    public void setIsActv(int isActv) {
        this.isActv = isActv;
    }
}
