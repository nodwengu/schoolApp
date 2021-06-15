package net.school.schoolApp.service;

import net.school.schoolApp.entity.*;
import net.school.schoolApp.exception.ResourceNotFoundException;
import net.school.schoolApp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class LearnerService {

   @Autowired
   private LearnerRepository learnerRepository;

   @Autowired
   private GradeRepository gradeRepository;

   @Autowired
   private SubjectRepository subjectRepository;

   @Autowired
   private LessonRepository lessonRepository;

   @Autowired
   private NoteRepository noteRepository;

   public List<Learner> getAll() {
      return learnerRepository.findAll();
   }

//   public Learner addLearner(Learner learner) {
//      return learnerRepository.save(learner);
//   }

   public Learner addLearner(Long gradeId, Learner learner) {
      Optional<Grade> optionalGrade = gradeRepository.findById(gradeId);
      learner.setGrade(optionalGrade.get());
      return learnerRepository.save(learner);
   }

   public Learner getLearnerById(Long id) {
      return learnerRepository.findById(id).orElse(null);
   }

   public Learner updateLearner(Long id, Learner learner) {
      Learner existingLearner = learnerRepository.findById(learner.getId()).orElse(null);
      existingLearner.setFirstName(learner.getFirstName());
      existingLearner.setLastName(learner.getLastName());
      existingLearner.setEmail(learner.getEmail());
      existingLearner.setTokens(learner.getTokens());
      return learnerRepository.save(existingLearner);
   }

   public String deleteLearner(Long id) {
      learnerRepository.deleteById(id);
      return "product removed: " + id;
   }


   // CUSTOM QUERIES
//   public List<Learner> getLearnerByGradeId(Long id) {
//      return learnerRepository.findAllByGradeId(id);
//   }
//
//   public List<Learner> getAllByGradeName(String name) {
//      return learnerRepository.findAllByGradeGradeNameIgnoreCase(name);
//   }
//
//   public List<Learner> allByGradeId(Long id) {
//      return learnerRepository.getByGradeId(id);
//   }


//   public Learner selectSubject(Long subjectId, Long learnerId) {
//      Subject subject = subjectRepository.findById(subjectId).orElse(null);
//      Learner learner = learnerRepository.findById(learnerId).orElse(null);
//      learner.getSubjects().add(subject);
//      subject.getLearners().add(learner);
//      return learnerRepository.save(learner);
//   }

   public Learner addSubject(Long subjectId, Learner learner) {
      Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
      Learner existingLearner = learnerRepository.findById(learner.getId()).orElse(null);
      existingLearner.getSubjects().add(existingSubject);

      this.addLearnerLesson(existingSubject, existingLearner);
      return learnerRepository.save(existingLearner);
   }

   private void addLearnerLesson(Subject existingSubject, Learner existingLearner) {
      List<Lesson> lessons = lessonRepository.findAll();
//      lessons.forEach(lesson -> 3 > 7);

      // NEED TO GET LESSONS FOR A SPECIFIC SUBJECT THEN LOOP THROUGH IT RATHER THAN ALL LESSONS

      for (Lesson lesson: lessons) {
         if(existingSubject.getId() == lesson.getSubject().getId() && lesson.getGrade().getId() == existingLearner.getGrade().getId()) {
            existingLearner.getLessons().add(lesson);
         }
      }
   }


   public Learner deleteLearnerSubject(Long subjectId, Learner learner) {
      Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
      Learner existingLearner = learnerRepository.findById(learner.getId()).orElse(null);

      //need to remove subject from existingSubject from existingLearner
      existingLearner.getSubjects().remove(existingSubject);

      existingLearner.getLessons().removeIf( lesson -> existingSubject.getId() == lesson.getSubject().getId() );

      return learnerRepository.save(existingLearner);
   }

   public List<Learner> getAllClassmates(Long id) {
      List<Learner> learners = learnerRepository.findAll();
      Learner existingLearner = learnerRepository.findById(id).orElse(null);
      List<Learner> classmates = new ArrayList<>();

      for (Learner l: learners) {
         if (l.getGrade().getId() == existingLearner.getGrade().getId() && existingLearner.getFirstName() != l.getFirstName()) {
            classmates.add(l);
         }
      }

      return classmates;
   }






   public Learner attendLesson(Long lessonId, Learner learner) {
      Lesson existingLesson = lessonRepository.findById(lessonId).orElse(null);
      Learner existingLearner = learnerRepository.findById(learner.getId()).orElse(null);

      existingLearner.increaseTokens(3);

      return learnerRepository.save(existingLearner);
   }

   public Learner addNotes(Long notesId, Learner learner) {
      Note existingNotes = noteRepository.findById(notesId).orElse(null);
      Learner existingLearner = learnerRepository.findById(learner.getId()).orElse(null);
      existingLearner.getNotes().add(existingNotes);
      return learnerRepository.save(existingLearner);
   }







   public List<Learner> addLearners(List<Learner> learners) {
      return learnerRepository.saveAll(learners);
   }

//   public Learner chooseSubject(Long id, Subject subject) {
//      Learner learner =  learnerRepository.findById(id).orElse(null);
//      learner.getSubjects().add(subject);
//      return learnerRepository.save(learner);
//   }

//
//   public boolean updateLearner(Long id, Learner learner) {
//      for (int i = 0; i < learners.size(); i++) {
//         Learner l = learners.get(i);
//         if (l.getId().equals(id)) {
//            learners.set(i, learner);
//         }
//      }
//      return true;
//   }
//
//   public boolean deleteLearner(Long id) {
//      learners.removeIf(learner -> learner.getId().equals(id));
//      return true;
//   }


   //   public List<Learner> getAll() {
//      List<Learner> learners = new ArrayList<>();
//      learnerRepository.findAll()
//        .forEach(learners::add);
//      System.out.println("LEARNERS: " + learners);
//      return learners;
//   }

//   public Learner getLearner(Long id) {
//      return learners.stream().filter(learner -> learner.getId().equals(id)).findFirst().get();
//   }

//   public Learner getLearnerById(Long id) {
//      return learnerRepository.findById(id)
//              .orElseThrow(() -> new ResourceNotFoundException("No learner found for ID " + id));
//   }



}
