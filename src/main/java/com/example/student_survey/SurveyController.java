package com.example.student_survey;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    private SurveyRepository repository;

    @GetMapping
    public List<Survey> getAllSurveys() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return repository.save(survey);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @RequestBody Survey updated) {
        Optional<Survey> optional = repository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Survey existing = optional.get();
    
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setStreet(updated.getStreet());
        existing.setCity(updated.getCity());
        existing.setState(updated.getState());
        existing.setZip(updated.getZip());
        existing.setTelephone(updated.getTelephone());
        existing.setEmail(updated.getEmail());
        existing.setSurveyDate(updated.getSurveyDate());
        existing.setLikedMost(updated.getLikedMost());
        existing.setInterestSource(updated.getInterestSource());
        existing.setRecommendLikelihood(updated.getRecommendLikelihood());

        return ResponseEntity.ok(repository.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/survey")
    public String getSurvey() {
        System.out.println("Pipeline trigger test - SurveyController accessed");
        return "Survey Page";
        
    }

}


