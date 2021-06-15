package net.school.schoolApp.service;

import jdk.nashorn.internal.ir.annotations.Ignore;
import net.school.schoolApp.entity.Grade;
import net.school.schoolApp.entity.Learner;
import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.repository.GradeRepository;
import net.school.schoolApp.repository.LearnerRepository;
import net.school.schoolApp.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LearnerServiceTest {
   @Autowired
   private LearnerService learnerService;

   @MockBean
   private LearnerRepository learnerRepository;

   @MockBean
   private GradeRepository gradeRepository;

   @MockBean
   private SubjectRepository subjectRepository;

//   @BeforeEach
//   public void init() {
//      MockitoAnnotations.openMocks(this);
//   }

   @Test
   @DisplayName("Should be able to return all learners")
   void getAllLearners() {
      Learner thando = new Learner(1L,"Thando", "Nodwengu", "t@gmail.com", 12);
      Learner zane = new Learner(2L,"Zane", "Moosa", "z@gmail.com", 12);

      Mockito.when(learnerRepository.findAll()).thenReturn(Stream.of(thando, zane).collect(Collectors.toList()));
      assertEquals(2, learnerService.getAll().size());
   }

   @Test
   @DisplayName("Should be able to add new student")
   void addLearner() {
      Learner thando = new Learner(1L,"Thando", "Nodwengu", "t@gmail.com", 12);
      Grade grade = new Grade(5L, "grade 10");

      Mockito.when(gradeRepository.findById(5L)).thenReturn(Optional.of(grade));
      //thando.setGrade(grade);
      Mockito.when(learnerRepository.save(thando)).thenReturn(thando);
      assertEquals(thando, learnerService.addLearner(5L, thando));
      System.out.println(learnerService.addLearner(5L, thando));
   }

   @Test
   @DisplayName("Should be able to return one student by ID")
   void getLearnerById() {
      Learner zane = new Learner(2L,"Zane", "Moosa", "z@gmail.com", 12);
      Mockito.when(learnerRepository.findById(2L)).thenReturn(Optional.of(zane));
      assertNotNull(zane);
      assertEquals(zane.getLastName(), "Moosa");
      assertEquals(zane, learnerService.getLearnerById(2L));
   }

   @Test
   @DisplayName("Should be able to change student record")
   void updateLearner() {
      Learner zane = new Learner(2L,"Zane", "Moosa", "z@gmail.com", 12);
      Mockito.when(learnerRepository.findById(2L)).thenReturn(Optional.of(zane));
      zane.setFirstName("Jacob");
      zane.setLastName("Zuma");
      Mockito.when(learnerRepository.save(zane)).thenReturn(zane);
      assertEquals(zane, learnerService.updateLearner(2L, zane));
      assertEquals("Jacob", learnerService.getLearnerById(2L).getFirstName());
   }

   @Test
   @DisplayName("Should be able to remove record learner record")
   void deleteLearner() {
      Learner zane = new Learner(2L,"Zane", "Moosa", "z@gmail.com", 12);
      Mockito.when(learnerRepository.save(zane)).thenReturn(zane);
      assertEquals("product removed: 2", learnerService.deleteLearner(2L));
   }

   @Test
   @DisplayName("Should be able to add a subject to learner_subjects")
   public void chooseSubject() {
      Learner zane = new Learner(2L,"Zane", "Moosa", "z@gmail.com", 12);
      Subject subject = new Subject(3L, "Biology");

      Mockito.when(learnerRepository.save(zane)).thenReturn(zane);
      Mockito.when(subjectRepository.findById(3L)).thenReturn(Optional.of(subject));
      Mockito.when(learnerRepository.findById(2L)).thenReturn(Optional.of(zane));

      assertEquals(zane, learnerService.addSubject(3L, zane));
   }



}