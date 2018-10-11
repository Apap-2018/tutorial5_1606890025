package com.apap.tutorial5.controller;

import javax.websocket.server.PathParam;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PilotController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService;

    @RequestMapping("/")
    private String home() {
        return "home";
    }

    @RequestMapping(value = "/pilot/view")
    private String viewPilot(@RequestParam(value = "licenseNumber", required = true) String licenseNumber, Model model) {
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        model.addAttribute("pilot", pilot);
        return "viewPilot"; 
    }

    @RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
    private String add(Model model) {
        model.addAttribute("pilot", new PilotModel());
        return "addPilot";
    }

    @RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
    private String addPilotSubmit(@ModelAttribute PilotModel pilot, Model model) {
        String response = pilotService.addPilot(pilot) ? "Berhasil Menambahkan Pilot":"Gagal Menambahkan Pilot";
        model.addAttribute("response", response);
        return "response";
    }

    @RequestMapping(value = "/pilot/add-flight", method = RequestMethod.GET)
    private String pilotAddFlight(@RequestParam(value = "licenseNumber", required = true) String licenseNumber, Model model) {
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        model.addAttribute("pilot", pilot);
        return "pilotAddFlight";
    }

    @RequestMapping(value = "/pilot/delete")
    private String deletePilot(@ModelAttribute PilotModel pilot, Model model) {
        for (FlightModel flight : pilot.getPilotFlight()) {
            flightService.deleteFlight(flight.getFlightNumber());
        }
        return "response";
    }
    
    @RequestMapping(value = "/pilot/update")
    private String updatePilot(@RequestParam(value = "licenseNumber", required = true) String licenseNumber, Model model) {
        model.addAttribute("pilot", pilotService.getPilotDetailByLicenseNumber(licenseNumber));
        return "updatePilot";
    }

    @RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
    private String updatePilotSubmit(@ModelAttribute PilotModel pilot, Model model) {
        pilotService.updatePilot(pilot);
        model.addAttribute("response", "Berhasil Mengubah Data Pilot");
        return "response";
    }
}