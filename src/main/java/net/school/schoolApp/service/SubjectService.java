package net.school.schoolApp.service;

import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
   @Autowired
   private SubjectRepository subjectRepository;

   public Subject createSubject(Subject subject) {
      return subjectRepository.save(subject);
   }

   public List<Subject> getAllSubjects() {
      return subjectRepository.findAll();
   }

   public Subject getSubjectById(Long id) {
      return subjectRepository.findById(id).orElse(null);
   }

   public Subject updateSubject(Long id, Subject subject) {
      Subject existingSubject = subjectRepository.findById(subject.getId()).orElse(null);
      existingSubject.setId(subject.getId());
      existingSubject.setSubjectName(subject.getSubjectName());
      return subjectRepository.save(existingSubject);
   }

   public String deleteSubject(Long id) {
      subjectRepository.deleteById(id);
      return "removed subject with ID: " + id;
   }

}
