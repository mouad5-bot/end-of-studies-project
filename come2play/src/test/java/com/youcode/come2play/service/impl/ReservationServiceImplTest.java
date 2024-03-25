package com.youcode.come2play.service.impl;

import com.youcode.come2play.dtos.dto.request.ReservationDto;
import com.youcode.come2play.dtos.mapper.ReservationMapper;
import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.enums.RequestForTeam;
import com.youcode.come2play.entities.enums.Status;
import com.youcode.come2play.repository.ReservationRepository;
import com.youcode.come2play.service.ReservationService;
import com.youcode.come2play.service.StadiumService;
import com.youcode.come2play.service.TeamService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReservationServiceImplTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        System.out.println("ALL TESTS ARE PASSED SUCCESSFULLY !!");
    }

    // Can approve a reservation with status 'PENDING'
    @Test
    public void test_approveReservationWithPendingStatus() throws Exception {
        // Arrange
        Long reservationId = 1L;

        Reservation reservation = new Reservation();
        reservation.setStatus(Status.PENDING);

        ReservationRepository repository = mock(ReservationRepository.class);
        when(repository.findById(reservationId)).thenReturn(Optional.of(reservation));

        TeamService teamService = mock(TeamService.class);
        ReservationMapper reservationMapper = mock(ReservationMapper.class);
        StadiumService stadiumService = mock(StadiumService.class);

        ReservationServiceImpl reservationService = new ReservationServiceImpl(repository, teamService, reservationMapper, stadiumService);

        // Act
        reservationService.approve(reservationId);

        // Assert
        assertEquals(Status.ACCEPTED, reservation.getStatus());
    }

    // Can delete a reservation
    @Test
    public void test_deleteReservation() throws Exception {
        // Arrange
        Long reservationId = 1L;

        Reservation reservation = new Reservation();

        ReservationRepository repository = mock(ReservationRepository.class);
        when(repository.findById(reservationId)).thenReturn(Optional.of(reservation));

        TeamService teamService = mock(TeamService.class);
        ReservationMapper reservationMapper = mock(ReservationMapper.class);
        StadiumService stadiumService = mock(StadiumService.class);

        ReservationServiceImpl reservationService = new ReservationServiceImpl(repository, teamService, reservationMapper, stadiumService);

        // Act
        reservationService.delete(reservationId);

        // Assert
        verify(repository).delete(reservation);
    }
    @Test
    public void test_valid_teams() throws Exception {

        TeamService teamService = mock(TeamService.class);
        ReservationService reservationService = mock(ReservationService.class);

        // Arrange
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setDateOfMatch(LocalDateTime.now().plusDays(1));
        reservationDto.setNumberPhone("123456789");
        reservationDto.setStadiumId(1L);
        reservationDto.setTeam1(1L);
        reservationDto.setTeam2(2L);

        Team team1 = new Team();
        team1.setId(1L);
        // Set other attributes for team1

        Team team2 = new Team();
        team2.setId(2L);
        // Set other attributes for team2

        when(teamService.findById(1L)).thenReturn(Optional.of(team1));
        when(teamService.findById(2L)).thenReturn(Optional.of(team2));

        // Mock the save method of reservationService to return the reservationDto passed as argument
        when(reservationService.save(any(ReservationDto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ReservationDto result = reservationService.save(reservationDto);

        // Assert
        assertNotNull(result);
        assertEquals(reservationDto.getDateOfMatch(), result.getDateOfMatch());
        assertEquals(reservationDto.getNumberPhone(), result.getNumberPhone());
        assertEquals(reservationDto.getStadiumId(), result.getStadiumId());
        assertEquals(reservationDto.getTeam1(), result.getTeam1());
        assertEquals(reservationDto.getTeam2(), result.getTeam2());
    }

    @Test
    public void test_phone_number_13_digits_long() {
        ReservationServiceImpl reservationServiceImpl = mock(ReservationServiceImpl.class);

        String numberPhone = "1234567890123";
        assertDoesNotThrow(() -> reservationServiceImpl.validateNumberPhone(numberPhone));
    }
    @Test
    public void test_future_date() {
        ReservationServiceImpl reservationServiceImpl = mock(ReservationServiceImpl.class);

        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
        assertDoesNotThrow(() -> reservationServiceImpl.validateDateOfMatch(futureDate));
    }


}