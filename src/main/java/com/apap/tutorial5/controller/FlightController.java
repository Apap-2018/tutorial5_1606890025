package com.apap.tutorial5.controller;

import javax.persistence.EntityManager;

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
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService;

    @RequestMapping(value = "/flight/view")
    private String viewFlight(@RequestParam(value = "flightNumber", required = true) String flightNumber, Model model) {
        FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
        model.addAttribute("flight", flight);
        return "viewFlight";
    }

    @RequestMapping(value = "/flight/add", method = RequestMethod.GET)
    private String add(@RequestParam(value = "licenseNumber", required = true) String licenseNumber, Model model ) {
        FlightModel flight = new FlightModel();
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        flight.setPilot(pilot);
        model.addAttribute("flight", flight);
        return "addFlight";
    }

    @RequestMapping(value = "/flight/add", method = RequestMethod.POST)
    private String addFlightSubmit(@ModelAttribute FlightModel flight, Model model) {
        String response = flightService.addFlight(flight)? "Berhasil Menambahkan Penerbangan":"Gagal Menambahkan Penerbangan";
        model.addAttribute("response", response);
        return "response";
    }

    @RequestMapping(value = "/flight/delete")
    private String deleteFlightSubmit(@ModelAttribute PilotModel pilot, Model model) {
        for (FlightModel flight : pilot.getPilotFlight()) {
            flightService.deleteFlightById(flight.getId());
        }
        return "response";
    }

    @RequestMapping(value = "/flight/update", method = RequestMethod.GET)
    private String updateFlight(@RequestParam(value = "flightNumber", required = true) String flightNumber, Model model) {
        model.addAttribute("flight", flightService.getFlightDetailByFlightNumber(flightNumber));
        return "updateFlight";
    }

    @RequestMapping(value = "/flight/update", method = RequestMethod.POST)
    private String updateFlightSubmit(@ModelAttribute FlightModel flight, Model model) {
        flightService.updateFlight(flight);
        model.addAttribute("response", "Berhasil Mengubah Data Penerbangan");
        return "response";
    }

}