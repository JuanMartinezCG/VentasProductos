package ventaproducto.ventasproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestVentasproductosApplication {
	public static void main(String[] args) {
		SpringApplication.from(VentasproductosApplication::main).with(TestVentasproductosApplication.class).run(args);
	}

}
