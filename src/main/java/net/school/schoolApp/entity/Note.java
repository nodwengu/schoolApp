package net.school.schoolApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "note")
public class Note {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "notes_text")
   private String notesText;

   @JsonIgnore
   @ManyToOne()
   private Learner learner;

   public Note(Long id, String notesText) {
      this.id = id;
      this.notesText = notesText;
   }


}
