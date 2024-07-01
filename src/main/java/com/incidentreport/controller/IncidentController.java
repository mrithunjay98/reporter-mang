package com.incidentreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incidentreport.entity.Incident;
import com.incidentreport.service.IncidentService;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    @Autowired
    private IncidentService incidentService;

    @PostMapping
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident ,Long userId ) {
        Incident createdIncident = incidentService.createIncident(userId,incident);
        return new ResponseEntity<>(createdIncident, HttpStatus.CREATED);
    }

    @GetMapping("/{incidentId}")
    public ResponseEntity<Incident> getIncidentByIncidentId(@PathVariable String incidentId) {
        Incident incident = incidentService.findIncidentByIncidentId(incidentId);
        return new ResponseEntity<>(incident, HttpStatus.OK);
    }
}

