package net.school.schoolApp.controller;

import lombok.RequiredArgsConstructor;
import net.school.schoolApp.entity.Grade;
import net.school.schoolApp.entity.Learner;
import net.school.schoolApp.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GradeController {
   @Autowired
   private GradeService gradeService;

   @PostMapping("/grades")
   public Grade addGrade(@RequestBody Grade grade) {
      return gradeService.addGrade(grade);
   }

   //@CrossOrigin(origins = "http://localhost:8080")
   @GetMapping("/grades")
   public List<Grade> getAllGrades() {
//      System.out.println("GRADES: " + gradeService.getAllGrades());
      return gradeService.getAllGrades();
   }

   @GetMapping("grades/{id}")
   public ResponseEntity getGradeById(@PathVariable Long id) {
      Grade grade = gradeService.getGradeById(id);

      if (grade == null) {
         return new ResponseEntity("No grade found for ID " + id, HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity(grade, HttpStatus.OK);
   }

   @PutMapping("grades/{id}")
   public Grade updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
     return gradeService.updateGrade(id, grade);
   }

//   @DeleteMapping("/grades/{id}")
//   public void removeGrade(@PathVariable Long id) {
//      gradeService.deleteGrade(id);
//   }

   @DeleteMapping("/grades/{id}")
   public ResponseEntity<?> removeGrade(@PathVariable Long id) {
      gradeService.deleteGrade(id);
      return ResponseEntity.ok().build();
   }

//   @PostMapping("grades/{id}/learner")
//   public void addLearner(@PathVariable Long id, Learner learner) {
//      //gradeService.addLearner(id, learner);
//
//   }


}
