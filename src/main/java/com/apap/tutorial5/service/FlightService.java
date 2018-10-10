package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;

public interface FlightService {
    FlightModel getFlightDetailByFlightNumber(String flightNumber);
    boolean addFlight(FlightModel flight);
    void updateFlight(FlightModel flight);
    boolean deleteFlight(String flightNumber);
}