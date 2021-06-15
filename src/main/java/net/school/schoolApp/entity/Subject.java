package net.school.schoolApp.entity;

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
@Entity(name = "subject")
@Table(name = "subject")
public class Subject {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "subject_name")
   private String subjectName;

   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
           name = "learner_subject",
           joinColumns = { @JoinColumn(name = "subject_id") },
           inverseJoinColumns = { @JoinColumn(name = "learner_id") })
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   List<Learner> learners = new ArrayList<>();

   @JsonIgnore
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "subject_id")
   private List<Lesson> lessons = new ArrayList<>();

   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
           name = "teacher_subject",
           joinColumns = { @JoinColumn(name = "subject_id") },
           inverseJoinColumns = { @JoinColumn(name = "teacher_id") })
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   List<Teacher> teachers = new ArrayList<>();

   public Subject(Long id, String subjectName) {
      this.id = id;
      this.subjectName = subjectName;
   }

}
