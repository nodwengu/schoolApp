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
@Entity(name = "grade")
@Table(name = "grade")
public class Grade {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "grade_name")
   private String gradeName;

   @JsonIgnore
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "grade_id")
   private List<Learner> learners = new ArrayList<>();

   @JsonIgnore
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "grade_id")
   private List<Lesson> lessons = new ArrayList<>();

   public Grade(String gradeName) {
      this.gradeName = gradeName;
   }

   public Grade(Long id, String gradeName) {
      this.id = id;
      this.gradeName = gradeName;
   }

   public void setLearner(List<Learner> learners) {
      this.learners = learners;
   }

}
