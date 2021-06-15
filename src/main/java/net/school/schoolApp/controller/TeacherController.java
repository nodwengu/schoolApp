package net.school.schoolApp.controller;

import net.school.schoolApp.entity.Teacher;
import net.school.schoolApp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TeacherController {
   @Autowired
   private TeacherService teacherService;

   @PostMapping("/teachers")
   public Teacher addTeacher(@RequestBody Teacher teacher) {
      return teacherService.addTeacher(teacher);
   }

   @GetMapping("/teachers")
   public List<Teacher> getAllTeachers() {
      return teacherService.getAllTeachers();
   }

   @GetMapping("/teachers/{id}")
   public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
      Teacher teacher = teacherService.getTeacherById(id);
      if (teacher == null) {
         return new ResponseEntity("No learner found for ID " + id, HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity(teacher, HttpStatus.OK);
   }

   @PutMapping("/teachers")
   public Teacher updateTeacher(@RequestBody Teacher teacher) {
      return teacherService.updateTeacher(teacher);
   }

   @DeleteMapping("/teachers/{id}")
   public String removeTeacher(@PathVariable Long id) {
      teacherService.deleteTeacher(id);
      return "removed teacher with ID: " + id;
   }

   @PostMapping("/teachers/subject/{id}/add")
   public Teacher addTeacherSubject(@PathVariable Long id, @RequestBody Teacher teacher) {
      return teacherService.addSubject(id, teacher);
   }


}
