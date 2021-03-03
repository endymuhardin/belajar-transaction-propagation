package com.artivisi.belajartransaction.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity @Data
public class LogTransaksi {
    @Id private String id;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_rekening", nullable = false)
    private Rekening rekening;

    @NotNull
    private LocalDateTime waktuTransaksi = LocalDateTime.now();

    @NotNull @Enumerated(EnumType.STRING)
    private JenisTransaksi jenisTransaksi;

    @NotNull @NotEmpty
    private String keterangan;
}
