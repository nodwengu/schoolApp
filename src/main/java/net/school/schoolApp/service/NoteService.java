package net.school.schoolApp.service;

import net.school.schoolApp.entity.Note;
import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.repository.NoteRepository;
import net.school.schoolApp.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
   @Autowired
   private NoteRepository noteRepository;

   public Note createNotes(Note note) {
      return noteRepository.save(note);
   }

   public List<Note> getAllNotes() {
      return noteRepository.findAll();
   }

   public Note getNotesById(Long id) {
      return noteRepository.findById(id).orElse(null);
   }

   public Note updateNotes(Note note) {
      Note existingNotes = noteRepository.findById(note.getId()).orElse(null);
      existingNotes.setId(note.getId());
      existingNotes.setNotesText(note.getNotesText());
      return noteRepository.save(existingNotes);
   }

   public String deleteNotes(Long id) {
      noteRepository.deleteById(id);
      return "removed notes with ID: " + id;
   }
}
