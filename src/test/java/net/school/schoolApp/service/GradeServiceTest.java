package net.school.schoolApp.service;

import net.school.schoolApp.controller.GradeController;
import net.school.schoolApp.entity.Grade;
import net.school.schoolApp.repository.GradeRepository;
import org.aspectj.lang.annotation.After;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GradeServiceTest {

   @Autowired
   private GradeService gradeService;

   @MockBean
   private GradeRepository gradeRepository;

   @Test
   @DisplayName("Should be able return all grades")
   public void getAllGrades() {
      Grade grade = new Grade("Grade 15");
      Grade grade2 = new Grade("Grade 16");
      Mockito.when(gradeRepository.findAll()).thenReturn( Stream
              .of(grade, grade2).collect(Collectors.toList()) );
      assertEquals(2, gradeService.getAllGrades().size());
   }

   @Test
   @DisplayName("Should be able add new grade record")
   public void createGrade() {
      Grade grade = new Grade(10L, "Grade 10");
      Mockito.when(gradeRepository.save(grade)).thenReturn(grade);

      assertNotNull(gradeService.addGrade(grade));
      assertEquals(grade, gradeService.addGrade(grade));
   }

   @Test
   @DisplayName("Should be able to return a specific grade by ID")
   void getGradeById() {
      Grade grade = new Grade(1L, "Grade 5");
      Mockito.when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));
      
      Grade result = gradeService.getGradeById(1L);

      assertNotNull(result);
      assertEquals(grade, result);
      assertEquals("Grade 5", result.getGradeName());
   }

   @Test
   @DisplayName("Should be able to update grade record")
   void updateGrade() {
      Grade grade = new Grade(1L, "Grade 15");

      Mockito.when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));
      grade.setGradeName("Updated name");
      Mockito.when(gradeRepository.save(grade)).thenReturn(grade);

      assertEquals(grade, gradeService.updateGrade(1L, grade));
   }

   @Test
   @DisplayName("Should be able remove grade record from the database")
   void deleteGrade() {
      Grade grade = new Grade(53L, "Grade 15");
      //Mockito.verify(gradeRepository, Mockito.times(1)).deleteById(53L);
      assertEquals(true, gradeService.deleteGrade(53L));
   }
}


//   private SessionFactory sessionFactory;

//   public SessionFactory getSessionFactory() {
//      return getSessionFactory();
//   }

//   public void setSessionFactory(SessionFactory sessionFactory) {
//      this.sessionFactory = sessionFactory;
//   }

//   public Session getSession() {
//      return sessionFactory.getCurrentSession();
//   }

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
//
//   private EntityManagerFactory emf;
//
//   @MockBean
//   private LearnerService learnerService;
//
//   @Autowired
//   private MockMvc mockMvc;
//
//   @After(value = "")
//   public void close() {
//      getSession().close();
//   }