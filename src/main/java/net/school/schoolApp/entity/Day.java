package net.school.schoolApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "days")
public class Day {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "day_name")
   private String dayName;

   @JsonIgnore
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "day_id")
   private List<Lesson> lessons = new ArrayList<>();

   public Day(Long id, String dayName) {
      this.id = id;
      this.dayName = dayName;
   }

   public Day(String dayName) {
      this.dayName = dayName;
   }
}
