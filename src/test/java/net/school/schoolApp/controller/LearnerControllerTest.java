package net.school.schoolApp.controller;

import net.school.schoolApp.entity.Grade;
import net.school.schoolApp.entity.Learner;
import net.school.schoolApp.repository.GradeRepository;
import net.school.schoolApp.repository.LearnerRepository;
import net.school.schoolApp.service.GradeService;
import net.school.schoolApp.service.LearnerService;
import org.aspectj.lang.annotation.After;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = LearnerController.class)
public class LearnerControllerTest {


//   private SessionFactory sessionFactory;
//
//   public SessionFactory getSessionFactory() {
//      return getSessionFactory();
//   }
//
//   public void setSessionFactory(SessionFactory sessionFactory) {
//      this.sessionFactory = sessionFactory;
//   }
//
//   public Session getSession() {
//      return sessionFactory.getCurrentSession();
//   }
//
//   public void createGrade(Grade grade) {
//      try {
//         getSession().beginTransaction();
//         getSession().save(grade);
//         getSession().getTransaction().commit();
//      }
//      catch (Exception ex) {
//         ex.printStackTrace();
//      }
//   }
//
//   public void updateGrade(Grade grade) {
//      try {
//         getSession().beginTransaction();
//         getSession().update(grade);
//         getSession().getTransaction().commit();
//      }
//      catch (Exception ex) {
//         ex.printStackTrace();
//      }
//   }
//
//   public void delete(Long id) {
//      try {
//         getSession().beginTransaction();
//         getSession().delete(id);
//         getSession().getTransaction().commit();
//      } catch (Exception ex) {
//         ex.printStackTrace();
//      }
//   }
//
//   public Grade GetOne(Long id) {
//      Grade grade = null;
//      try {
//         getSession().beginTransaction();
//         grade = (Grade) getSession().get(Grade.class, id);
//         getSession().getTransaction().commit();
//      } catch (Exception ex) {
//         ex.printStackTrace();
//      }
//      return grade;
//   }
//
//   public List<Grade> getAllGrades() {
//      List<Grade> grades = null;
//
//      try {
//         getSession().beginTransaction();
//         grades = getSession().createCriteria(Grade.class).list();
//         getSession().getTransaction().commit();
//
//      } catch (Exception ex) {
//         ex.printStackTrace();
//      }
//      return grades;
//   }

//   private EntityManagerFactory emf;

   @MockBean
   private LearnerService learnerService;

   @MockBean
   private LearnerRepository learnerRepository;

   @MockBean
   private GradeRepository gradeRepository;

   @Autowired
   private MockMvc mockMvc;

   //Learner mockLearner = new Learner("Thando", "Nodwengu", "t@gmail.com", 12, Arrays.asList("one", "two"));

//   @BeforeEach
//   public void init(@Mock ) {
//      MockitoAnnotations.openMocks(this);
//   }

   @AfterEach
   public void close() {
      //getSession().close();
   }

   @Test
   void addStudent() throws Exception {
//      mockMvc.perform(MockMvcRequestBuilders.get("/learners/{learnerId}/subject/{subjectId}/add"))
//              .andExpect(status().isOk())
//              .andExpect(jsonPath(""))
      System.out.println("KUYANYEWA...");
   }

   @Test
   void getAll() {
   }

   @Test
   void getLearner() {
   }

   @Test
   void updateLearner() {
   }

   @Test
   void removeLearner() {
   }
}