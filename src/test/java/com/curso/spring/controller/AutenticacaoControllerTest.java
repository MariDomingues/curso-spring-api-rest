package com.curso.spring.controller;

import com.curso.spring.model.form.LoginForm;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AutenticacaoControllerTest {

    private static final String URI = "/auth";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deveria devolver 400")
    public void autenticar1() throws Exception {

        URI uri = new URI(URI);

        LoginForm login = new LoginForm();
        login.setUsuario("invalido@email.com");
        login.setSenha("123456");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(login.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

    @Test
    @DisplayName("Deveria devolver 200")
    public void autenticar2() throws Exception {

        URI uri = new URI(URI);

        LoginForm login = new LoginForm();
        login.setUsuario("aluno@email.com");
        login.setSenha("123");

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(login.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}