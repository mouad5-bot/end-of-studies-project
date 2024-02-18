package com.youcode.come2play.service.impl;

import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.repository.ReservationRepository;
import com.youcode.come2play.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository repository;
    @Override
    public Reservation save(Reservation reservation) throws Exception {
        return repository.save(reservation);
    }

    @Override
    public Reservation edit(Long id) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public List<Reservation> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }
}
