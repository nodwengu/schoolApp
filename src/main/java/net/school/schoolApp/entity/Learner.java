package net.school.schoolApp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "learner")
@Table(name = "learner")
public class Learner {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @Column(name = "tokens")
   private int tokens;

   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
     name = "learner_subject",
     joinColumns = { @JoinColumn(name = "learner_id") },
     inverseJoinColumns = { @JoinColumn(name = "subject_id") })
   private List<Subject> subjects = new ArrayList<>();

   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
           name = "learner_lesson",
           joinColumns = { @JoinColumn(name = "learner_id") },
           inverseJoinColumns = { @JoinColumn(name = "lesson_id") })
   private List<Lesson> lessons = new ArrayList<>();



   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "learner_id")
   private List<Note> notes = new ArrayList<>();

   @ManyToOne()
   private Grade grade;

   public Learner(Long id, String firstName, String lastName, String email, int tokens) {
      super();
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.tokens = tokens;
   }

   public Learner(String firstName, String lastName, String email, int tokens) {
      super();
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.tokens = tokens;
   }

   public Learner(String firstName, String lastName, String email, int tokens, Grade grade) {
      super();
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.tokens = tokens;
   }

   public void increaseTokens(int value) {
      this.tokens += value;
   }

   public void decreaseTokens(int value) {
      this.tokens -= value;
   }

}

