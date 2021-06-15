package net.school.schoolApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue
   private long id;
   private String name;
   private String role;

   public User(String name, String role) {
      this.name = name;
      this.role = role;
   }

}
