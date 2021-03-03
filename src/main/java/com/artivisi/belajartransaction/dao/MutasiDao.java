package com.artivisi.belajartransaction.dao;

import com.artivisi.belajartransaction.entity.Mutasi;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MutasiDao extends PagingAndSortingRepository<Mutasi, String> {
}
