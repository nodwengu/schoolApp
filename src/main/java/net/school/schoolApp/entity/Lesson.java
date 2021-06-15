package net.school.schoolApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "lesson")
public class Lesson {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "lesson_name")
   private String lessonName;

   private String time;

   //@JsonIgnore
   @ManyToOne
   private Subject subject;

   @ManyToOne
   private Grade grade;

   @ManyToOne
   private Day day;

   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
           name = "learner_lesson",
           joinColumns = { @JoinColumn(name = "lesson_id") },
           inverseJoinColumns = { @JoinColumn(name = "learner_id") })
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   List<Learner> learners = new ArrayList<>();

   public Lesson(Long id, String lessonName, String time) {
      this.id = id;
      this.lessonName = lessonName;
      this.time = time;
   }

}
