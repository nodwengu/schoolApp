package net.school.schoolApp.service;

import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.entity.Teacher;
import net.school.schoolApp.repository.SubjectRepository;
import net.school.schoolApp.repository.TeacherRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TeacherServiceTest {

   @Autowired
   private TeacherService teacherService;

   @MockBean
   private TeacherRepository teacherRepository;

   @MockBean
   private SubjectRepository subjectRepository;

   @Test
   @DisplayName("Should be able to create new teacher record")
   public void addTeacher() {
      Teacher teacher = new Teacher(1L, "Vuso", "Mazibuko", "v@gmail.com", 12);
      Mockito.when(teacherRepository.save(teacher)).thenReturn(teacher);
      assertEquals(teacher, teacherService.addTeacher(teacher));
      assertEquals("v@gmail.com", teacherService.addTeacher(teacher).getEmail());
   }

   @Test
   @DisplayName("Should be able to return all teachers")
   public void getAllTeachers() {
      Teacher teacher1 = new Teacher(1L, "Vuso", "Mazibuko", "v@gmail.com", 12);
      Teacher teacher2 = new Teacher(2L, "James", "Bond", "j@gmail.com", 12);

      Mockito.when(teacherRepository.findAll()).thenReturn(Stream.of(teacher1, teacher2).collect(Collectors.toList()));
      assertEquals(2, teacherService.getAllTeachers().size());
      assertEquals("Bond", teacherService.getAllTeachers().get(1).getLastName());
   }

   @Test
   @DisplayName("Should be able to return one teacher by ID")
   public void getTeacherById() {
      Teacher teacher2 = new Teacher(2L, "James", "Bond", "j@gmail.com", 12);
      Mockito.when(teacherRepository.findById(2L)).thenReturn(Optional.of(teacher2));
      assertEquals(teacher2, teacherService.getTeacherById(2L));
      assertEquals("James", teacherService.getTeacherById(2L).getFirstName());
   }

   @Test
   @DisplayName("Should be able to modify teacher record")
   public void updateTeacher() {
      Teacher teacher2 = new Teacher(2L, "James", "Bond", "j@gmail.com", 12);

      Mockito.when(teacherRepository.findById(2L)).thenReturn(Optional.of(teacher2));
      teacher2.setEmail("james@gmail.com");
      teacher2.setFirstName("William");
      Mockito.when(teacherRepository.save(teacher2)).thenReturn(teacher2);

      assertEquals(teacher2, teacherService.updateTeacher(teacher2));
      assertEquals("William", teacherService.updateTeacher(teacher2).getFirstName());
      assertEquals("james@gmail.com", teacherService.updateTeacher(teacher2).getEmail());
   }

   @Test
   @DisplayName("Should be able to remove teacher record")
   public void deleteTeacher() {
      Teacher teacher2 = new Teacher(2L, "James", "Bond", "j@gmail.com", 12);
      Mockito.when(teacherRepository.save(teacher2)).thenReturn(teacher2);
      assertEquals("removed teacher with ID: 2", teacherService.deleteTeacher(2L));
   }

   @Test
   @DisplayName("Should be able to add new subject to teacher")
   public void addSubject() {
      Teacher teacher2 = new Teacher(2L, "James", "Bond", "j@gmail.com", 12);
      Subject subject = new Subject(1L, "History");

      Mockito.when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
      Mockito.when(teacherRepository.findById(2L)).thenReturn(Optional.of(teacher2));
      Mockito.when(teacherRepository.save(teacher2)).thenReturn(teacher2);

      assertEquals(teacher2, teacherService.addSubject(1L, teacher2));
   }
}