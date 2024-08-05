package com.example.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.app.dto.request.CreateReservationRequest;
import com.example.app.dto.response.ReservationResponse;
import com.example.app.model.Reservation;
import com.example.app.repository.ReservationRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;

    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        try {
            List<Reservation> reservations = reservationRepository.findAll();
            List<ReservationResponse> reservationData = reservations.stream().map(reservation ->
                new ReservationResponse(
                    reservation.getName(),
                    reservation.getDay()
                )
            ).collect(Collectors.toList());

            return ResponseEntity
                .ok()
                .body(reservationData);
        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }

    @Transactional
    public ResponseEntity<?> create(CreateReservationRequest request) {
        String reservationDay = request.getDay();

        if (reservationDay.equalsIgnoreCase("Rabu") || reservationDay.equalsIgnoreCase("Jumat")) {
            return ResponseEntity
                .badRequest()
                .body("Restoran Libur");
        }

        Integer reservationDayCount = reservationRepository.countCustomerDay(reservationDay);
        if (reservationDayCount >= 2) {
            return ResponseEntity
                .badRequest()
                .body("Restoran Penuh");
        }

        Reservation newReservation = Reservation.builder()
            .name(request.getName())
            .day(request.getDay())
            .build();
        
        reservationRepository.save(newReservation);

        return ResponseEntity
            .ok()
            .body("Reservasi berhasil disimpan");
    }
}
