package com.artivisi.belajartransaction.dao;

import com.artivisi.belajartransaction.entity.LogTransaksi;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LogTransaksiDao extends PagingAndSortingRepository<LogTransaksi, String> {
}
