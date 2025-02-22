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

	@Bean
	public CommandLineRunner initUtilisateurs(UtilisateurRepository utilisateurRepository) {
		return args -> {
			utilisateurRepository.save(new Utilisateur(1L, "Alice", "alice@example.com"));
			utilisateurRepository.save(new Utilisateur(2L, "Bob", "bob@example.com"));
		};
	}

}
