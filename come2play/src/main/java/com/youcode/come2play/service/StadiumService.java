package com.youcode.come2play.service;

import com.youcode.come2play.entities.Stadium;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StadiumService {
    Stadium save(Stadium stadium) throws Exception;
    Stadium edit(Long id) throws Exception;
    void delete(Long id) throws Exception;
    List<Stadium> findAll(Pageable pageable);
}
