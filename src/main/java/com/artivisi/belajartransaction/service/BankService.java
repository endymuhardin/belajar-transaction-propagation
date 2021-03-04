package com.artivisi.belajartransaction.service;

import com.artivisi.belajartransaction.dao.MutasiDao;
import com.artivisi.belajartransaction.dao.NasabahDao;
import com.artivisi.belajartransaction.dao.RekeningDao;
import com.artivisi.belajartransaction.entity.LogTransaksi;
import com.artivisi.belajartransaction.entity.Mutasi;
import com.artivisi.belajartransaction.entity.Rekening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BankService {
    @Autowired private MutasiDao mutasiDao;
    @Autowired private RekeningDao rekeningDao;
    @Autowired private NasabahDao nasabahDao;
    @Autowired private LogTransaksiService logTransaksiService;

    @Transactional
    public void transfer(Rekening asal, Rekening tujuan, BigDecimal nilai, String keterangan) {
        // log mulai transaksi, jangan ikut rollback kalau transfernya error
        logTransaksiService.logTransferMulai(asal, tujuan, nilai);

        // cek saldo
        if (asal.getSaldo().compareTo(nilai) < 0) {
            throw new IllegalStateException("Saldo tidak cukup");
        }

        // kurangi saldo pengirim
        asal.setSaldo(asal.getSaldo().subtract(nilai));
        rekeningDao.save(asal);

        // mutasi rekening pengirim
        Mutasi mutasiPengirim = new Mutasi();
        mutasiPengirim.setKeterangan("transfer ke "+tujuan.getNomor());
        mutasiPengirim.setRekening(asal);
        mutasiPengirim.setNilai(nilai.multiply(new BigDecimal(-1)));
        mutasiDao.save(mutasiPengirim);

        // mutasi rekening penerima
        Mutasi mutasiPenerima = new Mutasi();
        mutasiPenerima.setKeterangan("transfer dari "+asal.getNomor());
        mutasiPenerima.setRekening(tujuan);
        mutasiPenerima.setNilai(nilai);
        mutasiDao.save(mutasiPenerima);

        // tambah saldo penerima
        tujuan.setSaldo(tujuan.getSaldo().add(nilai));
        rekeningDao.save(tujuan);

        // log sukses transaksi
        logTransaksiService.logTransferSukses(asal, tujuan, nilai);
    }
}
