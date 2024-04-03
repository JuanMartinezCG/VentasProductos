package ventaproducto.ventasproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"ventaproducto.ventasproductos.dto.Cliente.*","ventaproducto.ventasproductos.dto.*"})
public class VentasproductosApplication {
	public static void main(String[] args) {
		SpringApplication.run(VentasproductosApplication.class, args);
	}

}
