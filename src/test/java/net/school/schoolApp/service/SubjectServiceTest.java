package net.school.schoolApp.service;

import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.repository.SubjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SubjectServiceTest {

   @Autowired
   private SubjectService subjectService;

   @MockBean
   private SubjectRepository subjectRepository;

   @Test
   @DisplayName("Should be able to add new subject record")
   void createSubject() {
      Subject subject = new Subject(1L, "Biology");

      Mockito.when(subjectRepository.save(subject)).thenReturn(subject);

      assertEquals(subject, subjectService.createSubject(subject));
      assertEquals("Biology", subjectService.createSubject(subject).getSubjectName());
   }

   @Test
   @DisplayName("Should be able to return all subjects")
   void getAllSubjects() {
      Subject subject1 = new Subject(1L, "Biology");
      Subject subject2 = new Subject(2L, "English");

      Mockito.when(subjectRepository.findAll()).thenReturn(Stream.of(subject1, subject2).collect(Collectors.toList()));
      assertEquals(2, subjectService.getAllSubjects().size());
      assertEquals("English", subjectService.getAllSubjects().get(1).getSubjectName());
   }

   @Test
   @DisplayName("Should be able to return subject record by ID")
   void getSubjectById() {
      Subject subject2 = new Subject(2L, "English");

      Mockito.when(subjectRepository.findById(2L)).thenReturn(Optional.of(subject2));

      assertNotNull(subjectService.getSubjectById(2L));
      assertEquals(subject2, subjectService.getSubjectById(2L));
   }

   @Test
   @DisplayName("Should be able to update subject record")
   void updateSubject() {
      Subject subject2 = new Subject(2L, "English");

      Mockito.when(subjectRepository.findById(2L)).thenReturn(Optional.of(subject2));
      subject2.setSubjectName("English Updated");
      Mockito.when(subjectRepository.save(subject2)).thenReturn(subject2);

      assertEquals(subject2, subjectService.updateSubject(2L, subject2));
      assertEquals("English Updated", subjectService.updateSubject(2L, subject2).getSubjectName());
   }

   @Test
   @DisplayName("Should be able to remove subject record")
   void deleteSubject() {
      Subject subject2 = new Subject(2L, "English");
      Mockito.when(subjectRepository.save(subject2)).thenReturn(subject2);
      assertEquals("removed subject with ID: 2", subjectService.deleteSubject(2L));
   }
}