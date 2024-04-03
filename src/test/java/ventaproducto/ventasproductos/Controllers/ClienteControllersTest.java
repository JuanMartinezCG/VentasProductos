package ventaproducto.ventasproductos.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ventaproducto.ventasproductos.api.Cliente.ClienteController;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDtoSave;
import ventaproducto.ventasproductos.dto.Cliente.ClienteMapper;
import ventaproducto.ventasproductos.dto.Cliente.ClienteMapperImpl;
import ventaproducto.ventasproductos.servicies.Cliente.ClienteServiceInterface;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
public class ClienteControllersTest {

    @MockBean
    ClienteServiceInterface clienteServiceInterface;

    @Autowired
    public ClienteControllersTest(ClienteServiceInterface clienteServiceInterface) {
        this.clienteServiceInterface = clienteServiceInterface;
    }

    ClienteMapper mapper = new ClienteMapperImpl();

    private JacksonTester<ClienteDtoSave> jsonSuperHero;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
     public void setUp(){
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    public void guardarClienteTest() throws Exception {
        // Crear un objeto ClienteDtoSave simulado para enviar en la solicitud
        ClienteDtoSave clienteGuardado = new ClienteDtoSave(
            1L,
            "juan",
            "correo@example.com",
            "Dirección");

        when(clienteServiceInterface.guardarCliente(clienteGuardado)).thenReturn(new ClienteDto(clienteGuardado.id(), clienteGuardado.nombre(), clienteGuardado.email(), clienteGuardado.direccion()));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonSuperHero.write(clienteGuardado).getJson()))
        .andExpect(status().isOk());
    }
}
