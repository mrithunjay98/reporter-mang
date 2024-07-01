package com.incidentreport.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incidentreport.entity.Incident;
import com.incidentreport.entity.User;
import com.incidentreport.repo.IncidentRepository;
import com.incidentreport.repo.UserRepository;

@Service
public class IncidentService {
	 @Autowired
	    private UserRepository userRepository;

	
	@Autowired
    private IncidentRepository incidentRepository;

    public Incident createIncident(Long userId ,Incident incident) {
     
    	
    	  User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
          incident.setUser(user);

          // Generate unique incident ID
          String incidentId = generateUniqueIncidentId();
          incident.setIncidentId(incidentId);
          incident.setReportedDateTime(LocalDateTime.now());
        return incidentRepository.save(incident);
    }

    public Incident findIncidentByIncidentId(String incidentId) {
       
    	
    	return incidentRepository.findByIncidentId(incidentId).orElse(null);
   
    
    }
    private String generateUniqueIncidentId() {
        String incidentId;
        Random random = new Random();
        do {
            int randomNumber = 10000 + random.nextInt(90000);
            incidentId = "RMG" + randomNumber + LocalDateTime.now().getYear();
        } while (incidentRepository.findByIncidentId(incidentId).isPresent());

        return incidentId;
    }
}

