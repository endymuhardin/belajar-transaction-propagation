package com.artivisi.belajartransaction.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data @Entity
public class Nasabah {
    @Id private String id;
}
