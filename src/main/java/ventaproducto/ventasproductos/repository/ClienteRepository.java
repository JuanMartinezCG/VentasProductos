package ventaproducto.ventasproductos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{
    // buscar por Email
    Optional<Cliente> findByEmail(String email);
    // buscar por direccion 
    Optional<List<Cliente>> findByDireccion(String direccion);
    // buscar todos los clientes por el nombre buscado
    Optional<List<Cliente>> findByNombreStartingWith(String nombre);   
}
