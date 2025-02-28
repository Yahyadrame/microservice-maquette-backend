package uasz.sn.maquette_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uasz.sn.maquette_backend.dto.EnseignementDTO;
import uasz.sn.maquette_backend.modele.Enseignement;

@Mapper
public interface EnseignementMapper {
    EnseignementMapper INSTANCE = Mappers.getMapper(EnseignementMapper.class);

    @Mapping(target = "utilisateurId", source = "utilisateur.id")
    @Mapping(target = "utilisateurNom", source = "utilisateur.nom") // Mapper le nom de l'utilisateur
    @Mapping(target = "ecId", source = "ec.id")
    @Mapping(target = "ecNom", source = "ec.libelle") // Mapper le nom de l'EC
    @Mapping(target = "classeId", source = "classe.id")
    @Mapping(target = "classeNom", source = "classe.nom") // Mapper le nom de la classe
    @Mapping(target = "formationId", source = "classe.formation.id")
    @Mapping(target = "formationNom", source = "classe.formation.nom")
    EnseignementDTO toDto(Enseignement enseignement);
    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "ec", ignore = true)
    @Mapping(target = "classe", ignore = true)
    Enseignement toEntity(EnseignementDTO enseignementDTO);

    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "ec", ignore = true)
    @Mapping(target = "classe", ignore = true)
    void updateEntity(EnseignementDTO enseignementDTO, @MappingTarget Enseignement enseignement);
}