package net.school.schoolApp.controller;

import net.school.schoolApp.entity.Learner;
import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.repository.GradeRepository;
import net.school.schoolApp.repository.LearnerRepository;
import net.school.schoolApp.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class LearnerController{

   @Autowired
   LearnerRepository learnerRepository;

   @Autowired
   private LearnerService learnerService;

   @Autowired
   private GradeRepository gradeRepository;


   @PostMapping("/learners/grade/{id}/add")
   public Learner addStudent(@PathVariable Long id, @Valid @RequestBody Learner learner) {
      return learnerService.addLearner(id, learner);
   }

//   @PostMapping("/learners/grade/{id}")
//   public ResponseEntity<Learner> create(@RequestBody Learner learner) {
//      Learner savedLearner = learnerRepository.save(learner);
//      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//              .buildAndExpand(savedLearner.getId()).toUri();
//
//      System.out.println("POSTING: " + ResponseEntity.created(location).body(savedLearner));
//      return ResponseEntity.created(location).body(savedLearner);
//
//   }

//   @PostMapping("/learners")
//   public ResponseEntity<Learner> create2(@RequestBody Learner learner) {
//      System.out.println("DO WE EVEN GET HERE...");
////      Optional<Grade> optionalGrade = gradeRepository.findById(13l);
//      Optional<Grade> optionalGrade = gradeRepository.findById(learner.getGrade().getId());
//
//      if (!optionalGrade.isPresent()) {
//         return ResponseEntity.unprocessableEntity().build();
//      }
//
//      learner.setGrade(optionalGrade.get());
//
//      Learner savedLearner = learnerRepository.save(learner);
//      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//              .buildAndExpand(savedLearner.getId()).toUri();
//
//      return ResponseEntity.created(location).body(savedLearner);
//   }

   @GetMapping("/learners")
   public List<Learner> getAll() {
      return learnerService.getAll();
   }

   @GetMapping("/learners/{id}")
   public ResponseEntity getLearner(@PathVariable Long id) {
      Learner learner = learnerService.getLearnerById(id);
      if (learner == null) {
         return new ResponseEntity("No learner found for ID " + id, HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity(learner, HttpStatus.OK);
   }

   @PutMapping("/learners/{id}")
   public Learner updateLearner(@PathVariable Long id, @RequestBody Learner learner) {
      return learnerService.updateLearner(id, learner);
   }

   @DeleteMapping("/learners/{id}")
   public void removeLearner(@PathVariable Long id) {
      learnerService.deleteLearner(id);
   }

//   @GetMapping("learners/grade/{id}")
//   public List<Learner> getLearneByGradeId(@PathVariable Long id) {
//      return learnerService.getLearnerByGradeId(id);
//   }

//   @GetMapping("learners/grade/{name}")
//   public List<Learner> getLearneByGradeName(@PathVariable String name) {
//      return learnerService.getAllByGradeName(name);
//   }

//   @GetMapping("learners/grade/{id}")
//   public List<Learner> getLearnersByGradeId(@PathVariable Long id) {
//      return learnerService.allByGradeId(id);
//   }

   @PutMapping("/learners/subject/{subjectId}/add")
   public Learner addLearnerSubject(@PathVariable Long subjectId, @RequestBody Learner learner) {
      return learnerService.addSubject(subjectId, learner);
   }

   @PutMapping("/learners/subject/{subjectId}/remove")
   public Learner removeLearnerSubject(@PathVariable Long subjectId, @RequestBody Learner learner) {
      return learnerService.deleteLearnerSubject(subjectId, learner);
   }

   @PutMapping("/learners/lesson/{id}/attend")
   public Learner attendLesson(@PathVariable Long id, @RequestBody Learner learner) {
      return learnerService.attendLesson(id, learner);
   }

   @PutMapping("/learners/notes/{id}/add")
   public Learner addLearnerNotes(@PathVariable Long id, @RequestBody Learner learner) {
      return learnerService.addNotes(id, learner);
   }

   @GetMapping("/learners/{id}/classmates")
   public List<Learner> getClassMates(@PathVariable Long id) {
      return learnerService.getAllClassmates(id);
   }











   //   @PostMapping("/learners")
//   public List<Learner> addLearners(@RequestBody List<Learner> learners) {
//      return learnerService.addLearners(learners);
//   }


//   @PostMapping("learners/{learnerId}/subjects")
//   public Learner selectSubject(@PathVariable Long learnerId, @RequestBody Subject subject) {
//      return learnerService.chooseSubject(learnerId, subject);
//   }


   //   @PostMapping("/learners")
//   public ResponseEntity addLearner(@RequestBody Learner learner) {
//      //return learnerService.addLearner(learner);
//      //return learnerService.addLearner1(learner);
//
//      Optional<Grade> optionalGrade= gradeRepository.findById(12L);
//      learner.setGrade(optionalGrade.get());
//
//      Learner savedLearner = learnerRepository.save(learner);
//      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//              .buildAndExpand(savedLearner.getId()).toUri();
//
//      return ResponseEntity.created(location).body(savedLearner);
//   }



}
