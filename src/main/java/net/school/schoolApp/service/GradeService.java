package net.school.schoolApp.service;

import net.school.schoolApp.entity.Grade;
import net.school.schoolApp.entity.Learner;
import net.school.schoolApp.exception.ResourceNotFoundException;
import net.school.schoolApp.repository.GradeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

   @Autowired
   private GradeRepository gradeRepository;

   public Grade addGrade(Grade grade) {
      return gradeRepository.save(grade);
   }

   public List<Grade> getAllGrades() {
      return gradeRepository.findAll();
   }

//   public Grade getGradeById(Long id) {
//      return gradeRepository.findById(id).orElse(null);
//   }

   public Grade getGradeById(Long id) {
      return gradeRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("No Grade found for ID: " + id));
   }

   public Grade updateGrade(Long id, Grade grade) {
      Grade existingGrade = gradeRepository.findById(grade.getId()).orElse(null);
      existingGrade.setId(grade.getId());
      existingGrade.setGradeName(grade.getGradeName());
      return gradeRepository.save(existingGrade);
   }

   public boolean deleteGrade(Long id) {
      gradeRepository.deleteById(id);
      return true;
   }

}
