package com.artivisi.belajartransaction.service;

import com.artivisi.belajartransaction.dao.LogTransaksiDao;
import com.artivisi.belajartransaction.entity.JenisTransaksi;
import com.artivisi.belajartransaction.entity.LogTransaksi;
import com.artivisi.belajartransaction.entity.Rekening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class LogTransaksiService {

    @Autowired private LogTransaksiDao logTransaksiDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logTransferMulai(Rekening asal, Rekening tujuan, BigDecimal nilai) {
        String keterangan = "Mulai transfer dari "+
                asal.getNomor() +" ke "+tujuan.getNomor() +
                "senilai "+nilai.setScale(2).toString();
        insertLogTransaksi(asal, keterangan);
        insertLogTransaksi(tujuan, keterangan);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logTransferSukses(Rekening asal, Rekening tujuan, BigDecimal nilai) {
        String keterangan = "Sukses transfer dari "+
                asal.getNomor() +" ke "+tujuan.getNomor() +
                "senilai "+nilai.setScale(2).toString();
        insertLogTransaksi(asal, keterangan);
        insertLogTransaksi(tujuan, keterangan);
    }

    private void insertLogTransaksi(Rekening asal, String keterangan) {
        LogTransaksi logTransaksiPengirim = new LogTransaksi();
        logTransaksiPengirim.setKeterangan(keterangan);
        logTransaksiPengirim.setJenisTransaksi(JenisTransaksi.TRANSFER);
        logTransaksiPengirim.setRekening(asal);
        logTransaksiDao.save(logTransaksiPengirim);
    }
}
