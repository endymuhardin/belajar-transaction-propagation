package com.artivisi.belajartransaction.dao;

import com.artivisi.belajartransaction.entity.Nasabah;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NasabahDao extends PagingAndSortingRepository<Nasabah, String> {
}
