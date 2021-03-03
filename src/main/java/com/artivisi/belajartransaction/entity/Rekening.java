package com.artivisi.belajartransaction.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data @Entity
public class Rekening {
    @Id private String id;

    @NotNull @NotEmpty
    private String nomor;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_nasabah", nullable = false)
    private Nasabah nasabah;

    @NotNull @Min(0)
    private BigDecimal saldo = BigDecimal.ZERO;
}
