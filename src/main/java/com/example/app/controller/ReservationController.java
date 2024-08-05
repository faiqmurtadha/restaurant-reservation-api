package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.request.CreateReservationRequest;
import com.example.app.dto.response.ReservationResponse;
import com.example.app.services.ReservationService;

@RestController
@RequestMapping("reservation-management")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/reservations")
    public ResponseEntity<?> createReservations(@RequestBody CreateReservationRequest request) {
        return reservationService.create(request);
    }
}
