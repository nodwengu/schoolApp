package net.school.schoolApp.service;

import lombok.RequiredArgsConstructor;
import net.school.schoolApp.entity.Day;
import net.school.schoolApp.entity.Grade;
import net.school.schoolApp.entity.Lesson;
import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.repository.DayRepository;
import net.school.schoolApp.repository.GradeRepository;
import net.school.schoolApp.repository.LessonRepository;
import net.school.schoolApp.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
   @Autowired
   private LessonRepository lessonRepository;

   @Autowired
   private SubjectRepository subjectRepository;

   @Autowired
   private GradeRepository gradeRepository;

   @Autowired
   private DayRepository dayRepository;

   public Lesson addLesson(Long subjectId, Long gradeId, Long dayId, Lesson lesson) {
      Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
      Optional<Grade> optionalGrade = gradeRepository.findById(gradeId);
      Optional<Day> optionalDay = dayRepository.findById(dayId);

      lesson.setSubject(optionalSubject.get());
      lesson.setGrade(optionalGrade.get());
      lesson.setDay(optionalDay.get());

      return lessonRepository.save(lesson);
   }

   public List<Lesson> getAllLessons() {
      return lessonRepository.findAll();
   }

   public Lesson getLessonById(Long id) {
      return lessonRepository.findById(id).orElse(null);
   }

   public Lesson updateLesson(Lesson lesson) {
      Lesson existingLesson = lessonRepository.findById(lesson.getId()).orElse(null);
      existingLesson.setLessonName(lesson.getLessonName());
      existingLesson.setTime(lesson.getTime());
      return lessonRepository.save(existingLesson);
   }

   public String deleteLesson(Long id) {
      lessonRepository.deleteById(id);
      return "removed lesson with ID: " + id;
   }







   //   public Lesson addLessonSubject(Long subjectId, Lesson lesson) {
//      Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
//      lesson.setSubject(optionalSubject.get());
//
//      return lessonRepository.save(lesson);
//   }

//   public Lesson addLessonGrade(Long gradeId, Lesson lesson) {
//      Optional<Grade> optionalGrade = gradeRepository.findById(gradeId);
//      lesson.setGrade(optionalGrade.get());
//      return lessonRepository.save(lesson);
//   }

//   public Lesson addLessonDay(Long dayId, Lesson lesson) {
//      Optional<Day> optionalDay = dayRepository.findById(dayId);
//      lesson.setDay(optionalDay.get());
//      return lessonRepository.save(lesson);
//   }


}
