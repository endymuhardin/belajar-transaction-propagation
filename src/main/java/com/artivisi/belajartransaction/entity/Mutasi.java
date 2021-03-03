package com.artivisi.belajartransaction.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @Entity
public class Mutasi {
    @Id
    private String id;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_rekening", nullable = false)
    private Rekening rekening;

    @NotNull
    private BigDecimal nilai;

    @NotNull
    private String keterangan;

    @NotNull
    private LocalDateTime waktuTransaksi = LocalDateTime.now();
}
