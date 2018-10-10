package com.apap.tutorial4.service;

import com.apap.tutorial4.model.PilotModel;

public interface PilotService {
    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
    boolean addPilot(PilotModel pilot);    
    boolean deletePilotByLicenseNumber(String licenseNumber);
	void updatePilot(PilotModel pilot);
}