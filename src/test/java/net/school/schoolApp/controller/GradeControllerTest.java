package net.school.schoolApp.controller;

import net.school.schoolApp.entity.Grade;
import net.school.schoolApp.service.GradeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = GradeController.class)
class GradeControllerTest {
   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private GradeService gradeService;

   String exampleJson = "{\"id\":10,\"gradeName\":\"grade 5\",\"learners\":[],\"lessons\":[]}";

   @Test
   void getGradeById() throws Exception {
      Grade grade = new Grade(10L, "grade 5");

      Mockito.when(gradeService.getGradeById(Mockito.anyLong())).thenReturn(grade);

      RequestBuilder requestBuilder = MockMvcRequestBuilders
              .get("/grades/10")
              .accept(MediaType.APPLICATION_JSON);

      MvcResult result = mockMvc.perform(requestBuilder).andReturn();

      String expected = "{\"id\":10,\"gradeName\":\"grade 5\",\"learners\":[],\"lessons\":[]}";
      JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
   }

   @Test
   public void addGrade() throws Exception {
      Grade mockGrade = new Grade(10L, "grade 5");

      Mockito.when(gradeService.addGrade(Mockito.any(Grade.class))).thenReturn(mockGrade);

      RequestBuilder requestBuilder = MockMvcRequestBuilders
              .post("/grades").content(exampleJson)
              .accept(MediaType.APPLICATION_JSON).content(exampleJson)
              .contentType(MediaType.APPLICATION_JSON);

      MvcResult result = mockMvc.perform(requestBuilder).andReturn();
      MockHttpServletResponse response = result.getResponse();

      String expected = "{\"id\":10,\"gradeName\":\"grade 5\",\"learners\":[],\"lessons\":[]}";
      JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
   }

   @Test
   void updateGrade() throws Exception {
      Grade mockGrade = new Grade(10L, "grade 5");

      Mockito.when(gradeService.updateGrade(Mockito.anyLong(), mockGrade)).thenReturn(mockGrade);
      Mockito.when(gradeService.getGradeById(Mockito.anyLong())).thenReturn(mockGrade);

      RequestBuilder requestBuilder = MockMvcRequestBuilders
              .put("/grades").content(exampleJson)
              .contentType(MediaType.APPLICATION_JSON);

      MvcResult result = mockMvc.perform(requestBuilder).andReturn();

      String expected = "{\"id\":10,\"gradeName\":\"grade 5\",\"learners\":[],\"lessons\":[]}";
      JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

   }

   @Test
   void removeGrade() {
   }
}