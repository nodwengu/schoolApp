package net.school.schoolApp.service;

import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.entity.Teacher;
import net.school.schoolApp.repository.SubjectRepository;
import net.school.schoolApp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
   @Autowired
   private TeacherRepository teacherRepository;

   @Autowired
   private SubjectRepository subjectRepository;

   public Teacher addTeacher(Teacher teacher) {
      return teacherRepository.save(teacher);
   }

   public List<Teacher> getAllTeachers() {
      return teacherRepository.findAll();
   }

   public Teacher getTeacherById(Long id) {
      return teacherRepository.findById(id).orElse(null);
   }

   public Teacher updateTeacher(Teacher teacher) {
      Teacher existingTeacher = teacherRepository.findById(teacher.getId()).orElse(null);
      existingTeacher.setFirstName(teacher.getFirstName());
      existingTeacher.setLastName(teacher.getLastName());
      existingTeacher.setEmail(teacher.getEmail());
      existingTeacher.setTokens(teacher.getTokens());
      return teacherRepository.save(existingTeacher);
   }

   public String deleteTeacher(Long id) {
      teacherRepository.deleteById(id);
      return "removed teacher with ID: " + id;
   }

   public Teacher addSubject(Long subjectId, Teacher teacher) {
      Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
      Teacher existingTeacher = teacherRepository.findById(teacher.getId()).orElse(null);
      existingTeacher.getSubjects().add(existingSubject);
      return teacherRepository.save(existingTeacher);
   }

}
