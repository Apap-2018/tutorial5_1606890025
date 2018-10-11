package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;

public interface PilotService {
    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
    boolean addPilot(PilotModel pilot);    
    boolean deletePilotByLicenseNumber(String licenseNumber);
	void updatePilot(PilotModel pilot);
}