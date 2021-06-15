package net.school.schoolApp.controller;

import net.school.schoolApp.entity.Grade;
import net.school.schoolApp.entity.Learner;
import net.school.schoolApp.entity.Lesson;
import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.repository.GradeRepository;
import net.school.schoolApp.repository.LessonRepository;
import net.school.schoolApp.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class LessonController {

   @Autowired
   private LessonService lessonService;

   @Autowired
   private GradeRepository gradeRepository;

   @Autowired
   private LessonRepository lessonRepository;

   @PostMapping("/lessons/subject/{subjectId}/grade/{gradeId}/day/{dayId}/add")
   public Lesson addLesson(@PathVariable Long subjectId, @PathVariable Long gradeId, @PathVariable Long dayId, @RequestBody Lesson lesson) {
      return lessonService.addLesson(subjectId, gradeId, dayId, lesson);
   }

   @GetMapping("/lessons")
   public List<Lesson> getAllLessons() {
      return lessonService.getAllLessons();
   }

   @GetMapping("/lessons/{id}")
   public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
      Lesson lesson = lessonService.getLessonById(id);
      if (lesson == null) {
         return new ResponseEntity("No lesson found for ID: " + id, HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity(lesson, HttpStatus.OK);
   }

   @PutMapping("/lessons")
   public Lesson updateLesson(@RequestBody Lesson lesson) {
      return lessonService.updateLesson(lesson);
   }

   @DeleteMapping("/lessons/{id}")
   public String deleteLesson(@PathVariable Long id) {
      lessonService.deleteLesson(id);
      return "removed lesson with ID: " + id;
   }


 //  @PostMapping("/lessons/subject/{id}/add")
//   public Lesson addLesson(@PathVariable Long id, @RequestBody Lesson lesson) {
//      return lessonService.addLessonSubject(id, lesson);
//   }
//
//   @PostMapping("/lessons/grade/{id}/add")
//   public Lesson addLessonGrade(@PathVariable Long id, @RequestBody Lesson lesson) {
//      return lessonService.addLessonGrade(id, lesson);
//   }
//
//
//   @PostMapping("/lessons/day/{id}/add")
//   public Lesson addLessonDay(@PathVariable Long id, @RequestBody Lesson lesson) {
//      return lessonService.addLessonDay(id, lesson);
//   }


}
