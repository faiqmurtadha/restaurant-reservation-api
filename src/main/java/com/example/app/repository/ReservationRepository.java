package com.example.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.app.model.Reservation;

@Repository
public class ReservationRepository {
    
    private List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> findAll() {
        return reservations;
    }

    public Reservation save(Reservation reservation) {
        reservations.add(reservation);
        return reservation;
    }

    public Integer countCustomerDay(String day) {
        return reservations.stream().filter(reservation ->
            reservation.getDay().equalsIgnoreCase(day)
        ).toList().size();
    }
}
