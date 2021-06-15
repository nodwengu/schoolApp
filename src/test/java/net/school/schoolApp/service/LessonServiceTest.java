package net.school.schoolApp.service;

import net.school.schoolApp.entity.Day;
import net.school.schoolApp.entity.Grade;
import net.school.schoolApp.entity.Lesson;
import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.repository.DayRepository;
import net.school.schoolApp.repository.GradeRepository;
import net.school.schoolApp.repository.LessonRepository;
import net.school.schoolApp.repository.SubjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LessonServiceTest {

   @Autowired
   private LessonService lessonService;

   @MockBean
   private LessonRepository lessonRepository;

   @MockBean
   private DayRepository dayRepository;

   @MockBean
   private GradeRepository gradeRepository;

   @MockBean
   private SubjectRepository subjectRepository;

   @Test
   @DisplayName("Should be able to add new lesson")
   public void addLesson() {
      Lesson lesson = new Lesson(1L, "algebra", "10:00");
      Day day = new Day(1L, "Friday");
      Grade grade = new Grade(1L, "grade 2");
      Subject subject = new Subject(1L, "Mathematics");

      Mockito.when(dayRepository.findById(1L)).thenReturn(Optional.of(day));
      Mockito.when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));
      Mockito.when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
      Mockito.when(lessonRepository.save(lesson)).thenReturn(lesson);

      assertEquals(lesson, lessonService.addLesson(1L, 1L, 1L, lesson));
   }

   @Test
   @DisplayName("Should be able to return all lessons")
   public void getAllLessons() {
      Lesson lesson1 = new Lesson(1L, "algebra", "10:00");
      Lesson lesson2 = new Lesson(2L, "Punctuation", "11:00");

      Mockito.when(lessonRepository.findAll()).thenReturn(Stream.of(lesson1, lesson2).collect(Collectors.toList()));
      assertEquals(2, lessonService.getAllLessons().size());
      assertEquals("Punctuation", lessonService.getAllLessons().get(1).getLessonName());
   }

   @Test
   @DisplayName("Should be able to return one lesson by ID")
   public void getLessonById() {
      Lesson lesson1 = new Lesson(5L, "algebra", "10:00");
      Mockito.when(lessonRepository.findById(5L)).thenReturn(Optional.of(lesson1));
      assertEquals(lesson1, lessonService.getLessonById(5L));
      assertEquals("algebra", lessonService.getLessonById(5L).getLessonName());
   }

   @Test
   @DisplayName("Should be able to modify lesson record")
   public void updateLesson() {
      Lesson lesson1 = new Lesson(5L, "algebra", "10:00");
      Day day = new Day(1L, "Friday");

      Mockito.when(dayRepository.findById(1L)).thenReturn(Optional.of(day));
      Mockito.when(lessonRepository.findById(5L)).thenReturn(Optional.of(lesson1));

      lesson1.setLessonName("linear expressions");
      lesson1.setTime("13:00");
      lesson1.setDay(day);
      Mockito.when(lessonRepository.save(lesson1)).thenReturn(lesson1);

      assertEquals(lesson1, lessonService.updateLesson(lesson1));
      assertEquals(day, lessonService.updateLesson(lesson1).getDay());
   }

   @Test
   @DisplayName("Should be able remove lesson record")
   public void deleteLesson() {
      Lesson lesson1 = new Lesson(5L, "algebra", "10:00");
      Mockito.when(lessonRepository.save(lesson1)).thenReturn(lesson1);
      assertEquals("removed lesson with ID: 5", lessonService.deleteLesson(5L));
   }
}