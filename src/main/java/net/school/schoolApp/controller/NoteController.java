package net.school.schoolApp.controller;

import net.school.schoolApp.entity.Note;
import net.school.schoolApp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class NoteController {

   @Autowired
   NoteService noteService;

   @PostMapping("/notes")
   public Note addNotes(@RequestBody Note note) {
      return noteService.createNotes(note);
   }

   @GetMapping("/notes")
   public List<Note> getAllNotes() {
      return noteService.getAllNotes();
   }

   @GetMapping("/notes/{id}")
   public ResponseEntity getNoteById(@PathVariable Long id) {
      Note note = noteService.getNotesById(id);
      if (note == null) {
         return new ResponseEntity("No note found for ID: " + id, HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity(note, HttpStatus.OK);
   }

   @PutMapping("/notes/{id}")
   public Note updateNote(@PathVariable Long id, @RequestBody Note note) {
      return noteService.updateNotes(note);
   }

   @DeleteMapping("/notes/{id}")
   public boolean deleteNote(@PathVariable Long id) {
      noteService.deleteNotes(id);
      return true;
   }
}
