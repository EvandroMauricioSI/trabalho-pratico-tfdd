package com.UFF.ProjetoRide.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.UFF.ProjetoRide.models.Grupo;
import com.UFF.ProjetoRide.models.Perfil;
import com.UFF.ProjetoRide.models.Usuario;
import com.UFF.ProjetoRide.repository.*;
import com.UFF.ProjetoRide.models.Carteira;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//import java.security.Principal;
import java.util.List;


public class CarteiraControllerTest {
//	 CarteiraDAO mockCarteiraDAO = mock(CarteiraDAO.class);

    @Mock
    UsuarioDAO mockUsuarioDAO = new UsuarioDAO();
    @Mock
    PerfilDAO mockPerfilDAO = new PerfilDAO();
    @Mock
    CarteiraDAO mockCarteiraDAO = new CarteiraDAO();
    @Mock
    GrupoDAO mockGrupoDAO = new GrupoDAO();
    @Mock
    Carteira carteiraMock = new Carteira();
    @Mock
    Perfil perfilMock = new Perfil();
    @Mock
    List<Usuario> listUsuarioMock;
    @Mock
    List<Grupo> listGrupoMock;
    @Mock
    Usuario usuarioMock;
    private AutoCloseable closeable;

    @InjectMocks
    CarteiraController carteiraController;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }


    @Test
    public void testAdicionarSaldo() {

        int idcarteira = 1;
        Carteira carteira = new Carteira();
        doReturn(carteira).when(mockCarteiraDAO).visualizarCarteira(idcarteira);
        carteira.setSaldoconta(20);
        doNothing().when(mockCarteiraDAO).atualizarCarteira(carteira);

        assertEquals("redirect:/config", carteiraController.adicionarSaldo(1,20));
        verify(mockCarteiraDAO, times(1)).visualizarCarteira(idcarteira);
    }


    @Test
    public void testNaoTransferirSaldo() { //nao transfere quando nao tem dinheiro suficiente

        int idcarteira = 1;
        double qtdTransferida = 30;
        doReturn(new Carteira()).when(mockCarteiraDAO).visualizarCarteira(idcarteira);

        assertEquals("redirect:/config", carteiraController.transferirSaldo(1,qtdTransferida));
        verify(mockCarteiraDAO, times(1)).visualizarCarteira(idcarteira);
        verify(mockCarteiraDAO, times(0)).atualizarCarteira(new Carteira());

    }

    @Test
    public void testTransferirSaldo() { //transfere quando tem dinheiro suficiente

        int idcarteira = 1;
        double qtdTransferida = 30;
        Carteira carteira = new Carteira();
        carteira.setSaldoconta(30);
        doReturn(carteira).when(mockCarteiraDAO).visualizarCarteira(idcarteira);

        assertEquals("redirect:/config", carteiraController.transferirSaldo(1,qtdTransferida));
        verify(mockCarteiraDAO, times(1)).visualizarCarteira(idcarteira);
        verify(mockCarteiraDAO, times(1)).atualizarCarteira(carteira);

    }

}
