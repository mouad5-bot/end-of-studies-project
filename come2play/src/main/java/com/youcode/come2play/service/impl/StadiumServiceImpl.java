package com.youcode.come2play.service.impl;

import com.youcode.come2play.entities.Stadium;
import com.youcode.come2play.repository.StadiumRepository;
import com.youcode.come2play.service.StadiumService;
import com.youcode.come2play.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StadiumServiceImpl implements StadiumService {
    private final StadiumRepository repository;
    private final FileUtils fileUtils;
    @Override
    public Stadium save(Stadium stadium, MultipartFile file) throws Exception {
        String storeFile = fileUtils.storeFile(file);
        stadium.setStadiumImage(storeFile);
        return repository.save(stadium);
    }

    @Override
    public Stadium edit(Long id) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public List<Stadium> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }
}
