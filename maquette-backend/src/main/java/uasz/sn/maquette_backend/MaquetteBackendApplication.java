package uasz.sn.maquette_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uasz.sn.maquette_backend.modele.Utilisateur;
import uasz.sn.maquette_backend.repository.UtilisateurRepository;

@SpringBootApplication
public class MaquetteBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaquetteBackendApplication.class, args);
	}



}
