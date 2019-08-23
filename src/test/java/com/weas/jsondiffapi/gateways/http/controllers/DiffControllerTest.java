package com.weas.jsondiffapi.gateways.http.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weas.jsondiffapi.gateways.http.resources.request.DataRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DiffControllerTest {

  @Autowired
  private MockMvc mvc;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void shouldCreateDataLeftAndReturnOK() throws Exception {
    Long id = 1L;

    final String jsonAsString = objectMapper.writeValueAsString(DataRequest.builder()
        .value("Content")
        .build());

    mvc.perform(MockMvcRequestBuilders
        .post("/v1/diff/{id}/left", id)
        .content(jsonAsString)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldCreateDataRightAndReturnOK() throws Exception {
    Long id = 1L;

    final String jsonAsString = objectMapper.writeValueAsString(DataRequest.builder()
        .value("Content")
        .build());

    mvc.perform(MockMvcRequestBuilders
        .post("/v1/diff/{id}/right", id)
        .content(jsonAsString)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldCreateDataRightAndLeftAndReturnDifferentSize()
      throws Exception {
    Long id = 1L;

    final String leftContent = objectMapper.writeValueAsString(DataRequest.builder()
        .value("Content")
        .build());

    final String rightContent = objectMapper.writeValueAsString(DataRequest.builder()
        .value("Content Test")
        .build());

    mvc.perform(MockMvcRequestBuilders
        .post("/v1/diff/{id}/left", id)
        .content(leftContent)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    mvc.perform(MockMvcRequestBuilders
        .post("/v1/diff/{id}/right", id)
        .content(rightContent)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    mvc.perform(MockMvcRequestBuilders
        .get("/v1/diff/{id}", id)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("DIFFERENT_SIZE")));
  }

  @Test
  public void shouldCreateDataRightAndLeftAndReturnEqual()
      throws Exception {
    Long id = 1L;

    final String leftContent = objectMapper.writeValueAsString(DataRequest.builder()
        .value("Content")
        .build());

    final String rightContent = objectMapper.writeValueAsString(DataRequest.builder()
        .value("Content")
        .build());

    mvc.perform(MockMvcRequestBuilders
        .post("/v1/diff/{id}/left", id)
        .content(leftContent)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    mvc.perform(MockMvcRequestBuilders
        .post("/v1/diff/{id}/right", id)
        .content(rightContent)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    mvc.perform(MockMvcRequestBuilders
        .get("/v1/diff/{id}", id)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("EQUAL")));
  }


  @Test
  public void shouldCreateDataRightAndLeftAndReturnDifferentContent()
      throws Exception {
    Long id = 1L;

    final String leftContent = objectMapper.writeValueAsString(DataRequest.builder()
        .value("Content")
        .build());

    final String rightContent = objectMapper.writeValueAsString(DataRequest.builder()
        .value("TnetnoC")
        .build());

    mvc.perform(MockMvcRequestBuilders
        .post("/v1/diff/{id}/left", id)
        .content(leftContent)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    mvc.perform(MockMvcRequestBuilders
        .post("/v1/diff/{id}/right", id)
        .content(rightContent)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    mvc.perform(MockMvcRequestBuilders
        .get("/v1/diff/{id}", id)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("DIFFERENT_CONTENT")));
  }
}
