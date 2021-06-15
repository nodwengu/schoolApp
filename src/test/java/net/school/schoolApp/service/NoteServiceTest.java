package net.school.schoolApp.service;

import net.school.schoolApp.entity.Note;
import net.school.schoolApp.entity.Note;
import net.school.schoolApp.repository.NoteRepository;
import net.school.schoolApp.repository.NoteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class NoteServiceTest {

   @Autowired
   private NoteService noteService;

   @MockBean
   private NoteRepository noteRepository;

   @Test
   @DisplayName("Should be able to add new notes record")
   void createNotes() {
      Note note = new Note(1L, "Causes of world war");
      Mockito.when(noteRepository.save(note)).thenReturn(note);

      assertEquals(note, noteService.createNotes(note));
      assertEquals("Causes of world war", noteService.createNotes(note).getNotesText());
   }

   @Test
   @DisplayName("Should be able to return all notes")
   void getAllNotes() {
      Note notes1 = new Note(1L, "note_test1");
      Note notes2 = new Note(2L, "note_test2");

      Mockito.when(noteRepository.findAll()).thenReturn(Stream.of(notes1, notes2).collect(Collectors.toList()));
      assertEquals(2, noteService.getAllNotes().size());
      assertEquals("note_test2", noteService.getAllNotes().get(1).getNotesText());
   }

   @Test
   @DisplayName("Should be able to return notes record by ID")
   void getNoteById() {
      Note notes2 = new Note(2L, "note_test");
      Mockito.when(noteRepository.findById(2L)).thenReturn(Optional.of(notes2));

      assertNotNull(noteService.getNotesById(2L));
      assertEquals(notes2, noteService.getNotesById(2L));
   }

   @Test
   @DisplayName("Should be able to update notes record")
   void updateNote() {
      Note notes2 = new Note(2L, "note_text");

      Mockito.when(noteRepository.findById(2L)).thenReturn(Optional.of(notes2));
      notes2.setNotesText("note_text");
      Mockito.when(noteRepository.save(notes2)).thenReturn(notes2);

      assertEquals(notes2, noteService.updateNotes(notes2));
      assertEquals("note_text", noteService.updateNotes(notes2).getNotesText());
   }

   @Test
   @DisplayName("Should be able to remove notes record")
   void deleteNote() {
      Note notes2 = new Note(2L, "note_text");
      Mockito.when(noteRepository.save(notes2)).thenReturn(notes2);
      assertEquals("removed notes with ID: 2", noteService.deleteNotes(2L));
   }

}