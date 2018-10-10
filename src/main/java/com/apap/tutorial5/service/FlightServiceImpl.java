package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.repository.FlightDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDb flightDb;

    @Override
    public boolean addFlight(FlightModel flight) {
        flightDb.save(flight);
        return flightDb.findByFlightNumber(flight.getFlightNumber()) != null;
    }
    
    @Override
    public boolean deleteFlight(String flightNumber) {
        flightDb.delete(this.getFlightDetailByFlightNumber(flightNumber));
        return flightDb.findByFlightNumber(flightNumber) == null;
    }

    @Override
    public void updateFlight(FlightModel flight) {
        FlightModel flightInDb = flightDb.findByFlightNumber(flight.getFlightNumber());
        flightInDb.setOrigin(flight.getOrigin());
        flightInDb.setDestination(flight.getDestination());
        flightInDb.setTime(flight.getTime());
    }

	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
    }
}