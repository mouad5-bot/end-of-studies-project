package com.youcode.come2play.service;

import com.youcode.come2play.entities.Stadium;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface StadiumService {
    Stadium save(Stadium stadium, MultipartFile file) throws Exception;
    Stadium edit(Long id) throws Exception;
    void delete(Long id) throws Exception;
    List<Stadium> findAll(Pageable pageable);
    Optional<Stadium> findById(Long id);
}
