package com.youcode.come2play.service.impl;

import com.youcode.come2play.dtos.dto.request.ReservationDto;
import com.youcode.come2play.dtos.mapper.ReservationMapper;
import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.enums.RequestForTeam;
import com.youcode.come2play.entities.enums.Status;
import com.youcode.come2play.repository.ReservationRepository;
import com.youcode.come2play.service.ReservationService;
import com.youcode.come2play.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository repository;
    private final TeamService teamService;
    private final ReservationMapper reservationMapper;
    @Override
    public ReservationDto save(ReservationDto reservationDto) throws Exception {
        Optional<Team> optionalTeam1 = teamService.findById(reservationDto.getTeam1());
        Team team1 = optionalTeam1.orElse(null);

        Optional<Team> optionalTeam2 = teamService.findById(reservationDto.getTeam2());
        Team team2 = optionalTeam2.orElse(null);

        Reservation reservation = reservationMapper.toEntity(reservationDto);
        reservation.setTeam1(team1);
        reservation.setTeam2(team2);
        reservation.setReservedAt(LocalDateTime.now());
        reservation.setStatus(Status.PENDING);

        if (optionalTeam1.isPresent() && optionalTeam2.isPresent())
            reservation.setRequestForTeam(RequestForTeam.ACCEPTED);
        else
            reservation.setRequestForTeam(RequestForTeam.PENDING);

        repository.save(reservation);

        return reservationDto;
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
