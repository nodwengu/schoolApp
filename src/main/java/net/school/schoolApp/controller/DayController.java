package net.school.schoolApp.controller;

import net.school.schoolApp.entity.Day;
import net.school.schoolApp.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@CrossOrigin
public class DayController {
   @Autowired
   private DayService dayService;

   @PostMapping("/days")
   public Day addDay(@RequestBody Day day) {
      return dayService.addDay(day);
   }

   @GetMapping("/days")
   public List<Day> getAllDays() {
      return dayService.getAllDays();
   }

   @GetMapping("/days/{id}")
   public ResponseEntity getDayById(@PathVariable Long id) {
      Day day = dayService.getDayById(id);
      if (day == null) {
         return new ResponseEntity("No day found for ID: " + id, HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity(day, HttpStatus.OK);
   }

   @PutMapping("days/{id}")
   public Day updateDay(@PathVariable Long id, @RequestBody Day day) {
      return dayService.updateDay(id, day);
   }

   @DeleteMapping("days/{id}")
   public String deleteDay(@PathVariable Long id) {
      dayService.deleteDay(id);
      return "removed day with ID: " + id;
   }


}
