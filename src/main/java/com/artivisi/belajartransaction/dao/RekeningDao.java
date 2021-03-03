package com.artivisi.belajartransaction.dao;

import com.artivisi.belajartransaction.entity.Rekening;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RekeningDao extends PagingAndSortingRepository<Rekening, String> {
}
