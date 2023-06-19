package com.UFF.ProjetoRide.controllers;

import com.UFF.ProjetoRide.models.*;
import com.UFF.ProjetoRide.repository.GrupoDAO;
import com.UFF.ProjetoRide.repository.MensagemDAO;
import com.UFF.ProjetoRide.repository.PerfilDAO;
import com.UFF.ProjetoRide.repository.UsuarioDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class GrupoControllerTest {
    private AutoCloseable closeable;
    @Mock
    UsuarioDAO mockUsuarioDAO = new UsuarioDAO();
    @Mock
    PerfilDAO mockPerfilDAO = new PerfilDAO();
    @Mock
    GrupoDAO mockGrupoDAO = new GrupoDAO();
    @Mock
    MensagemDAO mockMensagemDAO = new MensagemDAO();

    @InjectMocks
    GrupoController grupoController;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }


    @Test
    public void testCriarGrupoPerfilTipo1() {

        Grupo grupo = new Grupo();
        String email = "sbrubbles@gmail.com";
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
        doNothing().when(mockGrupoDAO).criarGrupo(any(Grupo.class));


        assertEquals("redirect:/grupo/0", grupoController.criarGrupo(new PrincipalMock(),grupo));
        verify(mockUsuarioDAO, times(1)).buscarUsuarioPeloEmail(anyString());
        verify(mockUsuarioDAO, times(1)).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        verify(mockPerfilDAO, times(1)).visualizarPerfil(anyInt());
        verify(mockPerfilDAO, times(1)).atualizarPerfil(any(Perfil.class));
        verify(mockGrupoDAO, times(1)).criarGrupo(any(Grupo.class));

    }

    @Test
    public void testCriarGrupoPerfilTipo2() {

        Grupo grupo = new Grupo();
        String email = "sbrubbles@gmail.com";
        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        perfil.setTipoperfil(2);

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doNothing().when(mockPerfilDAO).atualizarPerfil(any(Perfil.class));
        doNothing().when(mockGrupoDAO).criarGrupo(any(Grupo.class));


        assertEquals("redirect:/grupo/0", grupoController.criarGrupo(new PrincipalMock(),grupo));
        verify(mockUsuarioDAO, times(1)).buscarUsuarioPeloEmail(anyString());
        verify(mockUsuarioDAO, times(1)).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        verify(mockPerfilDAO, times(1)).visualizarPerfil(anyInt());
        verify(mockPerfilDAO, times(1)).atualizarPerfil(any(Perfil.class));
        verify(mockGrupoDAO, times(1)).criarGrupo(any(Grupo.class));

    }

    @Test
    public void testListaGruposIndex() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        List<Grupo> listaGrupo = new ArrayList<Grupo>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        perfil.setTipoperfil(2);

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(listaGrupo).when(mockGrupoDAO).listarGrupos();

        assertNotNull(grupoController.listaGruposIndex(new PrincipalMock()));
        verify(mockUsuarioDAO, times(1)).buscarUsuarioPeloEmail(anyString());
        verify(mockUsuarioDAO, times(1)).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        verify(mockPerfilDAO, times(1)).visualizarPerfil(anyInt());
    }

    @Test
    public void testEntrarGrupoUsuarioRepetido() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        perfil.setTipoperfil(2);
        Grupo grupo = new Grupo();
        grupo.setPerfil1(perfil);
        List<Mensagem> listMsgm = new ArrayList<Mensagem>();


        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());
        doReturn(listMsgm).when(mockMensagemDAO).buscarMensagemDoGrupo(anyInt());
        doReturn(1).when(mockMensagemDAO).qtdDeMsg(anyIterable());

        assertNotNull(grupoController.entrarGrupo(1, new PrincipalMock()));
    }

    @Test
    public void testEntrarGrupoSetPerfil2() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        perfil.setTipoperfil(2);
        Grupo grupo = new Grupo();
        grupo.setQtdparticipantes(2);
        grupo.setQtdatual(1);
        List<Mensagem> listMsgm = new ArrayList<Mensagem>();


        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());
        doReturn(listMsgm).when(mockMensagemDAO).buscarMensagemDoGrupo(anyInt());
        doReturn(1).when(mockMensagemDAO).qtdDeMsg(anyIterable());
        doNothing().when(mockGrupoDAO).atualizarGrupo(any(Grupo.class));

        assertNotNull(grupoController.entrarGrupo(1, new PrincipalMock()));
    }

    @Test
    public void testEntrarGrupoSetPerfil3() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Perfil perfil2 = new Perfil();
        perfil2.setTipoperfil(2);
        perfil2.setIdperfil(20);
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        perfil.setTipoperfil(2);
        Grupo grupo = new Grupo();
        grupo.setQtdparticipantes(2);
        grupo.setQtdatual(1);
        grupo.setPerfil2(perfil2);
        List<Mensagem> listMsgm = new ArrayList<Mensagem>();

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());
        doReturn(listMsgm).when(mockMensagemDAO).buscarMensagemDoGrupo(anyInt());
        doReturn(1).when(mockMensagemDAO).qtdDeMsg(anyIterable());
        doNothing().when(mockGrupoDAO).atualizarGrupo(any(Grupo.class));

        assertNotNull(grupoController.entrarGrupo(1, new PrincipalMock()));
    }

    @Test
    public void testEntrarGrupoSetPerfil4() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Perfil perfil2 = new Perfil();
        perfil2.setIdperfil(20);
        Perfil perfil3 = new Perfil();
        perfil3.setIdperfil(21);
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        perfil.setTipoperfil(2);
        Grupo grupo = new Grupo();
        grupo.setQtdparticipantes(2);
        grupo.setQtdatual(1);
        grupo.setPerfil2(perfil2);
        grupo.setPerfil3(perfil3);
        List<Mensagem> listMsgm = new ArrayList<Mensagem>();

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());
        doReturn(listMsgm).when(mockMensagemDAO).buscarMensagemDoGrupo(anyInt());
        doReturn(1).when(mockMensagemDAO).qtdDeMsg(anyIterable());
        doNothing().when(mockGrupoDAO).atualizarGrupo(any(Grupo.class));

        assertNotNull(grupoController.entrarGrupo(1, new PrincipalMock()));
    }

    @Test
    public void testEntrarGrupoSetPerfil5() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Perfil perfil2 = new Perfil();
        perfil2.setIdperfil(20);
        Perfil perfil3 = new Perfil();
        perfil3.setIdperfil(21);
        Perfil perfil4 = new Perfil();
        perfil4.setIdperfil(23);
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        perfil.setTipoperfil(2);
        Grupo grupo = new Grupo();
        grupo.setQtdparticipantes(2);
        grupo.setQtdatual(1);
        grupo.setPerfil2(perfil2);
        grupo.setPerfil3(perfil3);
        grupo.setPerfil4(perfil4);
        List<Mensagem> listMsgm = new ArrayList<Mensagem>();

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());
        doReturn(listMsgm).when(mockMensagemDAO).buscarMensagemDoGrupo(anyInt());
        doReturn(1).when(mockMensagemDAO).qtdDeMsg(anyIterable());
        doNothing().when(mockGrupoDAO).atualizarGrupo(any(Grupo.class));

        assertNotNull(grupoController.entrarGrupo(1, new PrincipalMock()));
    }

    @Test
    public void testEntrarGrupoRedirectHome() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        perfil.setTipoperfil(2);
        Grupo grupo = new Grupo();
        grupo.setQtdparticipantes(1);
        grupo.setQtdatual(2);
        List<Mensagem> listMsgm = new ArrayList<Mensagem>();


        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());
        doReturn(listMsgm).when(mockMensagemDAO).buscarMensagemDoGrupo(anyInt());
        doReturn(1).when(mockMensagemDAO).qtdDeMsg(anyIterable());
        doNothing().when(mockGrupoDAO).atualizarGrupo(any(Grupo.class));

        assertNotNull(grupoController.entrarGrupo(1, new PrincipalMock()));
    }

    @Test
    public void testSairGrupoPerfil1() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        perfil.setTipoperfil(2);
        Grupo grupo = new Grupo();
        grupo.setPerfil1(perfil);

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());

        assertNotNull(grupoController.sairGrupo(1, new PrincipalMock()));
    }

    @Test
    public void testSairGrupoPerfil2() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        Grupo grupo = new Grupo();
        grupo.setPerfil2(perfil);

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());

        assertNotNull(grupoController.sairGrupo(1, new PrincipalMock()));
    }

    @Test
    public void testSairGrupoPerfil3() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        Grupo grupo = new Grupo();
        grupo.setPerfil3(perfil);

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());

        assertNotNull(grupoController.sairGrupo(1, new PrincipalMock()));
    }

    @Test
    public void testSairGrupoPerfil4() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        Grupo grupo = new Grupo();
        grupo.setPerfil4(perfil);

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());

        assertNotNull(grupoController.sairGrupo(1, new PrincipalMock()));
    }

    @Test
    public void testSairGrupoPerfil5() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        Grupo grupo = new Grupo();
        grupo.setPerfil5(perfil);

        doReturn(listUsuario).when(mockUsuarioDAO).buscarUsuarioPeloEmail(anyString());
        doReturn(user).when(mockUsuarioDAO).findUsingEnhancedForLoop(anyString(), Mockito.<Usuario>anyList());
        doReturn(perfil).when(mockPerfilDAO).visualizarPerfil(anyInt());
        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());

        assertNotNull(grupoController.sairGrupo(1, new PrincipalMock()));
    }

    @Test
    public void testFinalizarCorrida() {

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Perfil perfil = new Perfil();
        Usuario user = new Usuario();
        user.setIdusuario(1);
        listUsuario.add(user);
        Grupo grupo = new Grupo();
        grupo.setPerfil5(perfil);

        doReturn(grupo).when(mockGrupoDAO).visualizarGrupo(anyInt());
        doNothing().when(mockGrupoDAO).atualizarGrupo(any(Grupo.class));

        assertNotNull(grupoController.finalizarCorrida(1));
    }


    public class PrincipalMock implements Principal {
        @Override
        public String getName() {
            return "testando";
        }
    }
}
