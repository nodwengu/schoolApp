package net.school.schoolApp.service;

import net.school.schoolApp.entity.Day;
import net.school.schoolApp.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class DayService {
   @Autowired
   private DayRepository dayRepository;

   public Day addDay(Day day) {
      return dayRepository.save(day);
   }

   public List<Day> getAllDays() {
      return dayRepository.findAll();
   }

   public Day getDayById(Long id) {
      return dayRepository.findById(id).orElse(null);
   }

   public Day updateDay(Long id, Day day) {
      Day existingDay = dayRepository.findById(day.getId()).orElse(null);
      existingDay.setId(day.getId());
      existingDay.setDayName(day.getDayName());
      return dayRepository.save(existingDay);
   }

   public String deleteDay(Long id) {
      dayRepository.deleteById(id);
      return "removed day with ID: " + id;
   }

}
