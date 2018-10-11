package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;

public interface FlightService {
    FlightModel getFlightDetailByFlightNumber(String flightNumber);
    boolean addFlight(FlightModel flight);
    void updateFlight(FlightModel flight);
    boolean deleteFlight(String flightNumber);
    void deleteFlightById(long id);
}