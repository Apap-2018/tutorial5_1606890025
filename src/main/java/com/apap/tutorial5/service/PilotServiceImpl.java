package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
    @Autowired
    private PilotDb pilotDb;

	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

    @Override
    public boolean addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
		return pilotDb.findByLicenseNumber(pilot.getLicenseNumber()) != null;
	}
	
	@Override
	public void updatePilot(PilotModel pilot) {
		PilotModel pilotInDb = pilotDb.findByLicenseNumber(pilot.getLicenseNumber());
		pilotInDb.setName(pilot.getName());
		pilotInDb.setFlyHour(pilot.getFlyHour());
	}

	@Override
	public boolean deletePilotByLicenseNumber(String licenseNumber) {
		pilotDb.delete(this.getPilotDetailByLicenseNumber(licenseNumber));
		return pilotDb.findByLicenseNumber(licenseNumber) == null;
	}
}