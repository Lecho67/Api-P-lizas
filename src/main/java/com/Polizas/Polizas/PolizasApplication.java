package com.Polizas.Polizas;

import com.Polizas.Polizas.Persistence.Entities.CompraPoliza;
import com.Polizas.Polizas.Persistence.Entities.Poliza;
import com.Polizas.Polizas.Persistence.Entities.Usuario;
import com.Polizas.Polizas.Persistence.Respositories.CompraPolizaRespository;
import com.Polizas.Polizas.Persistence.Respositories.PolizaRepository;
import com.Polizas.Polizas.Persistence.Respositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class PolizasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolizasApplication.class, args);
	}

}
