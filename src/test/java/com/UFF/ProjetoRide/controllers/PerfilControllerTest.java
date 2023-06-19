package com.UFF.ProjetoRide.controllers;

import com.UFF.ProjetoRide.models.Grupo;
import com.UFF.ProjetoRide.models.Perfil;
import com.UFF.ProjetoRide.models.Usuario;
import com.UFF.ProjetoRide.repository.PerfilDAO;
import com.UFF.ProjetoRide.repository.UsuarioDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PerfilControllerTest {

    @Mock
    UsuarioDAO mockUsuarioDAO = new UsuarioDAO();
    @Mock
    PerfilDAO mockPerfilDAO = new PerfilDAO();
    private AutoCloseable closeable;
    @InjectMocks
    PerfilController perfilController;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }


    @Test
    public void testVirarMotorista() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        perfil.setTipoperfil(1);

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doNothing().when(mockPerfilDAO).atualizarPerfil(any(Perfil.class));

        assertEquals("redirect:/home", perfilController.virarMotorista(new PrincipalMock()));
        verify(mockUsuarioDAO, times(1)).buscarUsuarioPeloEmail(anyString());
        verify(mockUsuarioDAO, times(1)).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        verify(mockPerfilDAO, times(1)).visualizarPerfil(anyInt());
        verify(mockPerfilDAO, times(1)).atualizarPerfil(any(Perfil.class));
    }

    @Test
    public void testVirarPassageiro() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        perfil.setTipoperfil(1);

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doNothing().when(mockPerfilDAO).atualizarPerfil(any(Perfil.class));

        assertEquals("redirect:/home", perfilController.virarPassageiro(new PrincipalMock()));
        verify(mockUsuarioDAO, times(1)).buscarUsuarioPeloEmail(anyString());
        verify(mockUsuarioDAO, times(1)).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        verify(mockPerfilDAO, times(1)).visualizarPerfil(anyInt());
        verify(mockPerfilDAO, times(1)).atualizarPerfil(any(Perfil.class));
    }

    public class PrincipalMock implements Principal {
        @Override
        public String getName() {
            return "testando";
        }
    }
}
