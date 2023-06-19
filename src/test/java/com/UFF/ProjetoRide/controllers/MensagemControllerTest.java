package com.UFF.ProjetoRide.controllers;

import com.UFF.ProjetoRide.models.*;
import com.UFF.ProjetoRide.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class MensagemControllerTest {

    @Mock
    MensagemDAO mockMensagemDAO = new MensagemDAO();
    @Mock
    PerfilDAO mockPerfilDAO = new PerfilDAO();
    @Mock
    GrupoDAO mockGrupoDAO = new GrupoDAO();

    private AutoCloseable closeable;
    @InjectMocks
    MensagemController mensagemController;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    public void testEnviarMensagem() {

        Perfil perfil = new Perfil();
        Grupo grupo = new Grupo();
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());
        doNothing().when(mockMensagemDAO).enviarMensagem(any(Mensagem.class));

        assertEquals("redirect:/grupo/{codigo}", mensagemController.enviarMensagem(1,new Mensagem(), 1));
        verify(mockPerfilDAO, times(1)).visualizarPerfil(anyInt());
        verify(mockGrupoDAO, times(1)).visualizarGrupo(anyInt());
        verify(mockMensagemDAO, times(1)).enviarMensagem(any(Mensagem.class));
    }

}
