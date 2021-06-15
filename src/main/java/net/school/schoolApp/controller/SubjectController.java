package net.school.schoolApp.controller;

import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SubjectController {

   @Autowired
   SubjectService subjectService;

   @PostMapping("/subjects")
   public Subject addSubject(@RequestBody Subject subject) {
      return subjectService.createSubject(subject);
   }

   @GetMapping("/subjects")
   public List<Subject> getAllSubject() {
      return subjectService.getAllSubjects();
   }

   @GetMapping("/subjects/{id}")
   public ResponseEntity getSubjectById(@PathVariable Long id) {
      Subject subject = subjectService.getSubjectById(id);
      if (subject == null) {
         return new ResponseEntity("No subject found for ID: " + id, HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity(subject, HttpStatus.OK);
   }

   @PutMapping("/subjects/{id}")
   public Subject updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
      return subjectService.updateSubject(id, subject);
   }

   @DeleteMapping("/subjects/{id}")
   public boolean deleteSubject(@PathVariable Long id) {
      subjectService.deleteSubject(id);
      return true;
   }


}
