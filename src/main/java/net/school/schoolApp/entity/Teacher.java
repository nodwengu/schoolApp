package net.school.schoolApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "teacher")
@Table(name = "teacher")
public class Teacher {

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
           name = "teacher_subject",
           joinColumns = { @JoinColumn(name = "teacher_id") },
           inverseJoinColumns = { @JoinColumn(name = "subject_id") })
   private List<Subject> subjects = new ArrayList<>();


   public Teacher(String firstName, String lastName, String email, int tokens) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.tokens = tokens;
   }

   public Teacher(Long id, String firstName, String lastName, String email, int tokens) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.tokens = tokens;
   }
}
