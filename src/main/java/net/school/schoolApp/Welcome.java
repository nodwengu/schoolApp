package net.school.schoolApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {
   @GetMapping("/")
   public String home() {
      return "Welcome to home page";
   }

   @GetMapping("/welcome")
   public String welcome() {
      return "Welcome to my FIRST Spring Boot project.";
   }
}
