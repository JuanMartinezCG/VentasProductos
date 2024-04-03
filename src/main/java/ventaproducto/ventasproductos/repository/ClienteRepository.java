package ventaproducto.ventasproductos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{

    List<Cliente> findByNombre(String nombre);
    // buscar por Email
    List<Cliente> findByEmail(String email);
    // buscar por direccion 
    List<Cliente> findByDireccion(String direccion);
    // buscar todos los clientes por el nombre buscado
    
}
