package ventaproducto.ventasproductos.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDtoSave;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void guardarClienteTest() throws Exception {
        // Crear un objeto ClienteDtoSave simulado para enviar en la solicitud
        ClienteDtoSave clienteDtoSave = new ClienteDtoSave("Nombre", "correo@example.com", "Dirección");

        // Convertir el objeto ClienteDtoSave a formato JSON
        String clienteDtoSaveJson = objectMapper.writeValueAsString(clienteDtoSave);

        // Realizar la solicitud POST al endpoint /clientes/guardar con el objeto JSON como cuerpo
        mockMvc.perform(MockMvcRequestBuilders.post("/clientes/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clienteDtoSaveJson))
                // Verificar que se devuelve un código de estado HTTP 201 (CREATED)
                .andExpect(status().isCreated())
                // Verificar que el cuerpo de la respuesta contiene el objeto ClienteDto guardado
                .andExpect(jsonPath("$.nombre").value("Nombre"))
                .andExpect(jsonPath("$.email").value("correo@example.com"))
                .andExpect(jsonPath("$.direccion").value("Dirección"));
    }
}
