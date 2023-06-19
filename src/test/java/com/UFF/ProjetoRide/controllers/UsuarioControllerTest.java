package com.UFF.ProjetoRide.controllers;

import com.UFF.ProjetoRide.models.Carteira;
import com.UFF.ProjetoRide.models.Perfil;
import com.UFF.ProjetoRide.models.Usuario;
import com.UFF.ProjetoRide.repository.CarteiraDAO;
import com.UFF.ProjetoRide.repository.PerfilDAO;
import com.UFF.ProjetoRide.repository.UsuarioDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UsuarioControllerTest {

    @Mock
    SecurityContext mockSecurityContext = new SecurityContextHolder().getContext();
    @Mock
    UsuarioDAO mockUsuarioDAO = new UsuarioDAO();
    @Mock
    PerfilDAO mockPerfilDAO = new PerfilDAO();
    @Mock
    CarteiraDAO mockCarteiraDAO = new CarteiraDAO();

    private AutoCloseable closeable;
    @InjectMocks
    UsuarioController usuarioController;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    public void testPaginaLoginNotAuthentication() {

        doReturn(null).when(mockSecurityContext).getAuthentication();

        assertEquals("login", usuarioController.paginaLogin());
    }

    @Test
    public void testPaginaLoginSuccessfulAuthentication() {

        AuthenticationMock authenticationMock = new AuthenticationMock();

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticationMock);
        SecurityContextHolder.setContext(context);

        doReturn(authenticationMock).when(mockSecurityContext).getAuthentication();

        assertEquals("redirect:/home", usuarioController.paginaLogin());
    }

    @Test
    public void testFormRegistro() {

        Usuario user = new Usuario();

        doNothing().when(mockUsuarioDAO).criarConta(any(Usuario.class));
        doNothing().when(mockPerfilDAO).criarPerfil(any(Perfil.class));
        doNothing().when(mockCarteiraDAO).criarCarteira(any(Carteira.class));

        assertEquals("redirect:/login", usuarioController.formRegistro(user));
        verify(mockUsuarioDAO, times(1)).criarConta(any(Usuario.class));
        verify(mockPerfilDAO, times(1)).criarPerfil(any(Perfil.class));
        verify(mockCarteiraDAO, times(1)).criarCarteira(any(Carteira.class));
    }

    @Test
    public void testAtualizarUsuario() {

        Usuario user = new Usuario();
        doNothing().when(mockUsuarioDAO).atualizarUsuario(any(Usuario.class));

        assertEquals("redirect:/config", usuarioController.atualizarUsuario(user));
    }

    @Test
    public void testAtualizarPerfil() {

        Perfil perfil = new Perfil();
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doNothing().when(mockPerfilDAO).atualizarPerfil(any(Perfil.class));

        assertEquals("redirect:/config", usuarioController.atualizarPerfil(perfil));
    }

    @Test
    public void testTrocarSenha() {

        Usuario user = new Usuario();
        String senha = "teste";
        user.setSenha(senha);
        doReturn(user).when(mockUsuarioDAO).visualizarUsuario(anyInt());
        doNothing().when(mockUsuarioDAO).atualizarUsuario(any(Usuario.class));

        assertEquals("redirect:/config", usuarioController.trocarSenha(1, senha, senha, senha));
        verify(mockUsuarioDAO, times(1)).atualizarUsuario(any(Usuario.class));
        verify(mockUsuarioDAO, times(1)).visualizarUsuario(anyInt());
    }

    @Test
    public void testListAllUsuariosa() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuario();

        assertNotNull( usuarioController.listAllUsuarios());
        verify(mockUsuarioDAO, times(1)).buscarUsuario();
    }

    public class AuthenticationMock implements Authentication {

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public Object getCredentials() {
            return true;
        }

        @Override
        public Object getDetails() {
            return true;
        }

        @Override
        public Object getPrincipal() {
            return true;
        }

        @Override
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

        }

        @Override
        public String getName() {
            return "true";
        }
    }

}
