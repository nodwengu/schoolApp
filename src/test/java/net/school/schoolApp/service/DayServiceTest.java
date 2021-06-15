package net.school.schoolApp.service;

import net.school.schoolApp.entity.Day;
import net.school.schoolApp.repository.DayRepository;
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
class DayServiceTest {

   @Autowired
   private DayService dayService;

   @MockBean
   private DayRepository dayRepository;

   @Test
   @DisplayName("Should be able to create new day record")
   void createDay() {
      Day day = new Day(1L, "Monday");
      Mockito.when(dayRepository.save(day)).thenReturn(day);
      assertEquals(day, dayService.addDay(day));
   }

   @Test
   @DisplayName("Should be able to return week days")
   void getAllDays() {
      Day day1 = new Day(1L, "Monday");
      Day day2 = new Day(2L, "Tuesday");
      Mockito.when(dayRepository.findAll()).thenReturn(Stream.of(day1, day2).collect(Collectors.toList()));
      assertEquals(2, dayService.getAllDays().size());
   }

   @Test
   @DisplayName("Should be able to return week day by ID")
   void getDayById() {
      Day day1 = new Day(1L, "Sunday");
      Mockito.when(dayRepository.findById(1L)).thenReturn(Optional.of(day1));
      assertNotNull(dayService.getDayById(1L));
      assertEquals(day1, dayService.getDayById(1L));
   }

   @Test
   @DisplayName("Should be able to modify day record")
   void updateDay() {
      Day day1 = new Day(1L, "Sunday");
      Mockito.when(dayRepository.findById(1L)).thenReturn(Optional.of(day1));
      day1.setDayName("dayEighth");
      Mockito.when(dayRepository.save(day1)).thenReturn(day1);
      assertEquals(day1, dayService.updateDay(1L, day1));
   }

   @Test
   @DisplayName("Should be able to remove day record")
   void deleteDay() {
      Day day1 = new Day(1L, "Monday");
      Mockito.when(dayRepository.save(day1)).thenReturn(day1);
      assertEquals("removed day with ID: 1", dayService.deleteDay(1L));
   }
}