package com.youcode.come2play.service.impl;

import com.youcode.come2play.dtos.dto.request.ReservationDto;
import com.youcode.come2play.dtos.dto.response.ReservationResponseDto;
import com.youcode.come2play.dtos.mapper.ReservationMapper;
import com.youcode.come2play.dtos.mapper.ReservationResponseMapper;
import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.entities.Stadium;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.enums.RequestForTeam;
import com.youcode.come2play.entities.enums.Status;
import com.youcode.come2play.repository.ReservationRepository;
import com.youcode.come2play.service.ReservationService;
import com.youcode.come2play.service.StadiumService;
import com.youcode.come2play.service.TeamService;
import com.youcode.come2play.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    private final StadiumService stadiumService;


    @Override
    public ReservationDto save(ReservationDto reservationDto) throws Exception {
        Team team1 = null;
        Team team2 = null;

        if (reservationDto.getTeam1() != null) {
            Optional<Team> optionalTeam1 = teamService.findById(reservationDto.getTeam1());
            team1 = optionalTeam1.orElse(null);
        }

        if (reservationDto.getTeam2() != null) {
            Optional<Team> optionalTeam2 = teamService.findById(reservationDto.getTeam2());
            team2 = optionalTeam2.orElse(null);
        }

        Long stadiumId = reservationDto.getStadiumId();
        Optional<Stadium> stadium = stadiumService.findById(stadiumId);
        Stadium stadium1 = stadium.orElse(null);

        Reservation reservation = reservationMapper.toEntity(reservationDto);
        reservation.setTeam1(team1);
        reservation.setTeam2(team2);
        reservation.setReservedAt(LocalDateTime.now());
        reservation.setStatus(Status.PENDING);
        reservation.setStadiumId(stadium1);

        if (team1 != null && team2 != null) {
            reservation.setRequestForTeam(RequestForTeam.ACCEPTED);

            if ( team1.getTeamType() != team2.getTeamType()){
                throw new IllegalArgumentException("team type must be same. e.g 7v7 ");
            }
            //if (team1.getUserList().stream());
        } else {
            reservation.setRequestForTeam(RequestForTeam.PENDING);
        }

        validateNumberPhone(reservation.getNumberPhone());
        validateDateOfMatch(reservation.getDateOfMatch());

        repository.save(reservation);

        return reservationDto;
    }

    void validateNumberPhone(String numberPhone) {
        if (numberPhone.length() < 9 || numberPhone.length() > 13) {
            throw new IllegalArgumentException("Phone number must be between 9 and 10 characters.");
        }
    }

     void validateDateOfMatch(LocalDateTime dateOfMatch) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (dateOfMatch.isBefore(currentDateTime)) {
            throw new IllegalArgumentException("Date of match must not be in the past.");
        }
    }

    @Override
    public Reservation edit(Long id) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {
        Reservation reservation = findById(id).orElseThrow(() -> new ResourceNotFoundException("reservation not found !"));
        repository.delete(reservation);
    }

    @Override
    public List<ReservationResponseDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                        .stream()
                                .map( ReservationResponseMapper::toDto )
                                .toList();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void approve(Long id) throws ResourceNotFoundException {
        Reservation reservation = findById(id).orElseThrow( () -> new ResourceNotFoundException("reservation not found"));

        Status status = reservation.getStatus();

       if (status  != Status.PENDING)
           throw new IllegalArgumentException("status is not pending !");

       reservation.setStatus(Status.ACCEPTED);
       repository.save(reservation);
    }
}
